package non_official.pokeum.fragile

import non_official.pokeum.fragile.Fragile.Companion.DEFAULT_SERIALIZE_NULLS

class FragileBuilder() {
    private var serializeNulls = DEFAULT_SERIALIZE_NULLS

    constructor(fragile: Fragile): this() {
        this.serializeNulls = fragile.serializeNulls
    }

    fun build(): Fragile {
        return Fragile(this.serializeNulls)
    }

    /**
     * Set serialize nulls options. (Defaults to true)
     * @param option Set false to disable serialize nulls option.
     */
    fun serializeNulls(option: Boolean): FragileBuilder {
        this.serializeNulls = option
        return this
    }
}