fun main() {
    println("Enter the number of books:")
    val numberBooks = readln().toInt()

    println("Enter all books:")
    val books = MutableList(numberBooks) {
        readln()
    }

    println("Enter the number of search queries:")
    var numberSearch = readln().toInt()

    repeat(numberSearch){
        println("Enter data to search people:")
        val searchWord = readln()

        if (searchWord.lowercase() in books.joinToString().lowercase()) for(book in books) {
                if (searchWord.lowercase() in book.lowercase()) println(book)
        } else println("No matching book found.")
    }
}