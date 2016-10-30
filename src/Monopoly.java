import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * This class involves a main and runs the basic board game of Monopoly, complete with a SWT User Interface.
 * @author Zach Jones
 *
 */
public class Monopoly {
	public static Shell sh;
	public static Label backGroundLabel;
	public static Image bgImg; 
	public static Label dieOne;
	public static Label dieTwo;
	public static Button btnRollDice;
	public static Button btnNewGame;
	public static Button btnOpenGame;
	public static Button btnSaveGame;
	public static Display display = new Display();
	public static Button btnHouseHotel;
	
	public static Image die1;
	public static Image die2;
	public static Image die3;
	public static Image die4;
	public static Image die5;
	public static Image die6;
	
	public static Image p1;
	public static Image p2;
	public static Image p3;
	public static Image p4;
	public static Image p5;
	public static Image p6;
	public static Image p7;
	public static Image p8;
	
	public static Image houseImg;
	public static Image hotelImg;
	
	public static Label lblPlayerPreview;
	public static Label lblCurrentPlayer;

	public static int numPlayers;
	public static int[] playerLocations = {0, 0, 0, 0};
	public static int[] playerMoney = {0,0,0,0};
	public static int currentPlayer = 1;
	public static String fileName = "";
	
	public static int[] propertyOwns = new int[40]; //the player that owns each property: 0 is bank, 1 is player 1, 2 is player 2, ...
	public static int[] propertyHouses = new int[40]; //the number of houses on each property: 5 is a hotel
	
	public static Text txtProperties;
	/**
	 * This is the main method
	 */
	public static void main(String[] args) {		
		System.out.println("Game started");
		long startTime = System.currentTimeMillis();
		sh = new Shell(display);
		sh.setSize(1200, 800);
		sh.setText("Monopoly");
		sh.setMinimumSize(new Point(800, 500));
		
		//TODO add what happens when a player doen't have enough money (either sell stuff or if they can't then they are out)
		
		//if you don't specify a layout for the shell, then the controls can set the positions using setBounds()
		backGroundLabel = new Label(sh, 0);
		backGroundLabel.setBounds(5, 35, sh.getBounds().width - 345, sh.getBounds().height - 95);

		bgImg = getImageFromResource("monopolygameboard.png");
		die1 = getImageFromResource("die1.png");
		die2 = getImageFromResource("die2.png");
		die3 = getImageFromResource("die3.png");
		die4 = getImageFromResource("die4.png");
		die5 = getImageFromResource("die5.png");
		die6 = getImageFromResource("die6.png");
		p1 = getImageFromResource("P1.png");
		p2 = getImageFromResource("P2.png");
		p3 = getImageFromResource("P3.png");
		p4 = getImageFromResource("P4.png");
		p5 = getImageFromResource("P5.png");
		p6 = getImageFromResource("P6.png");
		p7 = getImageFromResource("P7.png");
		p8 = getImageFromResource("P8.png");
		houseImg = getImageFromResource("House.png");
		hotelImg = getImageFromResource("Hotel.png");
				
		lblPlayerPreview = new Label(sh, 0);
		lblPlayerPreview.setBounds(sh.getBounds().width - 330, 230, 300, 300);
		lblPlayerPreview.setText("Players:");
		lblPlayerPreview.setFont(new Font(display, "Courier New", 10, 0));
		
		lblCurrentPlayer = new Label(sh, 0);
		lblCurrentPlayer.setBounds(sh.getBounds().width - 330, 5, 200, 20);
		lblCurrentPlayer.setFont(new Font(display, "Courier New", 12, 0));
		
		btnRollDice = new Button(sh, SWT.PUSH);
		btnRollDice.setText("Roll Dice");
		btnRollDice.setBounds(sh.getBounds().width - 330, 25, 200, 100);
		btnRollDice.setFont(new Font(display, "Courier New", 16, 0));
		btnRollDice.setEnabled(false);
		
		btnHouseHotel = new Button(sh, SWT.PUSH);
		btnHouseHotel.setBounds(sh.getBounds().width - 120, 25, 100, 100);
		btnHouseHotel.setFont(btnRollDice.getFont());
		btnHouseHotel.setEnabled(false);
		btnHouseHotel.setText("Buy / Sell");
		
		dieOne = new Label(sh, SWT.NONE);
		dieTwo = new Label(sh, SWT.NONE);
		dieOne.setBounds(sh.getBounds().width - 330, 145, 100, 100);
		dieTwo.setBounds(dieOne.getBounds());
		dieTwo.setLocation(sh.getBounds().width - 220, 145);
		dieOne.setImage(die1);
		dieTwo.setImage(die1);
		
		btnNewGame = new Button(sh, SWT.PUSH);
		btnNewGame.setText("New Game");
		btnNewGame.setBounds(5, 5, 150, 25);
		btnNewGame.setFont(new Font(display, "Courier New", 14, 0));
		
		btnNewGame.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) { newGame(); }
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		btnOpenGame = new Button(sh, SWT.PUSH);
		btnOpenGame.setText("Open Game");
		btnOpenGame.setBounds(160, 5, 150, 25);
		btnOpenGame.setFont(new Font(display, "Courier New", 14, 0));
		btnOpenGame.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) { openGame(); }
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		btnSaveGame = new Button(sh, SWT.PUSH);
		btnSaveGame.setText("Save Game");
		btnSaveGame.setBounds(315, 5, 150, 25);
		btnSaveGame.setFont(new Font(display, "Courier New", 14, 0));
		btnSaveGame.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) { saveGame(); }
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		
		txtProperties = new Text(sh, SWT.MULTI | SWT.V_SCROLL);
		txtProperties.setText("");
		txtProperties.setBounds(sh.getBounds().width - 330, 550, 300, 175);
		txtProperties.setFont(new Font(display, "Courier New", 8, 0));
		txtProperties.setEditable(false);

		
		sh.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				redraw();
				//System.out.println("Window resized to: " + sh.getSize().toString());
			}
		});
		
		
		btnRollDice.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
					rollDice();
			}
			public void widgetDefaultSelected(SelectionEvent event) {
					//this sub is required-but I’m not going to do anything on it
			}			
		});

		btnHouseHotel.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				HouseHotel.showHouseHotel();
			}

			public void widgetDefaultSelected(SelectionEvent e) {/*This isn't called*/}
			
		});
		
		sh.open();
		GC gc = new GC(backGroundLabel);
		gc.drawImage(bgImg, 0, 0, bgImg.getBounds().width, bgImg.getBounds().height, 0, 0, backGroundLabel.getBounds().width, backGroundLabel.getBounds().height);
		gc.dispose();
		
		//get elapsed time to open
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time for startup: " + estimatedTime / 1000.0  + " seconds.");
		
		while (!sh.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		System.out.println("Program closed");
	}
	/**
	 * This method creates a new Shell that shows the options for creating a new game. When the button is clicked, it sets some values of variables on this class.
	 */
	private static void newGame() {
		NewGame ng = new NewGame();
		System.out.println(ng.toString());
	}
	
	private static void openGame() {
		FileDialog fd = new FileDialog(sh);
		String[] fns = {"*.monop*"};
		fd.setFilterExtensions(fns);
		String[] fns1 = {"Monopoly file (*.monop*)"};
		fd.setFilterNames(fns1);
		String fn = fd.open();
		
		if (fn != null) {
			long startTime = System.currentTimeMillis();
			fileName = fn;
			try {
				sh.setText("Monopoly: " + fileName.substring(fileName.lastIndexOf("\\") + 1)); //the double backslash literal is just \
			} catch (Exception e1) {

			}
			
			try {
				FileReader fr = new FileReader(fn);
				BufferedReader bf = new BufferedReader(fr);
				String line = "";
				line = bf.readLine();
				numPlayers = Integer.parseInt(line);
				line = bf.readLine();
				currentPlayer = Integer.parseInt(line);
				String[] temp = bf.readLine().split(",");
				playerLocations = new int[temp.length];
				for (int i = 0; i < temp.length; i++ ) {
					playerLocations[i] = Integer.parseInt(temp[i]);
				}
				temp = bf.readLine().split(",");
				playerMoney = new int[temp.length];
				for (int i = 0; i < temp.length; i++) {
					playerMoney[i] = Integer.parseInt(temp[i]);
				}
				
				for (int i = 1; i <= 40; i++) {
					propertyOwns[i-1] = Integer.parseInt(bf.readLine());
					propertyHouses[i-1] = Integer.parseInt(bf.readLine());
				}
				
				bf.close();
				btnRollDice.setEnabled(true);
				btnHouseHotel.setEnabled(true);
				redraw();
				
				
			} catch(FileNotFoundException e) {
				System.out.println("File not found");
			} catch(IOException e) {
				System.out.println("Error reading file");
			} catch(Exception e) {
				System.out.println("Other error: " + e.getMessage());
			}
			//get elapsed time to open
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println("Time to open game: " + estimatedTime / 1000.0  + " seconds.");
		}
	}
	
	private static void saveGame() {
		long startTime = System.currentTimeMillis();
		try {
			FileWriter fr = new FileWriter(fileName);
			BufferedWriter br = new BufferedWriter(fr);
			String line = "";
			
			br.write(Integer.toString(numPlayers) + "\n");
			br.write(Integer.toString(currentPlayer) + "\n");
			for (int i = 0; i < numPlayers; i++) {
				line += playerLocations[i] + ",";
			}
			line = line.substring(0, line.length() - 1); //trim the last "," 
			br.write(line + "\n");
			line = "";
			for (int i = 0; i < numPlayers; i++) {
				line += playerMoney[i] + ",";
			}
			line = line.substring(0, line.length() - 1);
			br.write(line + "\n");
			
			for (int i = 1; i <= 40; i++){
				br.write(Integer.toString(propertyOwns[i-1]) + "\n");
				br.write(Integer.toString(propertyHouses[i-1]) + "\n");
			}
			
			br.close();
		} catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch(IOException e) {
			System.out.println("Error reading file");
		}
		//get elapsed time to open
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time to save game: " + estimatedTime / 1000.0  + " seconds.");
	}

	private static void rollDice() {
		Random r = new Random();
		int one, two;
		one = r.nextInt(6-1+1) + 1;
		two = r.nextInt(6-1+1) + 1;
		setDie(dieOne, one);
		setDie(dieTwo, two);
		if (playerLocations[currentPlayer - 1] != 30) { //if the player is not in jail
			playerLocations[currentPlayer - 1] += one + two;
			playerLocations[currentPlayer - 1] = playerLocations[currentPlayer - 1] % 40; // make the locations wrap so 42 is the 2nd spot
			System.out.println("Player " + currentPlayer + " rolled a " + (one+two) + " and moved to " + Locations.getName(playerLocations[currentPlayer - 1]));

		} else {
			if (one == two) { //if they roll doubles, then they are out and move that many places
				playerLocations[currentPlayer - 1] = 10 + one + two;
				nextPlayer();
				redraw();
				return; //exits this method
			} else { //did not roll doubles, so only send them to just visiting
				playerLocations[currentPlayer - 1] = 10;
				nextPlayer();
				redraw();
				return; //exits this method
			}
		}


		
		redraw();
		
		//test if property is owned or not if it is not a reserved property (like go, income tax, etc.)
		//test if the player passes GO
		if (playerLocations[currentPlayer - 1] - one - two < 0 ) {
			MessageBoxShow.show("GO", "You passed GO.\nCollect $200", sh, SWT.OK);
			playerMoney[currentPlayer - 1] += 200;
			redraw();
			System.out.println("Player " + currentPlayer + " passed GO and collected $200");
		}
		//test for the place landed on
		switch (playerLocations[currentPlayer - 1]) {
		case 0: //GO
			break;
		case 4://income tax
			MessageBoxShow.show("Income Tax", "You landed on Income Tax. You paid $200 to the bank.", sh, SWT.OK);
			playerMoney[currentPlayer - 1] -= 200;
			System.out.println("Player " + currentPlayer + " landed on Income Tax and paid $200 to the bank");
			break;
		case 10://just visiting
			break;
		case 20://free parking
			break;
		case 30://GO TO JAIL
			break;
		case 38://Luxury tax
			MessageBoxShow.show("Luxury Tax", "You landed on Luxury Tax. You paid $100 to the bank.", sh, SWT.OK);
			playerMoney[currentPlayer - 1] -= 100;
			System.out.println("Player " + currentPlayer + " landed on Luxury Tax and paid $100 to the bank");
			break;
		case 2://community chest
			ChanceAndCommunityChest.newCommunityChest(currentPlayer);return;
		case 7://chance
			ChanceAndCommunityChest.newChance(currentPlayer, one + two);return;
		case 17://Community chest
			ChanceAndCommunityChest.newCommunityChest(currentPlayer);return;
		case 22://chance
			ChanceAndCommunityChest.newChance(currentPlayer, one + two);return;
		case 33://community chest
			ChanceAndCommunityChest.newCommunityChest(currentPlayer);return;
		case 36://chance
			ChanceAndCommunityChest.newChance(currentPlayer, one + two);return;
			
		default: 
			if (propertyOwns[playerLocations[currentPlayer - 1]] == 0) {
				int temp = MessageBoxShow.show("Unowned property", "The property: " + Locations.getName(playerLocations[currentPlayer - 1]) + " is unowned. \nWould you like to buy it for: $" + Locations.costToBuy(playerLocations[currentPlayer - 1]), sh, SWT.YES | SWT.NO);
				if (temp == SWT.YES) {
					if (playerMoney[currentPlayer - 1] >= Locations.costToBuy(playerLocations[currentPlayer - 1])) {
						//transfer money and property
						propertyOwns[playerLocations[currentPlayer - 1]] = currentPlayer;
						playerMoney[currentPlayer - 1] -= Locations.costToBuy(playerLocations[currentPlayer - 1]);
						System.out.println("Player " + currentPlayer + " bought " + Locations.getName(playerLocations[currentPlayer - 1]) + " for $" + Locations.costToBuy(playerLocations[currentPlayer - 1]));
					} else {
						MessageBoxShow.show("Not enough funds", "You don't have enough money to buy this property", sh, SWT.OK);
					}
				}
			} else {
				//the player owes money to another player or they own it
				if (propertyOwns[playerLocations[currentPlayer - 1]] == currentPlayer) {
					MessageBoxShow.show("Property owned by you", "You own this property", sh, SWT.OK);
				} else {
					MessageBoxShow.show("Owned by someone else", "The property: " + Locations.getName(playerLocations[currentPlayer - 1]) + " is owned by player: " + propertyOwns[playerLocations[currentPlayer - 1]] + "\nYou owe $" + Locations.rentOwed(playerLocations[currentPlayer - 1], propertyHouses[playerLocations[currentPlayer-1]], one + two), sh, SWT.OK);
					// transfer funds
					playerMoney[currentPlayer - 1] -= Locations.rentOwed(playerLocations[currentPlayer - 1], propertyHouses[playerLocations[currentPlayer-1]], one + two);
					playerMoney[propertyOwns[playerLocations[currentPlayer - 1]] - 1] += Locations.rentOwed(playerLocations[currentPlayer - 1], propertyHouses[playerLocations[currentPlayer-1]], one + two);
					System.out.println("Player " + currentPlayer + " paid $" + Locations.rentOwed(playerLocations[currentPlayer - 1], propertyHouses[playerLocations[currentPlayer-1]], one + two) + " to player " + propertyOwns[playerLocations[currentPlayer - 1]]);
					redraw();
				}
			}
		}
		
		/*Test to see if the player is out of money	*/
		if (playerMoney[currentPlayer - 1] < 0) {
			while (playerMoney[currentPlayer - 1] < 0) {
				
				if (MessageBoxShow.show("Not enough funds", "You must sell properties or declare bankruptcy.\nYou need: $" + -Monopoly.playerMoney[Monopoly.currentPlayer - 1] + "\nWould you like to sell properties", Monopoly.sh, SWT.YES | SWT.NO) == SWT.YES) { 
					HouseHotel.showHouseHotel();
					redraw();
				} else {
					//they are out so return everything to the bank
					for(int i = 0; i<40; i++) {
						if (propertyOwns[i] == currentPlayer) {
							propertyOwns[i] = 0;
							propertyHouses[i] = 0;
						}
					}	
					nextPlayer();
					redraw();
				}
			}
		}
		
		
		if (one == two) { //if the two are doubles then you get a re-roll

			MessageBoxShow.show("Reroll", "You rolled doubles\nYou get to reroll", sh, SWT.OK);
			rollDice();

		}else {
			nextPlayer();
		}


		redraw();

	}
	/**
	 * This method redraws the game board, the pieces, and resizes the controls.
	 * This is automatically called when the shell's size changes, or the dice are rolled.
	 * There is no harm done is calling this method frequently
	 */
	public static void redraw() {
		
		backGroundLabel.setSize(sh.getBounds().width - 345, sh.getBounds().height - 95);
		GC gc = new GC(backGroundLabel);
		gc.drawImage(bgImg, 0, 0, bgImg.getBounds().width, bgImg.getBounds().height, 0, 0, backGroundLabel.getBounds().width, backGroundLabel.getBounds().height);
		double ratioX, ratioY;
		ratioX = 1500.0 / backGroundLabel.getSize().x;
		ratioY = 1500.0 / backGroundLabel.getSize().y;
		//draw the players
		for (int i = 1; i<=numPlayers; i++) {
			switch (i) {
			case 1: gc.drawImage(p1, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[0]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[0]).y / ratioY - 20), 40, 40);break;
			case 2: gc.drawImage(p2, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[1]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[1]).y / ratioY - 20), 40, 40);break;
			case 3: gc.drawImage(p3, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[2]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[2]).y / ratioY - 20), 40, 40);break;
			case 4: gc.drawImage(p4, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[3]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[3]).y / ratioY - 20), 40, 40);break;
			case 5: gc.drawImage(p5, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[4]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[4]).y / ratioY - 20), 40, 40);break;
			case 6: gc.drawImage(p6, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[5]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[5]).y / ratioY - 20), 40, 40);break;
			case 7: gc.drawImage(p7, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[6]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[6]).y / ratioY - 20), 40, 40);break;
			case 8: gc.drawImage(p8, 0, 0, 100, 100, (int)(Locations.getPoint(playerLocations[7]).x / ratioX - 20), (int)(Locations.getPoint(playerLocations[7]).y / ratioY - 20), 40, 40);break;
			}
			

		}
		//
		for (int i = 1; i < 40; i++) {
			int temp = propertyHouses[i];
			if (temp != 0 && temp != 5) {
				for (int j = 1; j <= temp; j++) {
					gc.drawImage(houseImg, 0, 0, houseImg.getBounds().width, houseImg.getBounds().height, (int)(Locations.getLocation(i, j).x / ratioX), (int)(Locations.getLocation(i, j).y / ratioY), 20, 20);
				}
			} else if (temp == 5) {
				if (i < 10) {
					gc.drawImage(hotelImg, 0, 0, hotelImg.getBounds().width, hotelImg.getBounds().height, (int)(Locations.getLocation(i, 4).x / ratioX), (int)(Locations.getLocation(i, 4).y / ratioY), 45, 20);
				} else if (i > 20 && i < 30) {
					gc.drawImage(hotelImg, 0, 0, hotelImg.getBounds().width, hotelImg.getBounds().height, (int)(Locations.getLocation(i, 0).x / ratioX), (int)(Locations.getLocation(i, 3).y / ratioY), 45, 20);
				} else if (i > 10 && i < 20){
					gc.drawImage(hotelImg, 0, 0, hotelImg.getBounds().width, hotelImg.getBounds().height, (int)(Locations.getLocation(i, 3).x / ratioX), (int)(Locations.getLocation(i, 4).y / ratioY), 20, 45);
				} else {
					gc.drawImage(hotelImg, 0, 0, hotelImg.getBounds().width, hotelImg.getBounds().height, (int)(Locations.getLocation(i, 3).x / ratioX), (int)(Locations.getLocation(i, 0).y / ratioY), 20, 45);
				}
			}
		}
		
		gc.dispose();
		
		GC gc2 = new GC(lblPlayerPreview);	
		
		for (int i = 1; i<=numPlayers; i++) {
			switch (i) {
			case 1: gc2.drawImage(p1, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[0]), 30, 30*i);break;
			case 2: gc2.drawImage(p2, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[1]), 30, 30*i);break;
			case 3: gc2.drawImage(p3, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[2]), 30, 30*i);break;
			case 4: gc2.drawImage(p4, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[3]), 30, 30*i);break;
			case 5: gc2.drawImage(p5, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[4]), 30, 30*i);break;
			case 6: gc2.drawImage(p6, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[5]), 30, 30*i);break;
			case 7: gc2.drawImage(p7, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[6]), 30, 30*i);break;
			case 8: gc2.drawImage(p8, 0, 0, 100, 100, 0, 30 * i, 20, 20);gc2.drawText("                           ", 30, 30*i);gc2.drawText(Locations.getName(playerLocations[7]), 30, 30*i);break;
			}
			//i have it draw the blank text to clear the area that will be drawn on
			if (playerMoney[i - 1] >= 0) {
				gc2.drawText("$" + Integer.toString(playerMoney[i-1]), 200, 30*i); //draw the money
			} else {
				gc2.drawText("Out", 200, 30*i);
			}
			
		}
		
		
		gc2.dispose();
		lblPlayerPreview.setLocation(sh.getBounds().width - 330, 230);

		btnRollDice.setBounds(sh.getBounds().width - 330, 35, 150, 100);
		btnHouseHotel.setBounds(sh.getBounds().width - 175, 35, 150, 100);
		dieOne.setBounds(sh.getBounds().width - 330, 135, 100, 100);
		dieTwo.setBounds(dieOne.getBounds());
		dieTwo.setLocation(sh.getBounds().width - 220, 135);
		lblCurrentPlayer.setBounds(sh.getBounds().width - 330, 5, 200, 20);
		lblCurrentPlayer.setText("Current Player is: " + currentPlayer);
		txtProperties.setLocation(sh.getBounds().width -330, 550);
		txtProperties.setText("");
		for (int i = 1; i < 40; i++) {
			if (Locations.costToBuy(i) != 0) { //if the cost is 0 then the property can't be owned
				if (propertyOwns[i] == 0 ) {
					txtProperties.append(BetterString.padRight(Locations.getName(i), 25) + ": bank" + "\n");

				} else {
					txtProperties.append(BetterString.padRight(Locations.getName(i), 25) + ": player " + propertyOwns[i] + "\n");
				}
			}
		}
		txtProperties.setSelection(0);
	}
	
	private static void setDie(Label x, int value) {
		switch (value) {
		case 1: x.setImage(die1); break;
		case 2: x.setImage(die2); break;
		case 3: x.setImage(die3); break;
		case 4: x.setImage(die4); break;
		case 5: x.setImage(die5); break;
		case 6: x.setImage(die6); break;
		
		}
	}
	/**
	 * This method returns an image by the name specified
	 * @param resourceName This is the name of the resource located in the default package folder
	 * @return the Image from the name
	 */
	public static Image getImageFromResource(String resourceName) {
		InputStream c = ClassLoader.getSystemResourceAsStream(resourceName);
		Image rImage = new Image(display, c);
		try {
			c.close();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
		
		
		return rImage;
		
	}
	/**
	 * This method moves the turn to the next player. If that player has a negative balance (they are out), then the turn is moved to the next
	 */
	public static void nextPlayer() {
		currentPlayer ++;
		if (currentPlayer > numPlayers) {
			currentPlayer = 1;
		}
		if (playerMoney[currentPlayer - 1] < 0) {
			nextPlayer();
		}
	}

}
