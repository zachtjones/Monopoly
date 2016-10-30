import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.*;


public class NewGame {
	private Text txPlayer;
	private Text txCash;
	private Button btnSet;
	private Shell sh;
	public NewGame() {
		
		sh = new Shell(Monopoly.display);
		sh.setSize(400, 150);
		sh.setText("Monopoly New Game");
		//if you don't specify a layout for the shell, then the controls can set the positions using setBounds()
		Label l1 = new Label(sh, SWT.NONE);
		Font f = new Font(Monopoly.display, "Courier New", 12, 0);
		l1.setText("Enter the number of players: ");
		l1.setBounds(5, 5, 300, 20);
		l1.setFont(f);

		txPlayer = new Text(sh, SWT.SINGLE);
		txPlayer.setFont(f);
		txPlayer.setBounds(305, 5, 20, 20);
		txPlayer.setTextLimit(1);
		ModifyListener listener1 = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				boolean isValid = true;
				try {
					int i1 = Integer.valueOf(txPlayer.getText());
					int i2 = Integer.valueOf(txCash.getText());
					if (i1 <= 0 || i2 <= 0) {
						isValid = false;
					}
					if (i1 > 8) {
						isValid = false;
					}
				} catch (Exception e1) {
					isValid = false;
				}
				btnSet.setEnabled(isValid);
			}
		};
		txPlayer.addModifyListener(listener1);
		Label l2 = new Label(sh, SWT.NONE);
		l2.setText("Enter the starting cash: ");
		l2.setBounds(5, 30, 300, 20);
		l2.setFont(f);
		
		txCash = new Text(sh, SWT.SINGLE);
		txCash.setFont(f);
		txCash.setBounds(305, 30, 60, 20);
		txCash.setTextLimit(6);
		txCash.addModifyListener(listener1);
		
		
		btnSet = new Button(sh, SWT.PUSH);
		btnSet.setFont(f);
		btnSet.setText("Create new Game");
		btnSet.setBounds(5, 55, 360, 40);
		btnSet.setEnabled(false);
		btnSet.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) { setValues(); }
			public void widgetDefaultSelected(SelectionEvent e) {}
		});

		sh.open();

		
	}
	/**
	 * This method sets the values in the Monopoly class and shows the filedialog to select the location to save, as well as creating the file
	 */
	private void setValues() {
		FileDialog fd = new FileDialog(sh, SWT.SAVE);
		String[] fns = {"*.monop*"};
		fd.setFilterExtensions(fns);
		String[] fns1 = {"Monopoly file (*.monop*)"};
		fd.setFilterNames(fns1);
		
		String fn = fd.open();
		if (fn.endsWith(".monop") == false) {
			fn += ".monop";
		}
		if (fn != null) {
			long startTime = System.currentTimeMillis();
			Monopoly.fileName = fn;
			try {
				Monopoly.sh.setText("Monopoly: " + Monopoly.fileName.substring(Monopoly.fileName.lastIndexOf("\\") + 1)); //the double backslash literal is just \
			} catch (Exception e1) {

			}

			try {
				FileWriter fw;
				if (fn.endsWith(".monop") == true ) {
					fw = new FileWriter(fn);
				} else {
					fw = new FileWriter(fn + ".monop");
				}
				
				BufferedWriter bw = new BufferedWriter(fw);
				Monopoly.numPlayers = Integer.parseInt(txPlayer.getText());
				bw.write(Integer.toString(Monopoly.numPlayers) + "\n");
				bw.write(Integer.toString(Monopoly.currentPlayer) + "\n");
				for (int i = 0; i < Monopoly.numPlayers; i++) { //all the players start at 0- which is "GO"
					if (i != Monopoly.numPlayers - 1) {
						bw.write("0" + ",");
					} else {
						bw.write("0");
					}
				}
				bw.write("\n");
				Monopoly.playerLocations = new int[Monopoly.numPlayers]; //the number in the [] is the length, not the end index like VB
				Monopoly.playerMoney = new int[Monopoly.numPlayers];
				for (int i = 0; i < Monopoly.numPlayers; i++) {
					Monopoly.playerMoney[i] = Integer.parseInt(txCash.getText());
					if (i != Monopoly.numPlayers - 1) {
						bw.write(Integer.toString(Monopoly.playerMoney[i]) + ",");
					} else {
						bw.write(Integer.toString(Monopoly.playerMoney[i]));
					}
				}
				bw.write("\n");
				
				for (int i = 1; i <= 40; i++){
					Monopoly.propertyOwns[i-1] = 0;
					Monopoly.propertyHouses[i-1] = 0;
					bw.write(Integer.toString(Monopoly.propertyOwns[i-1]) + "\n");
					bw.write(Integer.toString(Monopoly.propertyHouses[i-1]) + "\n");
				}
				bw.close();
				Monopoly.currentPlayer = 1;
				Monopoly.btnRollDice.setEnabled(true);
				Monopoly.btnHouseHotel.setEnabled(true);
				Monopoly.redraw();
				
				sh.close();
			} catch(IOException ex) {
				ex.printStackTrace();
			}

			//get elapsed time to open
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println("Time to create new: " + estimatedTime / 1000.0  + " seconds.");
		}
	}

	
}
