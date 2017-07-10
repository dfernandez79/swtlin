package swtlin

import org.eclipse.swt.widgets.Composite
import swtlin.core.*

fun composite(block: CompositeDescription.() -> Unit = {}) = builder(::CompositeBuilder, block)

fun ControlBuilderContainer.composite(id: String? = null, block: CompositeDescription.() -> Unit = {}) =
        builder(::CompositeBuilder, block, id, this)

interface CompositeDescription : ControlDescription<Composite>, ControlBuilderContainer {
    var layout: LayoutDescription

    override fun add(id: String?, builder: ControlBuilder<*>)
}

class CompositeBuilder : AbstractControlBuilder<Composite>(::Composite), CompositeDescription {
    private val container = IdentifiedControlBuilderContainer()

    override var layout: LayoutDescription = FormLayoutDescription()

    override fun add(id: String?, builder: ControlBuilder<*>) {
        container.add(id, builder)
    }

    override fun setUpControl(control: Composite, refs: MutableControlReferences?) {
        val pairs = container.children.map { it.builder to it.createControl(control, refs) }
        layout.layout(control, pairs, refs.orEmpty())
    }
}