package data.parser

class WeatherList {
    // Takes data from file and creates an instance of Weather()
    fun weatherList(list: List<String>): List<Weather> {
        val wList = mutableListOf<Weather>()
        for (i in 4 until list.size) {
            val dataRows = list[i].split("""(?U)\s+""".toRegex())
            val weather = Weather(years = dataRows[1].toInt(),
                    months = dataRows[2].toInt(),
                    rainAmount = dataRows[6].toFloat(),
                    sunHours = dataRows[7].toFloat())
            wList.add(weather)
        }
        return wList
    }

    /*
     Loops through the months and creates instance of YearValues,
      which creates a value of rainfall for the year paired with the given year
     */
    fun yearList(list: List<Weather>): List<YearValues> {
        val wetDryList = mutableListOf<YearValues>()
        var count = 0.0F
        list.forEach {
            count += it.rainAmount
            if (it.months == 12) {
                val wetVDry = YearValues(years = it.years,
                        rainAmount = count)
                count = 0.0F
                wetDryList.add(wetVDry)
            }
        }
        return wetDryList
    }
}


