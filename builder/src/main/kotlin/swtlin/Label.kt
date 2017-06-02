package swtlin

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label

fun label(block: LabelDescription.() -> Unit = {}) = LabelBuilder().also { it.block() }

fun CompositeDescription.label(id: String? = null, block: LabelDescription.() -> Unit = {}) =
        label(block).also { this.add(id, it) }

interface LabelDescription : ControlDescription<Label> {
    var text: String
}

class LabelBuilder : AbstractControlDescription<Label>(), LabelDescription, ControlBuilder<Label> {
    private val factory = GenericControlFactory(this, ::Label, this::setUpControl)

    override var text: String = ""

    override fun createControl(parent: Composite, refs: MutableControlReferences?) = factory.createControl(parent, refs)

    fun setUpControl(control: Label) {
        control.text = text
    }
}