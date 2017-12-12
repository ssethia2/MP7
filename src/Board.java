public class Board {
	
	/** 2D array of ships for game Board. **/
	private Ships[][] gameBoard;

	/**
	 * Player or computer's score
	 */
	int won = 0;
	
	/** creates an empty 2D array of ships **/
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
	 * 
	 * @param x
	 *            cord
	 * @param y
	 *            cord
	 * @return miss, hit, or invalid
	 */
	public int checkBoard(final int x, final int y) {
		if (this.gameBoard[x][y] == null) {
			return 0;//"You missed!";
		} else {
			if (this.gameBoard[x][y].hitShip()) {
				return 2;//"You sank " + this.gameBoard[x][y].getName();
			}
			return 1;//"You hit " + this.gameBoard[x][y].getName();
		}
	}

	/**
	 * Checks to see if computer's guessed co-ordinates are valid and if it hit a
	 * ship or not.
	 * 
	 * @param x
	 *            cord
	 * @param y
	 *            cord
	 * @return miss, hit, or invalid
	 */
	/*public String checkBoardForComp(final int x, final int y) {
		if (this.gameBoard[x][y] == null) {
			return "Computer missed!";
		} else {
			if (this.gameBoard[x][y].hitShip()) {
				return "Computer sank ship " + this.gameBoard[x][y].getName();
			}
			return "Computer hit ship " + this.gameBoard[x][y].getName();
		}
	}*/

	/**
	 * makes sure all parameters work.
	 * 
	 * @param board
	 * @param ship
	 * @param x
	 * @param y
	 * @return
	 */
	@SuppressWarnings("unused")
	public boolean canPlace(final Ships ship, boolean orientation, final int x, final int y) {
		if (orientation && ship.getLength() + x > 10) {
			return false;
		} else if (!orientation && ship.getLength() + y > 10)
			return false;
		for (int j = 0; j < ship.getLength(); j++) {
			if(orientation) {
				if (this.gameBoard[x + j][y] != null) {
					return false;
				} else {
					if (this.gameBoard[x][y + j] != null) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * places the ship on the board and makes sure its within the boundaries;
	 * 
	 * @param ship
	 *            the ship being placed
	 * @param orientation
	 *            determines whether the ship will be placed horizontally or
	 *            vertically
	 * @param x
	 *            Cord
	 * @param y
	 *            Cord
	 * @param board
	 *            the game board for the player
	 * @return the array with the ship placed
	 */
	public Ships[][] setShips(final Ships[][] board, final Ships ship, final boolean orientation, final int x,
			final int y) {
		// if being placed vertically
		if (orientation) {
			// if (canPlace(board, ship, x, y)) {
			for (int j = 0; j < ship.getLength(); j++) {
				board[x + j][y] = ship;
			}
		
		return board;
		// if being placed horizontally
	} else {
		//if (canPlace(board, ship, x, y)) {
			for (int j = 0; j < ship.getLength(); j++) {
				board[x][y + j] = ship;
			}
		//}
			return board;
		}

	}
	
	/**
	 * 
	 */
	public boolean gameWon() {
		if (this.won == 16)
			return true;
		return false;
	}
}