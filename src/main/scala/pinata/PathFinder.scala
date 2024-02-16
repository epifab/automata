package pinata

import cats.data.OptionT
import cats.effect.std.Queue
import cats.effect.Async
import cats.effect.Ref
import cats.syntax.all.*

trait PathFinder[F[_]]:
  def findPath[T](
      board: Board[T],
      start: T,
      goal: T,
      walkable: T => Boolean
  ): F[Path]

object PathFinder:

  def apply[F[_]: Async]: PathFinder[F] =
    new PathFinder[F]:

      override def findPath[T](
          board: Board[T],
          start: T,
          goal: T,
          walkable: T => Boolean
      ): F[Path] =

        def findPath(queue: Queue[F, F[Option[Path]]]): F[Path] =
          queue.tryTake.flatMap {
            case Some(callback) =>
              callback.flatMap(_.fold(findPath(queue))(_.pure))
            case None =>
              new IllegalArgumentException("Path not found").raiseError
          }

        def visitPoint(
            currentPos: Point,
            currentPath: Path,
            visited: Ref[F, Set[Point]],
            toVisit: Queue[F, F[Option[Path]]]
        ): F[Option[Path]] =
          Option
            .when(board.get(currentPos).contains(goal))(currentPath)
            .fold {
              val candidates: List[(Point, Direction)] = List(
                currentPos.left  -> Direction.Left,
                currentPos.right -> Direction.Right,
                currentPos.up    -> Direction.Up,
                currentPos.down  -> Direction.Down
              )

              val scheduleVisits: F[Unit] = candidates
                .filter { case (point, _) =>
                  board.get(point).exists(p => walkable(p) || p == goal)
                }
                .traverse_ { case (point, direction) =>
                  visited.get
                    .map(visitedSet =>
                      Option
                        .unless(visitedSet.contains(point))(
                          visitPoint(
                            point,
                            currentPath / direction,
                            visited,
                            toVisit
                          )
                        )
                    )
                    .flatMap(_.traverse(toVisit.offer))
                    .flatTap(_.traverse(_ => visited.update(_ + point)))
                    .void
                }

              scheduleVisits.as(None)
            }(_.some.pure)

        for
          toVisit <- Queue.unbounded[F, F[Option[Path]]]
          visited <- Ref.of[F, Set[Point]](Set.empty)
          origin <- board
            .findPosition(start)
            .liftTo[F](new IllegalArgumentException("Starting point not found"))
          _    <- visited.update(_ + origin)
          _    <- toVisit.offer(visitPoint(origin, Path.empty, visited, toVisit))
          path <- findPath(toVisit)
        yield path
