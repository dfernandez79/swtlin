package swtlin

import org.eclipse.swt.SWT
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.FormData
import org.eclipse.swt.layout.FormLayout
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class CompositeTest {
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
    fun `Create empty Composite`() {
        val result = composite().createControl(shell!!)

        assertNotNull(result)
        assertEquals(0, result.children.size)
    }

    @Test
    fun `Create Composite with a Label`() {
        val result = composite {
            label {
                text = "Hello World!"
            }
        }.createControl(shell!!)

        assertEquals(1, result.children.size.toLong())
        assertTrue(result.children[0] is Label)
        assertEquals("Hello World!", (result.children[0] as Label).text)
    }

    @Test
    fun `Create Composite with style`() {
        val result = composite {
            style = SWT.NO_FOCUS
        }.createControl(shell!!)
        assertTrue(result.style and SWT.NO_FOCUS == SWT.NO_FOCUS)
    }

    @Test
    fun `Create Label with id`() {
        val refs = mutableMapOf<String, Control>()

        composite {
            label("id") {
                text = "Hello"
            }
        }.createControl(shell!!, refs)

        assertTrue(refs["id"] is Label)
        assertEquals((refs["id"] as Label).text, "Hello")
    }

    @Test
    fun `Uses FormLayout by default`() {
        val result = composite().createControl(shell!!)
        assertTrue(result.layout is FormLayout)
    }

    @Test
    fun `Apply layout to children`() {
        val refs = mutableMapOf<String, Control>()

        composite {
            label("test") {
                left = 5
                top = 5
            }
        }.createControl(shell!!, refs)

        val testLabel = refs["test"] as Label

        assertTrue(testLabel.layoutData is FormData)

        val formData = testLabel.layoutData as FormData
        assertEquals(5, formData.left.offset)
        assertEquals(5, formData.top.offset)
    }

    @Test
    fun `Nested composite`() {
        val refs = mutableMapOf<String, Control>()

        composite {
            label("test1") { text = "first" }
            composite {
                label("test2") { text = "second" }
            }
        }.createControl(shell!!, refs)

        assertTrue(refs["test1"] is Label)
        assertEquals("first", (refs["test1"] as Label).text)
        assertTrue(refs["test2"] is Label)
        assertEquals("second", (refs["test2"] as Label).text)
    }

    @Test
    fun `Nested composite with id`() {
        val refs = mutableMapOf<String, Control>()

        composite {
            label("test1") { text = "first" }
            composite("comp") {
                label("test2") { text = "second" }
            }
        }.createControl(shell!!, refs)

        assertTrue(refs["test1"] is Label)
        assertEquals("first", (refs["test1"] as Label).text)
        assertTrue(refs["test2"] is Label)
        assertEquals("second", (refs["test2"] as Label).text)
        assertTrue(refs["comp"] is Composite)
        assertEquals((refs["test2"] as Control).parent, refs["comp"])
    }

    @Test
    fun `setUp block is executed after creation`() {
        var ref: Composite? = null

        val result = composite().also {
            it.setUp { c -> ref = c }
        }.createControl(shell!!)

        assertEquals(result, ref)
    }

    @Test
    fun `Uses FillLayout`() {
        val result = composite {
            layout = fillLayout()
        }.createControl(shell!!)

        assertTrue(result.layout is FillLayout)
    }
}
