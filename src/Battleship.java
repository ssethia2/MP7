import java.util.Scanner;

/* Driver program for game.
 */

public class Battleship {
	void main(){
		System.out.println("Welcome to Battleship!\n");
		Board player_board = new Board();
		Board comp_board = new Board();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter you name: ");
		String player_name = sc.next();
		player_name += sc.nextLine();
		Player player = new Player(player_name);
		Player comp = new Player("Computer");
		System.out.println("\nEnter the name, length, and orientation of 5 boats with lengths 2, 2, 3, 4, and 5.\n");
		String name = "";
		int len = 0;
		int[] check_len = {2,2,3,4,5};
		int count = 0;
		int x = 0;
		int y = 0;
		boolean orientation = false;
		Ships[] player_ships = new Ships[5];
		Ships[] comp_ships = new Ships[5];
		for(int i = 1; i < 6; i++) {
			System.out.println("Ship " + i + ".\n");
			System.out.print("Enter the name of the ship: ");
			name = sc.next();
			name += sc.nextLine();
			
			do {
			System.out.print("Enter the length of the boat: ");
			len = sc.nextInt();
			for(int j = 0; j < check_len.length; j++) {
				if(check_len[j] == len) {
					count++;
					check_len[j] = (Integer) null;
				}
			}
			if(count > 0) {
				break;
			}
			System.out.println("Invalid length or limit of number of boats possible with this length reached.");
			} while(count == 0);
			count = 0;
			player_ships[i - 1] = new Ships(name, len);
			
			do {
			System.out.print("Enter the co-ordinates of the position you want to place the boat in, separated by a space: ");
			x = sc.nextInt();
			y = sc.nextInt();
			System.out.println("Enter the orientation of the boat (0 for horizontal and 1 for vertical): ");
			if(sc.nextInt() == 0)
				orientation = false;
			else
				orientation = true;
			if(player_board.canPlace(player_board, player_ships[i-1], orientation, x, y))
				break;
			System.out.println("Invalid co-ordinates. Ship already in or around that orientation.");
			} while(true);
			
			comp_ships[i - 1] = new Ships("Computer's boat " + Integer.toString(i), (i>1)?i:i+1);
		}
		
		/*
		 * setShips
		 */
		
		System.out.println("\nEnter guesses as co-ordinates, which should be 2 integers separated by a space.\n");
		while(true) {
			//Player's turn
			System.out.print("Make a guess: ");
			x = sc.nextInt();
			y = sc.nextInt();
			if(x > 10 || x < 1 || y > 10 || y < 0 || comp_board.getGameBoard()[x][y] == null) {
				do {
					System.out.print("Out of bounds or already . Enter co-ordinates between (1,1) and (10,10): ");
					x = sc.nextInt();
					y = sc.nextInt();
				}while(x > 10 || x < 1 || y > 10 || y < 0 || comp_board.getGameBoard()[x][y] == null);
			}
			
			if(comp_board.getGameBoard()[x][y] != null || 0) {
				comp_board.getGameBoard()[x][y].setHit();
				if(gameWon()) {
					System.out.println("\nYou won! Congratulations!");
					break;
				}
			}
			//Computer's turn
			do {
			x = (int) (Math.random()*9 + 1);
			y = (int) (Math.random()*9 + 1);
			} while(x > 10 || x < 1 || y > 10 || y < 0 || player_board.getGameBoard()[x][y] == null);
			if(player_board.getGameBoard()[x][y] != null || 0) {
				player_board.getGameBoard()[x][y].setHit();
				if(gameWon()) {
					System.out.println("The computer has won the game. Better luck next time.");
					break;
				}
			}
		}
	}
}