package life.domain

import cats.Show

import scala.annotation.targetName

opaque type Path = List[Direction]

object Path:
  val empty: Path = Nil

  extension (path: Path)
    def toList: List[Direction] = path
    @targetName("append")
    def /(direction: Direction): Path = path :+ direction

  given Show[Path] = Show.show(path =>
    path
      .map {
        case Direction.Up    => "U"
        case Direction.Down  => "D"
        case Direction.Right => "R"
        case Direction.Left  => "L"
      }
      .mkString("")
  )
