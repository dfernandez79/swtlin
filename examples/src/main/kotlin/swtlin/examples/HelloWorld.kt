package swtlin.examples

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Shell
import swtlin.children
import swtlin.fillLayout
import swtlin.label
import swtlin.systemColor

fun main(args: Array<String>) {
    example {
        val shell = Shell()
        shell.setSize(300, 200)

        shell.children {
            layout = fillLayout()
            label {
                text = "Hello World!"
                style = SWT.CENTER
                background = systemColor(SWT.COLOR_CYAN)
            }
        }
    }
}