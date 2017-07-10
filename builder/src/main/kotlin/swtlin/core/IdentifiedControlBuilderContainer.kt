package swtlin.core

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import swtlin.core.IdentifiedControlBuilderContainer.IdentifiedControlBuilder

/**
 * Implementation of [ControlBuilderContainer] that wraps its child
 * builders with [IdentifiedControlBuilder].
 */
class IdentifiedControlBuilderContainer : ControlBuilderContainer {
    private val _children = mutableListOf<IdentifiedControlBuilder>()

    /**
     * Builders added to this container, wrapped with [IdentifiedControlBuilder].
     */
    val children: List<IdentifiedControlBuilder> get() = _children

    override fun add(id: String?, builder: ControlBuilder<*>) {
        _children.add(IdentifiedControlBuilder(id, builder))
    }

    /**
     * Wraps an instance of [ControlBuilder] with an optional string identifier.
     */
    class IdentifiedControlBuilder(val id: String?, val builder: ControlBuilder<*>) : ControlFactory<Control> {
        override fun createControl(parent: Composite, refs: MutableControlReferences?): Control {
            val control = builder.createControl(parent, refs)
            if (id != null) {
                refs?.put(id, control)
            }
            return control
        }
    }
}