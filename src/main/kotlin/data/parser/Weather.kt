package data.parser

data class Weather(val years: Int, val months: Int, val rainAmount: Float, val sunHours: Float) {
    init {
        require(years in 1930..2020) { "Invalid year" }
        require(months in 1..12) { "Invalid month" }
        }
    }

