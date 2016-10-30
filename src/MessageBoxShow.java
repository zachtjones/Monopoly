import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxShow {

	/**
	 * This shows a SWT message box and returns the result
	 * @param title The text that shows up as the title of the dialog
	 * @param caption The text displayed in the display area of the window
	 * @param shell The shell that this is showed from
	 * @param style The style of the messagebox, can be picked from the style constants: 
	 * <ul><li>SWT.OK</li>
	 * <li>SWT.OK | SWT.CANCEL</li>
	 * <li>SWT.YES | SWT.NO</li>
	 * <li>SWT.YES | SWT.NO | SWT.CANCEL</li>
	 * <li>SWT.RETRY | SWT.CANCEL</li>
	 * <li>SWT.ABORT | SWT.RETRY | SWT.IGNORE</li>
	 * </ul>
	 * @return The SWT constant that corresponds with the result. This is the <b>exact same</b> as the SWT constant that the button was assigned (OK for an OK button, etc.)
	 */
	public static int show(String title, String caption, Shell shell, int style) {
		MessageBox mb = new MessageBox(shell, style);
		mb.setText(title);
		mb.setMessage(caption);
		return mb.open();
	}
}