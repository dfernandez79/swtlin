package swtlin

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

interface ControlBuilderContainer {
    fun add(id: String? = null, builder: ControlBuilder<*>)
}

class GenericControlBuilderContainer : ControlBuilderContainer {
    private val _children = mutableListOf<IdentifiedBuilder>()
    val children: List<IdentifiedBuilder> get() = _children

    override fun add(id: String?, builder: ControlBuilder<*>) {
        _children.add(IdentifiedBuilder(id, builder))
    }
}

class IdentifiedBuilder(val id: String?, val builder: ControlBuilder<*>) : ControlFactory<Control> {
    override fun createControl(parent: Composite, refs: MutableControlReferences?): Control {
        val control = builder.createControl(parent, refs)
        if (id != null) {
            refs?.put(id, control)
        }
        return control
    }
}