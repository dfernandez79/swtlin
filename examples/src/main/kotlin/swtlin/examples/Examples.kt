package swtlin.examples

import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.Shell

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