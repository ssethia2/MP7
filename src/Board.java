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
	 *  makes sure all parameters work.
	 * @param board 
	 * @param ship
	 * @param orientation
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean canPlace(final Ships[][] board, final Ships ship,  final boolean orientation,
			final int x, final int y) {
		if (ship.getLength() + y > 10) {
			return false;
		}
		for (int j = 0; j < ship.getLength(); j++) {
			if(orientation) {
				if (board[x][y + j] != null) {
					return false;
				} else {
					if (board[x + 1][y] != null) {
						return false;
					}
				}
				}
			}
				return true;
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
	public void setShips(final Ships[][] board, final Ships ship, final boolean orientation,
			final int x, final int y) {
		// if being placed vertically
		if (orientation) {
			if (canPlace(board, ship, orientation, x, y)) {
				for (int j = 0; j < ship.getLength(); j++) {
				board[x][y + j] = ship;
				}
			}
		// if being placed horizontally
		} else{
			if (canPlace(board, ship, orientation, x, y)) {
				for (int j = 0; j < ship.getLength(); j++) {
				board[x + j][y] = ship;
				}
			}
		}
	}
}