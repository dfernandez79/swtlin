package swtlin.examples

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Shell
import swtlin.*

fun main(args: Array<String>) {
    example {
        val shell = Shell()
        var ref: Button? = null

        shell.setSize(300, 200)

        shell.children {
            layout = formLayout(5, 5)

            text("todoInput") {
                left = 0
                top = 0
                right = 10 fromLeftOf "add"
            }

            button("add") {
                text = "Add"
                top = 0
                right = 0
                background = systemColor(SWT.COLOR_RED)

                setUp { btn -> ref = btn }
            }

            table {
                top = 10 fromBottomOf "todoInput"
                left = 0
                right = 0
                bottom = 0
            }
        }
        shell.layout()

        if (ref != null) {
            println(ref?.borderWidth)
            println(ref?.bounds)
        }

        shell
    }
}