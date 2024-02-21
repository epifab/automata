package pinata

import cats.{Applicative, Eval, Functor, Monad, Show, Traverse}
import cats.syntax.all.*

import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.reflect.ClassTag

sealed trait Board[T]:
  def contains(point: Point): Boolean
  def map[U](f: T => U): Board[U]
  def mapPoint[U](f: Point => U): Board[U]
  def get(point: Point): Option[T]
  def set(point: Point, value: T): Board[T]
  def exists(f: T => Boolean): Boolean
  def find(target: T): List[Point]
  def findFirst(target: T): Option[Point]

object Board:

  given Functor[Board] = new Functor[Board]:
    override def map[A, B](fa: Board[A])(f: A => B): Board[B] =
      fa match
        case ListMapBoard(board) =>
          ListMapBoard(board.map { case (p, v) => p -> f(v) })

  given Traverse[Board] = new Traverse[Board]:
    override def traverse[G[_]: Applicative, A, B](fa: Board[A])(f: A => G[B]): G[Board[B]] =
      fa match
        case ListMapBoard(board) =>
          board.toList
            .traverse { case (p, v) => f(v).map(v2 => p -> v2) }
            .map(list => ListMapBoard(ListMap(list*)))

    override def foldLeft[A, B](fa: Board[A], b: B)(f: (B, A) => B): B =
      fa match
        case ListMapBoard(board) =>
          board
            .foldLeft(b) { case (b, (_, a)) => f(b, a) }

    override def foldRight[A, B](fa: Board[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] =
      fa match
        case ListMapBoard(board) =>
          board
            .foldRight(lb) { case ((_, a), b) => f(a, b) }

  private case class ListMapBoard[T](board: ListMap[Point, T]) extends Board[T]:
    def contains(point: Point): Boolean =
      board.contains(point)

    def exists(f: T => Boolean): Boolean =
      board.exists { case (_, v) => f(v) }

    def map[U](f: T => U): Board[U] =
      ListMapBoard(board.map { case (point, value) => point -> f(value) })

    def mapPoint[U](f: Point => U): Board[U] =
      ListMapBoard(board.map { case (point, _) => point -> f(point) })

    def get(point: Point): Option[T] =
      board.get(point)

    def set(point: Point, value: T): Board[T] =
      assert(contains(point))
      ListMapBoard(board.updated(point, value))

    def find(target: T): List[Point] =
      board.collect { case (point, value) if value == target => point }.toList

    def findFirst(target: T): Option[Point] =
      board.collectFirst { case (point, value) if value == target => point }

  def fill[T: ClassTag](value: T, width: Int, height: Int): Board[T] =
    assert(width >= 0)
    assert(height >= 0)
    apply(Array.fill(height)(Array.fill(width)(value)))

  def build[F[_]: Applicative, T: ClassTag](
      value: Point => F[T],
      width: Int,
      height: Int
  ): F[Board[T]] =

    def line(col: Int): F[List[(Point, T)]] =
      (0 to width).toList.traverse { row =>
        val p = Point(col, row)
        value(p).map(v => p -> v)
      }

    (0 to height).toList
      .flatTraverse(col => line(col))
      .map((cells: List[(Point, T)]) => ListMapBoard(ListMap(cells*)))

  def apply[T](input: Array[Array[T]]): Board[T] =
    assert(input.map(_.length).toSet.size == 1)

    val board: ListMap[Point, T] =
      ListMap(
        input.zipWithIndex.flatMap { case (lines, y) =>
          lines.zipWithIndex.map { case (value, x) => Point(x, y) -> value }
        }*
      )

    new ListMapBoard[T](board)

  given [T: Show]: Show[Board[T]] = Show.show { case ListMapBoard(board) =>
    @tailrec
    def rec(s: String, y: Int, remaining: List[(Point, T)]): String =
      remaining match
        case Nil                        => s
        case (p, v) :: tail if p.y != y => rec(s + show"\n$v", p.y, tail)
        case (p, v) :: tail             => rec(s + show"$v", p.y, tail)

    rec("", 0, board.toList)
  }
