package life.view

import cats.effect.std.Random
import cats.effect.IO
import life.domain.{Automata, Board}

import scala.concurrent.duration.DurationInt
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("GameOfLife")
object GameOfLife:

  type JsBoard = js.Array[js.Dictionary[Any]]

  extension (board: Board[Automata.Cell])
    def asJs: JsBoard =
      js.Array(board.asVector.map { (point, cell) =>
        js.Dictionary(
          "x"     -> point.x,
          "y"     -> point.y,
          "alive" -> cell.alive
        )
      }*)

  @JSExport
  def run(width: Int, height: Int, callback: js.Function1[JsBoard, Unit]): Unit =
    import cats.effect.unsafe.implicits.global

    val program: IO[Unit] = for
      given Random[IO] <- Random.scalaUtilRandom[IO]
      _ <- Automata
        .gameOfLife[IO](width, height)
        .run
        .evalMap(board => IO(callback(board.asJs)) *> IO.sleep(1.second))
        .compile
        .drain
    yield ()

    program.unsafeRunAndForget()
