import sbt._

object Dependencies {

  object Versions {
    val catsEffect = "3.5.1"
    val fs2        = "3.7.0"
    val scalaTest  = "3.2.17"
  }

  val catsEffect: ModuleID = "org.typelevel" %% "cats-effect" % Versions.catsEffect
  val scalaTest: ModuleID  = "org.scalatest" %% "scalatest"   % Versions.scalaTest % Test
  val fs2: List[ModuleID]  = List("co.fs2" %% "fs2-core", "co.fs2" %% "fs2-io").map(_ % Versions.fs2)
}
