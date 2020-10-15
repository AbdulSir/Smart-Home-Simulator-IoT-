package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import view.SHSGui;

public class SHSController {
	private SHSGui frame;
	private Console console;

	public SHSController(SHSGui frame) {
		// main ui
		this.frame = frame;
		createEvents();

		// control console
		this.console = new Console(frame.getTextAreaConsoleLog());
		console.msg("Welcome to the Smart Home Simulator");

	}

	//////////////////////////////////////////////////////////////
	// This method contains all of the code for creating events
	//////////////////////////////////////////////////////////////
	private void createEvents() {

		// Toggle Button State Change
		this.frame.getTogglebuttonSimulator().addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int state = arg0.getStateChange();

				if (state == ItemEvent.SELECTED)
					console.msg("Simulator ON");
				else if (state == ItemEvent.DESELECTED)
					console.msg("Simulator OFF");
			}
		});
	}
}