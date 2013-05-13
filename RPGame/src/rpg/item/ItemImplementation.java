package rpg.item;

import java.util.Enumeration;

import rpg.creature.Creature;
import be.kuleuven.cs.som.annotate.*;
/**
 * A class, involving an id, a weight, a backpack, a parent
 * and a value
 * 
 * @author Mathias, Frederic
 *
 */
public abstract class ItemImplementation implements Item, Parent {
	/**
	 * Initialize this new item with the given id, weight, backpack and parent
	 * 
	 * @param  id
	 * 		   The id for this item
	 * @param  weight
	 * 		   The weight of this item
	 * @param  parent
	 * 		   The parent of this item
	 * @param  value
	 * 			The value of this item
	 * @post   The weight of this item equals the given weight
	 * 		   | new.getWeight() == weight
	 * @effect The id of this item is set to the given id
	 * 		   | setId(id)
	 * @effect The parent of this item is set to the given parent
	 * 		   | setParent(parent)
	 * @effect The value of this item is set to the given value.
	 *         | setValue(value)
	 */
	@Raw
	public ItemImplementation(long id, Weight weight, Parent parent, int value){
		setId(id);
		this.weight = weight;
		setParent(parent);
		setValue(value);
	}
	
	/**
	 * Initialize this new item with the given id, weight, backpack and holder
	 * 
	 * @param  id
	 * 		   The id for this item
	 * @param  weight
	 * 		   The weight of this item
	 * @param  parent
	 * 		   The parent of this item
	 * @effect Initialize this item implementation with the given weight, backpack and holder
	 * 		   and 0 as its value and its id
	 *         | this(0, weight, parent, 0)
	 */
	@Raw
	public ItemImplementation(Weight weight, Parent parent){
		this(0, weight, parent, 0);
	}
	
	/**
	 * Initialize this new item with the given id, weight, and holder
	 * 
	 * @param  id
	 * 		   The id for this item
	 * @param  weight
	 * 		   The weight of this item
	 * @param  parent
	 * 		   The parent of this item
	 * @effect Initialize this item implementation with the given weight, parent and holder
	 * 		   and 0 as its id
	 *         | this(0, weight, backpack, holder, 0)
	 */
	public ItemImplementation(long id, Weight weight, Parent parent)
	{
		this(id, weight, parent, 0);
	}
	private int value;
	/**
	 * Set the value of this item to the given value
	 * 
	 * @param val
	 * 		  The value to set
	 * @post  The new value of this item equals the given value
	 * 		  | new.getValue() == val	
	 */
	protected void setValue(int val){
		value = val;
	}
	
	/**
	 * @see Interface Item
	 */
	@Override
	public int getValue() {
		return value;
	}
	
	
	private final Weight weight;
	
	/**
	 * @see Interface Item
	 */
	@Override @Basic @Immutable
	public Weight getWeight() {
		return weight;
	}
	
	protected long id;
	
	/**
	 * @see Interface Item
	 */
	@Override @Basic @Immutable
	public long getId() {
		return id;
	}
	
	/**
	 * Sets the id of this item implementation to the given id
	 * 
	 * @param id
	 *        The id to be set.
	 * @post The id of this item implementation is equal to the given id.
	 *       | getId() == id
	 */
	protected void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * @see Interface Item
	 */
	@Override
	public boolean canHaveAsId(long id)
	{
		return getId() >= 0;
	}
	
	/**
	 * @see Interface Item
	 */
	@Override
	public boolean hasValidId()
	{
		return canHaveAsId(getId());
	}
	
	private Parent parent;
	
	/**
	 * Set the parent of this item to the given parent
	 * 
	 * @param parent
	 * 		  The new parent for this item
	 * @post  The parent of this item equals the given parent
	 * 		  | new.getParent() == parent
	 */
	public void setParent(Parent parent){
		this.parent = parent;
	}
	
	/**
	 * Return the parent of this item
	 */
	@Basic
	public Parent getParent(){
		return parent;
	}
	
	/**
	 * Returns the holder of this item implementation.
	 * 
	 * @return The holder of the parent of this item implementation.
	 *         | result == getParent().getHolder()
	 */
	public Creature getHolder()
	{
		if(getParent() == null)
			return null;
		return getParent().getHolder();
	}

}
