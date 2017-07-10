package swtlin

import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Shell
import swtlin.core.ControlBuilder
import swtlin.core.ControlBuilderContainer
import swtlin.core.IdentifiedControlBuilderContainer

fun Shell.children(block: ShellChildrenDescription.() -> Unit): Shell {
    val description = ShellChildrenDescription(this)
    description.block()
    description.createChildren()
    return this
}

class ShellChildrenDescription(private val shell: Shell) : ControlBuilderContainer {
    private val container = IdentifiedControlBuilderContainer()

    private var _layout: LayoutDescription? = null
    var layout: LayoutDescription
        set (value) {
            _layout = value
        }
        get() {
            if (_layout != null) {
                return _layout!!
            } else if (shell.layout != null) {
                return shell.layout.toLayoutDescription()
            } else {
                return NullLayoutDescription
            }
        }

    override fun add(id: String?, builder: ControlBuilder<*>) {
        container.add(id, builder)
    }

    fun createChildren() {
        val refs = mutableMapOf<String, Control>()
        val pairs = container.children.map { it.builder to it.createControl(shell, refs) }
        layout.layout(shell, pairs, refs)
    }
}
