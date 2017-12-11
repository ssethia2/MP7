
public class Player {
	/*
	 * Player's name.
	 */
	private String name;
	
	public Player() {
		name = "";
	}
	/**
	 * Constructor to initialize the name.
	 */
	public Player(String n) {
		name = n;
	}
	
	/**
	 * Returns name of the player
	 */
	public String getName() {
		return this.name;
	}
}
