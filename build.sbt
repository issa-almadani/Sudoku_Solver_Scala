val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Sudoku_Solver_Scala",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "com.lihaoyi" %% "mainargs" % "0.7.0"
    )
  )
