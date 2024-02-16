package pinata

import cats.implicits.showInterpolator
import cats.Show

import scala.annotation.tailrec
import scala.collection.immutable.ListMap

opaque type Board[T] = ListMap[Point, T]

object Board:

  def apply[T](input: Array[Array[T]]): Board[T] =
    val arr: Array[(Point, T)] = input.zipWithIndex.flatMap { case (lines, y) =>
      lines.zipWithIndex.map { case (value, x) => Point(x, y) -> value }
    }
    ListMap(arr*)

  extension [T](board: Board[T])
    def contains(point: Point): Boolean =
      board.contains(point)

    def map[U](f: T => U): Board[U] =
      board.map { case (point, value) => point -> f(value) }

    def get(point: Point): Option[T] =
      board.get(point)

    def findPosition(target: T): Option[Point] =
      board.collectFirst { case (point, value) if value == target => point }

  given [T: Show]: Show[Board[T]] = Show.show { board =>
    @tailrec
    def rec(s: String, y: Int, remaining: List[(Point, T)]): String =
      remaining match
        case Nil                        => s
        case (p, v) :: tail if p.y == y => rec(s + show"$v", p.y, tail)
        case (p, v) :: tail             => rec(s + show"\n$v", p.y, tail)

    rec("", 0, board.toList)
  }
