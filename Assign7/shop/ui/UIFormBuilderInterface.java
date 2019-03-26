package shop.ui;

public interface UIFormBuilderInterface {
	UIFormMenu toUIForm(String heading);
	void add(String prompt, UIFormTest test);
}
