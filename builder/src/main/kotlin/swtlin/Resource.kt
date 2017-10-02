package swtlin

import org.eclipse.swt.graphics.Color
import org.eclipse.swt.graphics.Resource
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Display

private val emptyHandler: (Any) -> Unit = {}

class ResourceDisposeHandler<out R : Resource>(val resource: R, private val disposeHandler: (R) -> Unit) {
    fun dispose() = disposeHandler(resource)

    fun disposeWith(control: Control) {
        if (disposeHandler != emptyHandler) {
            control.addDisposeListener({ _ -> dispose()})
        }
    }
}

class ResourceFactory<out R : Resource>(
        private val factory: (Display) -> R,
        private val disposeHandler: (R) -> Unit = emptyHandler) {

    fun create(display: Display = Display.getDefault()) = ResourceDisposeHandler(factory(display), disposeHandler)
}

fun systemColor(value: Int): ResourceFactory<Color> = ResourceFactory({ display -> display.getSystemColor(value) })

