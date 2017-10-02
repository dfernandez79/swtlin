package swtlin

import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Control
import swtlin.core.*

fun button(block: ButtonDescription.() -> Unit = {}) = builder(::ButtonBuilder, block)

fun ControlBuilderContainer.button(id: String? = null,
                                   block: ButtonDescription.() -> Unit = {}) =
        builder(::ButtonBuilder, block, id, this)

interface ButtonDescription : ControlDescription<Button> {
    var text: String

    fun onSelection(block: (SelectionEvent, Button, ControlReferences) -> Unit)
    fun onSelectionDo(block: () -> Unit) = onSelection({_, _, _ -> block()})
}

class ButtonBuilder : AbstractControlBuilder<Button>(::Button), ButtonDescription {
    override var text: String = ""

    private val selectionBlocks = mutableListOf<((evt: SelectionEvent, Button, ControlReferences) -> Unit)>()

    override fun setUpControl(control: Button, refs: MutableControlReferences?) {
        control.text = text

        addSelectionListeners(control, refs.orEmpty())
    }

    private fun addSelectionListeners(control: Button, refs: ControlReferences) {
        for (block in selectionBlocks) {
            control.addSelectionListener(object : SelectionAdapter() {
                override fun widgetSelected(e: SelectionEvent) {
                    block(e, control, refs)
                }
            })
        }
    }

    override fun onSelection(block: (SelectionEvent, Button, ControlReferences) -> Unit) {
        selectionBlocks.add(block)
    }
}