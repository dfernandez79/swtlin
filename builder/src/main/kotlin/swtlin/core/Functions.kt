package swtlin.core

import org.eclipse.swt.widgets.Control

/**
 * Helps to implement extensions of the DSL.
 *
 * This function calls the `block` to configure the [description of a control](ControlDescription),
 * and returns a [builder](ControlBuilder) that can be used to create a [Control] instance.
 *
 * @param builderConstructor function to create an instance of [ControlBuilder].
 * @param block a function passed by the user of the DSL to configure the control.
 * @param id optional name used to identify the control inside a container.
 * @param container optional container of the control to create.
 */
fun <C, D, B> builder(builderConstructor: () -> B,
                      block: D.() -> Unit,
                      id: String? = null,
                      container: ControlBuilderContainer? = null): B
        where C : Control, D : ControlDescription<C>, B : ControlBuilder<C> {

    val controlBuilder = builderConstructor()

    @Suppress("UNCHECKED_CAST")
    (controlBuilder as D).block()

    container?.add(id, controlBuilder)

    return controlBuilder
}