package shop.ui;

import javax.swing.JOptionPane;
//import java.io.IOException;

final class PopupUI implements UI, UIFactoryInterface {
	PopupUI() {
	}

	public void displayMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void displayError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public void processMenu(UIFormMenuInterface menu) {
		StringBuffer b = new StringBuffer();
		b.append(menu.getHeading());
		b.append("\n");
		b.append("Enter choice by number:");
		b.append("\n");

		for (int i = 1; i < menu.size(); i++) {
			b.append("  " + i + ". " + menu.getPrompt(i));
			b.append("\n");
		}

		String response = JOptionPane.showInputDialog(b.toString());
		
		if(response == null)
			System.exit(0);
		
		int selection;
		try {
			selection = Integer.parseInt(response, 10);
			if ((selection < 0) || (selection >= menu.size()))
				selection = 0;
		} catch (NumberFormatException e) {
			selection = 0;
		}

		menu.runAction(selection);
	}

	public String[] processForm(UIFormMenuInterface form) {
		int size = form.size();
		String[] commands = new String[size];
		int i = 0;
		while (i < size) {
			String input = JOptionPane.showInputDialog(form.getPrompt(i));
			if(input == null) //Cancel button
				return commands;
			
			if(!form.checkInput(i, input)) { 
				displayError("Invalid, try again");
			}
			else {
				commands[i] = input;
				i++;
			}
		}
		return commands;
	}
}
