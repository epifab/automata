import org.scalajs.linker.interface.ModuleSplitStyle

ThisBuild / version      := "1.0"
ThisBuild / scalaVersion := "3.4.0"

lazy val domain = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("modules/domain"))
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core"                     % "2.10.0",
      "org.typelevel" %%% "cats-effect"                   % "3.5.2",
      "co.fs2"        %%% "fs2-core"                      % "3.9.4",
      "org.typelevel" %%% "cats-effect-testing-scalatest" % "1.5.0" % Test
    )
  )

lazy val domainJvm = domain.jvm
lazy val domainJs  = domain.js

lazy val sdk = (project in file("modules/sdk"))
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(domain.js)
  .settings(
    // Tell Scala.js that this is an application with a main method
    scalaJSUseMainModuleInitializer := false,
    /* Configure Scala.js to emit modules in the optimal way to
     * connect to Vite's incremental reload.
     * - emit ECMAScript modules
     * - emit as many small modules as possible for classes in the "life" package
     * - emit as few (large) modules as possible for all other classes
     *   (in particular, for the standard library)
     */
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
//        .withModuleSplitStyle(ModuleSplitStyle.SmallModulesFor(List("life")))
    },
    libraryDependencies ++= Seq(
      "org.typelevel" %%% "cats-core"                     % "2.10.0",
      "org.typelevel" %%% "cats-effect"                   % "3.5.2",
      "co.fs2"        %%% "fs2-core"                      % "3.9.4",
      "org.scala-js"  %%% "scalajs-dom"                   % "2.4.0",
      "org.typelevel" %%% "cats-effect-testing-scalatest" % "1.5.0" % Test
    )
  )

lazy val root = project.in(file(".")).aggregate(domainJvm)
