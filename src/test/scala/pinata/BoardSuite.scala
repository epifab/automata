package pinata

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class BoardSuite extends AnyFreeSpec with Matchers:
  "find" in {
    Board(
      Array(
        Array(4, 1, 2),
        Array(3, 4, 5),
        Array(6, 7, 4)
      )
    ).find(4) shouldBe List(Point(0, 0), Point(1, 1), Point(2, 2))
  }

  "findFirst" in {
    Board(
      Array(
        Array(0, 1, 2),
        Array(3, 4, 4),
        Array(4, 4, 4)
      )
    ).findFirst(4) shouldBe Some(Point(1, 1))
  }

  "get" in {
    Board(
      Array(
        Array(0, 1, 2),
        Array(3, 4, 5),
        Array(6, 7, 8)
      )
    ).get(Point(1, 1)) shouldBe Some(4)
  }

  "map" in {
    Board(
      Array(
        Array(0, 1, 2),
        Array(3, 4, 5),
        Array(6, 7, 8)
      )
    ).map(8 - _) shouldBe Board(
      Array(
        Array(8, 7, 6),
        Array(5, 4, 3),
        Array(2, 1, 0)
      )
    )
  }
