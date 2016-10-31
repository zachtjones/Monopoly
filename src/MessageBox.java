import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class MessageBox {

	/**
	 * Shows an Alert configured with the title, caption, alert type, and buttons with text.
	 * The return value is the text of the button pressed.
	 * @param title The string to display in the window's title
	 * @param caption The string to display in the content of the window
	 * @param buttonTexts The strings that are the buttons. 
	 * This has the ... parameter so that the values may be enumerated like multiple parameters.
	 * 
	 * @return The text of the button clicked, 
	 * 	or null if the window is closed without clicking any buttons.
	 * @throws IllegalArguementException if the buttonTexts parameter is an empty array.
	 */
	public static String show(String title, String caption, String... buttonTexts) {
		Alert alert = new Alert(AlertType.NONE);
		alert.setTitle(title);
		alert.setContentText(caption);
		alert.setHeaderText(null);
		
		if(buttonTexts.length == 0){
			throw new IllegalArgumentException("parameter: buttonTexts of method show(String, String, String...) in class MessageBox is empty");
		}
		
		ButtonType[] buttons = new ButtonType[buttonTexts.length];
		for(int i = 0; i < buttonTexts.length; i++){
			buttons[i] = new ButtonType(buttonTexts[i]);
		}
		//set the buttons
		alert.getButtonTypes().setAll(buttons);
		Optional<ButtonType> value = alert.showAndWait();
		if(!(value.isPresent())){
			return null;
		}
		return value.get().getText();
	}
	
	/**
	 * Shows an Alert with the title, caption, and an "OK" button. 
	 * Does not return a value;
	 * @param title The string to display in the window's title
	 * @param caption The string to display in the content of the window
	 */
	public static void showInfo(String title, String caption){
		show(title, caption, "OK");
	}
	
	/**
	 * Shows an Alert with the title, caption, and two buttons, "Yes" and "No".
	 * Returns if the "Yes" button was clicked.
	 * @param title The string to display in the window's title
	 * @param caption The string to display in the content of the window.
	 * @return true if the "Yes" button is clicked, 
	 * 	false if "No" was clicked, or the window was closed.
	 */
	public static boolean showYesNo(String title, String caption){
		String result = show(title, caption, "Yes", "No");
		if(result == null){ return false; }
		return result.equals("Yes");
	}
	
	/**
	 * Shows an input dialog, returning a string that was typed.
	 * @param title The string to display in the window's title
	 * @param caption The string to display in the content of the window.
	 * @param defaultValue The string to fill the text box.
	 * @return The contents of the text box, or null if the window is closed.
	 */
	public static String showInput(String title, String caption, String defaultValue){
		TextInputDialog dialog = new TextInputDialog(defaultValue);
		dialog.setTitle(title);
		dialog.setHeaderText(null);
		dialog.setContentText(caption);

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (!result.isPresent()){
			return null;
		}
		return result.get();
	}
	
	/**
	 * Shows an input dialog, returning a string that was typed.
	 * The textbox is initially empty.
	 * @param title The string to display in the window's title
	 * @param caption The string to display in the content of the window.
	 * @return The contents of the text box, or null if the window is closed.
	 */
	public static String showInput(String title, String caption){
		return showInput(title, caption, "");
	}
	
	
}
