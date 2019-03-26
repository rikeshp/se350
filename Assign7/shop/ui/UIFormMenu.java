package shop.ui;

/**
 * @see UIFormBuilder
 */
final class UIFormMenu extends UICommands implements UIFactoryInterface, UIFormMenuInterface {
	private final Pair[] _form;
	public UIFormMenu(String _heading, Pair[] pair) {
		super(_heading, pair);
		_form = pair;
	}


	public boolean checkInput(int i, String input) {
		if (null == _form[i])
			return true;
		return _form[i].test.run(input);
	}
	
	public void runAction(int i) {
		_form[i].action.run();
	}
}
