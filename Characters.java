public class Characters extends Card {
	
	/**
	 * A card that holds character names  
	 * @param name
	 */
	public Characters(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Characters){
			Characters c = (Characters)o;
			return this.getName().equals(c.getName());
		}
		return false;
	}

}
