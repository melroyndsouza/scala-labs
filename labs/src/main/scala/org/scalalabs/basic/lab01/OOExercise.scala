package org.scalalabs.basic.lab01

import scala.language.implicitConversions

/**
  * The goal of this exercise is to get familiar basic OO constructs in scala
  *
  * Fix the code so that the unit test 'CurrencyExerciseTest' passes.
  *
  * In order for the tests to pass you need to do the following:
  *
  * Exercise 1:
  * - Create a class Euro
  * - Provide it with two constructor parameters: euro:Int, cents:Int
  * - Provide the cents field with default value: 0
  * - Provide an immutable field named: inCents that converts euro + cents into cents.
  * - Create an object Euro with a factory method named: fromCents that creates an Euro based on cents.
  * - Create a method named: + to the Euro class that adds another Euro
  * - Create a method named: * to the Euro class that multiplies an Euro
  *
  * Exercise 2:
  * - Create an abstract class Currency
  * - Provide it with one constructor parameter: symbol:String
  * - Extend the previously created Euro class from Currency
  * - Override the toString method of Euro to represent the following String:
  * -> symbol + ': ' + euro + ',' + cents.  E.g: EUR 200,05
  * - In case the cents are 0 use this representation:
  * -> symbol + ': ' + euro + ',--. E.g.: EUR 200.--
  *
  * Exercise 3:
  * - Mix the Ordered trait in Euro
  * - Implement the compare method
  *
  * Exercise 4:
  * - Provide an implicit class that adds a *(euro:Euro) method to Int
  * - Create a new currency Dollar
  * - Provide a implicit conversion method that converts from Euro to Dollar using the
  * [[org.scalalabs.basic.lab01.DefaultCurrencyConverter]]
  *
  * Exercise 5:
  * - Extend the conversion method from Euro to Dollar with an implicit parameter
  * of type [[org.scalalabs.basic.lab01.CurrencyConverter]]
  * - Use the implicit CurrencyConverter to do the conversion.
  */

abstract class Currency(val symbol: String) {

}

class Dollar(val dollar: Int, val cents: Int = 0, override val symbol: String = "$") extends Currency(symbol) {
  def inCents = dollar * 100 + cents

}


class Euro(val euro: Int, val cents: Int = 0, override val symbol: String = "EUR") extends Currency(symbol) with Ordered[Euro] {
  def inCents = euro * 100 + cents

  def +(euro: Euro) = Euro.fromCents(inCents + euro.inCents)

  def *(euro: Int) = Euro.fromCents(inCents * euro)

  def /(divider: Int):Euro = if (divider <= 0) throw new IllegalArgumentException("Divider cannot be non-positive") else Euro.fromCents(inCents/divider)

  override def toString: String = f"${symbol}: ${euro}," + (if (cents == 0) "--" else f"${cents}%02d")

  override def compare(that: Euro): Int = inCents.compareTo(that.inCents)
}

object Euro {
  def fromCents(cents: Int) = new Euro(cents / 100, cents % 100)

  implicit def toEuro(dollar: Dollar)(implicit currencyConvertor: CurrencyConverter): Euro =
    Euro.fromCents(currencyConvertor.toEuroCents(dollar.inCents))

  implicit class EuroImprovements(int: Int) {
    def *(euro: Euro): Euro = Euro.fromCents(int * euro.inCents)

  }


}


