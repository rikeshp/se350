package shop.main;

import shop.ui.UIFormTest;

enum UIFormTestEnums {
	NUMBERTEST(new UIFormTest() {
		public boolean run(String input) {
			try {
				Integer.parseInt(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

	}), STRINGTEST(new UIFormTest() {
		public boolean run(String input) {
			return !"".equals(input.trim());
		}
	}), YEARTEST(new UIFormTest() {
		public boolean run(String input) {
			try {
				int i = Integer.parseInt(input);
				return i > 1800 && i < 5000;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	});

	private final UIFormTest UIFT;

	private UIFormTestEnums(UIFormTest UIFT) {
		this.UIFT = UIFT;
	}

	public UIFormTest getUIFT() {
		return UIFT;
	}
}
