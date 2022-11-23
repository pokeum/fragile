package non_official.pokeum.fragile.deserialization

interface Token {
    /** VALUE_SEPARATOR (",")       */  object COMMA : Token
    /** KEY_SEPARATOR (":")         */  object COLON : Token
    /** CURLY_BRACKET_BEGIN ("{")   */  object LEFT_BRACE : Token
    /** CURLY_BRACKET_END ("}")     */  object RIGHT_BRACE : Token
    /** SQUARE_BRACKET_BEGIN ("[")  */  object LEFT_BRACKET : Token
    /** SQUARE_BRACKET_END ("]")    */  object RIGHT_BRACKET : Token

    interface ValueToken : Token {
        val value: Any?
    }

    object NullValue : ValueToken {
        override val value: Any?
            get() = null
    }

    data class BoolValue(override val value: Boolean) : ValueToken
    data class StringValue(override val value: String) : ValueToken
    data class NumberValue(override val value: Number) : ValueToken

    companion object {
        val TRUE = BoolValue(true)
        val FALSE = BoolValue(false)
    }
}