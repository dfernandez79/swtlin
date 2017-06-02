package swtlin

import org.eclipse.swt.widgets.Control

interface ControlBuilder<C : Control> : ControlDescription<C>, ControlFactory<C>
