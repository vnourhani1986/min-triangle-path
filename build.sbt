import Dependencies._

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := "4.9.9"
ThisBuild / scalacOptions += "-Wunused:imports"
ThisBuild / scalafixOnCompile := true
ThisBuild / scalafmtOnCompile := true

lazy val root = (project in file("."))
  .settings(
    name := "Min Triangle Path",
    libraryDependencies ++= catsEffect :: scalaTest :: fs2
  )
