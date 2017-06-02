package swtlin

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

interface ControlFactory<out C : Control> {
    fun createControl(parent: Composite, refs: MutableControlReferences? = null): C
}

class GenericControlFactory<out C : Control>(
        private val description: ControlDescription<C>,
        private val controlConstructor: ControlConstructor<C>,
        private val setUpControl: (control: C, refs: MutableControlReferences?) -> Unit = { _, _ -> }) : ControlFactory<C> {

    constructor(description: ControlDescription<C>,
                controlConstructor: ControlConstructor<C>,
                setUpControl: (control: C) -> Unit)
            : this(description, controlConstructor, { c, _ -> setUpControl(c) })

    override fun createControl(parent: Composite, refs: MutableControlReferences?): C {
        val control = controlConstructor(parent, description.style)
        control.background = description.background?.createColor()
        setUpControl(control, refs)
        applySetupBlocks(control, refs)
        return control
    }

    private fun applySetupBlocks(control: C, refs: MutableControlReferences?) {
        val safeRefs = refs.orEmpty()
        for (block in description.setUpBlocks) {
            block(control, safeRefs)
        }
    }
}