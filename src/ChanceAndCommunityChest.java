import java.util.Random;

import org.eclipse.swt.SWT;

public class ChanceAndCommunityChest {

	/**
	 * Call this method when a player lands on a chance space. 
	 * This method handles all of the code that is to be executed when the player lands on chance. 
	 * This method automatically changes the turn as well.
	 * @param playerNum The current player (player 1 is 1, player 2 is 2, ...)
	 * @param numDice The sum of the dice rolled (used if a makes the player gets moved to a utility)
	 */
	public static void newChance(int playerNum, int numDice) {
		Random r = new Random();
		int temp = r.nextInt(16); //between 0 and 15 inclusive
		switch (temp) {
		case 0: 
			MessageBoxShow.show("Chance", "Advance to Go \nCollect $200", Monopoly.sh, SWT.OK);
			Monopoly.playerLocations[playerNum - 1] = 0;
			Monopoly.playerMoney[playerNum - 1] += 200;break;
		case 1:
			MessageBoxShow.show("Chance", "Advance to Illinois Avenue. \nIf you pass Go, collect $200", Monopoly.sh, SWT.OK);
			//24 is Illinois Avenue
			if (Monopoly.playerLocations[playerNum - 1] > 24) {//passes go
				Monopoly.playerMoney[playerNum - 1] += 200;
			}
			Monopoly.playerLocations[playerNum - 1] = 24;
			Monopoly.redraw();
			transactions(24, playerNum, numDice);
			Monopoly.redraw();
			break;
		case 2:
			MessageBoxShow.show("Chance", "Advance to St. Charles Place. \nIf you pass Go, collect $200", Monopoly.sh, SWT.OK);
			//St charles is 11
			if (Monopoly.playerLocations[playerNum - 1] > 11) { //passes go
				Monopoly.playerMoney[playerNum - 1] += 200;
			}
			Monopoly.playerLocations[playerNum - 1] = 11;
			Monopoly.redraw();
			
			transactions(11, playerNum, numDice);
			Monopoly.redraw();
			break;
		case 3:
			MessageBoxShow.show("Chance", "Advance token to nearest utility \nIf you pass Go, collect $200", Monopoly.sh, SWT.OK);
			if (Monopoly.playerLocations[playerNum - 1] == 7) {
				Monopoly.playerLocations[playerNum - 1] = 12;
				Monopoly.redraw();
				transactions(12, playerNum, numDice);
			} else if (Monopoly.playerLocations[playerNum - 1] == 22) {
				Monopoly.playerLocations[playerNum - 1] = 28;
				Monopoly.redraw();
				transactions(28, playerNum, numDice);
			} else { //36 so passes go
				Monopoly.playerLocations[playerNum - 1] = 12;
				Monopoly.playerMoney[playerNum - 1] += 200;
				Monopoly.redraw();
				transactions(12, playerNum, numDice);
			}
			break;
		case 4:
			MessageBoxShow.show("Chance", "Advance token to nearest Railroad and pay the owner twice the rent to which he/she is otherwise entitled.", Monopoly.sh, SWT.OK);
			if (Monopoly.playerLocations[playerNum - 1] == 7) {
				Monopoly.playerLocations[playerNum - 1] = 15;
				Monopoly.redraw();
				transactions(15, playerNum, numDice);
			} else if (Monopoly.playerLocations[playerNum - 1] == 22) {
				Monopoly.playerLocations[playerNum - 1] = 25;
				Monopoly.redraw();
				transactions(25, playerNum, numDice);
			} else { //36 so passes go
				Monopoly.playerLocations[playerNum - 1] = 5;
				Monopoly.playerMoney[playerNum - 1] += 200;
				Monopoly.redraw();
				transactions(5, playerNum, numDice);
			}
			break;
		case 5: //there are two nearest railroad cards
			MessageBoxShow.show("Chance", "Advance token to nearest Railroad and pay the owner twice the rent to which he/she is otherwise entitled.", Monopoly.sh, SWT.OK);
			if (Monopoly.playerLocations[playerNum - 1] == 7) {
				Monopoly.playerLocations[playerNum - 1] = 15;
				Monopoly.redraw();
				transactions(15, playerNum, numDice);
			} else if (Monopoly.playerLocations[playerNum - 1] == 22) {
				Monopoly.playerLocations[playerNum - 1] = 25;
				Monopoly.redraw();
				transactions(25, playerNum, numDice);
			} else { //36 so passes go
				Monopoly.playerLocations[playerNum - 1] = 5;
				Monopoly.playerMoney[playerNum - 1] += 200;
				Monopoly.redraw();
				transactions(5, playerNum, numDice);
			}
			break;
		case 6:
			MessageBoxShow.show("Chance", "Bank pays you a dividend of $50", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 50;
			Monopoly.redraw();
			break;
		case 7:
			MessageBoxShow.show("Chance", "Go back 3 spaces", Monopoly.sh, SWT.OK);
			if (Monopoly.playerLocations[playerNum - 1] == 7) {
				MessageBoxShow.show("Income Tax", "You landed on Income Tax. You paid $200 to the bank.", Monopoly.sh, SWT.OK);
				Monopoly.playerMoney[playerNum - 1] -= 200;
				Monopoly.playerLocations[playerNum - 1] = 4;
				System.out.println("Player " + playerNum + " landed on Income Tax and paid $200 to the bank");
			} else if (Monopoly.playerLocations[playerNum - 1] == 22) {
				Monopoly.playerLocations[playerNum - 1] = 19;
				transactions(19, playerNum, numDice);
			} else { //36 - moves to Comm. Chest
				Monopoly.playerLocations[playerNum - 1] = 33;
				newCommunityChest(playerNum);
				return; //don't want the player's turn to get switched twice
			}
			break;
		case 8:
			MessageBoxShow.show("Chance", "Go to Jail\nDo not pass Go, do not collect $200", Monopoly.sh, SWT.OK);
			Monopoly.playerLocations[playerNum - 1] = 30;
			System.out.println("Player " + playerNum + " went to Jail");
			break;
		case 9:
			MessageBoxShow.show("Chance", "Make general repairs on all your property - For each house pay $25 - For each hotel $100\nYou owe: $" + Locations.costOfRepairsChance(playerNum), Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] -= Locations.costOfRepairsChance(playerNum);
			System.out.println("Player " + playerNum + " owed $" + Locations.costOfRepairsChance(playerNum) + " for repairs.");
			break;
		case 10:
			MessageBoxShow.show("Chance", "Pay poor tax of $15", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] -= 15;
			System.out.println("Player " + playerNum + " paid $15 poor tax");
			break;
		case 11:
			MessageBoxShow.show("Chance", "Take a trip on the Reading Railroad - If you pass Go, collect $200", Monopoly.sh, SWT.OK);
			//you always pass GO
			Monopoly.playerMoney[playerNum - 1] += 200;
			Monopoly.playerLocations[playerNum - 1] = 5;
			transactions(5, playerNum, numDice);
			break;
		case 12:
			MessageBoxShow.show("Chance", "Take a walk on the BoardWalk", Monopoly.sh, SWT.OK);
			Monopoly.playerLocations[playerNum - 1] = 39;
			transactions(39, playerNum, numDice);
			break;
		case 13:
			MessageBoxShow.show("Chance", "You have been elected Chairman of the Board - Pay each player $50", Monopoly.sh, SWT.OK);
			for(int i = 0; i < Monopoly.numPlayers; i++){
				if (Monopoly.playerMoney[i] >= 0) {
					Monopoly.playerMoney[i] += 50; //you also gain 50, but later in this block, it is subtracted
				}
			}
			Monopoly.playerMoney[playerNum - 1] -= Monopoly.numPlayers * 50; //it is subtracted here
			break;
		case 14:
			MessageBoxShow.show("Chance", "You building loan matures - Collect $150", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] +=  150;
			break;
		case 15:
			MessageBoxShow.show("Chance", "You have won a crossword competition - Collect $100", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] +=  100;
			break;
		}
		
		/*Test to see if the player is out of money	*/
		if (Monopoly.playerMoney[Monopoly.currentPlayer - 1] < 0) {
			while (Monopoly.playerMoney[Monopoly.currentPlayer - 1] < 0) {
				
				if (MessageBoxShow.show("Not enough funds", "You must sell properties or declare bankruptcy.\nYou need: $" + -Monopoly.playerMoney[Monopoly.currentPlayer - 1] + "\nWould you like to sell properties", Monopoly.sh, SWT.YES | SWT.NO) == SWT.YES) { 
					HouseHotel.showHouseHotel();
					Monopoly.redraw();
				} else {
					//they are out so return everything to the bank
					for(int i = 0; i<40; i++) {
						if (Monopoly.propertyOwns[i] == Monopoly.currentPlayer) {
							Monopoly.propertyOwns[i] = 0;
							Monopoly.propertyHouses[i] = 0;
						}
					}	
					Monopoly.nextPlayer();
					Monopoly.redraw();
				}
			}
		}
		Monopoly.nextPlayer();
		Monopoly.redraw();
	}
	/**
	 * Call this method when a player lands on a Community Chest space. 
	 * This method handles all of the code that is to be executed when the player lands on Community Chest. 
	 * This method automatically changes the turn as well.
	 * @param playerNum The current player (player 1 is 1, player 2 is 2, ...)
	 * @param numDice The sum of the dice rolled (used if a makes the player gets moved to a utility)
	 */
	public static void newCommunityChest(int playerNum) {
		Random r = new Random();
		int temp = r.nextInt(16); //between 0 and 15 inclusive
		switch (temp) {
		case 0:
			MessageBoxShow.show("Community Chest", "Advance to Go \nCollect $200", Monopoly.sh, SWT.OK);
			Monopoly.playerLocations[playerNum - 1] = 0;
			Monopoly.playerMoney[playerNum - 1] += 200;
			break;
		case 1:
			MessageBoxShow.show("Community Chest", "Bank error in your favor\nCollect $200", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 200;
			break;
		case 2:
			MessageBoxShow.show("Community Chest", "Doctor's fees\nPay $50", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] -= 50;
			break;			
		case 3:
			MessageBoxShow.show("Community Chest", "From sale of stock you get $50", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 50;
			break;
		case 4:
			MessageBoxShow.show("Community Chest", "Go to Jail\nDo not pass Go, do not collect $200", Monopoly.sh, SWT.OK);
			Monopoly.playerLocations[playerNum - 1] = 30;
			System.out.println("Player " + playerNum + " went to Jail");
			break;
		case 5:
			MessageBoxShow.show("Community Chest", "Grand Opera Night Opening - Collect $50 from every player for opening night seats", Monopoly.sh, SWT.OK);
			for(int i = 0; i < Monopoly.numPlayers; i++){
				Monopoly.playerMoney[i] -= 50; //you also lose 50, but later in this block, it is added
			}
			Monopoly.playerMoney[playerNum - 1] += Monopoly.numPlayers * 50; //it is added here
			break;
		case 6:
			MessageBoxShow.show("Community Chest", "Holiday Fund Matures - Recieve $100", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 100;
			break;
		case 7:
			MessageBoxShow.show("Community Chest", "Income tax refund - Collect $20", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 20;
			break;
		case 8:
			MessageBoxShow.show("Community Chest", "It is your birthday - Collect $10 from each player", Monopoly.sh, SWT.OK);
			for(int i = 0; i < Monopoly.numPlayers; i++){
				Monopoly.playerMoney[i] -= 10; //you also lose 10, but later in this block, it is added
			}
			Monopoly.playerMoney[playerNum - 1] += Monopoly.numPlayers * 10; //it is added here
			break;
		case 9:
			MessageBoxShow.show("Community Chest", "Life insurace matures - Recieve $100", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 100;
			break;
		case 10:
			MessageBoxShow.show("Community Chest", "Pay hospital fee of $100", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] -= 100;
			break;
		case 11:
			MessageBoxShow.show("Community Chest", "Pay school fees of $150", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] -= 150;
			break;
		case 12:
			MessageBoxShow.show("Community Chest", "Receive $25 consultancy fee", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 25;
			break;
		case 13:
			MessageBoxShow.show("Community Chest", "You are assessed for street repairs - $40 per house - $115 per hotel\nYou owe: $ " + Locations.costOfRepairsCommunityChest(playerNum), Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] -= Locations.costOfRepairsCommunityChest(playerNum);
			System.out.println("Player " + playerNum + " owed $" + Locations.costOfRepairsCommunityChest(playerNum) + " for repairs.");
			break;
		case 14:
			MessageBoxShow.show("Community Chest", "You have won second prize in a beauty contest - Collect $10", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 10;
			break;
		case 15:
			MessageBoxShow.show("Community Chest", "You inherit $100", Monopoly.sh, SWT.OK);
			Monopoly.playerMoney[playerNum - 1] += 100;
			break;
		}
		/*Test to see if the player is out of money	*/
		if (Monopoly.playerMoney[Monopoly.currentPlayer - 1] < 0) {
			while (Monopoly.playerMoney[Monopoly.currentPlayer - 1] < 0) {
				
				if (MessageBoxShow.show("Not enough funds", "You must sell properties or declare bankruptcy.\nYou need: $" + -Monopoly.playerMoney[Monopoly.currentPlayer - 1] + "\nWould you like to sell properties", Monopoly.sh, SWT.YES | SWT.NO) == SWT.YES) { 
					HouseHotel.showHouseHotel();
					Monopoly.redraw();
				} else {
					//they are out so return everything to the bank
					for(int i = 0; i<40; i++) {
						if (Monopoly.propertyOwns[i] == Monopoly.currentPlayer) {
							Monopoly.propertyOwns[i] = 0;
							Monopoly.propertyHouses[i] = 0;
						}
					}	
					Monopoly.nextPlayer();
					Monopoly.redraw();
				}
			}
		}
		Monopoly.nextPlayer();
		Monopoly.redraw();
	}
	
	
	/**
	 * Inner method used to simplify the logic process behind moving to another place from either chance or community chest.
	 * @param locationOnBoard The location on the game board (0 is go, 39 is boardwalk)
	 * @param playerNum The current player (1 is player 1, 2 is player 2, ...)
	 * @param numDice The total of the dice rolled (used for water works and electric company)
	 */
	private static void transactions(int locationOnBoard, int playerNum, int numDice) {
		if (Monopoly.propertyOwns[locationOnBoard] == 0) {
			int temp2 = MessageBoxShow.show("Unowned property", "The property: " + Locations.getName(locationOnBoard) + " is unowned. \nWould you like to buy it for: $" + Locations.costToBuy(locationOnBoard), Monopoly.sh, SWT.YES | SWT.NO);
			if (temp2 == SWT.YES) {
				//transfer money and property
				Monopoly.propertyOwns[locationOnBoard] = playerNum;
				Monopoly.playerMoney[playerNum - 1] -= Locations.costToBuy(Monopoly.playerLocations[playerNum - 1]);
				System.out.println("Player " + playerNum + " bought " + Locations.getName(Monopoly.playerLocations[playerNum - 1]) + " for $" + Locations.costToBuy(Monopoly.playerLocations[playerNum - 1]));
			}
		} else {
			//the player owes money to another player or they own it
			if (Monopoly.propertyOwns[locationOnBoard] == playerNum) {
				MessageBoxShow.show("Property owned by you", "You own this property", Monopoly.sh, SWT.OK);
			} else {
				if (locationOnBoard == 5 || locationOnBoard == 15 || locationOnBoard == 25) {
					//double the rent
					MessageBoxShow.show("Owned by someone else", "The property: " + Locations.getName(Monopoly.playerLocations[playerNum - 1]) + " is owned by player: " + Monopoly.propertyOwns[locationOnBoard] + "\nYou owe $" + 2*Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice), Monopoly.sh, SWT.OK);
					Monopoly.playerMoney[playerNum - 1] -= 2*Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice);
					Monopoly.playerMoney[Monopoly.propertyOwns[Monopoly.playerLocations[playerNum - 1]] - 1] += 2*Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice);
					System.out.println("Player " + playerNum + " paid $" + 2*Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice) + " to player " + Monopoly.propertyOwns[locationOnBoard]);
					Monopoly.redraw();
				} else {
					MessageBoxShow.show("Owned by someone else", "The property: " + Locations.getName(Monopoly.playerLocations[playerNum - 1]) + " is owned by player: " + Monopoly.propertyOwns[locationOnBoard] + "\nYou owe $" + Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice), Monopoly.sh, SWT.OK);
					// transfer funds
					Monopoly.playerMoney[playerNum - 1] -= Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice);
					Monopoly.playerMoney[Monopoly.propertyOwns[Monopoly.playerLocations[playerNum - 1]] - 1] += Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice);
					System.out.println("Player " + playerNum + " paid $" + Locations.rentOwed(locationOnBoard, Monopoly.propertyHouses[locationOnBoard], numDice) + " to player " + Monopoly.propertyOwns[locationOnBoard]);
					Monopoly.redraw();
				}
			}
		}
	}
	
}
