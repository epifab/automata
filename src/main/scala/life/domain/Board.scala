package life.domain

import cats.{Applicative, Eval, Functor, Monad, Show, Traverse}
import cats.syntax.all.*

import scala.annotation.tailrec
import scala.collection.immutable.ListMap
import scala.reflect.ClassTag

case class Board[T](data: Vector[Vector[T]]):

  lazy val asMap: Map[Point, T] =
    data.zipWithIndex.flatMap { case (col, x) =>
      col.zipWithIndex.map { case (value, y) =>
        Point(x, y) -> value
      }
    }.toMap

  def get(point: Point): Option[T] =
    try data(point.x)(point.y).some
    catch case _: ArrayIndexOutOfBoundsException => None

  def contains(point: Point): Boolean =
    get(point).nonEmpty

  def mapPoint[U](f: Point => U): Board[U] =
    Board(data.zipWithIndex.map { case (col, x) =>
      col.zipWithIndex.map { case (_, y) =>
        f(Point(x, y))
      }
    })

  def map[U](f: T => U): Board[U] =
    Board(data.map(_.map(f)))

  def set(point: Point, value: T): Board[T] =
    assert(contains(point))
    Board(data.zipWithIndex.map { case (col, x) =>
      col.zipWithIndex.map {
        case (_, y) if x == point.x && y == point.y => value
        case (originalValue, _)                     => originalValue
      }
    })

  def exists(f: T => Boolean): Boolean =
    data.exists(_.exists(f))

  def find(f: T => Boolean): Iterable[Point] =
    data.zipWithIndex.flatMap { case (col, x) =>
      col.zipWithIndex.collect {
        case (v, y) if f(v) => Point(x, y)
      }
    }

  def findFirst(f: T => Boolean): Option[Point] =
    find(f).headOption

object Board:

  def empty[T]: Board[T] =
    Board(Vector.empty)

  def uniform[T: ClassTag](value: T, width: Int, height: Int): Board[T] =
    assert(width >= 0)
    assert(height >= 0)
    Board(Vector.fill(height)(Vector.fill(width)(value)))

  def build[F[_]: Applicative, T: ClassTag](
      value: Point => F[T],
      width: Int,
      height: Int
  ): F[Board[T]] =

    def column(x: Int): F[Vector[T]] =
      (0 to height).toVector
        .traverse { y => value(Point(x, y)) }

    (0 to width).toVector
      .traverse(x => column(x))
      .map(new Board(_))

  given [T: Show]: Show[Board[T]] = Show.show { board =>
    @tailrec
    def rec(s: String, y: Int, remaining: List[(Point, T)]): String =
      remaining match
        case Nil                        => s
        case (p, v) :: tail if p.y != y => rec(s + show"\n$v", p.y, tail)
        case (p, v) :: tail             => rec(s + show"$v", p.y, tail)

    rec("", 0, board.asMap.toList)
  }
