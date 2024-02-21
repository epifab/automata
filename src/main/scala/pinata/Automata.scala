package pinata

import cats.effect.{Async, Clock, Concurrent}
import cats.effect.std.Random
import cats.syntax.all.*
import cats.Show
import fs2.Stream
import pinata.Automata.Cell

import scala.collection.immutable.{ListMap, StringOps}

trait Automata[F[_]]:
  def run: Stream[F, Board[Cell]]

object Automata:

  enum Cell:
    case Alive, Dead

  object Cell:
    extension (cell: Cell)
      def alive: Boolean = cell == Alive
      def dead: Boolean  = cell == Dead

    def apply(boolean: Boolean): Cell = if boolean then Alive else Dead
    given Show[Cell] = Show.show {
      case Alive => "▓"
      case Dead  => "░"
    }

  def gameOfLife[F[_]: Async: Random](width: Int, height: Int): Automata[F] = new Automata[F]:
    override def run: Stream[F, Board[Cell]] =
      Stream.eval(randomBoard).flatMap(recurse)

    val randomBoard: F[Board[Cell]] =
      Board.build(_ => Random[F].nextBoolean.map(Cell.apply), width, height)

    def recurse(board: Board[Cell]): Stream[F, Board[Cell]] =
      if board.exists(_.alive) then
        val newBoard = board.mapPoint(p => automata(board, p))
        Stream(newBoard) ++ recurse(newBoard)
      else Stream.empty

    def automata(board: Board[Cell], point: Point): Cell =
      val neighbours: List[Cell] = List(
        point.up,
        point.up.right,
        point.right,
        point.right.down,
        point.down,
        point.down.left,
        point.left,
        point.left.up
      ).flatMap(board.get)

      val aliveNeighbours: Int = neighbours.count(_ == Cell.Alive)

      board
        .get(point)
        .map {
          case Cell.Alive if aliveNeighbours < 2 => Cell.Dead
          case Cell.Alive if aliveNeighbours < 4 => Cell.Alive
          case Cell.Alive                        => Cell.Dead
          case Cell.Dead if aliveNeighbours == 3 => Cell.Alive
          case Cell.Dead                         => Cell.Dead
        }
        .getOrElse(throw new IllegalArgumentException(show"$point not found in $board"))
