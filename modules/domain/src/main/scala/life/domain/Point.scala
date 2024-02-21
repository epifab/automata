package life.domain

import cats.Show

case class Point(x: Int, y: Int):
  lazy val up: Point    = Point(x, y - 1)
  lazy val down: Point  = Point(x, y + 1)
  lazy val left: Point  = Point(x - 1, y)
  lazy val right: Point = Point(x + 1, y)

object Point:
  given Show[Point] = Show.show(p => s"(${p.x},${p.y})")
