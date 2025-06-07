package com.suprnation

import cats.effect.IO
import cats.effect.unsafe.implicits.global
import com.suprnation.MinTrianglePathSpec.Scope
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MinTrianglePathSpec extends AnyWordSpec with Matchers {
  "MinTrianglePath#minPath" should {
    "return the minimum path" in new Scope {
      MinTrianglePath[IO].minPath(triangle1).unsafeRunSync() shouldBe List(7, 6, 3, 2)
      MinTrianglePath[IO].minPath(triangle2).unsafeRunSync() shouldBe List(2, 3, 5, 1)
    }
  }
}

object MinTrianglePathSpec {
  private[suprnation] trait Scope {
    val triangle1: Array[Array[Int]] = Array(Array(7), Array(6, 3), Array(3, 8, 5), Array(11, 2, 10, 9))
    val triangle2: Array[Array[Int]] = Array(Array(2), Array(3, 4), Array(6, 5, 7), Array(4, 1, 8, 3))
  }
}
