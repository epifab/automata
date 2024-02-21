ThisBuild / version      := "1.0"
ThisBuild / scalaVersion := "3.4.0"

lazy val root = (project in file("."))
  .settings(
    name := "pinata",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core"                     % "2.10.0",
      "org.typelevel" %% "cats-effect"                   % "3.5.2",
      "co.fs2"        %% "fs2-core"                      % "3.9.4",
      "org.typelevel" %% "cats-effect-testing-scalatest" % "1.5.0" % Test
    )
  )
