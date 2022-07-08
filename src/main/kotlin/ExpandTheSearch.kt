import java.io.File

fun main() {
    val fileName = "/Users/welli/Google Drive/Desenvolvedor/JetBrains Kotlin/Agenda.txt"
    val books = File(fileName).readLines().toMutableList()

    menu(books)


}

fun menu(books: MutableList<String>) {

    var menu = true

    do {
        println("1. Find a person")
        println("2. Print all people")
        println("0. Exit")
        when (readln()) {
            "1" -> search(books = books)
            "2" -> for (book in books) println(book)
            "0" -> break
            else -> continue
        }
    } while (menu)
}

fun search(books: MutableList<String>) {
    println("Enter data to search people:")
    val searchWord = readln()

    if (searchWord.lowercase() in books.joinToString().lowercase())
        for(book in books) {
            if (searchWord.lowercase() in book.lowercase()) println(book)
    } else println("No matching book found.")
}
