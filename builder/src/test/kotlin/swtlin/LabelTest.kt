package swtlin

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell
import org.junit.After
import org.junit.Before
import org.junit.Test
import swtlin.core.MutableControlReferences
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class LabelTest {
    var shell: Shell? = null

    @Before
    fun createShell() {
        shell = Shell()
    }

    @After
    fun disposeShell() {
        shell?.dispose()
    }

    @Test
    fun `Create label with text`() {
        val result = label {
            text = "Hello World!"
        }.createControl(shell!!)

        assertNotNull(result)
        assertEquals("Hello World!", result.text)
    }

    @Test
    fun `Create label with style`() {
        val result = label {
            style = SWT.CENTER
        }.createControl(shell!!)

        assertNotNull(result)
        assertTrue((result.style and SWT.CENTER) == SWT.CENTER)
    }

    @Test
    fun `Execute setUp block after creation`() {
        var ref: String? = null

        label {
            text = "Test"
            setUp { lbl -> ref = lbl.text }
        }.createControl(shell!!)

        assertEquals("Test", ref)
    }

    @Test
    fun `Execute setUp with references`() {
        var ref: Control? = null
        val testControl = Label(shell!!, SWT.NONE)
        val refs: MutableControlReferences = mutableMapOf("test" to testControl)

        label {
            text = "Test"
            setUp { _, refs -> ref = refs["test"] }
        }.createControl(shell!!, refs)

        assertEquals(testControl, ref)
    }

    @Test
    fun `Set properties without closure`() {
        val lbl = label()
        lbl.text = "Hello World!"
        val result = lbl.createControl(shell!!)

        assertEquals("Hello World!", result.text)
    }
}
