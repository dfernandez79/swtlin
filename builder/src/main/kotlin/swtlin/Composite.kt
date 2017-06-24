package swtlin

import org.eclipse.swt.widgets.Composite

fun composite(block: CompositeDescription.() -> Unit = {}) = CompositeBuilder().also { it.block() }

fun ControlBuilderContainer.composite(id: String? = null, block: CompositeDescription.() -> Unit = {}) =
        composite(block).also { this.add(id, it) }

interface CompositeDescription : ControlDescription<Composite>, ControlBuilderContainer {
    var layout: LayoutDescription

    override fun add(id: String?, builder: ControlBuilder<*>)
}

class CompositeBuilder : AbstractControlDescription<Composite>(), CompositeDescription, ControlBuilder<Composite> {
    private val factory = GenericControlFactory(this, ::Composite, this::setUpControl)
    private val container = GenericControlBuilderContainer()

    override var layout: LayoutDescription = FormLayoutDescription()

    override fun createControl(parent: Composite, refs: MutableControlReferences?) =
            factory.createControl(parent, refs)

    override fun add(id: String?, builder: ControlBuilder<*>) {
        container.add(id, builder)
    }

    fun setUpControl(control: Composite, refs: MutableControlReferences?) {
        val pairs = container.children.map { it.builder to it.createControl(control, refs) }
        layout.layout(control, pairs, refs.orEmpty())
    }
}