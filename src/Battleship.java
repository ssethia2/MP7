import java.util.Scanner;

/* Driver program for game.
 */

public class Battleship {
	public static void main(String args[]) {
		System.out.println("Welcome to Battleship!\n");
		Board player_board = new Board();
		Board comp_board = new Board();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter you name: ");
		String player_name = sc.next();
		player_name += sc.nextLine();
		//String comp_name = "Computer";
		// Player player = new Player(player_name);
		// Player comp = new Player("Computer");
		System.out.println("\nHere is what your board looks like.\n");
		for (int l = 1; l < 11; l++)
			System.out.print("\t" + l);
		System.out.println();
		for (int k = 0; k < 10; k++) {
			System.out.print((k + 1) + "\t");
			for (int j = 0; j < 10; j++) {
				System.out.print(
						((player_board.getGameBoard()[k][j] != null) ? player_board.getGameBoard()[k][j].getName()
								: "O") + "\t");
			}
			System.out.println();
		}
		System.out.println("\nEnter the name, length, and orientation of 5 boats with lengths 2, 2, 3, 4, and 5.\n");
		String name = "";
		int len = 0;
		int[] check_len = { 2, 2, 3, 4, 5 };
		int count = 0;
		int x = 0;
		int y = 0;
		int orient = 0;
		// int player_won = 0;
		// int computer_won = 0;
		boolean orientation = false;
		Ships[] player_ships = new Ships[5];
		Ships[] comp_ships = new Ships[5];
		for (int i = 1; i < 6; i++) {
			System.out.println("Ship " + i + ".\n");
			System.out.print("Enter the name of the ship: ");
			name = sc.next();
			name += sc.nextLine();
			do {
				System.out.print("Enter the length of the ship: ");
				len = sc.nextInt();
				for (int j = 0; j < check_len.length; j++) {
					if (check_len[j] == len) {
						count++;
						check_len[j] = -1;
						break;
					}
				}
				if (count > 0 && len > 0) {
					break;
				}
				System.out.println("Invalid length or limit of number of boats possible with this length reached.");
			} while (count == 0);
			count = 0;
			player_ships[i - 1] = new Ships(name, len);
			do {
				System.out.print(
						"Enter the co-ordinates of the position you want to place the boat in, separated by a space: ");
				x = sc.nextInt();
				y = sc.nextInt();
				System.out.print("Enter the orientation of the boat (0 for horizontal and 1 for vertical): ");
				orient = sc.nextInt();
				if (orient == 0)
					orientation = false;
				else if (orient == 1)
					orientation = true;
				else {
					System.out.println("Invalid orientation.");
					continue;
				}
				if (player_board.canPlace(player_ships[i - 1], orientation, --x, --y)) {
					player_board.setShips(player_board.getGameBoard(), player_ships[i - 1], orientation, x, y);
					break;
				}
				System.out.println("Invalid co-ordinates. Ship already in or around that orientation.");
			} while (true);

			comp_ships[i - 1] = new Ships("Computer's ship " + Integer.toString(i), (i == 1) ? 2 : i);
			do {
				x = (int) (Math.random() * 9);
				y = (int) (Math.random() * 9);
				if (comp_board.canPlace(comp_ships[i - 1], orientation, x, y)) {
					comp_board.setShips(comp_board.getGameBoard(), comp_ships[i - 1], orientation, x, y);
					break;
				} /*
					 * else if (comp_board.canPlace(comp_ships[i - 1], !orientation, x, y)) {
					 * comp_board.setShips(comp_board.getGameBoard(), comp_ships[i - 1],
					 * !orientation, x, y); break; }
					 */
			} while (true);
		}

		System.out.println("\nHere is what your board looks like.\n");
		for (int l = 1; l < 11; l++)
			System.out.print("\t" + l);
		System.out.println();
		for (int k = 0; k < 10; k++) {
			System.out.print((k + 1) + "\t");
			for (int j = 0; j < 10; j++) {
				System.out.print(
						((player_board.getGameBoard()[k][j] != null) ? player_board.getGameBoard()[k][j].getName()
								: "O") + "\t");
			}
			System.out.println();
		}

		int[][] player_guesses = new int[10][10];
		int[][] comp_guesses = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				player_guesses[i][j] = 0;
				comp_guesses[i][j] = 0;
			}
		}

		System.out.println("\nTime to play!");
		System.out.println("\nEnter guesses as co-ordinates, which should be 2 integers separated by a space.\n");
		while (true) {
			// Player's turn
			do {
				System.out.print("Make a guess: ");
				x = sc.nextInt();
				y = sc.nextInt();
				// do {
				do {
					if (x > 10 || x < 1 || y > 10 || y < 1) {
						System.out.print("Out of bounds. Enter co-ordinates between (1,1) and (10,10): ");
						x = sc.nextInt();
						y = sc.nextInt();
					} else
						break;
				} while (true);
				// do {
				if (player_guesses[--x][--y] == 0) {
					player_guesses[x][y] = -1;
					break;
				}
				System.out.println("You have already entered that guess before.");
				// x = sc.nextInt();
				// y = sc.nextInt();
				// } while(true);
			} while (true);
			if (comp_board.checkBoard(x, y) == 0) {
				player_guesses[x][y] = 1;
				System.out.println("You missed!");
			}
			else {
				player_guesses[x][y] = 2;
				if (comp_board.checkBoard(x, y) == 1)
					System.out.println("You hit " + comp_board.getGameBoard()[x][y].getName());
				else
					System.out.println("You sank " + comp_board.getGameBoard()[x][y].getName());
			}
			System.out.println("\nHere is what your guesses on Computer's board look like.\n");
			for (int l = 1; l < 11; l++)
				System.out.print("\t" + l);
			System.out.println();
			for (int k = 0; k < 10; k++) {
				System.out.print((k + 1) + "\t");
				for (int j = 0; j < 10; j++) {
					if (player_guesses[k][j] == 2)
						System.out.print("X\t");
					else if (player_guesses[k][j] == 1)
						System.out.print("~\t");
					else
						System.out.print("O\t");
				}
				System.out.println();
			}
			System.out.println();

			if (comp_board.gameWon()) {
				System.out.println("\n" + player_name + ", you won! Congratulations!");
				break;
			}
			// Computer's turn
			System.out.println("Computer's turn...");
			do {
				x = (int) (Math.random() * 9);
				y = (int) (Math.random() * 9);
				if (comp_guesses[x][y] == 0) {
					comp_guesses[x][y] = -1;
					break;
				}
			} while (true);

			if (player_board.checkBoard(x, y) == 0) {
				comp_guesses[x][y] = 1;
				System.out.println("Computer missed!");
			}
			else {
				comp_guesses[x][y] = 2;
				if (player_board.checkBoard(x, y) == 1)
					System.out.println("Computer hit ship " + player_board.getGameBoard()[x][y].getName());
				else
					System.out.println("Computer sank ship " + player_board.getGameBoard()[x][y].getName());
			}
			System.out.println("\nHere is what your board looks like.\n");
			for (int l = 1; l < 11; l++)
				System.out.print("\t" + l);
			System.out.println();
			for (int k = 0; k < 10; k++) {
				System.out.print((k + 1) + "\t");
				for (int j = 0; j < 10; j++) {
					if (comp_guesses[k][j] == 2)
						System.out.print("X\t");
					else
						System.out.print(((player_board.getGameBoard()[k][j] != null)
								? player_board.getGameBoard()[k][j].getName()
								: "O") + "\t");
				}
				System.out.println();
			}
			System.out.println();

			if (player_board.gameWon()) {
				System.out.println("The computer has won the game. Better luck next time.");
				break;
			}
		}
	}
}
