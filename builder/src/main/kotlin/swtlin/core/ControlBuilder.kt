package swtlin.core

import org.eclipse.swt.widgets.Control

/**
 * Combines the [ControlDescription] and [ControlFactory] interfaces.
 *
 * @see AbstractControlBuilder
 */
interface ControlBuilder<C : Control> : ControlDescription<C>, ControlFactory<C>
