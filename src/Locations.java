import org.eclipse.swt.graphics.Point;
/**
 * This class is used to get the relative percentages on the board, names of places, costs, and houses and hotels.
 * 
 * @author Zachary
 *
 */
public class Locations {
	/**
	 * This returns the org.eclipse.swt.graphics.Point that corresponds to the location to draw the characters on the map.
	 * @param locationOnBoard The number from 0 to 39, inclusive that is the position on the board. 0 is go, with 39 as boardwalk.
	 * @return The point with (1500, 1500) being the bottom right
	 */
	public static Point getPoint(int locationOnBoard) {
		Point pt = new Point(0,0);
		switch (locationOnBoard) {
		case 0: pt = new Point(1415, 1415); break;
		case 1: pt = new Point(1245, 1415); break;
		case 2: pt = new Point(1125, 1415); break;
		case 3: pt = new Point(1005, 1415); break;
		case 4: pt = new Point(870, 1415); break;
		case 5: pt = new Point(750, 1415); break;
		case 6: pt = new Point(630, 1415); break;
		case 7: pt = new Point(500, 1415); break;
		case 8: pt = new Point(380, 1415); break;
		case 9: pt = new Point(260, 1415); break;
		case 10: pt = new Point(30, 1470); break;
		case 11: pt = new Point(75, 1240); break;
		case 12: pt = new Point(75, 1120); break;
		case 13: pt = new Point(75, 1000); break;
		case 14: pt = new Point(75, 880); break;
		case 15: pt = new Point(75, 750); break;
		case 16: pt = new Point(75, 630); break;
		case 17: pt = new Point(75, 510); break;
		case 18: pt = new Point(75, 390); break;
		case 19: pt = new Point(75, 260); break;
		case 20: pt = new Point(100, 100); break;
		case 21: pt = new Point(260, 75); break;
		case 22: pt = new Point(380, 75); break;
		case 23: pt = new Point(500, 75); break;
		case 24: pt = new Point(630, 75); break;
		case 25: pt = new Point(750, 75); break;
		case 26: pt = new Point(870, 75); break;
		case 27: pt = new Point(1005, 75); break;
		case 28: pt = new Point(1125, 75); break;
		case 29: pt = new Point(1245, 75); break;
		case 30: pt = new Point(130, 1370); break;//new Point(1400, 100); break;//the larger point is the GO TO Jail spot
		case 31: pt = new Point(1415, 260); break;
		case 32: pt = new Point(1415, 390); break;
		case 33: pt = new Point(1415, 510); break;
		case 34: pt = new Point(1415, 630); break;
		case 35: pt = new Point(1415, 750); break;
		case 36: pt = new Point(1415, 880); break;
		case 37: pt = new Point(1415, 1000); break;
		case 38: pt = new Point(1415, 1120); break;
		case 39: pt = new Point(1415, 1240); break;
		}
		return pt;
	}
	
	/**
	 * 
	 * @param locationOnBoard The number from 0 to 39, inclusive that is the position on the board. 0 is go, with 39 as boardwalk.
	 * @return The name to display on the board
	 */
	public static String getName(int locationOnBoard) {
		String rtn = "";
		switch (locationOnBoard) {
		case 0: rtn = "Go"; break;
		case 1: rtn = "Mediterranean Avenue"; break;
		case 2: rtn = "Community Chest"; break;
		case 3: rtn = "Baltic Avenue"; break;
		case 4: rtn = "Income Tax"; break;
		case 5: rtn = "Reading Railroad"; break;
		case 6: rtn = "Oriental Avenue"; break;
		case 7: rtn = "Chance"; break;
		case 8: rtn = "Vermont Avenue"; break;
		case 9: rtn = "Connecticut Avenue"; break;
		case 10: rtn = "Just Visiting"; break;
		case 11: rtn = "St. Charles Place"; break;
		case 12: rtn = "Electric Company"; break;
		case 13: rtn = "States Avenue"; break;
		case 14: rtn = "Virginia Avenue"; break;
		case 15: rtn = "Pennsylvania Railroad"; break;
		case 16: rtn = "St. James Place"; break;
		case 17: rtn = "Community Chest"; break;
		case 18: rtn = "Tennessee Avenue"; break;
		case 19: rtn = "New York Avenue"; break;
		case 20: rtn = "Free Parking"; break;
		case 21: rtn = "Kentucky Avenue"; break;
		case 22: rtn = "Chance"; break;
		case 23: rtn = "Indiana Avenue"; break;
		case 24: rtn = "Illinois Avenue"; break;
		case 25: rtn = "B. & O. Railroad"; break;
		case 26: rtn = "Atlantic Avenue"; break;
		case 27: rtn = "Ventnor Avenue"; break;
		case 28: rtn = "Water Works"; break;
		case 29: rtn = "Marvin Gardens"; break;
		case 30: rtn = "Jail"; break;
		case 31: rtn = "Pacific Avenue"; break;
		case 32: rtn = "North Carolina Avenue"; break;
		case 33: rtn = "Community Chest"; break;
		case 34: rtn = "Pennsylvania Avenue"; break;
		case 35: rtn = "Short Line"; break;
		case 36: rtn = "Chance"; break;
		case 37: rtn = "Park Place"; break;
		case 38: rtn = "Luxury Tax"; break;
		case 39: rtn = "Boardwalk"; break;
		}
		return rtn;
	}
	
	/**
	 * 
	 * @param locationOnBoard The number from 0 to 39, inclusive that is the position on the board. 0 is go, with 39 as boardwalk.
	 * @return The cost in dollars to buy the property. One half this cost is the amount that is given to a player from the bank when it is mortgaged. 0 is returned for properties that the player can not buy.
	 */
	public static int costToBuy(int locationOnBoard) {
		int rtn = 0;
		switch (locationOnBoard) {
		case 0: rtn = 0; break;
		case 1: rtn = 60; break;
		case 2: rtn = 0; break;
		case 3: rtn = 60; break;
		case 4: rtn = 0; break;
		case 5: rtn = 200; break;
		case 6: rtn = 100; break;
		case 7: rtn = 0; break;
		case 8: rtn = 100; break;
		case 9: rtn = 120; break;
		case 10: rtn = 0; break;
		case 11: rtn = 140; break;
		case 12: rtn = 150; break;
		case 13: rtn = 140; break;
		case 14: rtn = 160; break;
		case 15: rtn = 200; break;
		case 16: rtn = 180; break;
		case 17: rtn = 0; break;
		case 18: rtn = 180; break;
		case 19: rtn = 200; break;
		case 20: rtn = 0; break;
		case 21: rtn = 220; break;
		case 22: rtn = 0; break;
		case 23: rtn = 220; break;
		case 24: rtn = 240; break;
		case 25: rtn = 200; break;
		case 26: rtn = 260; break;
		case 27: rtn = 260; break;
		case 28: rtn = 150; break;
		case 29: rtn = 280; break;
		case 30: rtn = 0; break;
		case 31: rtn = 300; break;
		case 32: rtn = 300; break;
		case 33: rtn = 0; break;
		case 34: rtn = 320; break;
		case 35: rtn = 200; break;
		case 36: rtn = 0; break;
		case 37: rtn = 350; break;
		case 38: rtn = 0; break;
		case 39: rtn = 400; break;
		}
		return rtn;
		
	}
	/**
	 * This method returns the cost due to the player that owns the property. <br>
	 * If there are no houses and one player owns all two/three of the same 
	 * type, then the rent is automatically doubled. <br><b>Only call this method if the property is not mortgaged or unowned.</b>
	 * @param locationOnBoard The number from 0 to 39, inclusive that is the position on the board. 0 is go, with 39 as boardwalk.
	 * @param numberHouses A number from 0 to 5, inclusive that specifies the number of houses on a property. 5 represents a hotel.
	 * @param numRolled The number corresponding to the sum of the two die rolled for the player to land on this property. <br>
	 * This parameter is only used if the player lands on Electric Company or Water Works
	 * @return The rent owed to the player that owns the property
	 */
	public static int rentOwed(int locationOnBoard, int numberHouses, int numRolled) {
		int temp = 0;
		boolean doubleRent = false;
		
		if (numberHouses == 0) {
			switch (locationOnBoard) {
			case 1: temp = 2; break;
			case 3: temp = 4; break;
			case 6: temp = 6; break;
			case 8: temp = 6; break;
			case 9: temp = 8; break;
			case 11: temp = 10; break;
			case 13: temp = 10; break;
			case 14: temp = 12; break;
			case 16: temp = 14; break;
			case 18: temp = 14; break;
			case 19: temp = 16; break;
			case 21: temp = 18; break;
			case 23: temp = 18; break;
			case 24: temp = 20; break;
			case 26: temp = 22; break;
			case 27: temp = 22; break;
			case 29: temp = 24; break;
			case 31: temp = 26; break;
			case 32: temp = 26; break;
			case 34: temp = 28; break;
			case 37: temp = 35; break;
			case 39: temp = 50; break;
			}
			//test to see if the rent needs doubled
			switch (locationOnBoard) {
			case 1: 
			case 3: if (Monopoly.propertyOwns[1] == Monopoly.propertyOwns[3]) {	doubleRent = true; } break;
			case 6: 
			case 8: 
			case 9: if (Monopoly.propertyOwns[6] == Monopoly.propertyOwns[8] && Monopoly.propertyOwns[8] == Monopoly.propertyOwns[9]) { doubleRent = true; } break;
			case 11: 
			case 13: 
			case 14: if (Monopoly.propertyOwns[11] == Monopoly.propertyOwns[13] && Monopoly.propertyOwns[13] == Monopoly.propertyOwns[14]) { doubleRent = true; } break;
			case 16: 
			case 18: 
			case 19: if (Monopoly.propertyOwns[16] == Monopoly.propertyOwns[18] && Monopoly.propertyOwns[18] == Monopoly.propertyOwns[19]) { doubleRent = true; } break;
			case 21:
			case 23: 
			case 24: if (Monopoly.propertyOwns[21] == Monopoly.propertyOwns[23] && Monopoly.propertyOwns[23] == Monopoly.propertyOwns[24]) { doubleRent = true; } break;
			case 26:
			case 27:
			case 29: if (Monopoly.propertyOwns[26] == Monopoly.propertyOwns[27] && Monopoly.propertyOwns[27] == Monopoly.propertyOwns[29]) { doubleRent = true; } break;
			case 31: 
			case 32: 
			case 34: if (Monopoly.propertyOwns[31] == Monopoly.propertyOwns[32] && Monopoly.propertyOwns[32] == Monopoly.propertyOwns[34]) { doubleRent = true; } break;
			case 37: 
			case 39: if (Monopoly.propertyOwns[37] == Monopoly.propertyOwns[39]) {	doubleRent = true; } break;
			}
			if (doubleRent) {
				temp *= 2;
			}
			
		} else if (numberHouses == 1) {
			switch (locationOnBoard) {
			case 1: temp = 10; break;
			case 3: temp = 20; break;
			case 6: temp = 30; break;
			case 8: temp = 30; break;
			case 9: temp = 40; break;
			case 11: temp = 50; break;
			case 13: temp = 50; break;
			case 14: temp = 60; break;
			case 16: temp = 70; break;
			case 18: temp = 70; break;
			case 19: temp = 80; break;
			case 21: temp = 90; break;
			case 23: temp = 90; break;
			case 24: temp = 100; break;
			case 26: temp = 110; break;
			case 27: temp = 110; break;
			case 29: temp = 120; break;
			case 31: temp = 130; break;
			case 32: temp = 130; break;
			case 34: temp = 150; break;
			case 37: temp = 175; break;
			case 39: temp = 200; break;
			}
		} else if (numberHouses == 2) {
			switch (locationOnBoard) {
			case 1: temp = 30; break;
			case 3: temp = 60; break;
			case 6: temp = 90; break;
			case 8: temp = 90; break;
			case 9: temp = 100; break;
			case 11: temp = 150; break;
			case 13: temp = 150; break;
			case 14: temp = 180; break;
			case 16: temp = 200; break;
			case 18: temp = 200; break;
			case 19: temp = 220; break;
			case 21: temp = 250; break;
			case 23: temp = 250; break;
			case 24: temp = 300; break;
			case 26: temp = 330; break;
			case 27: temp = 330; break;
			case 29: temp = 360; break;
			case 31: temp = 390; break;
			case 32: temp = 390; break;
			case 34: temp = 450; break;
			case 37: temp = 500; break;
			case 39: temp = 600; break;
			}
		} else if (numberHouses == 3) {
			switch (locationOnBoard) {
			case 1: temp = 90; break;
			case 3: temp = 180; break;
			case 6: temp = 270; break;
			case 8: temp = 270; break;
			case 9: temp = 300; break;
			case 11: temp = 450; break;
			case 13: temp = 450; break;
			case 14: temp = 500; break;
			case 16: temp = 550; break;
			case 18: temp = 550; break;
			case 19: temp = 600; break;
			case 21: temp = 700; break;
			case 23: temp = 700; break;
			case 24: temp = 750; break;
			case 26: temp = 800; break;
			case 27: temp = 800; break;
			case 29: temp = 850; break;
			case 31: temp = 900; break;
			case 32: temp = 900; break;
			case 34: temp = 1000; break;
			case 37: temp = 1100; break;
			case 39: temp = 1400; break;
			}
		} else if (numberHouses == 4) {
			switch (locationOnBoard) {
			case 1: temp = 160; break;
			case 3: temp = 320; break;
			case 6: temp = 400; break;
			case 8: temp = 400; break;
			case 9: temp = 450; break;
			case 11: temp = 625; break;
			case 13: temp = 625; break;
			case 14: temp = 700; break;
			case 16: temp = 750; break;
			case 18: temp = 750; break;
			case 19: temp = 800; break;
			case 21: temp = 875; break;
			case 23: temp = 875; break;
			case 24: temp = 925; break;
			case 26: temp = 975; break;
			case 27: temp = 975; break;
			case 29: temp = 1025; break;
			case 31: temp = 1100; break;
			case 32: temp = 1100; break;
			case 34: temp = 1200; break;
			case 37: temp = 1300; break;
			case 39: temp = 1700; break;
			}
		} else if (numberHouses == 5) {
			switch (locationOnBoard) {
			case 1: temp = 250; break;
			case 3: temp = 450; break;
			case 6: temp = 550; break;
			case 8: temp = 550; break;
			case 9: temp = 600; break;
			case 11: temp = 750; break;
			case 13: temp = 750; break;
			case 14: temp = 900; break;
			case 16: temp = 950; break;
			case 18: temp = 950; break;
			case 19: temp = 1000; break;
			case 21: temp = 1050; break;
			case 23: temp = 1050; break;
			case 24: temp = 1050; break;
			case 26: temp = 1100; break;
			case 27: temp = 1150; break;
			case 29: temp = 1200; break;
			case 31: temp = 1275; break;
			case 32: temp = 1275; break;
			case 34: temp = 1400; break;
			case 37: temp = 1500; break;
			case 39: temp = 2000; break;
			}
		}
		
		//test for the RR or Utilities
		switch (locationOnBoard) {
		case 5: temp = 25; break;
		case 12: temp = 4 * numRolled; break;
		case 15: temp = 25; break;
		case 25: temp = 25; break;
		case 28: temp = 4 * numRolled; break;
		case 35: temp = 25; break;
		}
		
		switch (locationOnBoard) {
		case 5: 
			if (Monopoly.propertyOwns[5] == Monopoly.propertyOwns[15] 
					|| Monopoly.propertyOwns[5] == Monopoly.propertyOwns[25] 
					|| Monopoly.propertyOwns[5] == Monopoly.propertyOwns[35]) { temp = 50; } 
			if ((Monopoly.propertyOwns[5] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[5] == Monopoly.propertyOwns[25]) 
					|| (Monopoly.propertyOwns[5] == Monopoly.propertyOwns[25] && Monopoly.propertyOwns[5] == Monopoly.propertyOwns[35]) 
					|| (Monopoly.propertyOwns[5] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[5] == Monopoly.propertyOwns[35])) { temp = 100; }
			if (Monopoly.propertyOwns[5] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[15] == Monopoly.propertyOwns[25] && Monopoly.propertyOwns[25] == Monopoly.propertyOwns[35]) {temp = 200;} 
			break;
		case 15: 
			if (Monopoly.propertyOwns[15] == Monopoly.propertyOwns[5] 
					|| Monopoly.propertyOwns[15] == Monopoly.propertyOwns[25] 
					|| Monopoly.propertyOwns[15] == Monopoly.propertyOwns[35]) { temp = 50; } 
			if ((Monopoly.propertyOwns[15] == Monopoly.propertyOwns[5] && Monopoly.propertyOwns[15] == Monopoly.propertyOwns[25]) 
					|| (Monopoly.propertyOwns[15] == Monopoly.propertyOwns[5] && Monopoly.propertyOwns[15] == Monopoly.propertyOwns[35]) 
					|| (Monopoly.propertyOwns[15] == Monopoly.propertyOwns[25] && Monopoly.propertyOwns[15] == Monopoly.propertyOwns[35])) { temp = 100; }
			if (Monopoly.propertyOwns[5] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[15] == Monopoly.propertyOwns[25] && Monopoly.propertyOwns[25] == Monopoly.propertyOwns[35]) {temp = 200;} 
			break;
		case 25:
			if (Monopoly.propertyOwns[25] == Monopoly.propertyOwns[5] 
					|| Monopoly.propertyOwns[25] == Monopoly.propertyOwns[15] 
					|| Monopoly.propertyOwns[25] == Monopoly.propertyOwns[35]) { temp = 50; } 
			if ((Monopoly.propertyOwns[25] == Monopoly.propertyOwns[5] && Monopoly.propertyOwns[25] == Monopoly.propertyOwns[15]) 
					|| (Monopoly.propertyOwns[25] == Monopoly.propertyOwns[5] && Monopoly.propertyOwns[25] == Monopoly.propertyOwns[35]) 
					|| (Monopoly.propertyOwns[25] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[25] == Monopoly.propertyOwns[35])) { temp = 100; }
			if (Monopoly.propertyOwns[5] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[15] == Monopoly.propertyOwns[25] && Monopoly.propertyOwns[25] == Monopoly.propertyOwns[35]) {temp = 200;} 
			break;
		case 35: 
			if (Monopoly.propertyOwns[35] == Monopoly.propertyOwns[5] 
					|| Monopoly.propertyOwns[35] == Monopoly.propertyOwns[15] 
					|| Monopoly.propertyOwns[35] == Monopoly.propertyOwns[25]) { temp = 50; } 
			if ((Monopoly.propertyOwns[35] == Monopoly.propertyOwns[5] && Monopoly.propertyOwns[35] == Monopoly.propertyOwns[15]) 
					|| (Monopoly.propertyOwns[35] == Monopoly.propertyOwns[5] && Monopoly.propertyOwns[35] == Monopoly.propertyOwns[25]) 
					|| (Monopoly.propertyOwns[35] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[35] == Monopoly.propertyOwns[25])) { temp = 100; }
			if (Monopoly.propertyOwns[5] == Monopoly.propertyOwns[15] && Monopoly.propertyOwns[15] == Monopoly.propertyOwns[25] && Monopoly.propertyOwns[25] == Monopoly.propertyOwns[35]) {temp = 200;} 
			break;
		case 12: 
		case 28: if (Monopoly.propertyOwns[12] == Monopoly.propertyOwns[28]) { temp = 10 * numRolled; }; break;
		}
		
		return temp;
		
	}
	/**
	 * This method return the SWT Point that should be the location of the house drawn on top of the game board.
	 * @param locationOnBoard The number from 0 to 39, inclusive that is the position on the board. 0 is go, with 39 as boardwalk.
	 * @param numHouse The number of the house on that property. Should be from 1 to 4 inclusive. For a hotel, use 2 and draw 3 times a wide.
	 * @return The SWT Point that corresponds to the top left coordinate on the 1500 X 1500 game board image
	 */
	public static Point getLocation(int locationOnBoard, int numHouse) {
		int x = 0, y = 0;
		if (locationOnBoard > 0 && locationOnBoard < 10) {
			y = 1300;
			x = getPoint(locationOnBoard).x;
			x -= (numHouse - 2) * 25;
		}
		if (locationOnBoard > 10 && locationOnBoard < 20) {
			x = 150;
			y = getPoint(locationOnBoard).y;
			y -= (numHouse - 2) * 25;
		}
		if (locationOnBoard > 20 && locationOnBoard < 30) {
			y = 150;
			x = getPoint(locationOnBoard).x;
			x += (numHouse - 2) * 25;
		}
		if (locationOnBoard > 30 && locationOnBoard < 40) {
			x = 1300;
			y = getPoint(locationOnBoard).y;
			y += (numHouse - 2) * 25;
		}
		
		return new Point(x, y);
	}
	
	/**
	 * This method return the name of the group for an id from 0 to 7, inclusive. Group 0 is: <br>
	 * <tt>"Mediterranean Avenue, Baltic Avenue"</tt>
	 * @param id The number of the group from 0 to 7 inclusive.
	 * @return The name of the places followed by commas and a space.
	 */
	public static String getNameOfGroup(int id) {
		String temp = "";
		switch(id) {
		case 0:
			temp += Locations.getName(1) + ", ";
			temp += Locations.getName(3);
			break;
		case 1:
			temp += Locations.getName(6) + ", ";
			temp += Locations.getName(8) + ", ";
			temp += Locations.getName(9);
			break;
		case 2:
			temp += Locations.getName(11) + ", ";
			temp += Locations.getName(13) + ", ";
			temp += Locations.getName(14);
			break;
		case 3:
			temp += Locations.getName(16) + ", ";
			temp += Locations.getName(18) + ", ";
			temp += Locations.getName(19);
			break;
		case 4:
			temp += Locations.getName(21) + ", ";
			temp += Locations.getName(23) + ", ";
			temp += Locations.getName(24);
			break;
		case 5:
			temp += Locations.getName(26) + ", ";
			temp += Locations.getName(27) + ", ";
			temp += Locations.getName(29);
			break;
		case 6:
			temp += Locations.getName(31) + ", ";
			temp += Locations.getName(32) + ", ";
			temp += Locations.getName(34);
			break;
		case 7:
			temp += Locations.getName(37) + ", ";
			temp += Locations.getName(39);
			break;
		}
		
		return temp;
	}
	
	/**
	 * This method returns true if the player can add houses on the id group.
	 * @param id The number of the group from 0 to 7, inclusive.
	 * @param playerNum The number of the player, with 1 being player 1
	 * @return if the player can add houses on the id group specified
	 */
	public static boolean canAddHouses(int id, int playerNum) {
		boolean good = false;
		switch(id) {
		case 0:
			if (Monopoly.propertyOwns[1] == playerNum && Monopoly.propertyOwns[3] == playerNum && Monopoly.propertyHouses[1] != 5) {
				good = true;
			}
			break;
		case 1:
			if (Monopoly.propertyOwns[6] == playerNum && Monopoly.propertyOwns[8] == playerNum && Monopoly.propertyOwns[9] == playerNum && Monopoly.propertyHouses[6] != 5) {
				good = true;
			}
			break;
		case 2:
			if (Monopoly.propertyOwns[11] == playerNum && Monopoly.propertyOwns[13] == playerNum && Monopoly.propertyOwns[14] == playerNum && Monopoly.propertyHouses[11] != 5) {
				good = true;
			} 
			break;
		case 3:
			if (Monopoly.propertyOwns[16] == playerNum && Monopoly.propertyOwns[18] == playerNum && Monopoly.propertyOwns[19] == playerNum && Monopoly.propertyHouses[16] != 5) {
				good = true;
			}
			break;
		case 4:
			if (Monopoly.propertyOwns[21] == playerNum && Monopoly.propertyOwns[23] == playerNum && Monopoly.propertyOwns[24] == playerNum && Monopoly.propertyHouses[21] != 5) {
				good = true;
			}
			break;
		case 5:
			if (Monopoly.propertyOwns[26] == playerNum && Monopoly.propertyOwns[27] == playerNum && Monopoly.propertyOwns[29] == playerNum && Monopoly.propertyHouses[26] != 5) {
				good = true;
			}
			break;
		case 6:
			if (Monopoly.propertyOwns[31] == playerNum && Monopoly.propertyOwns[32] == playerNum && Monopoly.propertyOwns[34] == playerNum && Monopoly.propertyHouses[31] != 5) {
				good = true;
			}
			break;
		case 7:
			if (Monopoly.propertyOwns[37] == playerNum && Monopoly.propertyOwns[39] == playerNum && Monopoly.propertyHouses[37] != 5) {
				good = true;
			}
			break;
		}
		return good;
	}
	
	/**
	 * This method returns true if the player can remove houses on the id group.
	 * @param id The number of the group from 0 to 7, inclusive.
	 * @param playerNum The number of the player, with 1 being player 1
	 * @return if the player can add houses on the id group specified
	 */
	public static boolean canRemoveHouses(int id, int playerNum) {
		boolean good = false;
		switch(id) {
		case 0:
			if (Monopoly.propertyOwns[1] == playerNum && Monopoly.propertyOwns[3] == playerNum && Monopoly.propertyHouses[1] != 0) {
				good = true;
			}
			break;
		case 1:
			if (Monopoly.propertyOwns[6] == playerNum && Monopoly.propertyOwns[8] == playerNum && Monopoly.propertyOwns[9] == playerNum && Monopoly.propertyHouses[6] != 0) {
				good = true;
			}
			break;
		case 2:
			if (Monopoly.propertyOwns[11] == playerNum && Monopoly.propertyOwns[13] == playerNum && Monopoly.propertyOwns[14] == playerNum && Monopoly.propertyHouses[11] != 0) {
				good = true;
			} 
			break;
		case 3:
			if (Monopoly.propertyOwns[16] == playerNum && Monopoly.propertyOwns[18] == playerNum && Monopoly.propertyOwns[19] == playerNum && Monopoly.propertyHouses[16] != 0) {
				good = true;
			}
			break;
		case 4:
			if (Monopoly.propertyOwns[21] == playerNum && Monopoly.propertyOwns[23] == playerNum && Monopoly.propertyOwns[24] == playerNum && Monopoly.propertyHouses[21] != 0) {
				good = true;
			}
			break;
		case 5:
			if (Monopoly.propertyOwns[26] == playerNum && Monopoly.propertyOwns[27] == playerNum && Monopoly.propertyOwns[29] == playerNum && Monopoly.propertyHouses[26] != 0) {
				good = true;
			}
			break;
		case 6:
			if (Monopoly.propertyOwns[31] == playerNum && Monopoly.propertyOwns[32] == playerNum && Monopoly.propertyOwns[34] == playerNum && Monopoly.propertyHouses[31] != 0) {
				good = true;
			}
			break;
		case 7:
			if (Monopoly.propertyOwns[37] == playerNum && Monopoly.propertyOwns[39] == playerNum && Monopoly.propertyHouses[37] != 0) {
				good = true;
			}
			break;
		}
		return good;
	}
	/**
	 * This method returns the cost to either buy or sell numberOfHouses houses on a group.
	 * @param id The number of the group from 0 to 7, inclusive.
	 * @param numberOfHouses The number of more houses the player wants to buy/sell.
	 * @return the cost to buy, or the amount given to the player when they sell
	 */
	public static int costForHouses(int id, int numberOfHouses) {
		int temp = 0;
		switch(id) {
		case 0: return 50*2*numberOfHouses;
		case 1: return 50*3*numberOfHouses;
		case 2: return 100*3*numberOfHouses;
		case 3: return 100*3*numberOfHouses;
		case 4: return 150*3*numberOfHouses;
		case 5: return 150*3*numberOfHouses;
		case 6: return 200*3*numberOfHouses;
		case 7: return 200*2*numberOfHouses;
		}
		return temp;
	}
	/**
	 * This method adds the houses to a group.
	 * @param id The number of the group from 0 to 7, inclusive.
	 * @param numberOfHouses The number of more houses the player wants to add.
	 */
	public static void addHouses(int id, int numberOfHouses) {
		switch(id) {
		case 0: 
			Monopoly.propertyHouses[1] += numberOfHouses;
			Monopoly.propertyHouses[3] += numberOfHouses;break;
		case 1:
			Monopoly.propertyHouses[6] += numberOfHouses;
			Monopoly.propertyHouses[8] += numberOfHouses;
			Monopoly.propertyHouses[9] += numberOfHouses;break;
		case 2: 
			Monopoly.propertyHouses[11] += numberOfHouses;
			Monopoly.propertyHouses[13] += numberOfHouses;
			Monopoly.propertyHouses[14] += numberOfHouses;break;
		case 3:
			Monopoly.propertyHouses[16] += numberOfHouses;
			Monopoly.propertyHouses[18] += numberOfHouses;
			Monopoly.propertyHouses[19] += numberOfHouses;break;
		case 4:
			Monopoly.propertyHouses[21] += numberOfHouses;
			Monopoly.propertyHouses[23] += numberOfHouses;
			Monopoly.propertyHouses[24] += numberOfHouses;break;
		case 5:
			Monopoly.propertyHouses[26] += numberOfHouses;
			Monopoly.propertyHouses[27] += numberOfHouses;
			Monopoly.propertyHouses[29] += numberOfHouses;break;
		case 6:
			Monopoly.propertyHouses[31] += numberOfHouses;
			Monopoly.propertyHouses[32] += numberOfHouses;
			Monopoly.propertyHouses[34] += numberOfHouses;break;
		case 7:
			Monopoly.propertyHouses[37] += numberOfHouses;
			Monopoly.propertyHouses[39] += numberOfHouses;break;
		}
	}
	/**
	 * This method removes the numberOfHouses houses from a group.
	 * @param id The number of the group from 0 to 7, inclusive.
	 * @param numberOfHouses The number of more houses the player wants to remove.
	 */
	public static void removeHouses(int id, int numberOfHouses) {
		addHouses(id, -numberOfHouses);
	}
	
	/**
	 * This method returns the number of houses on a group
	 * @param id The number of the group from 0 to 7, inclusive.
	 * @return The current number of houses on the group specified by the id
	 */
	public static int getNumberOfHouses(int id) {
		int temp = 0;
		switch(id) {
		case 0: return Monopoly.propertyHouses[1];
		case 1: return Monopoly.propertyHouses[6];
		case 2: return Monopoly.propertyHouses[11];
		case 3: return Monopoly.propertyHouses[16];
		case 4: return Monopoly.propertyHouses[21];
		case 5: return Monopoly.propertyHouses[26];
		case 6: return Monopoly.propertyHouses[31];
		case 7: return Monopoly.propertyHouses[37];
		}
		return temp;
	}
	/**
	 * This method shows the total value of the player.
	 * @param player The number of the player, starting at 1
	 * @return The total value of the player.
	 */
	public static int netWorth(int player) {
		int value = 0;
		for (int i = 1; i < 40; i++) {
			if(Monopoly.propertyOwns[i] == player) {
				value += costToBuy(i);
				if(i < 10) {
					value += Monopoly.propertyHouses[i] * 50;
				} else if (i < 20) {
					value += Monopoly.propertyHouses[i] * 100;
				} else if (i < 30) {
					value += Monopoly.propertyHouses[i] * 150;
				} else if (i < 40) {
					value += Monopoly.propertyHouses[i] * 200;
				}
				
			}
		}
		value += Monopoly.playerMoney[player - 1];
		return value;
	}
	/**
	 * This method returns the cost of the repairs card on chance ($25 per house, $100 per hotel)
	 * @param player The number of the player, starting at 1
	 * @return
	 */
	public static int costOfRepairsChance(int player) {
		int value = 0;
		for(int i=1; i<40; i++){
			if(Monopoly.propertyOwns[i] == player) {
				if(Monopoly.propertyHouses[i] == 5) {
					value += 100;
				} else {
					value += 25 * Monopoly.propertyHouses[i];
				}
			}
			
		}
		return value;
	}
	/**
	 * This method returns the cost of the repairs card on community chest ($40 per house, $115 per hotel)
	 * @param player The number of the player, starting at 1
	 * @return
	 */
	public static int costOfRepairsCommunityChest(int player){
		int value = 0;
		for(int i=1; i<40; i++){
			if(Monopoly.propertyOwns[i] == player) {
				if(Monopoly.propertyHouses[i] == 5) {
					value += 115;
				} else {
					value += 40 * Monopoly.propertyHouses[i];
				}
			}
			
		}
		return value;
	}
}
