package swtlin

import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

interface LayoutDescription {
    fun layout(parent: Composite, pairs: List<Pair<ControlDescription<*>, Control>>, refs: Map<String, Control>)
}

fun fillLayout() = FillLayoutDescription()

class FillLayoutDescription : LayoutDescription {
    override fun layout(parent: Composite, pairs: List<Pair<ControlDescription<*>, Control>>, refs: Map<String, Control>) {
        parent.layout = FillLayout()
    }
}