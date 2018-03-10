package org.scalalabs.basic.lab01

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

/**
  * In this Lab you will implement a ScalaTest testcase.
  *
  * Instructions:
  * 1. Implement the divide method in Euro that has the following signature:  def /(divider:Int) = ???
  * - If the divider is <=0 throw an IllegalArgumentException
  *
  * 2. Write a ScalaTest using a Spec of your choice to test:
  * - Happy flow (divider is > 0)
  * - Alternative flow (divider is <= 0)
  */
@RunWith(classOf[JUnitRunner])
class ScalaTestExerciseTest extends Specification {

  "Dividing an euro" should {
    "divide the cent value if the divider is positive" in {
      val euro = new Euro(10, 10)
      (euro / 10).inCents ==== 101
    }

    "throws IllegalArgumentException if divider is negative" in {
      def tst: Euro = {
        new Euro(10, 10) / (-1)
      }

      tst must throwA(new IllegalArgumentException("Divider cannot be non-positive"))
    }

    "throws IllegalArgumentException if divider is zero" in {
      def tst: Euro = {
        new Euro(10, 10) / 0
      }

      tst must throwA(new IllegalArgumentException("Divider cannot be non-positive"))
    }

  }


}
