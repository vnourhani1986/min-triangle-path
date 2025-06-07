import sbt._

object Dependencies {

  object Versions {
    val catsEffect = "3.5.1"
    val log4Cats = "2.6.0"
    val scalaTest = "3.2.17"
  }

  val catsEffect: ModuleID = "org.typelevel" %% "cats-effect" % Versions.catsEffect
  val log: ModuleID = "org.typelevel" %% "log4cats-slf4j" % Versions.log4Cats
  val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % Versions.scalaTest % Test
}
