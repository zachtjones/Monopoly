import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * This class involves a main and runs the basic board game of Monopoly, complete with a SWT User Interface.
 * @author Zach Jones
 *
 */
public class Monopoly extends Application {
	//TODO- remove the BetterString class and replace with java String.format(stuff);
	public Stage primaryStage;
	public Canvas backgroundCanvas;
	public Image bgImg; 
	public ImageView dieOne;
	public ImageView dieTwo;
	public Button btnRollDice;
	public Button btnNewGame;
	public Button btnOpenGame;
	public Button btnSaveGame;
	public Button btnHouseHotel;
	public Image die1, die2, die3, die4, die5, die6;
	public Image p1, p2, p3, p4, p5, p6, p7, p8;
	public Image houseImg;
	public Image hotelImg;
	public Canvas playerPreviewCanvas;
	public Label lblCurrentPlayer;
	public int numPlayers;
	public int[] playerLocations = {0, 0, 0, 0};
	public int[] playerMoney = {0,0,0,0};
	public int currentPlayer = 1;
	public String fileName = "";
	public static int[] propertyOwns = new int[40]; //the player that owns each property: 0 is bank, 1 is player 1, 2 is player 2, ...
	public static int[] propertyHouses = new int[40]; //the number of houses on each property: 5 is a hotel
	public TextArea txtProperties;
	public AnchorPane mainPane;
	
	@Override
	public void init(){
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
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		mainPane = new AnchorPane();
		
		double width = 800.0;

		backgroundCanvas = new Canvas();
		backgroundCanvas.setLayoutX(5);
		backgroundCanvas.setLayoutY(35);
		mainPane.getChildren().add(backgroundCanvas);
		
		playerPreviewCanvas = new Canvas(300, 300);
		playerPreviewCanvas.setLayoutX(width - 330);
		playerPreviewCanvas.setLayoutY(200);
		mainPane.getChildren().add(playerPreviewCanvas);
		
		lblCurrentPlayer = new Label();
		lblCurrentPlayer.setPrefSize(200, 20);
		lblCurrentPlayer.setLayoutX(width - 330);
		lblCurrentPlayer.setLayoutY(5);
		mainPane.getChildren().add(lblCurrentPlayer);
		
		btnRollDice = new Button("Roll Dice");
		btnRollDice.setPrefSize(150, 60);
		btnRollDice.setLayoutX(width - 330);
		btnRollDice.setLayoutY(25);
		btnRollDice.setFont(new Font("Courier New", 16));
		btnRollDice.setDisable(true);
		btnRollDice.setOnAction(event -> {
			rollDice();
		});
		mainPane.getChildren().add(btnRollDice);
		
		btnHouseHotel = new Button("Buy / Sell");
		btnHouseHotel.setPrefSize(150, 60);
		btnHouseHotel.setLayoutX(width - 120);
		btnHouseHotel.setLayoutY(25);
		btnHouseHotel.setFont(btnRollDice.getFont());
		btnHouseHotel.setDisable(true);
		btnHouseHotel.setOnAction(event -> {
			HouseHotel.showHouseHotel();
		});
		mainPane.getChildren().add(btnHouseHotel);
		
		dieOne = new ImageView();
		dieOne.setImage(die1);
		dieOne.setLayoutX(width - 330);
		dieOne.setLayoutY(90);
		dieOne.setFitWidth(100);
		dieOne.setFitHeight(100);
		mainPane.getChildren().add(dieOne);
		
		dieTwo = new ImageView();
		dieTwo.setImage(die2);
		dieTwo.setLayoutX(width - 220);
		dieTwo.setLayoutY(90);
		dieTwo.setFitWidth(100);
		dieTwo.setFitHeight(100);
		mainPane.getChildren().add(dieTwo);
		
		btnNewGame = new Button("New Game");
		btnNewGame.setPrefSize(150, 25);
		btnNewGame.setFont(new Font("Courier New", 14));
		btnNewGame.setLayoutX(5);
		btnNewGame.setLayoutY(5);
		btnNewGame.setOnAction(event -> {
			newGame(); //start a new game
		});
		mainPane.getChildren().add(btnNewGame);
		
		btnOpenGame = new Button("Open Game");
		btnOpenGame.setLayoutX(160);
		btnOpenGame.setLayoutY(5);
		btnOpenGame.setPrefSize(150, 25);
		btnOpenGame.setFont(btnNewGame.getFont());
		btnOpenGame.setOnAction(event -> {
			openGame(); //open an existing game
		});
		mainPane.getChildren().add(btnOpenGame);
		
		btnSaveGame = new Button("Save Game");
		btnSaveGame.setPrefSize(150, 25);
		btnSaveGame.setLayoutX(315);
		btnSaveGame.setLayoutY(5);
		btnSaveGame.setFont(btnNewGame.getFont());
		btnSaveGame.setOnAction(event -> {
			saveGame();
		});
		mainPane.getChildren().add(btnSaveGame);
		
		txtProperties = new TextArea("");
		txtProperties.setPrefSize(300, 175);
		txtProperties.setLayoutX(width - 330);
		txtProperties.setLayoutY(450);
		txtProperties.setFont(new Font("Courier New", 12));
		txtProperties.setEditable(false);
		mainPane.getChildren().add(txtProperties);
		
		Scene scene = new Scene(mainPane);
		ChangeListener<Number> cl = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				
				redraw();
			}
		};
		scene.widthProperty().addListener(cl); //redraw on resize
		scene.heightProperty().addListener(cl);
		
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(500);
		primaryStage.sizeToScene();
		primaryStage.setTitle("Monopoly");
		primaryStage.show();
		primaryStage.setMaximized(true);
	}
	
	/**
	 * This is the main method. Called by the Java Virtual Machine on the thread "main".
	 */
	public static void main(String[] args) {
		System.out.println("Game started");
		
		//launch this application. Calls the default constructor, init, and then start.
		Application.launch(args);
		
		System.out.println("Program closed");

	}
	
	/**
	 * This method creates a new Shell that shows the options for creating a new game. 
	 * When the button is clicked, it sets some values of variables on this class.
	 */
	private void newGame() {
		//prompt the user for the number of players
		String players = MessageBox.showInput("Monopoly", "Enter the number of players: ");
		int numPlayers;
		while(true){
			try {
				numPlayers = Integer.parseInt(players);
				if(numPlayers <= 8 && numPlayers >= 2){
					break;
				} else {
					players = MessageBox.showInput("Monopoly", "Enter the number of players: ");
				}
			} catch(NumberFormatException e){
				players = MessageBox.showInput("Monopoly", "Enter the number of players: ");
			}
			
		}
		// prompt the user for the number of starting cash
		String cash = MessageBox.showInput("Monopoly", "Enter the starting cash (must be >= 500): ");
		int numCash;
		while(true){
			try {
				numCash = Integer.parseInt(cash);
				if (numCash >= 500){
					break;
				} else {
					cash = MessageBox.showInput("Monopoly", "Enter the starting cash (must be > 500): ");
				}
			} catch(NumberFormatException e){
				cash = MessageBox.showInput("Monopoly", "Enter the starting cash (must be > 500): ");
			}
		}
		//prompt for the filename
		FileChooser fc = new FileChooser();
		fc.setSelectedExtensionFilter(new ExtensionFilter("Monopoly game file: (*.monop)", "monop"));
		File f = fc.showSaveDialog(primaryStage);
		if(f == null){ //the schedule was canceled
			return;
		}
		
		//set the variables
		long startTime = System.currentTimeMillis();
		this.fileName = f.getAbsolutePath();
		this.primaryStage.setTitle("Monopoly: " + f.getName());
		
		this.numPlayers = numPlayers;
		this.playerLocations = new int[this.numPlayers];
		this.playerMoney = new int[this.numPlayers];
		for(int i = 0; i < this.numPlayers; i++){
			this.playerMoney[i] = numCash;
		}
		for (int i = 1; i <= 40; i++){
			Monopoly.propertyOwns[i-1] = 0;
			Monopoly.propertyHouses[i-1] = 0;
		}
		this.currentPlayer = 1;
		this.btnRollDice.setDisable(false);
		this.btnHouseHotel.setDisable(false);
		
		this.saveGame();
		this.redraw();

		//get elapsed time to open
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time to create new: " + estimatedTime / 1000.0  + " seconds.");

	}

	
	private void openGame() {
		FileChooser fc = new FileChooser();
		fc.setSelectedExtensionFilter(new ExtensionFilter("Monopoly game file: (*.monop)", "monop"));
		File f = fc.showOpenDialog(primaryStage);
		if(f == null){ //canceled
			return;
		}
		String fn = f.getAbsolutePath();
		long startTime = System.currentTimeMillis();
		fileName = fn;
		primaryStage.setTitle("Monopoly: " + f.getName()); //the last name
		
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
			fr.close();
			btnRollDice.setDisable(false);
			btnHouseHotel.setDisable(false);
			redraw();


		} catch(FileNotFoundException e) {
			System.err.println("File not found");
		} catch(IOException e) {
			System.err.println("Error reading file");
		} catch(Exception e) {
			System.err.println("Other error: " + e.getMessage());
		}
		//get elapsed time to open
		long estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time to open game: " + estimatedTime / 1000.0  + " seconds.");
	}

	private void saveGame() {
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

	private void rollDice() {
		Random r = new Random();
		int one, two;
		one = r.nextInt(6-1+1) + 1;
		two = r.nextInt(6-1+1) + 1;
		setDie(dieOne, one);
		setDie(dieTwo, two);
		if (playerLocations[currentPlayer - 1] != 30) { //if the player is not in jail
			playerLocations[currentPlayer - 1] += one + two;
			// make the locations wrap so 42 is the 2nd spot
			playerLocations[currentPlayer - 1] = playerLocations[currentPlayer - 1] % 40; 
			System.out.println("Player " + currentPlayer + " rolled a " + (one+two) + 
					" and moved to " + Locations.getName(playerLocations[currentPlayer - 1]));
			redraw();
		} else {
			if (one == two) { //if they roll doubles, then they are out and move that many places
				playerLocations[currentPlayer - 1] = 10 + one + two;
			} else { //did not roll doubles, so only send them to just visiting
				playerLocations[currentPlayer - 1] = 10;
			}
			nextPlayer();
			redraw();
			return; //end of turn for the person in jail
		}
				
		//test if property is owned or not if it is not a reserved property (like go, income tax)
		//test if the player passes GO
		if (playerLocations[currentPlayer - 1] - one - two < 0 ) {
			MessageBox.showInfo("GO", "You passed GO.\nCollect $200");
			playerMoney[currentPlayer - 1] += 200;
			redraw();
			System.out.println("Player " + currentPlayer + " passed GO and collected $200");
		}
		//test for the place landed on
		switch (playerLocations[currentPlayer - 1]) {
		case 0: //GO
			break;
		case 4://income tax
			MessageBox.showInfo("Income Tax", "You landed on Income Tax. You paid $200 to the bank.");
			playerMoney[currentPlayer - 1] -= 200;
			System.out.println("Player " + currentPlayer + 
					" landed on Income Tax and paid $200 to the bank");
			break;
		case 10://just visiting
			break;
		case 20://free parking
			break;
		case 30://GO TO JAIL
			break;
		case 38://Luxury tax
			MessageBox.showInfo("Luxury Tax", "You landed on Luxury Tax. You paid $100 to the bank.");
			playerMoney[currentPlayer - 1] -= 100;
			System.out.println("Player " + currentPlayer + 
					" landed on Luxury Tax and paid $100 to the bank");
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
				boolean temp = MessageBox.showYesNo("Unowned property", 
						"The property: " + Locations.getName(playerLocations[currentPlayer - 1]) + 
						" is unowned. \nWould you like to buy it for: $" + 
						Locations.costToBuy(playerLocations[currentPlayer - 1]));
				if (temp) {
					if (playerMoney[currentPlayer - 1] >= 
							Locations.costToBuy(playerLocations[currentPlayer - 1])) {
						//transfer money and property
						propertyOwns[playerLocations[currentPlayer - 1]] = currentPlayer;
						playerMoney[currentPlayer - 1] -= 
								Locations.costToBuy(playerLocations[currentPlayer - 1]);
						System.out.println("Player " + currentPlayer + " bought " + 
								Locations.getName(playerLocations[currentPlayer - 1]) + " for $" + 
								Locations.costToBuy(playerLocations[currentPlayer - 1]));
					} else {
						MessageBox.showInfo("Not enough funds", 
								"You don't have enough money to buy this property");
					}
				}
			} else {
				//the player owes money to another player or they own it
				if (propertyOwns[playerLocations[currentPlayer - 1]] == currentPlayer) {
					MessageBox.showInfo("Property owned by you", "You own this property");
				} else {
					MessageBox.showInfo("Owned by someone else", "The property: " + 
							Locations.getName(playerLocations[currentPlayer - 1]) + 
							" is owned by player: " + propertyOwns[playerLocations[currentPlayer - 1]]
							+ "\nYou owe $" + Locations.rentOwed(playerLocations[currentPlayer - 1], 
							propertyHouses[playerLocations[currentPlayer-1]], one + two));
					// transfer funds
					playerMoney[currentPlayer - 1] -= 
							Locations.rentOwed(playerLocations[currentPlayer - 1], 
								propertyHouses[playerLocations[currentPlayer-1]], one + two);
					playerMoney[propertyOwns[playerLocations[currentPlayer - 1]] - 1] += 
							Locations.rentOwed(playerLocations[currentPlayer - 1], 
								propertyHouses[playerLocations[currentPlayer-1]], one + two);
					System.out.println("Player " + currentPlayer + " paid $" + 
							Locations.rentOwed(playerLocations[currentPlayer - 1], 
								propertyHouses[playerLocations[currentPlayer-1]], one + two) + 
								" to player " + propertyOwns[playerLocations[currentPlayer - 1]]);
					redraw();
				}
			}
		}
		
		//Test to see if the player is out of money
		if (playerMoney[currentPlayer - 1] < 0) {
			while (playerMoney[currentPlayer - 1] < 0) {
				
				if (MessageBox.showYesNo("Not enough funds", 
						"You must sell properties or declare bankruptcy.\nYou need: $" + 
						-playerMoney[currentPlayer - 1] + 
						"\nWould you like to sell properties")) { 
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
			MessageBox.showInfo("Reroll", "You rolled doubles\nYou get to reroll");
			rollDice();

		}else {
			nextPlayer();
		}

		redraw();

	}
	
	/**
	 * This method redraws the game board, the pieces, and resizes the controls.
	 * This is automatically called when the window's size changes, or the dice are rolled.
	 * There is no harm done is calling this method frequently
	 */
	public void redraw() {
		double width = primaryStage.getWidth();
		double height = primaryStage.getHeight();
		//if the window is not set, then return
		if(width == Double.NaN){ return; }

		//redo the background canvas to fit the bounds
		mainPane.getChildren().remove(backgroundCanvas);
		backgroundCanvas = new Canvas(width - 345, height - 95);
		backgroundCanvas.setLayoutX(5);
		backgroundCanvas.setLayoutY(35);
		mainPane.getChildren().add(backgroundCanvas);
		
		GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
		gc.drawImage(bgImg, 0, 0, backgroundCanvas.getWidth(), backgroundCanvas.getHeight());
		
		double ratioX = 1500.0 / backgroundCanvas.getWidth();
		double ratioY = 1500.0 / backgroundCanvas.getHeight();
		
		//draw the players
		for (int i = 1; i<=numPlayers; i++) {
			switch (i) {
			case 1: 
				gc.drawImage(p1, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[0]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[0]).getY() / ratioY - 20), 40, 40);
				break;
			case 2: 
				gc.drawImage(p2, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[1]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[1]).getY() / ratioY - 20), 40, 40);
				break;
			case 3: 
				gc.drawImage(p3, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[2]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[2]).getY() / ratioY - 20), 40, 40);
				break;
			case 4: 
				gc.drawImage(p4, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[3]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[3]).getY() / ratioY - 20), 40, 40);
				break;
			case 5: 
				gc.drawImage(p5, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[4]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[4]).getY() / ratioY - 20), 40, 40);
				break;
			case 6: 
				gc.drawImage(p6, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[5]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[5]).getY() / ratioY - 20), 40, 40);
				break;
			case 7: 
				gc.drawImage(p7, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[6]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[6]).getY() / ratioY - 20), 40, 40);
				break;
			case 8: 
				gc.drawImage(p8, 0, 0, 100, 100, 
						(int)(Locations.getPoint(playerLocations[7]).getX() / ratioX - 20), 
						(int)(Locations.getPoint(playerLocations[7]).getY() / ratioY - 20), 40, 40);
				break;
			}
			

		}
		
		//draw the houses/hotels
		for (int i = 1; i < 40; i++) {
			int temp = propertyHouses[i];
			if (temp != 0 && temp != 5) {
				for (int j = 1; j <= temp; j++) {
					gc.drawImage(houseImg, 0, 0, houseImg.getWidth(), houseImg.getHeight(), 
							(int)(Locations.getLocation(i, j).getX() / ratioX), 
							(int)(Locations.getLocation(i, j).getY() / ratioY), 20, 20);
				}
			} else if (temp == 5) {
				if (i < 10) {
					gc.drawImage(hotelImg, 0, 0, hotelImg.getWidth(), hotelImg.getHeight(), 
							(int)(Locations.getLocation(i, 4).getX() / ratioX), 
							(int)(Locations.getLocation(i, 4).getY() / ratioY), 45, 20);
				} else if (i > 20 && i < 30) {
					gc.drawImage(hotelImg, 0, 0, hotelImg.getWidth(), hotelImg.getHeight(), 
							(int)(Locations.getLocation(i, 0).getX() / ratioX), 
							(int)(Locations.getLocation(i, 3).getY() / ratioY), 45, 20);
				} else if (i > 10 && i < 20){
					gc.drawImage(hotelImg, 0, 0, hotelImg.getWidth(), hotelImg.getHeight(), 
							(int)(Locations.getLocation(i, 3).getX() / ratioX), 
							(int)(Locations.getLocation(i, 4).getY() / ratioY), 20, 45);
				} else {
					gc.drawImage(hotelImg, 0, 0, hotelImg.getWidth(), hotelImg.getHeight(), 
							(int)(Locations.getLocation(i, 3).getX() / ratioX), 
							(int)(Locations.getLocation(i, 0).getY() / ratioY), 20, 45);
				}
			}
		}
		
		//draw on the player preview
		GraphicsContext gc2 = playerPreviewCanvas.getGraphicsContext2D();
		//gc2.setFont(new Font("Courier New", 12));
		gc2.fillText("Players:", 10, 10);
		for (int i = 1; i<=numPlayers; i++) {
			switch (i) {
			case 1: 
				gc2.drawImage(p1, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				//the blank text is to clear the text
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[0]), 30, 30*i);
				break;
			case 2: 
				gc2.drawImage(p2, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[1]), 30, 30*i);
				break;
			case 3: 
				gc2.drawImage(p3, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[2]), 30, 30*i);
				break;
			case 4: 
				gc2.drawImage(p4, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[3]), 30, 30*i);
				break;
			case 5: 
				gc2.drawImage(p5, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[4]), 30, 30*i);
				break;
			case 6: 
				gc2.drawImage(p6, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[5]), 30, 30*i);
				break;
			case 7: 
				gc2.drawImage(p7, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[6]), 30, 30*i);
				break;
			case 8: 
				gc2.drawImage(p8, 0, 0, 100, 100, 0, 30 * i, 20, 20);
				gc2.fillText("                           ", 30, 30*i);
				gc2.fillText(Locations.getName(playerLocations[7]), 30, 30*i);
				break;
			}
			
			if (playerMoney[i - 1] >= 0) {
				gc2.fillText("$" + Integer.toString(playerMoney[i-1]), 200, 30*i); //draw the money
			} else {
				gc2.fillText("Out", 200, 30*i);
			}
			
		}
		
		playerPreviewCanvas.setLayoutX(width - 330);
		playerPreviewCanvas.setLayoutY(230);

		btnRollDice.setLayoutX(width - 330);
		btnHouseHotel.setLayoutX(width - 175);
		dieOne.setLayoutX(width - 330);
		dieTwo.setLayoutX(width - 220);
		lblCurrentPlayer.setLayoutX(width - 330);
		lblCurrentPlayer.setText("Current Player is: " + currentPlayer);
		txtProperties.setLayoutX(width - 330);
		txtProperties.setText("");
		//go through the properties
		for (int i = 1; i < 40; i++) {
			if (Locations.costToBuy(i) != 0) { //if the cost is 0 then the property can't be owned
				if (propertyOwns[i] == 0 ) {
					txtProperties.appendText(BetterString.padRight(Locations.getName(i), 25) 
							+ ": bank" + "\n");

				} else {
					txtProperties.appendText(BetterString.padRight(Locations.getName(i), 25) 
							+ ": player " + propertyOwns[i] + "\n");
				}
			}
		}
		txtProperties.selectHome(); //moves the caret to the start of the textbox
	}
	
	/**
	 * Sets the image on the ImageView to the corresponding value's image.
	 * @param x The ImageView instance to set the image of.
	 * @param value An int value from 1 to 6 (inclusive) corresponding to the number on the die.
	 */
	private void setDie(ImageView x, int value) {
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
	 * @return the Image from the name, or null if an error occured.
	 */
	public Image getImageFromResource(String resourceName) {
		InputStream i = null;
		try {
			i = new FileInputStream(resourceName);
			Image r = new Image(i);
			i.close();
			return r;
		} catch(IOException e){
			return null;
		} finally {
			//try to close the input stream no matter what
			if(i != null){
				try {
					i.close();
				} catch (IOException e) {}
			}
		}
	}
	
	/**
	 * This method moves the turn to the next player. 
	 * If that player has a negative balance (they are out), then the turn is moved to the next
	 */
	public void nextPlayer() {
		currentPlayer ++;
		if (currentPlayer > numPlayers) {
			currentPlayer = 1;
		}
		if (playerMoney[currentPlayer - 1] < 0) {
			nextPlayer();
		}
	}
	
	

}
