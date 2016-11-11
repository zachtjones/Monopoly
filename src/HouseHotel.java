import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

public class HouseHotel {
	/**
	 * The method shows the UI for selling properties/houses and buying properties/houses 
	 * as a modal dialog. <br>
	 * This will cause program execution to wait on the response of either: 
	 * buying/selling a property or buying/selling houses.
	 */
	public static void show(Monopoly monop) {
		System.out.println("A new UI for houses was created.");
		final ArrayList<Integer> ownedProps = new ArrayList<Integer>(0);
		final ArrayList<Integer> ownedOtherProps = new ArrayList<Integer>(0);
		final ArrayList<Integer> houseGroups = new ArrayList<Integer>(0);
		
		final Button btnBuyFrom = new Button("Buy property from player");
		final Button btnSell = new Button("Sell property to bank");
		final Button btnSellTo = new Button("Sell property to player");
		final Button btnAddHouses = new Button("Add houses");
		final Button btnSellHouses = new Button("Sell houses");

		Alert a = new Alert(AlertType.NONE);
		AnchorPane ap = new AnchorPane();
		
		ap.setLayoutX(monop.primaryStage.getX());
		ap.setLayoutY(monop.primaryStage.getY());
		ap.setPrefSize(monop.primaryStage.getWidth(), monop.primaryStage.getHeight());
		
		//info label
		Label lblInfo = new Label(
				"Only properties with 0 houses can be bought / sold");
		lblInfo.setLayoutX(5);
		lblInfo.setLayoutY(5);
		lblInfo.setFont(new Font("Courier New", 10));
		ap.getChildren().add(lblInfo);
		
		//header label
		Label lblHeader = new Label("Player " + monop.currentPlayer + 
				"'s Properties and values:     Other properties   recommended price");
		lblHeader.setLayoutX(5);
		lblHeader.setFont(new Font("Courier New", 14));
		lblHeader.setLayoutY(30);
		lblHeader.setPrefSize(700, 20);
		ap.getChildren().add(lblHeader);
		
		//choice box - displays what you have
		ChoiceBox<String> youOwn = new ChoiceBox<>();
		youOwn.setLayoutX(5);
		youOwn.setLayoutY(55);
		youOwn.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//when selection changes
				if (newValue.intValue() > -1) {
					btnSell.setDisable(false);
					btnSellTo.setDisable(false);
				}
			}		
		});
		//youOwn.setPrefSize(310, 40);
		ap.getChildren().add(youOwn);
		
		
		//choice box - displays what other players have
		ChoiceBox<String> theyOwn = new ChoiceBox<>();
		theyOwn.setLayoutX(320);
		theyOwn.setLayoutY(55);
		//theyOwn.setPrefSize(310, 40);
		theyOwn.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, 
					Number newValue) {
				//set the buy from button to be enabled when the selection index is not -1
				btnBuyFrom.setDisable(newValue.intValue() <= -1);
			}
		});
		ap.getChildren().add(theyOwn);
		
		
		btnSell.setLayoutX(5);
		btnSell.setLayoutY(235);
		btnSell.setPrefSize(150, 75);
		btnSell.setFont(new Font("Courier New", 12));
		btnSell.setDisable(true);
		btnSell.setOnAction(event -> {
			//sell the property to the bank (set the property as unowned and refund the player)
			int temp = ownedProps.get(youOwn.getSelectionModel().getSelectedIndex());
			Monopoly.propertyOwns[temp] = 0;
			monop.playerMoney[monop.currentPlayer - 1] += Locations.costToBuy(temp);
			a.close();
			monop.redraw();
		});
		ap.getChildren().add(btnSell);
		
		btnSellTo.setLayoutX(160);
		btnSellTo.setLayoutY(235);
		btnSellTo.setPrefSize(150, 75);
		btnSellTo.setFont(new Font("Courier New", 12));
		btnSellTo.setDisable(true);
		btnSellTo.setOnAction(event -> {
			int temp = ownedProps.get(youOwn.getSelectionModel().getSelectedIndex());
			try {
				int num = Integer.parseInt(MessageBox.showInput("Monopoly", 
						"What player do you wish to sell " + Locations.getName(temp) + " to?"));
				int num2 = Integer.parseInt(MessageBox.showInput("Monopoly", 
						"What amount do you wish to sell " + Locations.getName(temp) + " for?"));
				if (num > 0 && num <= monop.numPlayers) {
					if (MessageBox.showYesNo("Sell property", "Does player " + num + " agree to buy "
							+ Locations.getName(temp) + " for $" + num2 + "?")) {
						
						//test if the other player has enough money
						if (monop.playerMoney[num - 1] >= num2) { 
							//transfer funds and money
							monop.playerMoney[monop.currentPlayer - 1] += num2;
							monop.playerMoney[num - 1] -= num2;
							Monopoly.propertyOwns[temp] = num;
							a.close();
							monop.redraw();
						} else {
							MessageBox.showInfo("Not enough funds", "Player " + num +
									" does not have enough money for this transaction");
						}	
					}
				}
				
			} catch (NumberFormatException e1) {
				System.out.println("Number format exception: " + e1.getMessage());
			}
		});
		ap.getChildren().add(btnSellTo);
		
		btnBuyFrom.setLayoutX(320);
		btnBuyFrom.setLayoutY(235);
		btnBuyFrom.setPrefSize(310, 75);
		btnBuyFrom.setDisable(true);
		btnBuyFrom.setFont(new Font("Courier New", 12));
		btnBuyFrom.setOnAction(event -> {
			//get the selected index
			int temp = ownedOtherProps.get(theyOwn.getSelectionModel().getSelectedIndex());
			
			if (MessageBox.showYesNo("Agree to sell", "Does player " + Monopoly.propertyOwns[temp]
					+ " agree to sell " + Locations.getName(temp))) {
				try {
					int amount = Integer.parseInt(MessageBox.showInput("Enter amount", 
							"How much money does player " + Monopoly.propertyOwns[temp] + 
							" wish to sell " + Locations.getName(temp)));
					
					if (MessageBox.showYesNo("Agree to buy", "Does player " + monop.currentPlayer + 
						" agree to buy " + Locations.getName(temp) + " for $" + amount + "?")) {
						
						//test if the player has enough money
						if (monop.playerMoney[monop.currentPlayer - 1] >= amount) { 
							//transfer funds and property
							monop.playerMoney[monop.currentPlayer - 1] -= amount;
							monop.playerMoney[Monopoly.propertyOwns[temp] - 1] += amount;
							Monopoly.propertyOwns[temp] = monop.currentPlayer;
							a.close();
							monop.redraw();
						} else {
							MessageBox.showInfo("Not enough funds", "Player " + monop.currentPlayer 
									+ " does not have enough money for this transaction");
						}
						
					}
				} catch (NumberFormatException e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		ap.getChildren().add(btnBuyFrom);
		
		Label lblHouses = new Label("Edit houses/hotels for your groups (5 is a hotel)");
		lblHouses.setFont(new Font("Courier New", 12));
		lblHouses.setLayoutX(5);
		lblHouses.setLayoutY(315);
		lblHouses.setPrefSize(600, 20);
		ap.getChildren().add(lblHouses);
		
		ChoiceBox<String> lsHouses = new ChoiceBox<>();
		lsHouses.setLayoutX(5);
		lsHouses.setLayoutY(335);
		lsHouses.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				//when selection changes
				if (newValue.intValue() > -1) {
					btnAddHouses.setDisable(false);
					btnSellHouses.setDisable(false);
				}
			}
		});
		ap.getChildren().add(lsHouses);
		
		btnAddHouses.setFont(btnSell.getFont());
		btnAddHouses.setLayoutX(5);
		btnAddHouses.setLayoutY(540);
		btnAddHouses.setPrefSize(310, 75);
		btnAddHouses.setDisable(true);
		btnAddHouses.setOnAction(event -> {
			try {
				int temp = houseGroups.get(lsHouses.getSelectionModel().getSelectedIndex());
				int temp1 = Integer.parseInt(MessageBox.showInput("Monopoly", "Enter the number of houses to add"));
				
				int temp2 = Locations.costForHouses(temp, temp1);
				if (Locations.getNumberOfHouses(temp) + temp1 > 5) {
					MessageBox.showInfo("More than 5 houses", "You can't have more than 5 houses on a property"); 
				}else if (temp1 <= 0) {
					MessageBox.showInfo("Negative houses", "You can't add negative or zero houses.");
				} else {
					if (monop.playerMoney[monop.currentPlayer - 1] < temp2) {
						MessageBox.showInfo("Not enough money", "You don't have enough money for this transaction.");
					} else {
						if (MessageBox.showYesNo("Cost $" + temp2, "This will cost you $" + temp2 + "\nBuy houses?")) {
							Locations.addHouses(temp, temp1);
							monop.playerMoney[monop.currentPlayer - 1] -= temp2;
							a.close();
							monop.redraw();
						}
					}

				}
			} catch (NumberFormatException e1) {
				System.out.println("Number format exception: " + e1.getMessage());
			}
		});
		ap.getChildren().add(btnAddHouses);
		
		btnSellHouses.setFont(btnSell.getFont());
		btnSellHouses.setLayoutX(320);
		btnSellHouses.setLayoutY(540);
		btnSellHouses.setPrefSize(310, 75);
		btnSellHouses.setDisable(true);
		btnSellHouses.setOnAction(event -> {
			try {
				int temp = houseGroups.get(lsHouses.getSelectionModel().getSelectedIndex());
				int temp1 = Integer.parseInt(MessageBox.showInput("Monopoly", "Enter the number of houses to sell"));

				int temp2 = Locations.costForHouses(temp, temp1);
				if (Locations.getNumberOfHouses(temp) - temp1 < 0) {
					MessageBox.showInfo("Less than 0 houses", "You can't have less than 0 houses on a property"); 
				}else if (temp1 <= 0) {
					MessageBox.showInfo("Negative houses", "You can't remove negative or zero houses.");
				} else {
					if (MessageBox.showYesNo("Cost $" + temp2, "This will give you $" + temp2 + "\nSell houses?")) {
						Locations.removeHouses(temp, temp1);
						monop.playerMoney[monop.currentPlayer - 1] += temp2;
						a.close();
						monop.redraw();
					}
				}
			} catch (NumberFormatException e1) {
				System.out.println("Number format exception: " + e1.getMessage());
			}
		});
		ap.getChildren().add(btnSellHouses);
		
		for (int i = 1; i < 40; i++) { 
			ArrayList<String> temp = new ArrayList<>();
			//you can't buy or sell a property with houses on it
			if (Monopoly.propertyOwns[i] == monop.currentPlayer && Monopoly.propertyHouses[i] == 0) { 
				temp.add(BetterString.padRight(Locations.getName(i), 25) + " $" + Locations.costToBuy(i));
				ownedProps.add(i);
			}
			youOwn.setItems(FXCollections.observableArrayList(temp));
			temp.clear();
			if (Monopoly.propertyOwns[i] != 0 && Monopoly.propertyOwns[i] != monop.currentPlayer && Monopoly.propertyHouses[i] == 0) {
				temp.add(BetterString.padRight(Locations.getName(i), 25) + " $" + Locations.costToBuy(i));
				ownedOtherProps.add(i);
			}
			theyOwn.setItems(FXCollections.observableArrayList(temp));
		}
		
		ArrayList<String> tempHouses = new ArrayList<>();
		for (int i = 0; i <= 7; i++) {
			if (Locations.canAddHouses(i, monop.currentPlayer) || Locations.canRemoveHouses(i,  monop.currentPlayer)) {
				tempHouses.add(Locations.getNameOfGroup(i) + ": Houses: " + Locations.getNumberOfHouses(i));
				houseGroups.add(i);
			}
		}
		lsHouses.setItems(FXCollections.observableArrayList(tempHouses));
		
		a.getDialogPane().setContent(ap);
		ButtonType b = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		a.setTitle("Edit properties, houses, and hotels");
		a.getButtonTypes().add(b);
		a.showAndWait();
	}
}
