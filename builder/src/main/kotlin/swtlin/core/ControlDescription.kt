package swtlin.core

import org.eclipse.swt.graphics.Color
import org.eclipse.swt.widgets.Control
import swtlin.ResourceFactory
import swtlin.systemColor

/**
 * Base interface to describe the properties of a [org.eclipse.swt.widgets.Control].
 *
 * Specific controls will extend this interface to provide additional properties.
 */
interface ControlDescription<C : Control> {
    var style: Int
    val layoutData: MutableMap<String, Any?>
    var left: Any?
    var top: Any?
    var right: Any?
    var bottom: Any?
    var background: ResourceFactory<Color>?
    val setUpBlocks: Iterable<(C, ControlReferences) -> Unit>

    /**
     * Adds a function that will be executed right after the control instance is created.
     *
     * @param block a function that receives the control instance and a map of control
     * references in the same parent.
     */
    fun setUp(block: (C, ControlReferences) -> Unit)

    /**
     * Adds a function that will be executed right after the control instance is created.
     *
     * @param block a function that receives the control instance.
     */
    fun setUp(block: (C) -> Unit) = setUp({ control, _ -> block(control) })
}