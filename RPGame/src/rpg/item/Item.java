package rpg.item;

import be.kuleuven.cs.som.annotate.*;

/**
 * An interface of items involving a weight, a value and an identification
 * 
 * @author Mathias
 * 
 * @invar Each ItemImplementation has a valid id.
 *        | hasValidId()
 * @invar Each ItemImplementation has a valid value.
 *        | hasValidValue()
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
	 * Checks whether this ItemImplementation has a valid value.
	 * 
	 * @return True if and only if this item can have the value it has, as its value.
	 *         | result == canHaveAsValue( getValue() )
	 */
	public boolean hasValidValue();
	
	/**
	 * Checks whether or not this ItemImplementation can have the given value as its value.
	 * 
	 * @param  value
	 *         The value to check.
	 */
	public boolean canHaveAsValue(int value);
	
	/**
	 * Return the weight of this item
	 */
	@Basic @Immutable
	public Weight getWeight();

	
	
	
}
