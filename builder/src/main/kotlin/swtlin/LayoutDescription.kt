package swtlin

import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Layout

interface LayoutDescription {
    fun layout(parent: Composite, pairs: List<Pair<ControlDescription<*>, Control>>, refs: Map<String, Control>)
}

object NullLayoutDescription : LayoutDescription {
    override fun layout(parent: Composite, pairs: List<Pair<ControlDescription<*>, Control>>, refs: Map<String, Control>) {
    }
}

fun Layout.toLayoutDescription(): LayoutDescription = NullLayoutDescription

fun fillLayout() = FillLayoutDescription()

class FillLayoutDescription : LayoutDescription {
    override fun layout(parent: Composite, pairs: List<Pair<ControlDescription<*>, Control>>, refs: Map<String, Control>) {
        parent.layout = FillLayout()
    }
}