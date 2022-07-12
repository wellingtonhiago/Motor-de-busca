package search

import java.io.File

fun main(args: Array<String>) {
    val dataFile = args[args.indexOf("--data") + 1]
    val dataList = File(dataFile).readLines()
    val dataMap = mutableMapOf<String, List<Int>>()
    dataList.forEachIndexed { index, line ->
        line.split(" ").forEach { word ->
            val indexList: List<Int> = dataMap[word.lowercase()]?.plus(index) ?: listOf<Int>(index)
            dataMap += word.lowercase() to indexList
        }
    }
    while (true) {
        print("""
            1. Search information.
            2. Print all data.
            0. Exit.
            
        """.trimIndent())
        when (readln()) {
            "1" -> {
                val indices = dataMap[readln().lowercase()]
                indices?.forEach { println(dataList[it]) } ?: ""
            }
            "2" -> println(dataList.joinToString("\n"))
            "0" -> break
        }
    }
}