import java.util.Random;

/**
 * This class holds static methods for the chance and community chest cards.
 * 
 * @author zachjones
 */
public class ChanceAndCommunityChest {

	/**
	 * Call this method when a player lands on a chance space. 
	 * This method handles all of the code that is to be executed when the player lands on chance. 
	 * This method automatically changes the turn as well.
	 * @param playerNum The current player (player 1 is 1, player 2 is 2, ...)
	 * @param numDice The sum of the dice rolled (used if a makes the player gets moved to a utility)
	 * @param monop The Monopoly game instance to change the properties when this card is drawn.
	 */
	public static void newChance(int playerNum, int numDice, Monopoly monop) {
		Random r = new Random();
		int temp = r.nextInt(16); //between 0 and 15 inclusive
		switch (temp) {
		case 0: 
			MessageBox.showInfo("Chance", "Advance to Go \nCollect $200");
			monop.playerLocations[playerNum - 1] = 0;
			monop.playerMoney[playerNum - 1] += 200;
			break;
		case 1:
			MessageBox.showInfo("Chance", "Advance to Illinois Avenue. \nIf you pass Go, collect $200");
			//24 is Illinois Avenue
			if (monop.playerLocations[playerNum - 1] > 24) {//passes go
				monop.playerMoney[playerNum - 1] += 200;
			}
			monop.playerLocations[playerNum - 1] = 24;
			monop.redraw();
			transactions(24, playerNum, numDice, monop);
			monop.redraw();
			break;
		case 2:
			MessageBox.showInfo("Chance", 
					"Advance to St. Charles Place. \nIf you pass Go, collect $200");
			//St charles is 11
			if (monop.playerLocations[playerNum - 1] > 11) { //passes go
				monop.playerMoney[playerNum - 1] += 200;
			}
			monop.playerLocations[playerNum - 1] = 11;
			monop.redraw();
			
			transactions(11, playerNum, numDice, monop);
			monop.redraw();
			break;
		case 3:
			MessageBox.showInfo("Chance", 
					"Advance token to nearest utility \nIf you pass Go, collect $200");
			if (monop.playerLocations[playerNum - 1] == 7) {
				monop.playerLocations[playerNum - 1] = 12;
				monop.redraw();
				transactions(12, playerNum, numDice, monop);
			} else if (monop.playerLocations[playerNum - 1] == 22) {
				monop.playerLocations[playerNum - 1] = 28;
				monop.redraw();
				transactions(28, playerNum, numDice, monop);
			} else { //36 so passes go
				monop.playerLocations[playerNum - 1] = 12;
				monop.playerMoney[playerNum - 1] += 200;
				monop.redraw();
				transactions(12, playerNum, numDice, monop);
			}
			break;
		case 4:
			MessageBox.showInfo("Chance", "Advance token to nearest Railroad and pay the owner" + 
				" twice the rent to which he/she is otherwise entitled.");
			if (monop.playerLocations[playerNum - 1] == 7) {
				monop.playerLocations[playerNum - 1] = 15;
				monop.redraw();
				transactions(15, playerNum, numDice, monop);
			} else if (monop.playerLocations[playerNum - 1] == 22) {
				monop.playerLocations[playerNum - 1] = 25;
				monop.redraw();
				transactions(25, playerNum, numDice, monop);
			} else { //36 so passes go
				monop.playerLocations[playerNum - 1] = 5;
				monop.playerMoney[playerNum - 1] += 200;
				monop.redraw();
				transactions(5, playerNum, numDice, monop);
			}
			break;
		case 5: //there are two nearest railroad cards
			MessageBox.showInfo("Chance", "Advance token to nearest Railroad and pay the owner" + 
					" twice the rent to which he/she is otherwise entitled.");
			if (monop.playerLocations[playerNum - 1] == 7) {
				monop.playerLocations[playerNum - 1] = 15;
				monop.redraw();
				transactions(15, playerNum, numDice, monop);
			} else if (monop.playerLocations[playerNum - 1] == 22) {
				monop.playerLocations[playerNum - 1] = 25;
				monop.redraw();
				transactions(25, playerNum, numDice, monop);
			} else { //36 so passes go
				monop.playerLocations[playerNum - 1] = 5;
				monop.playerMoney[playerNum - 1] += 200;
				monop.redraw();
				transactions(5, playerNum, numDice, monop);
			}
			break;
		case 6:
			MessageBox.showInfo("Chance", "Bank pays you a dividend of $50");
			monop.playerMoney[playerNum - 1] += 50;
			monop.redraw();
			break;
		case 7:
			MessageBox.showInfo("Chance", "Go back 3 spaces");
			if (monop.playerLocations[playerNum - 1] == 7) {
				MessageBox.showInfo("Income Tax", "You landed on Income Tax. " + 
						"You paid $200 to the bank.");
				monop.playerMoney[playerNum - 1] -= 200;
				monop.playerLocations[playerNum - 1] = 4;
				System.out.println("Player " + playerNum + 
						" landed on Income Tax and paid $200 to the bank");
			} else if (monop.playerLocations[playerNum - 1] == 22) {
				monop.playerLocations[playerNum - 1] = 19;
				transactions(19, playerNum, numDice, monop);
			} else { //36 - moves to Comm. Chest
				monop.playerLocations[playerNum - 1] = 33;
				newCommunityChest(playerNum, monop);
				return; //don't want the player's turn to get switched twice
			}
			break;
		case 8:
			MessageBox.showInfo("Chance", "Go to Jail\nDo not pass Go, do not collect $200");
			monop.playerLocations[playerNum - 1] = 30;
			System.out.println("Player " + playerNum + " went to Jail");
			break;
		case 9:
			MessageBox.showInfo("Chance", 
					"Make general repairs on all your property - " + 
					"For each house pay $25 - For each hotel $100\nYou owe: $" + 
					Locations.costOfRepairsChance(playerNum));
			monop.playerMoney[playerNum - 1] -= Locations.costOfRepairsChance(playerNum);
			System.out.println("Player " + playerNum + " owed $" + 
					Locations.costOfRepairsChance(playerNum) + " for repairs.");
			break;
		case 10:
			MessageBox.showInfo("Chance", "Pay poor tax of $15");
			monop.playerMoney[playerNum - 1] -= 15;
			System.out.println("Player " + playerNum + " paid $15 poor tax");
			break;
		case 11:
			MessageBox.showInfo("Chance", 
					"Take a trip on the Reading Railroad - If you pass Go, collect $200");
			//you always pass GO
			monop.playerMoney[playerNum - 1] += 200;
			monop.playerLocations[playerNum - 1] = 5;
			transactions(5, playerNum, numDice, monop);
			break;
		case 12:
			MessageBox.showInfo("Chance", "Take a walk on the BoardWalk");
			monop.playerLocations[playerNum - 1] = 39;
			transactions(39, playerNum, numDice, monop);
			break;
		case 13:
			MessageBox.showInfo("Chance", 
					"You have been elected Chairman of the Board - Pay each player $50");
			for(int i = 0; i < monop.numPlayers; i++){
				if (monop.playerMoney[i] >= 0) {
					monop.playerMoney[i] += 50; 
					//you also gain 50, but later in this block, it is subtracted
				}
			}
			monop.playerMoney[playerNum - 1] -= monop.numPlayers * 50; //it is subtracted here
			break;
		case 14:
			MessageBox.showInfo("Chance", "You building loan matures - Collect $150");
			monop.playerMoney[playerNum - 1] +=  150;
			break;
		case 15:
			MessageBox.showInfo("Chance", "You have won a crossword competition - Collect $100");
			monop.playerMoney[playerNum - 1] +=  100;
			break;
		}
		
		/*Test to see if the player is out of money	*/
		if (monop.playerMoney[monop.currentPlayer - 1] < 0) {
			while (monop.playerMoney[monop.currentPlayer - 1] < 0) {
				
				if (MessageBox.showYesNo("Not enough funds", 
						"You must sell properties or declare bankruptcy.\nYou need: $"
						+ -monop.playerMoney[monop.currentPlayer - 1] + 
						"\nWould you like to sell properties") == true) { 
					HouseHotel.show();
					monop.redraw();
				} else {
					//they are out so return everything to the bank
					for(int i = 0; i<40; i++) {
						if (Monopoly.propertyOwns[i] == monop.currentPlayer) {
							Monopoly.propertyOwns[i] = 0;
							Monopoly.propertyHouses[i] = 0;
						}
					}	
					monop.nextPlayer();
					monop.redraw();
				}
			}
		}
		monop.nextPlayer();
		monop.redraw();
	}
	
	/**
	 * Call this method when a player lands on a Community Chest space. 
	 * This method handles all of the code that is to be executed when the
	 *  player lands on Community Chest. 
	 * This method automatically changes the turn as well.
	 * @param playerNum The current player (player 1 is 1, player 2 is 2, ...)
	 * @param numDice The sum of the dice rolled (used if a makes the player gets moved to a utility)
	 * @param monop The Monopoly game instance to change the properties when this card is drawn.
	 */
	public static void newCommunityChest(int playerNum, Monopoly monop) {
		Random r = new Random();
		int temp = r.nextInt(16); //between 0 and 15 inclusive
		switch (temp) {
		case 0:
			MessageBox.showInfo("Community Chest", "Advance to Go \nCollect $200");
			monop.playerLocations[playerNum - 1] = 0;
			monop.playerMoney[playerNum - 1] += 200;
			break;
		case 1:
			MessageBox.showInfo("Community Chest", "Bank error in your favor\nCollect $200");
			monop.playerMoney[playerNum - 1] += 200;
			break;
		case 2:
			MessageBox.showInfo("Community Chest", "Doctor's fees\nPay $50");
			monop.playerMoney[playerNum - 1] -= 50;
			break;			
		case 3:
			MessageBox.showInfo("Community Chest", "From sale of stock you get $50");
			monop.playerMoney[playerNum - 1] += 50;
			break;
		case 4:
			MessageBox.showInfo("Community Chest", "Go to Jail\nDo not pass Go, do not collect $200");
			monop.playerLocations[playerNum - 1] = 30;
			System.out.println("Player " + playerNum + " went to Jail");
			break;
		case 5:
			MessageBox.showInfo("Community Chest", 
				"Grand Opera Night Opening - Collect $50 from every player for opening night seats");
			for(int i = 0; i < monop.numPlayers; i++){
				monop.playerMoney[i] -= 50; //you also lose 50, but later in this block, it is added
			}
			monop.playerMoney[playerNum - 1] += monop.numPlayers * 50; //it is added here
			break;
		case 6:
			MessageBox.showInfo("Community Chest", "Holiday Fund Matures - Recieve $100");
			monop.playerMoney[playerNum - 1] += 100;
			break;
		case 7:
			MessageBox.showInfo("Community Chest", "Income tax refund - Collect $20");
			monop.playerMoney[playerNum - 1] += 20;
			break;
		case 8:
			MessageBox.showInfo("Community Chest", 
					"It is your birthday - Collect $10 from each player");
			for(int i = 0; i < monop.numPlayers; i++){
				monop.playerMoney[i] -= 10; //you also lose 10, but later in this block, it is added
			}
			monop.playerMoney[playerNum - 1] += monop.numPlayers * 10; //it is added here
			break;
		case 9:
			MessageBox.showInfo("Community Chest", "Life insurace matures - Recieve $100");
			monop.playerMoney[playerNum - 1] += 100;
			break;
		case 10:
			MessageBox.showInfo("Community Chest", "Pay hospital fee of $100");
			monop.playerMoney[playerNum - 1] -= 100;
			break;
		case 11:
			MessageBox.showInfo("Community Chest", "Pay school fees of $150");
			monop.playerMoney[playerNum - 1] -= 150;
			break;
		case 12:
			MessageBox.showInfo("Community Chest", "Receive $25 consultancy fee");
			monop.playerMoney[playerNum - 1] += 25;
			break;
		case 13:
			MessageBox.showInfo("Community Chest", 
					"You are assessed for street repairs - $40 per house - $115 per hotel\nYou owe: $ "
					+ Locations.costOfRepairsCommunityChest(playerNum));
			monop.playerMoney[playerNum - 1] -= Locations.costOfRepairsCommunityChest(playerNum);
			System.out.println("Player " + playerNum + " owed $" + 
					Locations.costOfRepairsCommunityChest(playerNum) + " for repairs.");
			break;
		case 14:
			MessageBox.showInfo("Community Chest", 
					"You have won second prize in a beauty contest - Collect $10");
			monop.playerMoney[playerNum - 1] += 10;
			break;
		case 15:
			MessageBox.showInfo("Community Chest", "You inherit $100");
			monop.playerMoney[playerNum - 1] += 100;
			break;
		}
		/*Test to see if the player is out of money	*/
		if (monop.playerMoney[monop.currentPlayer - 1] < 0) {
			while (monop.playerMoney[monop.currentPlayer - 1] < 0) {
				
				if (MessageBox.showYesNo("Not enough funds", 
						"You must sell properties or declare bankruptcy.\nYou need: $" 
						+ -monop.playerMoney[monop.currentPlayer - 1] 
						+ "\nWould you like to sell properties")) { 
					
					HouseHotel.show();
					monop.redraw();
				} else {
					//they are out so return everything to the bank
					for(int i = 0; i<40; i++) {
						if (Monopoly.propertyOwns[i] == monop.currentPlayer) {
							Monopoly.propertyOwns[i] = 0;
							Monopoly.propertyHouses[i] = 0;
						}
					}	
					monop.nextPlayer();
					monop.redraw();
				}
			}
		}
		monop.nextPlayer();
		monop.redraw();
	}
	
	/**
	 * Inner method used to simplify the logic process behind moving to another place from either
	 *  chance or community chest.
	 * @param locationOnBoard The location on the game board (0 is go, 39 is boardwalk)
	 * @param playerNum The current player (1 is player 1, 2 is player 2, ...)
	 * @param numDice The total of the dice rolled (used for water works and electric company)
	 * @param monop The Monopoly game instance to change the properties of
	 */
	private static void transactions(int locationOnBoard, int playerNum, int numDice, Monopoly monop) {
		if (Monopoly.propertyOwns[locationOnBoard] == 0) {
			boolean temp2 = MessageBox.showYesNo("Unowned property", "The property: " + 
				Locations.getName(locationOnBoard) + " is unowned. \nWould you like to buy it for: $" +
					Locations.costToBuy(locationOnBoard));
			if (temp2) {
				//transfer money and property
				Monopoly.propertyOwns[locationOnBoard] = playerNum;
				monop.playerMoney[playerNum - 1] -= 
						Locations.costToBuy(monop.playerLocations[playerNum - 1]);
				System.out.println("Player " + playerNum + " bought " + 
						Locations.getName(monop.playerLocations[playerNum - 1]) + " for $" + 
						Locations.costToBuy(monop.playerLocations[playerNum - 1]));
			}
		} else {
			//the player owes money to another player or they own it
			if (Monopoly.propertyOwns[locationOnBoard] == playerNum) {
				MessageBox.showInfo("Property owned by you", "You own this property");
			} else {
				if (locationOnBoard == 5 || locationOnBoard == 15 || locationOnBoard == 25) {
					//double the rent
					int rentOwed = 2*Locations.rentOwed(locationOnBoard, 
							Monopoly.propertyHouses[locationOnBoard], numDice);
					MessageBox.showInfo("Owned by someone else", "The property: " + 
							Locations.getName(monop.playerLocations[playerNum - 1]) + 
							" is owned by player: " + Monopoly.propertyOwns[locationOnBoard] + 
							"\nYou owe $" + rentOwed);
					
					monop.playerMoney[playerNum - 1] -= rentOwed;
					monop.playerMoney[Monopoly.propertyOwns[monop.playerLocations[playerNum - 1]] - 1]
							+= rentOwed;
					System.out.println("Player " + playerNum + " paid $" + rentOwed);
					monop.redraw();
				} else {
					int rentOwed = Locations.rentOwed(locationOnBoard, 
							Monopoly.propertyHouses[locationOnBoard], numDice);
					
					MessageBox.showInfo("Owned by someone else", "The property: " + 
							Locations.getName(monop.playerLocations[playerNum - 1]) + 
							" is owned by player: " + Monopoly.propertyOwns[locationOnBoard] + 
							"\nYou owe $" + rentOwed);
					
					// transfer funds
					monop.playerMoney[playerNum - 1] -= rentOwed;
					monop.playerMoney[Monopoly.propertyOwns[monop.playerLocations[playerNum - 1]] - 1]
							+= rentOwed;
					System.out.println("Player " + playerNum + " paid $" + rentOwed + " to player "
							+ Monopoly.propertyOwns[locationOnBoard]);
					monop.redraw();
				}
			}
		}
	}
	
}
