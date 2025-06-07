import Dependencies._

ThisBuild / scalaVersion := "2.13.11"
ThisBuild / scalafixOnCompile := true
ThisBuild / scalafmtOnCompile := true

lazy val root = (project in file("."))
  .settings(
    name := "Min Triangle Path",
    libraryDependencies ++= Seq(catsEffect, scalaTest, log)
  )
