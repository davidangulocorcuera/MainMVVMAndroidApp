package david.angulo.productsApp.modules.utils

import java.util.*

class RandomUtils {
    fun getRandomFloat(min: Int, max: Int): Float {
        val rand = Random()

        var randomNumber = getRandomValue(rand, min, max, 2)
        randomNumber = randomNumber.replace(",", ".")
        return java.lang.Float.parseFloat(randomNumber)
    }

    fun getRandomLong(min: Int, max: Int): Long {
        val r = Random()
        return min + (r.nextDouble() * (max - min)).toLong()
    }

    fun getRandomInt(min: Int, max: Int): Int {
        val r = Random()
        return r.nextInt(max - min + 1) + min
    }

    private fun getRandomValue(random: Random?, lowerBound: Int, upperBound: Int, decimalPlaces: Int): String {
        var lowerBound = lowerBound
        var upperBound = upperBound

        if (lowerBound < 0 || decimalPlaces < 0) {
            throw IllegalArgumentException("Put error message here")
        }

        if (upperBound <= lowerBound) {
            val auxLower = upperBound
            upperBound = lowerBound
            lowerBound = auxLower
        }

        val dbl = ((random ?: Random()).nextDouble() //
                * (upperBound - lowerBound)) + lowerBound
        return String.format("%." + decimalPlaces + "f", dbl)
    }
}