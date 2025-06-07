package com.suprnation

import cats.implicits._
import cats.{Monad, Parallel}

trait MinTrianglePath[F[_]] {
  def minPath(triangle: Array[Array[Int]]): F[List[Int]]
}

object MinTrianglePath {
  def apply[F[_]: Monad: Parallel]: MinTrianglePath[F] =
    new MinTrianglePath[F] {
      def minPath(triangle: Array[Array[Int]]): F[List[Int]] =
        for {
          triangle <- minTriangle(triangle, triangle.length)
        } yield triangle
          .foldLeft((0, List.empty[Int])) {
            case ((pIndex, path), row) if row.length == 1                => (pIndex, path)
            case ((pIndex, path), row) if row(pIndex) <= row(pIndex + 1) =>
              (pIndex, path :+ (triangle(path.length)(pIndex) - row(pIndex)))
            case ((pIndex, path), row) if row(pIndex) > row(pIndex + 1)  =>
              (pIndex + 1, path :+ (triangle(path.length)(pIndex) - row(pIndex + 1)))
          } match {
          case (index, path) => path :+ triangle.last(index)
        }

      private def minTriangle(triangle: Array[Array[Int]], index: Int): F[Array[Array[Int]]] = {
        def minRow(parent: Array[Int], child: Array[Int]): F[List[Int]] = {
          def min(
            root: Int,
            left: Int,
            right: Int
          ): F[Int] = (root + List(left, right).min).pure[F]

          parent
            .foldLeft(List.empty[F[Int]]) {
              case (fi, value) => fi :+ min(value, child(fi.length), child(fi.length + 1))
            }
            .parSequence
        }

        if (index == 1) triangle.pure[F]
        else
          for {
            list     <- minRow(triangle(index - 2), triangle(index - 1))
            triangle <- minTriangle(triangle.updated(index - 2, list.toArray), index - 1)
          } yield triangle
      }
    }
}
