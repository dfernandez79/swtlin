package swtlin

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FormAttachment
import org.eclipse.swt.layout.FormData
import org.eclipse.swt.layout.FormLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

fun formLayout(marginLeft: Int = 0, marginTop: Int = 0,
               marginRight: Int = marginLeft, marginBottom: Int = marginTop) =
        FormLayoutDescription(marginLeft, marginTop, marginRight, marginBottom)

class ControlAttachment(private val id: String, private val offset: Int, private val alignment: Int) {
    fun createFormAttachment(refs: ControlReferences) = FormAttachment(refs[id], offset, alignment)
}

fun Int.fromLeftOf(id: String) = ControlAttachment(id, -this, SWT.LEFT)

fun Int.fromBottomOf(id: String) = ControlAttachment(id, this, SWT.BOTTOM)

class FormLayoutDescription(val marginLeft: Int = 0,
                            val marginTop: Int = 0,
                            val marginRight: Int = 0,
                            val marginBottom: Int = 0) : LayoutDescription {
    override fun layout(parent: Composite, pairs: List<Pair<ControlDescription<*>, Control>>,
                        refs: Map<String, Control>) {
        val formLayout = FormLayout()
        parent.layout = formLayout
        formLayout.marginLeft = marginLeft
        formLayout.marginTop = marginTop
        formLayout.marginRight = marginRight
        formLayout.marginBottom = marginBottom

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

    private fun createAttachment(value: Any?, refs: Map<String, Control>, negate: Boolean = false): FormAttachment? =
            when (value) {
                is Int -> FormAttachment(if (negate) 100 else 0, (if (negate) -1 else 1) * value)
                is ControlAttachment -> value.createFormAttachment(refs)
                else -> null
            }
}