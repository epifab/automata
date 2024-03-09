package life.view

import cats.effect.{Deferred, IO}
import cats.effect.std.Random
import fs2.concurrent.Signal
import life.domain.{Automata, Board, Point}

import scala.concurrent.duration.DurationInt
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

object GameOfLife:

  type JsBoard = js.Array[js.Dictionary[Any]]

  extension (jsBoard: JsBoard)
    def asScala: Board[Automata.Cell] =
      Board.fromVector(
        jsBoard.toVector.map(cell =>
          Point(
            cell("x").asInstanceOf[Int],
            cell("y").asInstanceOf[Int]
          ) -> Automata.Cell(cell("alive").asInstanceOf[Boolean])
        )
      )

  extension (board: Board[Automata.Cell])
    def asJs: JsBoard =
      js.Array(board.asVector.map { (point, cell) =>
        js.Dictionary(
          "x"     -> point.x,
          "y"     -> point.y,
          "alive" -> cell.alive
        )
      }*)

  @JSExportTopLevel("runGameOfLife")
  def run(
      initialBoard: JsBoard,
      delays: Int,
      callback: js.Function1[JsBoard, Unit]
  ): js.Function0[Unit] =
    import cats.effect.unsafe.implicits.global

    val program: IO[Unit] =
      Automata
        .gameOfLife[IO](initialBoard.asScala)
        .run
        .evalMap(board => IO(callback(board.asJs)) *> IO.sleep(delays.millis))
        .compile
        .drain
        .onCancel(IO.println("Execution cancelled"))
        .flatTap(_ => IO.println("Execution complete"))

    val cancel = program.unsafeRunCancelable()
    () => cancel()
