package swtlin

import org.eclipse.swt.graphics.Color
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Display

interface ColorDescription {
    fun createColor(): Color
}

fun color(value: Int): ColorDescription =
        object : ColorDescription {
            override fun createColor(): Color = Display.getDefault().getSystemColor(value)
        }

interface ControlDescription<C : Control> {
    var style: Int
    val layoutData: MutableMap<String, Any?>
    var left: Any?
    var top: Any?
    var right: Any?
    var bottom: Any?
    var background: ColorDescription?
    val setUpBlocks: Iterable<(C, refs: ControlReferences) -> Unit>

    fun setUp(block: (C) -> Unit)
    fun setUp(block: (C, ControlReferences) -> Unit)
}