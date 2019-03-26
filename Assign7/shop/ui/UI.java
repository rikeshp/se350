package shop.ui;

public interface UI {
  public void processMenu(UIFormMenuInterface _menus);
  public String[] processForm(UIFormMenuInterface _getVideoForm);
  public void displayMessage(String message);
  public void displayError(String message);
}
