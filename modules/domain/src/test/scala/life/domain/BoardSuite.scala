package life.domain

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class BoardSuite extends AnyFreeSpec with Matchers:
  "find" in {
    Board(
      Vector(
        Vector(4, 1, 2),
        Vector(3, 4, 5),
        Vector(6, 4, 4)
      )
    ).find(_ == 4) shouldBe List(Point(0, 0), Point(1, 1), Point(2, 1), Point(2, 2))
  }

  "findFirst" in {
    Board(
      Vector(
        Vector(0, 1, 2),
        Vector(3, 4, 4),
        Vector(4, 4, 4)
      )
    ).findFirst(_ == 4) shouldBe Some(Point(1, 1))
  }

  "get" in {
    Board(
      Vector(
        Vector(0, 1, 2),
        Vector(3, 4, 5),
        Vector(6, 7, 8)
      )
    ).get(Point(1, 1)) shouldBe Some(4)
  }

  "map" in {
    Board(
      Vector(
        Vector(0, 1, 2),
        Vector(3, 4, 5),
        Vector(6, 7, 8)
      )
    ).map(8 - _) shouldBe Board(
      Vector(
        Vector(8, 7, 6),
        Vector(5, 4, 3),
        Vector(2, 1, 0)
      )
    )
  }
