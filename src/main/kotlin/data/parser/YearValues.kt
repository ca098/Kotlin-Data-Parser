package data.parser

data class YearValues(val years: Int, val rainAmount: Float) {
    init {
        require(years in 1930..2020) { "Invalid year" }
        require(rainAmount in 1.0..12000.0) { "Invalid rainfall" }
    }
}