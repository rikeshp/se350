package shop.ui;

final class Pair <T> {
	String prompt;
	UIMenuAction action;
	UIFormTest test;
	
	Pair(String prompt, T t){
		this.prompt = prompt;
		if(t instanceof UIMenuAction)
			action = (UIMenuAction) t;
		else if(t instanceof UIFormTest)
			test = (UIFormTest) t;
		else
			throw new IllegalArgumentException();
	}
}
