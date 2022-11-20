package com.example.json_parser.model.kotlin

data class Coordinate(
    val x: Int,
    val y: Int
) {
    override fun toString(): String {
        return "Coordinate(x=$x, y=$y)"
    }
}
