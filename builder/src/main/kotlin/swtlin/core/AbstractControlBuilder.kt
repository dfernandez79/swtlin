package swtlin.core

import org.eclipse.swt.SWT
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import swtlin.ResourceFactory

/**
 * Base class to implement a [ControlBuilder].
 *
 * @param controlConstructor a factory function to create a control instance,
 * usually a reference to a specific [Control] constructor, ie `::Button`.
 */
abstract class AbstractControlBuilder<C : Control>(
        private val controlConstructor: ControlConstructor<C>) : ControlBuilder<C> {

    override var style = SWT.NONE
    override final val layoutData = mutableMapOf<String, Any?>()
    override var left: Any? by layoutData
    override var top: Any? by layoutData
    override var right: Any? by layoutData
    override var bottom: Any? by layoutData
    override var background: ResourceFactory<Color>? = null
    override val setUpBlocks = mutableListOf<(C, refs: ControlReferences) -> Unit>()

    override fun setUp(block: (C, ControlReferences) -> Unit) {
        setUpBlocks.add(block)
    }

    override fun createControl(parent: Composite, refs: MutableControlReferences?): C {
        val control = controlConstructor(parent, style)
        control.background = background?.create(parent.display)?.resource
        setUpControl(control, refs)
        applySetupBlocks(control, refs.orEmpty())
        return control
    }

    abstract fun setUpControl(control: C, refs: MutableControlReferences?)

    private fun applySetupBlocks(control: C, refs: ControlReferences) {
        for (block in setUpBlocks) {
            block(control, refs)
        }
    }
}