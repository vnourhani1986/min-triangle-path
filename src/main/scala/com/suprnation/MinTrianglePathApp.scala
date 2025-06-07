package com.suprnation

import cats.effect.{ExitCode, IO, IOApp}
import fs2.io.file.{Files, Path}
import fs2.text

object MinTrianglePathApp extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    def read(file: String): IO[Array[Array[Int]]] =
      Files[IO]
        .readAll(Path(file))
        .through(text.utf8.decode)
        .through(text.lines)
        .map(_.split(" "))
        .map(_.map(_.toInt))
        .compile
        .toList
        .map(_.toArray)

    for {
      triangle <- read("./src/main/resources/data_small.txt")
      path     <- MinTrianglePath[IO].minPath(triangle)
      _         = println(s"Small Data Minimum path is: ${path.mkString(" + ")} = ${path.sum}")
      triangle <- read("./src/main/resources/data_big.txt")
      path     <- MinTrianglePath[IO].minPath(triangle)
      _         = println(s"Big Data Minimum path is: ${path.mkString(" + ")} = ${path.sum}")
    } yield ExitCode.Success

  }
}
