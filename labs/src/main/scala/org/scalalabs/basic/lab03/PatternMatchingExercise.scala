package org.scalalabs.basic.lab03

import sys._
/**
 * This exercise introduces you to the powerful pattern matching features of Scala.
 *
 * Pattern matching can in its essence be compared to Java's 'switch' statement,
 * even though it provides many more possibilites. Whereas the Java switch statmenet
 * lets you 'match' primitive types up to int's, Scala's pattern matching goes much
 * further. Practically everything from all types of objects and Collections
 * can be matched, not forgetting xml and a special type of class called case classes.
 *
 * Pattern matching is also often used in combination with recursive algorithms.
 *
 * For this exercise exclusively use pattern matching constructs in order to make the
 * corresponding unit test work.
 *
 * Reference material to solve these exercises can be found here:
 * Pattern matching in general: http://programming-scala.labs.oreilly.com/ch03.html#PatternMatching
 * Pattern matching in combination with partial functions: http://programming-scala.labs.oreilly.com/ch08.html#PartialFunctions
 */

object PatternMatchingExercise {

  /**
   * ***********************************************************************
   *  pattern matching exercises
   * For expected solution see unittest @PatternMatchingExerciseTest
   * ***********************************************************************
   */

  def describeLanguage(s: String) = {
    s match {
      case "Java" | "Smalltalk" => "OOP"
      case "Clojure" | "Haskell" => "Functional"
      case "Scala" => "Hybrid"
      case "C" => "Procedural"
      case _ => "Unknown"
    }
  }

  def matchOnInputType(in: Any) = {
    in match {
      case s: String => s"A string with length ${s.length}"
      case _: Int => "A positive integer"
      case Person(name, age) => s"A person with name: ${name}"
      case s:Seq[Any] if s.length <= 10 => s"first: ${s(0)}, second: ${s(1)}, rest: ${s.drop(2).toList}"
      case _:Seq[Any] => "Seq with more than 10 elements"
      case _:Option[Any] => "A Scala Option subtype"
      case null => "A null value"
      case _ => "Some Scala class"
    }
  }

  def older(p: Person): Option[String] = {
    List(p).find(_.age > 30).map(_.name)
  }

  /**
   * ***********************************************************************
   * Pattern matching with partial functions
   * For expected solution see @PatternMatchingExerciseTest
   * ***********************************************************************
   */

  val pf1: PartialFunction[String, String] = {
    case "scala-labs" => "scala-labs"
    case "stuff" => "stuff"
  }

  val pf2: PartialFunction[String, String] = {
    case "other stuff" => "other stuff"
  }

  val pf3: PartialFunction[String, String] = {
    pf1 orElse pf2
  }

}

case class Person(name: String, age: Int)