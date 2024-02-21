package life.domain

import cats.effect.testing.scalatest.AsyncIOSpec
import cats.effect.IO
import cats.implicits.showInterpolator
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers

class PathFinderSuite extends AsyncFreeSpec with AsyncIOSpec with Matchers:

  "Tiny board" in {
    PathFinder[IO]
      .findPath(
        board = Board(
          Vector(
            "              ",
            "     0  ....  ",
            "     ....  ..X"
          ).map(_.toVector)
        ),
        start = '0',
        goal = 'X',
        walkable = _ == '.'
      )
      .map(p => show"$p")
      .asserting(_ shouldBe "DRRRURRRDRR")
  }

  "Board with no obstacles" in {
    PathFinder[IO]
      .findPath(
        board = Board(
          Vector(
            "..............",
            ".....0........",
            ".............X"
          ).map(_.toVector)
        ),
        start = '0',
        goal = 'X',
        walkable = _ == '.'
      )
      .map(p => show"$p")
      .asserting(_ shouldBe "RRRRRRRRD")
  }

  "Finds the nearest reachable goal" in {
    PathFinder[IO]
      .findPath(
        board = Board(
          Vector(
            "...... X .....",
            ".....0   .....",
            ".X...........X"
          ).map(_.toVector)
        ),
        start = '0',
        goal = 'X',
        walkable = _ == '.'
      )
      .map(p => show"$p")
      .asserting(_ shouldBe "LLLLD")
  }

  "Finds the piñata" in {
    import Fixtures.*
    PathFinder[IO]
      .findPath(
        board,
        start,
        goal,
        !obstacles.contains(_)
      )
      .asserting(path =>
        show"$path" shouldBe "DDRRDDDDDDLLDDRRRRUURRUULLUURRRRDDRRUURRUULLLLUURRRRRRDDRRUURRRRDDDDRRUURRDDRRRRDDLLDDLLDDDDDDRRDDRRDDRRRRDDLLDDRRDDDDDDLLLLUUUUUUUULLUULLDDDDLLLLLLUUUURRDDRRUUUULLUUUURRUUUULLLLLLLLUULLDDDDLLDDLLDDDDDDLLLLLLUUUUUULLDDDDDDDDRRRRRRDDDDDDDDRRRRUURRDDRRRRUULLUURRRRRRRRRRDDLLLLDDDDRRDDRRRRUURRDDRRRR"
      )
  }
