import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.*;


public class InputBox {
	private static String val = "";
	/**
	 * Shows a dialog specifying an string to enter, with the location set from the Monopoly sh's location
	 * @param text The text to display on the dialog.
	 * @return A String specifying the text entered if Ok is pressed, or "" (the empty string) if Cancel is pressed
	 */
	public static String show(String text) {
		//TODO - change this class to create its own shell similar to the HouseHotel class
		Display display = Display.getDefault();
		final Shell shdlg = new Shell(display, SWT.APPLICATION_MODAL);
		shdlg.setBounds(Monopoly.sh.getBounds().x, Monopoly.sh.getBounds().y, 300, 150);
		Label lbl = new Label(shdlg, SWT.WRAP);
		lbl.setBounds(5, 5, 200, 50);
		lbl.setText(text);
		lbl.setFont(new Font(display, "Courier New", 10, SWT.NONE));
		final Text txt = new Text(shdlg, SWT.SINGLE);
		txt.setBounds(5, 60, 200, 25);
		txt.setFont(lbl.getFont());
		Button btnOk = new Button(shdlg, SWT.PUSH);
		btnOk.setBounds(5, 90, 100, 25);
		btnOk.setFont(lbl.getFont());
		btnOk.setText("Ok");
		btnOk.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				val = txt.getText();
				shdlg.dispose();
			}

			public void widgetDefaultSelected(SelectionEvent e) { /*Not called*/ }
		});
		Button btnCancel = new Button(shdlg, SWT.PUSH);
		btnCancel.setBounds(105, 90, 95, 25);
		btnCancel.setFont(lbl.getFont());
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				val = "";
				shdlg.dispose();
			}

			public void widgetDefaultSelected(SelectionEvent e) { /*Not called*/ }
		});
		shdlg.open();
		
		
		while (!shdlg.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return val;
	}
}
