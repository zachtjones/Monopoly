import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.*;

public class HouseHotel {
	/**
	 * The method shows the UI for selling properties/houses and buying properties/houses as a modal dialog. <br>
	 * This will cause program execution to wait on the response of either: buying/selling a property or buying/selling houses.
	 */
	public static void showHouseHotel() {
		System.out.println("A new UI for houses was created.");
		final ArrayList<Integer> ownedProps = new ArrayList<Integer>(0);
		final ArrayList<Integer> ownedOtherProps = new ArrayList<Integer>(0);
		final ArrayList<Integer> houseGroups = new ArrayList<Integer>(0);
		Display display = Display.getDefault();
		final Shell shdlg = new Shell(display, SWT.APPLICATION_MODAL);
		shdlg.setBounds(Monopoly.sh.getBounds());
		shdlg.setText("Edit houses and hotels");
		
		final Label la = new Label(shdlg, SWT.NONE);
		la.setBounds(5, 5, 600, 20);
		la.setText("Player " + Monopoly.currentPlayer + "'s Properties and values:      Other properties   recommeded price");
		la.setFont(new Font(Monopoly.display, "Courier New", 10, 0));
		
		final List lsOwned = new List(shdlg, SWT.MULTI | SWT.V_SCROLL);
		lsOwned.setBounds(5, 30, 310, 200);
		lsOwned.setFont(la.getFont());		
		
		final List lsBuyFrom = new List(shdlg, SWT.NONE | SWT.V_SCROLL);
		lsBuyFrom.setBounds(320, 30, 310, 200);
		lsBuyFrom.setFont(la.getFont());
		
		final Label lblInfo = new Label(shdlg, SWT.WRAP);
		lblInfo.setBounds(640, 30, 310, 200);
		lblInfo.setFont(la.getFont());
		lblInfo.setText("Only properties with 0 houses can be bought / sold");
		
		final Button btnSell = new Button(shdlg, SWT.PUSH | SWT.WRAP);
		btnSell.setBounds(5, 235, 150, 75);
		btnSell.setText("Sell property to bank");
		btnSell.setFont(new Font(Monopoly.display, "Courier New", 12, 0));
		btnSell.setEnabled(false);
		btnSell.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				//sell the property to the bank (set the property as unowned and refund the player)
				int temp = ownedProps.get(lsOwned.getSelectionIndex());
				Monopoly.propertyOwns[temp] = 0;
				Monopoly.playerMoney[Monopoly.currentPlayer - 1] += Locations.costToBuy(temp);
				shdlg.close();
				Monopoly.redraw();
			}
			public void widgetDefaultSelected(SelectionEvent e) {/*this is never called */}
		});
		
		final Button btnSellTo = new Button(shdlg, SWT.PUSH | SWT.WRAP);
		btnSellTo.setBounds(160, 235, 150, 75);
		btnSellTo.setText("Sell property to player");
		btnSellTo.setFont(btnSell.getFont());
		btnSellTo.setEnabled(false);
		btnSellTo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				int temp = ownedProps.get(lsOwned.getSelectionIndex());
				try {
					int num = Integer.parseInt(InputBox.show("What player do you wish to sell " + Locations.getName(temp) + " to?"));
					int num2 = Integer.parseInt(InputBox.show("What amount do you wish to sell " + Locations.getName(temp) + " for?"));
					if (num > 0 && num <=Monopoly.numPlayers) {
						if (MessageBoxShow.show("Sell property", "Does player " + num + " agree to buy " + Locations.getName(temp) + " for $" + num2 + "?", shdlg, SWT.YES | SWT.NO) == SWT.YES) {
							if (Monopoly.playerMoney[num - 1] >= num2) { //test if the other player has enough money
								//transfer funds and money
								Monopoly.playerMoney[Monopoly.currentPlayer - 1] += num2;
								Monopoly.playerMoney[num - 1] -= num2;
								Monopoly.propertyOwns[temp] = num;
								shdlg.close();
								Monopoly.redraw();
							} else {
								MessageBoxShow.show("Not enough funds", "Player " + num + " does not have enough money for this transaction", shdlg, SWT.OK);
							}
							
						}
					}
					
				} catch (NumberFormatException e1) {
					System.out.println("Number format exception: " + e1.getMessage());
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {/*NOT CALLED*/}
		});
		
		final Button btnBuyFrom = new Button(shdlg, SWT.PUSH | SWT.WRAP);
		btnBuyFrom.setBounds(320, 235, 310, 75);
		btnBuyFrom.setText("Buy property from player");
		btnBuyFrom.setFont(btnSell.getFont());
		btnBuyFrom.setEnabled(false);
		btnBuyFrom.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				int temp = ownedOtherProps.get(lsBuyFrom.getSelectionIndex());
				if (MessageBoxShow.show("Agree to sell", "Does player " + Monopoly.propertyOwns[temp] + " agree to sell " + Locations.getName(temp), shdlg, SWT.YES | SWT.NO) == SWT.YES) {
					try {
						int amount = Integer.parseInt(InputBox.show("How much money does player " + Monopoly.propertyOwns[temp] + " wish to sell " + Locations.getName(temp)));
						if (MessageBoxShow.show("Agree to buy", "Does player " + Monopoly.currentPlayer + " agree to buy " + Locations.getName(temp) + " for $" + amount + "?", shdlg, SWT.YES | SWT.NO) == SWT.YES) {
							if (Monopoly.playerMoney[Monopoly.currentPlayer - 1] >= amount) { //test if the player has enough money
								//transfer funds and property
								Monopoly.playerMoney[Monopoly.currentPlayer - 1] -= amount;
								Monopoly.playerMoney[Monopoly.propertyOwns[temp] - 1] += amount;
								Monopoly.propertyOwns[temp] = Monopoly.currentPlayer;
								shdlg.dispose();
								Monopoly.redraw();
							} else {
								MessageBoxShow.show("Not enough funds", "Player " + Monopoly.currentPlayer + " does not have enough money for this transaction", shdlg, SWT.OK);
							}
							
						}
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
					
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {/*Not called*/}
		});
		
		lsBuyFrom.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				//when selection changes
				if (lsBuyFrom.getSelectionIndex() > -1) {
					btnBuyFrom.setEnabled(true);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				//when it is double-clicked-i don't need to do anything here
			}
		});
		
		lsOwned.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				//when selection changes
				if (lsOwned.getSelectionIndex() > -1) {
					btnSell.setEnabled(true);
					btnSellTo.setEnabled(true);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				//when it is double-clicked-i don't need to do anything here
			}
		});
		
		final Label lblHouses = new Label(shdlg, SWT.NONE);
		lblHouses.setBounds(5, 315, 600, 20);
		lblHouses.setFont(la.getFont());
		lblHouses.setText("Edit houses/hotels for your groups (5 is a hotel)");
		
		final List lsHouses = new List(shdlg, SWT.None | SWT.V_SCROLL);
		lsHouses.setBounds(5, 335, 625, 200);
		lsHouses.setFont(la.getFont());
		
		final Button btnAddHouses = new Button(shdlg, SWT.PUSH);
		btnAddHouses.setFont(btnSell.getFont());
		btnAddHouses.setBounds(5, 540, 310, 75);
		btnAddHouses.setText("Add houses");
		btnAddHouses.setEnabled(false);
		btnAddHouses.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				try {
					int temp = houseGroups.get(lsHouses.getSelectionIndex());
					int temp1 = Integer.parseInt(InputBox.show("Enter the number of houses to add"));
					
					int temp2 = Locations.costForHouses(temp, temp1);
					if (Locations.getNumberOfHouses(temp) + temp1 > 5) {
						MessageBoxShow.show("More than 5 houses", "You can't have more than 5 houses on a property", shdlg, SWT.OK); 
					}else if (temp1 <= 0) {
						MessageBoxShow.show("Negative houses", "You can't add negative or zero houses.", shdlg, SWT.OK);
					} else {
						if (Monopoly.playerMoney[Monopoly.currentPlayer - 1] < temp2) {
							MessageBoxShow.show("Not enough money", "You don't have enough money for this transaction.", shdlg, SWT.OK);
						} else {
							if (MessageBoxShow.show("Cost $" + temp2, "This will cost you $" + temp2 + "\nBuy houses?", shdlg, SWT.YES | SWT.NO) == SWT.YES) {
								Locations.addHouses(temp, temp1);
								Monopoly.playerMoney[Monopoly.currentPlayer - 1] -= temp2;
								shdlg.dispose();
								Monopoly.redraw();
							}
						}

					}
				} catch (NumberFormatException e1) {
					System.out.println("Number format exception: " + e1.getMessage());
				}
				
				
			}
			public void widgetDefaultSelected(SelectionEvent e) {/*Not called*/}
		});
		
		
		
		final Button btnSellHouses = new Button(shdlg, SWT.PUSH);
		btnSellHouses.setFont(btnSell.getFont());
		btnSellHouses.setBounds(320, 540, 310, 75);
		btnSellHouses.setText("Sell houses");
		btnSellHouses.setEnabled(false);
		btnSellHouses.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				try {
					int temp = houseGroups.get(lsHouses.getSelectionIndex());
					int temp1 = Integer.parseInt(InputBox.show("Enter the number of houses to sell"));

					int temp2 = Locations.costForHouses(temp, temp1);
					if (Locations.getNumberOfHouses(temp) - temp1 < 0) {
						MessageBoxShow.show("Less than 0 houses", "You can't have less than 0 houses on a property", shdlg, SWT.OK); 
					}else if (temp1 <= 0) {
						MessageBoxShow.show("Negative houses", "You can't remove negative or zero houses.", shdlg, SWT.OK);
					} else {
						if (MessageBoxShow.show("Cost $" + temp2, "This will give you $" + temp2 + "\nSell houses?", shdlg, SWT.YES | SWT.NO) == SWT.YES) {
							Locations.removeHouses(temp, temp1);
							Monopoly.playerMoney[Monopoly.currentPlayer - 1] += temp2;
							shdlg.dispose();
							Monopoly.redraw();
						}
					}
				} catch (NumberFormatException e1) {
					System.out.println("Number format exception: " + e1.getMessage());
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {/*Not called*/}
		});
		
		lsHouses.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				//when selection changes
				if (lsHouses.getSelectionIndex() > -1) {
					btnAddHouses.setEnabled(true);
					btnSellHouses.setEnabled(true);
				}
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				//when it is double-clicked-i don't need to do anything here
			}
		});
		
		for (int i = 1; i < 40; i++) { 
			if (Monopoly.propertyOwns[i] == Monopoly.currentPlayer && Monopoly.propertyHouses[i] == 0) { //you can't buy or sell a property with houses on it
				lsOwned.add(BetterString.padRight(Locations.getName(i), 25) + " $" + Locations.costToBuy(i));
				ownedProps.add(i);
			}
			if (Monopoly.propertyOwns[i] != 0 && Monopoly.propertyOwns[i] != Monopoly.currentPlayer && Monopoly.propertyHouses[i] == 0) {
				lsBuyFrom.add(BetterString.padRight(Locations.getName(i), 25) + " $" + Locations.costToBuy(i));
				ownedOtherProps.add(i);
			}
		}
		
		for (int i = 0; i <= 7; i++) {
			if (Locations.canAddHouses(i, Monopoly.currentPlayer) || Locations.canRemoveHouses(i,  Monopoly.currentPlayer)) {
				lsHouses.add(Locations.getNameOfGroup(i) + ": Houses: " + Locations.getNumberOfHouses(i));
				houseGroups.add(i);
			}
		}
		
		final Button btnClose = new Button(shdlg, SWT.PUSH);
		btnClose.setBounds(5, 630, 625, 150);
		btnClose.setFont(new Font(display, "Courier New", 16, SWT.NONE));
		btnClose.setText("Cancel");
		btnClose.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
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
	}
}
