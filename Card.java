public abstract class Card {
	
	protected String name; 
	
	/**
	 * A card type that holds the name field of cards  
	 * @param name
	 */
	public Card(String name){
		this.name = name;
	}
	
	/**
	 * Gets name of Card 
	 */
	public abstract String getName();
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Card){
			Card c = (Card)o;
			return this.getName().equals(c.getName());
		}
		return false;
	}
	
}