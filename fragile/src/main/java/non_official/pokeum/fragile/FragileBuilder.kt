package non_official.pokeum.fragile

import non_official.pokeum.fragile.Fragile.Companion.DEFAULT_ESCAPE_HTML
import non_official.pokeum.fragile.Fragile.Companion.DEFAULT_SERIALIZE_NULLS

class FragileBuilder() {
    private var serializeNulls = DEFAULT_SERIALIZE_NULLS
    private var escapeHtml = DEFAULT_ESCAPE_HTML

    constructor(fragile: Fragile): this() {
        this.serializeNulls = fragile.serializeNulls
        this.escapeHtml = fragile.escapeHtml
    }

    fun build(): Fragile {
        return Fragile(serializeNulls, escapeHtml)
    }

    /**
     * Set serialize nulls options. (Defaults to true)
     * @param option Set false to disable serialize nulls option.
     */
    fun serializeNulls(option: Boolean): FragileBuilder {
        this.serializeNulls = option
        return this
    }

    /**
     * Set escape html options. (Defaults to true)
     * @param option Set false to disable escape html option.
     */
    fun escapeHtml(option: Boolean): FragileBuilder {
        this.escapeHtml = option
        return this
    }
}