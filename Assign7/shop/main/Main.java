package shop.main;

import shop.data.Data;
import shop.ui.UI;
import shop.ui.UIFactory;

public class Main {
	private Main() {
	}

	public static void main(String[] args) {
		UIFactory uiFactory = new UIFactory();
		Control control = new Control(Data.newInventory(), (UI) uiFactory.start("text", null, null));
		control.run();
	}
}
