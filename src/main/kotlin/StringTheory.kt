fun main() {
    val words = readln().split(" ").toMutableList()
    val searchWord = readln()

    when (searchWord) {
        in words -> println(words.indexOf(searchWord) + 1)
        else -> println("Not found")
    }

}