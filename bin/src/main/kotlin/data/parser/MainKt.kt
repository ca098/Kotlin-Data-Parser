package comp3222.cwk2

import java.io.File

class MainKt {

companion object {
    @JvmStatic
    fun main(args: Array<String>) {
        println("I'm ALIVE!!!")
    }



    fun readFileAsTextUsingInputStream(fileName: String)
            = File(fileName).inputStream().readBytes().toString(Charsets.UTF_8)
 }
}