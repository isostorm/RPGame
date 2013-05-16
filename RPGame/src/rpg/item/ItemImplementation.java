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
public abstract class ItemImplementation implements Item{
	/**
	 * Initialize this new item with the given id, weight, backpack and parent
	 * 
	 * @param  id
	 * 		   The id for this item
	 * @param  weight
	 * 		   The weight of this item
	 * @param  value
	 * 		   The value of this item
	 * @post   If the given weight is effective, the weight 
	 * 		   of this item equals the given weight
	 * 		   | new.getWeight() == weight
	 * 		   Otherwise the weight of this item equals a weight of 0 KG
	 * 		   | new.getWeight() == new Weight(0, WeightUnit.KG)
	 * @effect The id of this item is set to the given id
	 * 		   | setId(id)
	 * @effect The value of this item is set to the given value.
	 *         | setValue(value)
	 */
	@Raw
	public ItemImplementation(long id, Weight weight, int value){
		setId(id);
		if(weight != null)
			this.weight = weight;
		else
			this.weight = new Weight(0, WeightUnit.KG);
		setValue(value);
	}
	
	/**
	 * Initialize this new item with the given id, weight, backpack and holder
	 * 
	 * @param  id
	 * 		   The id for this item
	 * @param  weight
	 * 		   The weight of this item
	 * @effect Initialize this item implementation with the given weight, backpack and holder
	 * 		   and 0 as its value and its id
	 *         | this(0, weight, 0)
	 */
	@Raw
	public ItemImplementation(Weight weight){
		this(0, weight, 0);
	}
	
	/**
	 * Initialize this new item with the given id, weight, and holder
	 * 
	 * @param  id
	 * 		   The id for this item
	 * @param  weight
	 * 		   The weight of this item
	 * @effect Initialize this item implementation with the given weight, parent and holder
	 * 		   and 0 as its id
	 *         | this(0, weight, backpack, 0)
	 */
	public ItemImplementation(long id, Weight weight)
	{
		this(id, weight, 0);
	}
	private int value;
	/**
	 * Set the value of this item to the given value
	 * 
	 * @param val
	 * 		  The value to set
	 * @pre   This item implementation can have the given value as its value
	 * 		  | canHaveAsValue(value)
	 * @post  The new value of this item equals the given value
	 * 		  | new.getValue() == value	
	 */
	protected void setValue(int value){
		this.value = value;
	}
	
	/**
	 * @see Interface Item
	 */
	@Override
	public int getValue() {
		return value;
	}
	
	 /**
	  * Checks whether the given value is a valid value for this weapon.
	  * 
	  * @see interface Item
	  */
	@Override
	 public boolean canHaveAsValue(int value)
	 {
		 return(value<0);
	 }
	/**
	 * Checks whether this weapon has a valid value.
	 * 
	 * @see interface Item
	 */
	@Override
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
	 * @post If this item implementation can have the given id as its id, 
	 * 	     the id of this item implementation is equal to the given id.
	 *       | if(canHaveAsId(id)) then
	 *       |		getId() == id
	 */
	protected void setId(long id)
	{
		if(canHaveAsId(id))
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
	 * Checks whether this item implementation has a parent.
	 * 
	 * @return True if and only if the parent of this item implementation is effective.
	 *         | result == getParent() != null
	 */
	public boolean hasParent()
	{
		return getParent() != null;
	}
	
	/**
	 * Checks whether this item implementation has a holder.
	 * 
	 * @return True if and only if the holder of this item implementation is effective.
	 *         | result == getHolder() != null
	 */
	public boolean hasHolder()
	{
		return getHolder() != null;
	}
	
	/**
	 * Returns the holder of this item implementation.
	 * 
	 * @return The holder of the parent of this item implementation.
	 *         | result == getParent().getHolder()
	 */
	public Creature getHolder()
	{
		if(!hasParent())
			return null;
		return getParent().getHolder();
	}
	
	private boolean isTerminated = false;
	/**
	 * Check whether this item implementation is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated()
	{
		return isTerminated;
	}
	/**
	 * Terminate this item implementation.
	 */
	public void terminate()
	{
		if(hasParent())
			getParent().removeDirectItem(this);
		isTerminated = true;
	}
	/**
	 * Checks whether this item can have the given item as its parent.
	 * 
	 * @param parent
	 *        The parent to check.
	 * @return True if and only if the given parent is not effective or
	 *         this item can be added to the given parent.
	 *         | result == (parent == null) || parent.canAddItem(this)
	 * @see  p.439
	 */
	public boolean canHaveAsParent(Parent parent)
	{
		return (parent == null) || parent.canAddItem(this);
	}
	

}
