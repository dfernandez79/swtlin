package swtlin

import org.eclipse.swt.SWT
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
    var layoutData: MutableMap<String, Any>
    var left: Any?
    var top: Any?
    var background: ColorDescription?
    val setUpBlocks: Iterable<(C, refs: ControlReferences) -> Unit>

    fun setUp(block: (C) -> Unit)
    fun setUp(block: (C, ControlReferences) -> Unit)
}

abstract class AbstractControlDescription<C : Control> : ControlDescription<C> {
    override var style: Int = SWT.NONE
    override var layoutData: MutableMap<String, Any> = mutableMapOf()

    override var left: Any?
        get() = layoutData["left"]
        set(value) {
            layoutData.putOrRemove("left", value)
        }

    override var top: Any?
        get() = layoutData["top"]
        set(value) {
            layoutData.putOrRemove("top", value)
        }

    override var background: ColorDescription? = null

    override val setUpBlocks = mutableListOf<(C, refs: ControlReferences) -> Unit>()


    override fun setUp(block: (C) -> Unit) {
        setUp({ c, _ -> block(c) })
    }

    override fun setUp(block: (C, ControlReferences) -> Unit) {
        setUpBlocks.add(block)
    }
}

private fun MutableMap<String, Any>.putOrRemove(key: String, value: Any?) {
    if (value != null) {
        this[key] = value
    } else {
        this.remove(key)
    }
}