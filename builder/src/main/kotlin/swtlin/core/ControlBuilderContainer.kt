package swtlin.core

/**
 * Implementors of this interface contains instances of [ControlBuilder]
 */
interface ControlBuilderContainer {
    fun add(id: String? = null, builder: ControlBuilder<*>)
}

