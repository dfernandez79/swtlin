package swtlin.core

import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Control

typealias ControlConstructor<C> = (Composite, Int) -> C

typealias MutableControlReferences = MutableMap<String, Control>
typealias ControlReferences = Map<String, Control>