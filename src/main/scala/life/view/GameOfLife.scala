package life.view

import cats.effect.std.Random
import cats.effect.IO
import life.domain.{Automata, Board}

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import scala.scalajs.js.Array as JsArray

@JSExportTopLevel("GameOfLife")
object GameOfLife:

  extension (board: Board[Automata.Cell])
    def asJs: JsArray[JsArray[Boolean]] =
      JsArray(board.data.map { col =>
        JsArray(col.map(_.alive)*)
      }*)

  @JSExport
  def run(width: Int, height: Int, callback: JsArray[JsArray[Boolean]] => Unit): Unit =
    import cats.effect.unsafe.implicits.global

    val program: IO[Unit] = for
      given Random[IO] <- Random.scalaUtilRandom[IO]
      _ <- Automata
        .gameOfLife[IO](width, height)
        .run
        .evalMap(board => IO(callback(board.asJs)))
        .compile
        .drain
    yield ()

    program.unsafeRunAndForget()
