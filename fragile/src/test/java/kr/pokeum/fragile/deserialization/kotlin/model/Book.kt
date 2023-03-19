package kr.pokeum.fragile.deserialization.kotlin.model

import kr.pokeum.fragile.element.JsonObject

data class Book(
    val title: String,
    val author: Author,
    val publisher: Publisher
)
internal fun JsonObject.toBook(): Book = Book(
    title = get("title") as String,
    author = getJsonObject("author")!!.toAuthor(),
    publisher = getJsonObject("publisher")!!.toPublisher()
)

data class Author(
    val name: String,
    val birthYear: Int,
    val deathYear: Int?
)
internal fun JsonObject.toAuthor(): Author = Author(
    name = get("name").toString(),
    birthYear = get("birthYear").toString().toInt(),
    deathYear = get("deathYear")?.toString()?.toInt()
)

data class Publisher(
    val name: String,
    val year: Int,
    val location: Location
)
internal fun JsonObject.toPublisher(): Publisher = Publisher(
    name = get("name") as String,
    year = (get("year") as Number).toInt(),
    location = getJsonObject("location")!!.toLocation()
)

data class Location(
    val city: String,
    val state: String
)
internal fun JsonObject.toLocation(): Location = Location(
    city = get("city") as String,
    state = get("state") as String
)