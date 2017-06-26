package swtlin

import org.eclipse.swt.widgets.Text

fun text(block: TextDescription.() -> Unit = {}) = builder(::TextBuilder, block)

fun ControlBuilderContainer.text(id: String? = null, block: TextDescription.() -> Unit = {}) =
        builder(::TextBuilder, block, id, this)

interface TextDescription : ControlDescription<Text> {
    var text: String
}

class TextBuilder : AbstractControlBuilder<Text>(::Text), TextDescription {
    override var text: String = ""

    override fun setUpControl(control: Text, refs: MutableControlReferences?) {
        control.text = text
    }
}