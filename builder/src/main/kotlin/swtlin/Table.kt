package swtlin

import org.eclipse.swt.widgets.Table

fun table(block: TableDescription.() -> Unit = {}) = builder(::TableBuilder, block)

fun ControlBuilderContainer.table(id: String? = null, block: TableDescription.() -> Unit = {}) =
        builder(::TableBuilder, block, id, this)

interface TableDescription : ControlDescription<Table>

class TableBuilder : AbstractControlBuilder<Table>(::Table), TableDescription {
    override fun setUpControl(control: Table, refs: MutableControlReferences?) {
    }
}