package shop.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import javax.swing.JOptionPane;

final class TextUI implements UI, UIFactoryInterface {
	final BufferedReader _in;
	final PrintStream _out;

	TextUI() {
		_in = new BufferedReader(new InputStreamReader(System.in));
		_out = System.out;
	}

	public void displayMessage(String message) {
		_out.println(message);
	}

	public void displayError(String message) {
		_out.println(message);
	}

	private String getResponse() {
		String result;
		try {
			result = _in.readLine();
		} catch (IOException e) {
			throw new UIError(); // re-throw UIError
		}
		if (result == null) {
			throw new UIError(); // input closed
		}
		return result;
	}

	public void processMenu(UIFormMenuInterface menu) {
		_out.println(menu.getHeading());
		_out.println("Enter choice by number:");

		for (int i = 1; i < menu.size(); i++) {
			_out.println("  " + i + ". " + menu.getPrompt(i));
		}

		String response = getResponse();
		if (response == null)
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
		_out.flush();
		int i = 0;
		while (i < form.size()) {
			_out.print(form.getPrompt(i) + ": ");
			_out.flush();
			String response = getResponse();
			if (!form.checkInput(i, response)) {
				displayError("Invalid Input.  Try again.");
			} else {
				commands[i] = response;
				i++;
			}
		}
		return commands;
	}

}
