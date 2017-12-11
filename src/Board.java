public class Board {
	/**2D array of ships for game Board.**/
	private Ships[][] gameBoard;

	/**creates an empty 2D array of ships**/
	public Board() {
		this.gameBoard = new Ships[10][10];
	}
	/**
	 * gets gameboard 
	 * 
	 */
	public Ships[][] getGameBoard() {
		return gameBoard;
	}
	/**
	 * Checks to see if guessed co-ordinates are valid and if it hit a ship of not.
	 * @param x cord
	 * @param y cord
	 * @return miss, hit, or invalid
	 */
	public String checkBoard(final int x, final int y) {
		if (this.gameBoard[x][y] == null) {
			this.gameBoard[x][y] = new Ships("destroyed", 1);
			return "Miss";
		} else if (this.gameBoard[x][y].getName() == "destroyed") {
			return null;
		} else {
			if(gameBoard[x][y].hitShip()) {
				return "Sunk";
			}
			return "Hit";
		}
	}
	/**
	 * places the ship on the board and makes sure its within the boundaries;
	 * @param ship the ship being placed
	 * @param orientation determines whether the ship will be placed horizontally or vertically
	 * @param x Cord
	 * @param y Cord
	 * @param board the game board for the player
	 * @return the array with the ship placed
	 */
	public Ships[][] setShips(final Ships[][] board, final Ships ship, final boolean orientation,
			final int x, final int y) {
		// if being placed vertically
		if (orientation) {
			if (ship.getLength() + y > 10) {
				return null;//so that the place where the method is being called knows if the ship can't be placed there
			}
			/**
			 * Check for ships that have already been placed around these co-ordinates
			 * and if this ship can't be placed in this orientation
			 */
			for (int j = 0; j < ship.getLength(); j++) {
				board[x][y - 1 + j] = ship;
			}
			return board;
		} 
		// if being placed horizontally
		else {
			if (ship.getLength() + x > 10) {
				return null;
			}

			/**
			 * Check for ships that have already been placed around these co-ordinates
			 * and if this ship can't be placed in this orientation
			 */
			for (int j = 0; j < ship.getLength(); j++) {
				board[x - 1 + j][y] = ship;
			}
			return board;
		}
	}
}