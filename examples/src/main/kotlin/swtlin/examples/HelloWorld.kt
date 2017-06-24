package swtlin.examples

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Shell
import swtlin.children
import swtlin.color
import swtlin.fillLayout
import swtlin.label

fun main(args: Array<String>) {
    example {
        val shell = Shell()
        shell.setSize(300, 200)

        shell.children {
            layout = fillLayout()
            label {
                text = "Hello World!"
                style = SWT.CENTER
                background = color(SWT.COLOR_CYAN)
            }
        }
    }
}