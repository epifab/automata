package life.domain

enum Direction:
  case Left, Right, Up, Down

object Direction:
  extension (d: Direction) def toString: String = d.toString.take(1)
