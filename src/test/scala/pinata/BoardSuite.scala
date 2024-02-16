package pinata

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class BoardSuite extends AnyFreeSpec with Matchers:
  "Find a target" in {
    Board(
      Array(
        Array(0, 1, 2),
        Array(3, 4, 5),
        Array(6, 7, 8)
      )
    ).findPosition(4) shouldBe Some(Point(1, 1))
  }
