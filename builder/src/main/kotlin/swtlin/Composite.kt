package swtlin

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

fun composite(block: CompositeDescription.() -> Unit = {}) = CompositeBuilder().also { it.block() }

fun CompositeDescription.composite(id: String? = null, block: CompositeDescription.() -> Unit = {}) = composite(block).also { this.add(id, it) }

interface CompositeDescription : ControlDescription<Composite> {
    var layout: LayoutDescription

    fun add(id: String? = null, builder: ControlBuilder<*>)
}

class CompositeBuilder : AbstractControlDescription<Composite>(), CompositeDescription, ControlBuilder<Composite> {
    private val factory = GenericControlFactory(this, ::Composite, this::setUpControl)
    private val children = mutableListOf<Wrapper>()

    override var layout: LayoutDescription = FormLayoutDescription()

    override fun createControl(parent: Composite, refs: MutableControlReferences?) = factory.createControl(parent, refs)

    override fun add(id: String?, builder: ControlBuilder<*>) {
        children.add(Wrapper(id, builder))
    }

    fun setUpControl(control: Composite, refs: MutableControlReferences?) {
        val pairs = children.map { it.builder to it.createControl(control, refs) }
        layout.layout(control, pairs, refs.orEmpty())
    }

    private class Wrapper(val id: String?, val builder: ControlBuilder<*>) : ControlFactory<Control> {
        override fun createControl(parent: Composite, refs: MutableControlReferences?): Control {
            val control = builder.createControl(parent, refs)
            if (id != null) {
                refs?.put(id, control)
            }
            return control
        }
    }
}