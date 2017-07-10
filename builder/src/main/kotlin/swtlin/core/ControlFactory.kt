package swtlin.core

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

/**
 * Objects implementing this interface can create instances of [org.eclipse.swt.widgets.Control].
 */
interface ControlFactory<out C : Control> {
    /**
     * Returns an instance of [org.eclipse.swt.widgets.Control].
     *
     * Optionally it can receive a map to store pairs of id / control instance.
     * The map can be used to access specific controls of a composite.
     *
     * @param parent parent of the control.
     * @param refs optional map to store id / control instance pairs.
     */
    fun createControl(parent: Composite, refs: MutableControlReferences? = null): C
}