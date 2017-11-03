package swtlin.examples

import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Table
import org.eclipse.swt.widgets.TableItem
import org.eclipse.swt.widgets.Text
import swtlin.*

fun main(args: Array<String>) {
    example {
        Shell().children {

            size(300, 200)
            layout = formLayout(5, 5)

            text("todoInput") {
                left = 0
                top = 0
                right = 10 fromLeftOf "add"

                onKeyPressed { evt, _, _ ->
                    println(evt.keyCode)
                }
            }

            button("add") {
                text = "Add"
                top = 0
                right = 0

                onSelectionDoWithRefs { addItem(it["todoList"] as Table, it["todoInput"] as Text) }
            }

            table("todoList") {
                top = 10 fromBottomOf "todoInput"
                left = 0
                right = 0
                bottom = 0
            }

        }.apply {
            layout()
        }
    }
}

fun addItem(table: Table, input: Text) {
    val text = input.text.orEmpty()

    if (text.isNotEmpty()) {
        TableItem(table, SWT.NONE).text = text
    }

}
