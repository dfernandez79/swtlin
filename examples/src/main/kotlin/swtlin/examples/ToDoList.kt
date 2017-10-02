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
            }

            button("add") {
                text = "Add"
                top = 0
                right = 0

                onSelection { _, _, refs ->
                    val table = refs["todoList"] as Table
                    val input = refs["todoInput"] as Text
                    val text = input.text.orEmpty()

                    if (text.isNotEmpty()) {
                        TableItem(table, SWT.NONE).text = text
                    }
                }
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