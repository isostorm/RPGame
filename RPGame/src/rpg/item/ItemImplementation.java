package rpg.item;

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
	 * @post   The id of this item is set to the given id
	 * 		   | new.getId() == id
	 * @effect The parent of this item is set to the given parent
	 * 		   | setParent(parent)
	 * @effect The value of this item is set to the given value.
	 *         | setValue(value)
	 */
	@Raw
	public ItemImplementation(long id, Weight weight, Parent parent, int value){
		this.id = id;
		this.weight = weight;
		setParent(parent);
		setValue(value);
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
	
	/**
	 * @see Interface Item
	 */
	public boolean hasValidValue()
	{
		return canHaveAsValue(getValue());
	}
	
	private final Weight weight;
	
	/**
	 * @see Interface Item
	 */
	@Override @Basic @Immutable
	public Weight getWeight() {
		return weight;
	}
	
	protected final long id;
	
	/**
	 * @see Interface Item
	 */
	@Override @Basic @Immutable
	public long getId() {
		return id;
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
