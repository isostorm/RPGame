package rpg.item;

import be.kuleuven.cs.som.annotate.*;

/**
 * An interface of items involving a weight, a value and an identification
 * 
 * @author Mathias
 * 
 * @invar Each ItemImplementation has a valid id.
 *        | hasValidId()
 *        
 */
public interface Item {
	
	
	/**
	 * Return the identification of this item
	 */
	@Basic
	public long getId();
	
	/**
	 * Checks whether or not this item can have the given id as its id.
	 * 
	 * @return True if and only if the given id is positive.
	 *         | result == (id >= 0)
	 */
	public boolean canHaveAsId(long id);
	/**
	 * Checks whether or not this item has a valid id.
	 * 
	 * @return True if and only if this item has a valid id.
	 *         | result == canHaveAsId( getId() )
	 */
	public boolean hasValidId();
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
	
	/**
	 * Checks whether the given weight is a valid weight for this item.
	 * 
	 * @return True if the given weight is effective.
	 *         | getWeight() != null
	 */
	public static boolean isValidWeight();
	
	public boolean hasValidWeight();

	
	
	
}
