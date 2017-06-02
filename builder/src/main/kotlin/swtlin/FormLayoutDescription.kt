package swtlin

import org.eclipse.swt.layout.FormAttachment
import org.eclipse.swt.layout.FormData
import org.eclipse.swt.layout.FormLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

class FormLayoutDescription : LayoutDescription {
    override fun layout(parent: Composite, pairs: List<Pair<ControlDescription<*>, Control>>, refs: Map<String, Control>) {
        parent.layout = FormLayout()

        for ((description, control) in pairs) {
            layoutControl(description, control, refs)
        }
    }

    private fun layoutControl(description: ControlDescription<*>, control: Control,
                              refs: Map<String, Control>) {
        val formData = FormData()
        control.layoutData = formData

        createAttachment(description.layoutData["top"], refs)?.let { formData.top = it }
        createAttachment(description.layoutData["left"], refs)?.let { formData.left = it }
        createAttachment(description.layoutData["right"], refs, true)?.let { formData.right = it }
        createAttachment(description.layoutData["bottom"], refs, true)?.let { formData.bottom = it }
    }

    private fun createAttachment(value: Any?, refs: Map<String, Control>, negate: Boolean = false): FormAttachment? {
        val sign = if (negate) -1 else 1

        return when (value) {
            is Int -> FormAttachment(if (negate) 100 else 0, sign * value)
            else -> null
        }
    }
}