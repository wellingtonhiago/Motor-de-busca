package search

import java.io.File

fun main(args: Array<String>) {
    val dataFile = args[args.indexOf("--data") + 1]
    val dataList = File(dataFile).readLines()
    val dataMap = getMapFromList(dataList)
    val data = Main(dataList, dataMap)
    while (true) {
        print("""
        ===Menu===
        1. Find a person
        2. Print all persons
        0. Exit
        
        """.trimIndent())
        when (readln()) {
            "1" -> {
                print("\nSelect a matching strategy: ALL, ANY, NONE\n")
                when (readln()) {
                    "ALL"   -> {
                        println("\nEnter a name or email to search all matching people.")
                        println(data.searchAll(readWord()))
                    }
                    "ANY"   -> {
                        println("\nEnter a name or email to search all matching people.")
                        println(data.searchAny(readWord()))
                    }
                    "NONE"  -> println(data.searchNone(readWord()))
                    else    -> break
                }
            }
            "2" -> println(data)
            "0" -> {
                println("\nBye!")
                break
            }
        }
    }
}

class Main(private val dataList: List<String>, private val dataMap: Map<String, List<Int>>) {
    private fun joinList(lst: List<String>): String = lst.joinToString("\n")
    fun searchAny(queryList: List<String>): String {
        val returnList = mutableListOf<String>()
        queryList.forEach { query -> dataMap[query]?.forEach { index -> returnList.add(dataList[index]) } }
        return joinList(returnList)
    }
    fun searchAll(queryList: List<String>): String {
        val returnList = mutableListOf<String>()
        queryList.forEach { query -> dataMap[query]?.forEach { index -> returnList.add(dataList[index]) } }
        returnList.distinct().forEach { line ->
            if (returnList.count{ it == line } != queryList.size) returnList.removeAll { it == line}
        }
        return joinList(returnList)
    }
    fun searchNone(queryList: List<String>): String {
        val returnList = mutableListOf<String>()
        queryList.forEach { query ->
            dataList.forEachIndexed { index, line ->
                if (index !in (dataMap[query.lowercase()] ?: -2..-1)) returnList.add(line)
            }
        }
        returnList.distinct().forEach { line ->
            if (returnList.count{ it == line } != queryList.size) returnList.removeAll { it == line}
        }
        return joinList(returnList)
    }
    override fun toString(): String = dataList.joinToString("\n")
}

fun getMapFromList(lst: List<String>): Map<String, List<Int>> {
    val dataMap = mutableMapOf<String, List<Int>>()
    lst.forEachIndexed { index, line ->
        line.split(" ").forEach { word ->
            val indexList: List<Int> = dataMap[word.lowercase()]?.plus(index) ?: listOf(index)
            dataMap += word.lowercase() to indexList
        }
    }
    return dataMap
}
fun readWord(): List<String> = readln().split(" ")