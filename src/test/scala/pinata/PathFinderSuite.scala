package pinata

import cats.effect.{IO, Ref}
import cats.effect.testing.scalatest.AsyncIOSpec
import cats.implicits.showInterpolator
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers

import Direction.*

class PathFinderSuite extends AsyncFreeSpec with AsyncIOSpec with Matchers:

  "Tiny board" in {
    PathFinder[IO]
      .findPath(
        board = Board(
          Array(
            "              ",
            "     0  ....  ",
            "     ....  ..X"
          ).map(_.toArray)
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
          Array(
            "..............",
            ".....0........",
            ".............X"
          ).map(_.toArray)
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
          Array(
            "...... X .....",
            ".....0   .....",
            ".X...........X"
          ).map(_.toArray)
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
