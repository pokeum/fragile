package non_official.pokeum.fragile.etc.string

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.StringReader
import java.io.StringWriter

class StringReaderTest {

    @Test
    fun `Example - Java StringReader (Reads characters into an array)`() {
        val data: String = "This is the text read from StringReader."
        var result: String = ""

        // Create a character array
        val target = CharArray(100) { ' ' }

        try {
            // Creates a StringReader
            val reader: StringReader = StringReader(data)

            /**
             * Reads characters into an array
             * count: The number of characters read, or -1 if the end of the stream has been reached.
             */
            val count = reader.read(target)
            result = String(target, 0, count)

            reader.close()
        } catch (e: Exception) {
            println(e.stackTrace)
        }

        assertEquals(data, result)
    }

    // https://docs.oracle.com/javase/7/docs/api/java/io/Reader.html#read()
    @Test
    fun `Example - Java StringReader (Reads a single character)`() {
        val data: String = "This is the text read from StringReader."

        val reader: StringReader = StringReader(data)
        var eof: Boolean = false

        val writer: StringWriter = StringWriter()

        while (!eof) {
            val c = reader.read()
            if (c == -1) { eof = true }
            else { writer.write(c) }
        }

        assertEquals(data, writer.toString())
    }

    @Test
    fun `Example - Java StringReader (Reads characters into a portion of an array)`() {
        val data: String = "This is the text read from StringReader."   // 40

        val reader: StringReader = StringReader(data)
        val cbuf = CharArray(6)
        var eof: Boolean = false

        val writer: StringWriter = StringWriter()

        while (!eof) {
            // The number of characters read, or -1 if the end of the stream has been reached
            val count = reader.read(cbuf, 0, cbuf.size)
            if (count == -1) { eof = true }
            else { writer.write(String(cbuf, 0, count)) }
        }

        assertEquals(data, writer.toString())
    }
}