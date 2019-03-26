package shop.ui;

public interface UIMenuBuilderInterface {
	UIFormMenu toUIMenu(String heading);
	void add(String prompt, UIMenuAction action);
}
