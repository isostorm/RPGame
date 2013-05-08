package rpg.item;

import be.kuleuven.cs.som.annotate.*;

/**
 * An interface of items involving a weight, a value and an identification
 * 
 * @author Mathias
 *
 */
public interface Item {
	
	
	/**
	 * Return the identification of this item
	 */
	@Basic
	public long getId();
	
	/**
	 * Return the value of this item
	 */
	@Basic
	public int getValue();
	
	
	/**
	 * Return the weight of this item
	 */
	@Basic @Immutable
	public Weight getWeight();
	
	
}
