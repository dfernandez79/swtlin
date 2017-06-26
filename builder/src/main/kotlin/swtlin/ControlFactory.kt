package swtlin

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

interface ControlFactory<out C : Control> {
    fun createControl(parent: Composite, refs: MutableControlReferences? = null): C
}