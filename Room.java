public class Room extends Card {

	/**
	 * A card that holds room names
	 * 
	 * @param name
	 */
	public Room(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Room){
			Room c = (Room)o;
			return this.getName().equals(c.getName());
		}
		return false;
	}

}
