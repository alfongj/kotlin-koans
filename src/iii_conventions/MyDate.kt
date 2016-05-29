package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    val pseudoNumDays = year * 365 + month * 30 + dayOfMonth

    override fun compareTo(other: MyDate): Int {
        return pseudoNumDays - other.pseudoNumDays
    }

    infix operator fun plus(timeInterval: TimeInterval): MyDate {
        return addTimeIntervals(timeInterval, timeInterval.quantity)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval(var quantity: Int = 1) {
    DAY(1),
    WEEK(1),
    YEAR(1);

    infix operator fun times(i: Int): TimeInterval {
        quantity = i
        return this
    }
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(date: MyDate) =
            date.pseudoNumDays in start.pseudoNumDays..endInclusive.pseudoNumDays

    operator fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {

            var currentDate = start

            override fun hasNext(): Boolean {
                return currentDate <= endInclusive
            }

            override fun next(): MyDate {
                val oldDate = currentDate
                currentDate = currentDate.nextDay()
                return oldDate
            }

        }
    }
}
