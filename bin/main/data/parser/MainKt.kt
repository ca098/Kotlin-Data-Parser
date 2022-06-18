package data.parser

import java.io.File
import java.io.FileNotFoundException
import java.text.DateFormatSymbols
import java.util.*

class MainKt {

    companion object {

        private var size: Int = 0

        @JvmStatic
        fun main(args: Array<String>) {

            //Reads the filepath given as commandline argument
            val fileContents = getData(args[0])
            if (fileContents.size <= 4) {
                throw InputMismatchException("Insufficient data in file")
            }

            val topOfFile = fileContents[1].split(",").toTypedArray()
            val weatherList = WeatherList().weatherList(fileContents)
            val yearValueList = WeatherList().yearList(weatherList)

            // Class needed for "Advanced Solution"
            val yearValues = sortedByYearRain(yearValueList)
            size = weatherList.size

            // Display data given 1 or two arguments
            when (args.size) {
                1 -> {
                    with(System.out) {
                        printf("Station: %s%nLatitude: %s°%nLongitude: %s°%n", fileContents[0], latOrLon(topOfFile, "lat"), latOrLon(topOfFile, "lon"))
                        printf("Elevation: %s m%n", topOfFile[2].replace("\\D+".toRegex(), ""))
                        printf("Number of records: %d%n", size)
                        printf("Years spanned: %d to %d%n", weatherList[0].years, weatherList[size - 1].years)

                        printf("Wettest year: %d (%.1f mm)%n", yearValues[yearValues.size - 1].years,
                                yearValues[yearValues.size - 1].rainAmount)
                        printf("Driest year: %d (%.1f mm)%n", yearValues[0].years,
                                yearValues[0].rainAmount)

                        printf("Wettest month: %s %d (%.1f mm)%n", monthString(sortedByRainfall(weatherList)[size - 1].months),
                                sortedByRainfall(weatherList)[size - 1].years,
                                sortedByRainfall(weatherList)[size - 1].rainAmount)
                        printf("Driest month: %s %d (%.1f mm)%n", monthString(sortedByRainfall(weatherList)[0].months),
                                sortedByRainfall(weatherList)[0].years,
                                sortedByRainfall(weatherList)[0].rainAmount)
                    }
                } //End of 1
                2 -> {
                    val year = args[1]
                    showGraph(weatherList, year)
                } // End of 2
                else -> throw InputMismatchException("Please supply 1 or 2 arguments")
            } // End of when statement
        }


        // Function that displays graph when year is passed as an argument
        private fun showGraph(weatherList: List<Weather>, year: String) {
            for (i in 0 until size) {
                if (weatherList[i].years == year.toInt()) {
                    var barChartStr = ""
                    val stringSize: Int = (weatherList[i].rainAmount / 3).toInt()
                    for (j in 0 until stringSize) {
                        barChartStr += "#"
                    }
                    System.out.printf("%s (%.1f): %s%n", monthString(weatherList[i].months), weatherList[i].rainAmount, barChartStr)
                }
            }
        }

        // Returns the latitude or longitude given the input parameters
        private fun latOrLon(strList: Array<String>, word: String): String {
            val latAndLong = strList[1].replace("[^\\d.-]".toRegex(), "")
            var result: String
            if (strList.isEmpty())
                throw InputMismatchException("List is too small")

            return when (word) {
                "lat" -> {
                    result = latAndLong.substring(0, 6)
                    result
                }
                "lon" -> {
                    result = latAndLong.substring(6, latAndLong.length)
                    result
                }
                else -> "N/A"
            }

        }
        // Returns the month as a string rather than int
        private fun monthString(month: Int): String {
            return DateFormatSymbols().months[month - 1]
        }

        // Sorts the yearList by values in the rain column (Advanced Solution)
        private fun sortedByYearRain(list: List<YearValues>): List<YearValues> {
            return list.sortedBy { it.rainAmount }
        }

        // Sorts the weatherList by values in the rain column (Basic Solution)
        private fun sortedByRainfall(list: List<Weather>): List<Weather> {
            return list.sortedBy { it.rainAmount }
        }

        // Reads the text file, with error handling for insufficient data
        private fun getData(filePath: String): List<String> {
            val fileContents: List<String>
            try {
                fileContents = readFileAsLinesUsingReadLines(filePath)
            } catch (e: FileNotFoundException) {
                val list = mutableListOf<String>()
                File("data/").walk().forEach {
                    list.add(it.toString())
                    list.remove("data/README.md")
                    list.remove("data")
                }

                for (item in list)
                    println(item)

                println("\nEnter a valid Filepath from the list provided above...\n".toUpperCase())
                throw e
            }
            return fileContents
        }

        // Creates a String List with the contents of the text file.
        private fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()
    } // End of companion object
} // End of MainKt class