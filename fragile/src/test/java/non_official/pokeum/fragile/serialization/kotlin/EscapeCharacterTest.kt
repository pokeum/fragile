package non_official.pokeum.fragile.serialization.kotlin

import org.junit.Test

class EscapeCharacterTest {

    @Test
    fun `quotation mark`() {
        println("quotation mark: ${'"'}")
    }

    @Test
    fun `reverse solidus`() {
        println("reverse solidus: ${'\\'}")
    }

    @Test
    fun `solidus`() {
        println("reverse solidus: ${'/'}")
    }
}