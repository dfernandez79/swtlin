package swtlin

import org.eclipse.swt.widgets.Button
import swtlin.core.*

fun button(block: ButtonDescription.() -> Unit = {}) = builder(::ButtonBuilder, block)

fun ControlBuilderContainer.button(id: String? = null,
                                   block: ButtonDescription.() -> Unit = {}) =
        builder(::ButtonBuilder, block, id, this)

interface ButtonDescription : ControlDescription<Button> {
    var text: String
}

class ButtonBuilder : AbstractControlBuilder<Button>(::Button), ButtonDescription {
    override var text: String = ""

    override fun setUpControl(control: Button, refs: MutableControlReferences?) {
        control.text = text
    }
}