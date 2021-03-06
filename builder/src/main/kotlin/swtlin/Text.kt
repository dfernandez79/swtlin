package swtlin

import org.eclipse.swt.SWT
import org.eclipse.swt.events.KeyEvent
import org.eclipse.swt.widgets.Text
import swtlin.core.*

fun text(block: TextDescription.() -> Unit = {}) = builder(::TextBuilder, block)

fun ControlBuilderContainer.text(id: String? = null, block: TextDescription.() -> Unit = {}) =
        builder(::TextBuilder, block, id, this)

interface TextDescription : ControlDescription<Text> {
    var text: String

    fun onKeyPressed(block: (KeyEvent, Text, ControlReferences) -> Unit)
}

class TextBuilder : AbstractControlBuilder<Text>(::Text), TextDescription {
    override var text: String = ""

    init {
        style = SWT.BORDER
    }

    override fun setUpControl(control: Text, refs: MutableControlReferences?) {
        control.text = text
    }

    override fun onKeyPressed(block: (KeyEvent, Text, ControlReferences) -> Unit) {

    }
}