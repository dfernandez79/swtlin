package swtlin.examples

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell
import swtlin.color
import swtlin.composite
import swtlin.fillLayout
import swtlin.label

fun example(block: () -> Shell) {
    val shell = block()
    shell.open()

    val display = Display.getDefault()
    while (!shell.isDisposed) {
        if (!display.readAndDispatch()) {
            display.sleep()
        }
    }
}

fun main(args: Array<String>) {
    example {
        val shell = Shell()
        shell.setSize(300, 200)
        shell.layout = FillLayout()

        composite {
            layout = fillLayout()
            label {
                text = "Hello World!"
                style = SWT.CENTER
                background = color(SWT.COLOR_CYAN)
            }
        }.createControl(shell)

        shell
    }
}