package swtlin

import org.eclipse.swt.widgets.Control

fun <C : Control, D : ControlDescription<C>, B : ControlBuilder<C>>
        builder(builderConstructor: () -> B, block: D.() -> Unit,
                id: String? = null, container: ControlBuilderContainer? = null): B {

    val controlBuilder = builderConstructor()

    @Suppress("UNCHECKED_CAST")
    (controlBuilder as D).block()
    container?.add(id, controlBuilder)

    return controlBuilder
}

interface ControlBuilder<C : Control> : ControlDescription<C>, ControlFactory<C>
