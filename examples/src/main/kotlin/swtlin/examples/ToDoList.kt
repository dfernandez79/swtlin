package swtlin.examples

import org.eclipse.swt.widgets.Shell
import swtlin.*

fun main(args: Array<String>) {
    example {
        val shell = Shell()
        shell.setSize(300, 200)

        shell.children {
            layout = formLayout(5, 5)

            text("todoInput") {
                left = 0
                top = 0
                right = 10.fromLeftOf("add")
            }

            button("add") {
                text = "Add"
                top = 0
                right = 0
            }

            table {
                top = 10.fromBottomOf("todoInput")
                left = 0
                right = 0
                bottom = 0
            }
        }
    }
}