package non_official.pokeum.fragile.deserialization

import non_official.pokeum.fragile.element.*
import java.io.Reader

class Parser(reader: Reader) {
    private val lexer = Lexer(reader)
    private lateinit var rootElement: JsonElement

    fun parse(): JsonElement {
        when (val token = nextToken()) {
            is Token.NullValue -> rootElement = JsonNull()
            is Token.BoolValue -> rootElement = JsonPrimitive(token.value)
            is Token.StringValue -> rootElement = JsonPrimitive(token.value)
            is Token.NumberValue -> rootElement = JsonPrimitive(token.value)

            Token.LEFT_BRACE -> {
                rootElement = JsonObject()
                parseObjectBody(rootElement)
            }

            Token.LEFT_BRACKET -> {
                rootElement = JsonArray()
                parseArrayBody(rootElement, "")
            }
        }
        if (lexer.nextToken() != null) {
            throw IllegalArgumentException("Too many tokens")
        }
        return rootElement
    }

    private fun parseObjectBody(jsonObject: JsonElement) {
        parseCommaSeparated(Token.RIGHT_BRACE) { token ->
            if (token !is Token.StringValue) {
                throw MalformedJSONException("Unexpected token $token")
            }

            val propName = token.value
            expect(Token.COLON)
            parsePropertyValue(jsonObject, propName, nextToken())
        }
    }

    private fun parseArrayBody(currentElement: JsonElement, propName: String) {
        parseCommaSeparated(Token.RIGHT_BRACKET) { token ->
            parsePropertyValue(currentElement, propName, token)
        }
    }

    private fun parseCommaSeparated(stopToken: Token, body: (Token) -> Unit) {
        var expectComma = false
        while (true) {
            var token = nextToken()
            if (token == stopToken) return
            if (expectComma) {
                if (token != Token.COMMA) throw MalformedJSONException("Expected comma")
                token = nextToken()
            }

            body(token)

            expectComma = true
        }
    }

    private fun parsePropertyValue(currentElement: JsonElement, propName: String, token: Token) {
        when (token) {
            is Token.ValueToken ->
                currentElement.setProperty(propName, token.value)

            Token.LEFT_BRACE -> {
                val childObj = currentElement.createObject(propName)
                parseObjectBody(childObj)
            }

            Token.LEFT_BRACKET -> {
                val childObj = currentElement.createArray(propName)
                parseArrayBody(childObj, propName)
            }

            else ->
                throw MalformedJSONException("Unexpected token $token")
        }
    }

    private fun expect(token: Token) {
        if (lexer.nextToken() != token) {
            throw IllegalArgumentException("$token expected")
        }
    }

    private fun nextToken(): Token = lexer.nextToken() ?: throw IllegalArgumentException("Premature end of data")
}