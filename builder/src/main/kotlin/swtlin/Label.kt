package swtlin

import org.eclipse.swt.widgets.Label

fun label(block: LabelDescription.() -> Unit = {}) = builder(::LabelBuilder, block)

fun ControlBuilderContainer.label(id: String? = null, block: LabelDescription.() -> Unit = {}) =
        builder(::LabelBuilder, block, id, this)

interface LabelDescription : ControlDescription<Label> {
    var text: String
}

class LabelBuilder : AbstractControlBuilder<Label>(::Label), LabelDescription {
    override var text: String = ""

    override fun setUpControl(control: Label, refs: MutableControlReferences?) {
        control.text = text
    }
}