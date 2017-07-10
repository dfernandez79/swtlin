package swtlin

import org.eclipse.swt.graphics.Color
import org.eclipse.swt.graphics.Resource
import org.eclipse.swt.widgets.Display

class ResourceDisposeHandler<out R : Resource>(val resource: R, private val disposeHandler: (R) -> Unit) {
    fun dispose() {
        disposeHandler(resource)
    }
}

class ResourceFactory<out R : Resource>(private val factory: (Display) -> R, private val disposeHandler: (R) -> Unit) {
    fun create(display: Display?) =
            ResourceDisposeHandler(factory(display ?: Display.getDefault()), disposeHandler)
}

fun systemColor(value: Int): ResourceFactory<Color> = ResourceFactory({ display -> display.getSystemColor(value) }, {})

