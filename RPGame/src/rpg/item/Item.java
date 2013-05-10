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
	 * Checks whether or this item can have the given id as its id.
	 * 
	 * @return True if and only if the given id is positive.
	 *         | result == (id >= 0)
	 */
	public boolean canHaveAsId(long id);
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
