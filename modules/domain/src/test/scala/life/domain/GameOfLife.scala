package life.domain

import cats.effect.{IO, IOApp}
import cats.effect.std.Random
import cats.implicits.showInterpolator
import fs2.Stream

import scala.concurrent.duration.DurationInt

object GameOfLife extends IOApp.Simple:

  given Random[IO] = Random.javaUtilConcurrentThreadLocalRandom

  override val run: IO[Unit] =
    fs2.Stream
      .eval(Automata.gameOfLife[IO](100, 10))
      .flatMap(_.run)
      .evalMap(board => IO.println(show"$board"))
      .interleave(Stream.awakeEvery[IO](200.millis).evalMap(_ => IO.println("")))
      .compile
      .drain
