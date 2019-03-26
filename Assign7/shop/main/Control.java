package shop.main;

import shop.data.Inventory;
import shop.main.UIMenuActionEnums.UIMenuActionEnumConstructor;
import shop.ui.UI;
import shop.ui.UIError;
import shop.ui.UIFactory;
import shop.ui.UIFormBuilderInterface;
import shop.ui.UIFormMenuInterface;
import shop.ui.UIMenuBuilderInterface;

class Control {
	private UIFormMenuInterface[] _menus;
	private UIFormMenuInterface _getVideoForm;
	private UIFormTestEnums _numberTest;
	private UIFormTestEnums _stringTest;
	private States _state;
	private Inventory _inventory;
	private UI _ui;
	private static UIFactory uiFactory = new UIFactory();

	Control(Inventory inventory, UI ui) {
		_inventory = inventory;
		_ui = ui;
		_menus = new UIFormMenuInterface[States.NUMSTATES.getValue()];
		_state = States.START;
		addSTART(States.START);
		addEXIT(States.EXIT);
		UIFormTestEnums yearTest = UIFormTestEnums.YEARTEST;
		_numberTest = UIFormTestEnums.NUMBERTEST;
		_stringTest = UIFormTestEnums.STRINGTEST;
		UIFormBuilderInterface f = (UIFormBuilderInterface) uiFactory.start("UIFB", null, null);
		f.add("Title", _stringTest.getUIFT());
		f.add("Year", yearTest.getUIFT());
		f.add("Director", _stringTest.getUIFT());
		_getVideoForm = f.toUIForm("Enter Video");
		new UIMenuActionEnumConstructor(_ui, _inventory, _getVideoForm, _state);

	}

	void run() {
		try {
			while (_state != States.EXITED) {
				_ui.processMenu(_menus[_state.getValue()]);
				_state = UIMenuActionEnums.getState();
			}
		} catch (UIError e) {
			_ui.displayError("UI closed");
		}
	}

	private void addSTART(States start) {
		UIMenuBuilderInterface m = (UIMenuBuilderInterface) uiFactory.start("UIMB", null, null);

		m.add("Default", UIMenuActionEnums.DEFAULT.getUIMA());
		m.add("Add/Remove copies of a video", UIMenuActionEnums.ADDREMOVE.getUIMA());
		m.add("Check in a video", UIMenuActionEnums.CHECKIN.getUIMA());
		m.add("Check out a video", UIMenuActionEnums.CHECKOUT.getUIMA());
		m.add("Print the inventory", UIMenuActionEnums.PRINT.getUIMA());
		m.add("Clear the inventory", UIMenuActionEnums.CLEAR.getUIMA());
		m.add("Undo", UIMenuActionEnums.UNDO.getUIMA());
		m.add("Redo", UIMenuActionEnums.REDO.getUIMA());
		m.add("Print top ten all time rentals in order", UIMenuActionEnums.TOPTEN.getUIMA());
		m.add("Exit", UIMenuActionEnums.EXIT.getUIMA());
		m.add("Initialize with bogus contents", UIMenuActionEnums.BOGUS.getUIMA());
		_menus[start.getValue()] = m.toUIMenu("Bob's Video");
	}

	private void addEXIT(States start) {
		UIMenuBuilderInterface m = (UIMenuBuilderInterface) uiFactory.start("UIMB", null, null);

		m.add("Default", UIMenuActionEnums.DEFAULT.getUIMA());
		m.add("Yes", UIMenuActionEnums.YES.getUIMA());
		m.add("No", UIMenuActionEnums.NO.getUIMA());

		_menus[start.getValue()] = m.toUIMenu("Are you sure you want to exit?");
	}
}
