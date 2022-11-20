package ru.yole.jkid.examples._2

import org.junit.Test
import ru.yole.jkid.examples.testJsonSerializer

data class Author(val fullName: String)
data class Book(val name: String, val authors: List<Author>)

class BookTest {
    @Test fun test() = testJsonSerializer(
            value = Book("Lord of the Rings", listOf(Author("J.R.R.Tolkien"))),
            json = """{"authors": [{"fullName": "J.R.R.Tolkien"}], "name": "Lord of the Rings"}"""
    )
}