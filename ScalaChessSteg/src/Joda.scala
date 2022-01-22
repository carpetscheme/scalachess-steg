package org.joda.time

sealed trait DateTimeZone

object DateTimeZone {
  case object UTC extends DateTimeZone
}

object Duration {
  def standardSeconds(seconds: Long) = Duration(seconds)
}

case class Duration(length: Long) {
  def toPeriod = length.toInt
}

case class Period(length: Long)

package format {

  object DateTimeFormat {
    def forPattern(pattern: String) = DateTimeFormat(pattern)
  }

  @scala.annotation.nowarn
  case class DateTimeFormat(out: String) {
    def withZone(dateTimeZone: DateTimeZone) = DateTimeFormat(out)
  }

  case object PeriodFormatter {
    def print: String = "carpet"
  }

  @scala.annotation.nowarn
  class PeriodFormatterBuilder {

    def printZeroAlways = this

    def minimumPrintedDigits(int: Int) = this

    def appendHours = this

    def appendSeparator(string: String) = this

    def appendMinutes = this

    def appendSeconds = this

    def toFormatter = PeriodFormatter

  }

}
