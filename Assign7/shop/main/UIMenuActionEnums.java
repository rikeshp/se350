package shop.main;

import java.util.Iterator;

import shop.command.Command;
import shop.data.Data;
import shop.data.Inventory;
import shop.data.Record;
import shop.data.Video;
import shop.ui.UI;
import shop.ui.UIFactory;
import shop.ui.UIFormBuilderInterface;
import shop.ui.UIFormMenuInterface;
import shop.ui.UIMenuAction;

enum UIMenuActionEnums {
	DEFAULT(new UIMenuAction() {
		public void run() {
			_ui.displayError("doh!");
		}
	}), ADDREMOVE(new UIMenuAction() {
		public void run() {
			String[] result1 = _ui.processForm(_getVideoForm);
			boolean isNull = false;
			for (int x = 0; x < result1.length; x++) {
				if (result1[x] == null) {
					isNull = true;
					break;
				}
			}

			if (!isNull) {
				Video v = Data.newVideo(result1[0], Integer.parseInt(result1[1]), result1[2]);

				UIFormBuilderInterface f = (UIFormBuilderInterface) uiFactory.start("UIFB", null, null);
				f.add("Number of copies to add/remove", UIFormTestEnums.NUMBERTEST.getUIFT());
				String[] result2 = _ui.processForm(f.toUIForm(""));

				Command c = Data.newAddCmd(_inventory, v, Integer.parseInt(result2[0]));
				if (!c.run()) {
					_ui.displayError("Command failed");
				}
			}
		}
	}), CHECKIN(new UIMenuAction() {
		public void run() {
			String[] commands = _ui.processForm(_getVideoForm);
			boolean isNull = false;
			for (int x = 0; x < commands.length; x++) {
				if (commands[x] == null) {
					isNull = true;
					break;
				}
			}

			if (!isNull) {
				Video video = Data.newVideo(commands[0], Integer.valueOf(commands[1]), commands[2]);

				Command command = Data.newInCmd(_inventory, video);
				if (!command.run())
					_ui.displayError("Command failed");
			}
		}
	}), CHECKOUT(new UIMenuAction() {
		public void run() {
			// TODO
			String[] commands = _ui.processForm(_getVideoForm);
			boolean isNull = false;
			for (int x = 0; x < commands.length; x++) {
				if (commands[x] == null) {
					isNull = true;
					break;
				}
			}

			if (!isNull) {
				Video video = Data.newVideo(commands[0], Integer.valueOf(commands[1]), commands[2]);

				Command command = Data.newOutCmd(_inventory, video);
				if (!command.run())
					_ui.displayError("Command failed");
			}
		}
	}), PRINT(new UIMenuAction() {
		public void run() {
			_ui.displayMessage(_inventory.toString());
		}
	}), CLEAR(new UIMenuAction() {
		public void run() {
			if (!Data.newClearCmd(_inventory).run()) {
				_ui.displayError("Command failed");
			}
		}
	}), UNDO(new UIMenuAction() {
		public void run() {
			if (!Data.newUndoCmd(_inventory).run()) {
				_ui.displayError("Command failed");
			}
		}
	}), REDO(new UIMenuAction() {
		public void run() {
			if (!Data.newRedoCmd(_inventory).run()) {
				_ui.displayError("Command failed");
			}
		}
	}), TOPTEN(new UIMenuAction() {
		public void run() {
			Iterator<Record> iter = _inventory.iterator((r1, r2) -> r2.numRentals() - r1.numRentals());
			String result = "";
			int count = 0;
			while (iter.hasNext() && count < 10) {
				Record record = iter.next();
				result += record.toString();
				result += "\n";
				count++;
			}
			_ui.displayMessage(result);
		}
	}), EXIT(new UIMenuAction() {
		public void run() {
			_state = States.EXIT;
		}
	}), BOGUS(new UIMenuAction() {
		public void run() {
			Data.newAddCmd(_inventory, Data.newVideo("a", 2000, "m"), 1).run();
			Data.newAddCmd(_inventory, Data.newVideo("b", 2000, "m"), 2).run();
			Data.newAddCmd(_inventory, Data.newVideo("c", 2000, "m"), 3).run();
			Data.newAddCmd(_inventory, Data.newVideo("d", 2000, "m"), 4).run();
			Data.newAddCmd(_inventory, Data.newVideo("e", 2000, "m"), 5).run();
			Data.newAddCmd(_inventory, Data.newVideo("f", 2000, "m"), 6).run();
			Data.newAddCmd(_inventory, Data.newVideo("g", 2000, "m"), 7).run();
			Data.newAddCmd(_inventory, Data.newVideo("h", 2000, "m"), 8).run();
			Data.newAddCmd(_inventory, Data.newVideo("i", 2000, "m"), 9).run();
			Data.newAddCmd(_inventory, Data.newVideo("j", 2000, "m"), 10).run();
			Data.newAddCmd(_inventory, Data.newVideo("k", 2000, "m"), 11).run();
			Data.newAddCmd(_inventory, Data.newVideo("l", 2000, "m"), 12).run();
			Data.newAddCmd(_inventory, Data.newVideo("m", 2000, "m"), 13).run();
			Data.newAddCmd(_inventory, Data.newVideo("n", 2000, "m"), 14).run();
			Data.newAddCmd(_inventory, Data.newVideo("o", 2000, "m"), 15).run();
			Data.newAddCmd(_inventory, Data.newVideo("p", 2000, "m"), 16).run();
			Data.newAddCmd(_inventory, Data.newVideo("q", 2000, "m"), 17).run();
			Data.newAddCmd(_inventory, Data.newVideo("r", 2000, "m"), 18).run();
			Data.newAddCmd(_inventory, Data.newVideo("s", 2000, "m"), 19).run();
			Data.newAddCmd(_inventory, Data.newVideo("t", 2000, "m"), 20).run();
			Data.newAddCmd(_inventory, Data.newVideo("u", 2000, "m"), 21).run();
			Data.newAddCmd(_inventory, Data.newVideo("v", 2000, "m"), 22).run();
			Data.newAddCmd(_inventory, Data.newVideo("w", 2000, "m"), 23).run();
			Data.newAddCmd(_inventory, Data.newVideo("x", 2000, "m"), 24).run();
			Data.newAddCmd(_inventory, Data.newVideo("y", 2000, "m"), 25).run();
			Data.newAddCmd(_inventory, Data.newVideo("z", 2000, "m"), 26).run();
		}
	}), YES(new UIMenuAction() {
		public void run() {
			_state = States.EXITED;
		}
	}), NO(new UIMenuAction() {
		public void run() {
			_state = States.START;
		}
	});

	private final UIMenuAction action;
	private static UI _ui;
	private static Inventory _inventory;
	private static UIFormMenuInterface _getVideoForm;
	private static States _state;

	private static UIFactory uiFactory = new UIFactory();
	public void setUi(UI ui) {
		_ui = ui;
	}

	public void setInventory(Inventory inv) {
		_inventory = inv;
	}

	public void setGetvideoform(UIFormMenuInterface videoForm) {
		_getVideoForm = videoForm;
	}

	public void setState(States state) {
		_state = state;
	}

	private UIMenuActionEnums(UIMenuAction UIMA) {
		this.action = UIMA;
	}

	public UIMenuAction getUIMA() {
		return action;
	}

	static class UIMenuActionEnumConstructor {
		public UIMenuActionEnumConstructor(UI ui, Inventory inventory, UIFormMenuInterface getVideoForm, States state) {
			_ui = ui;
			_inventory = inventory;
			_getVideoForm = getVideoForm;
			_state = state;
		}
	}

	public static States getState() {
		return _state;
	}
}
