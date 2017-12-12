public class Ships{
	/**Name of the ship **/
	private String name;
	/**Length of the ship MAX can be 10 units **/
	private int length;
	/**false if boat has not sunk
	 * true if boat has sunk **/
	private boolean sunk = false;
	/**amount of hits taken **/
	private int hit = 0;
	
	
	/**
	 * creates ship
	 * @param string name of ship
	 * @param i len of ship
	 */
	public Ships(String name, int i) {
		//super();//This was giving me an error, fixed now.
		this.setName(name);
		this.setLength(i);
	}

	/**
	 * Checks to see if ship has sunk,
	 *  if ship has not sunk it adds 1 to the amount of hits taken
	 * @param shipLen length of ship
	 * @param hits amount of hits taken
	 */
	public boolean hitShip(/*final int shipLen, final int hits*/) {//I changed this to not need parameters.
		this.setHit();
		if (this.length == this.hit) {
			this.sunk = true;
		}/* else {
			this.hit++;
		} */
		return this.sunk;
	}
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSunk() {
		return sunk;
	}
	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}
	public int getHit() {
		return hit;
	}
	public void setHit() {
		hit++;
	}
}