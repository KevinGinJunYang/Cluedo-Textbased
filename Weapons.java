public class Weapons extends Card {

	/**
	 *  A card that holds weapon names
	 *  
	 * @param name
	 */
	public Weapons(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Weapons){
			Weapons c = (Weapons)o;
			return this.getName().equals(c.getName());
		}
		return false;
	}

}
