package swtlin

import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ShellTest {
    private fun withShellDo(block: (Shell) -> Unit) {
        val shell = Shell()
        shell.children
        try {
            block(shell)
        } finally {
            shell.dispose()
        }
    }

    @Test
    fun `Shell children extension`() {
        withShellDo {
            it.children {
                label {
                    text = "Hello"
                }
            }

            assertTrue(it.children[0] is Label)
            assertEquals((it.children[0] as Label).text, "Hello")
        }
    }

    @Test
    fun `Preserve existing layout`() {
        val initial = GridLayout()

        withShellDo {
            it.layout = initial
            it.children {
                label()
            }

            assertEquals(it.layout, initial)
        }
    }

    @Test
    fun `Set layout description`() {
        withShellDo {
            it.children {
                layout = fillLayout()
                label()
            }

            assertTrue(it.layout is FillLayout)
        }
    }
}