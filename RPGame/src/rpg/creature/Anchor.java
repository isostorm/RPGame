package rpg.creature;

import rpg.exception.IllegalAddItemException;
import rpg.exception.NoSuchItemException;
import rpg.item.Item;
import rpg.item.ItemImplementation;
import rpg.item.Parent;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of anchors involving a name and item
 * 
 * @author Frederic
 *
 * @invar The holder is an effective creature.
 *        | getHolder() != null
 */
public class Anchor implements Parent {
	
	/**
	 * Initializes a new anchor with the given holder, name and item
	 * 
	 * @param  holder
	 *         The holder of this new anchor
	 * @param  name
	 *         The name of this new anchor
	 * @param  item
	 *         The item of this new anchor
	 * @pre    The given holder must be effective.
	 *         | holder != null
	 * @post   The holder of this new anchor equals the given holder
	 * 		   | new.getHolder() == holder
	 * @post   The name of this new anchor equals the given name
	 * 		   | new.getName() == name
	 * @effect The given item is added to this anchor.
	 *         | addItem(item)
	 */
	@Raw
	public Anchor(Creature holder, String name, Item item)
	{
		this.holder = holder;
		this.name = name;
		addItem(item);
		holder.addAnchor(this);
	}
	
	/**
	 * Initialize a new anchor with the given holder and name
	 * @param holder
	 *        The holder of this new anchor
	 * @param name
	 *        The name of this new anchor
	 * @effect This new anchor is initialized with the given holder as its holder,
	 *         the given name as its name and null as its item
	 *         | this(holder, name, null)
	 */
	@Raw
	public Anchor(Creature holder, String name) {
		this(holder, name, null);
	}
	
	/**
	 * Initialize a new anchor with the given holder.
	 * 
	 * @param  holder
	 *         The holder of this new anchor
	 * @effect This new anchor is initialized with the given holder as its holder,
	 *         the given item as its item and null as its name.
	 *         | this(holder, null, item)
	 */
	@Raw
	public Anchor(Creature holder, Item item) {
		this(holder, null, item);
	}
	
	/**
	 * Checks whether this anchor can have the given holder as its holder.
	 * 
	 * @param  holder
	 *         The holder to check
	 * @return True if and only if the given holder is effective.
	 *         | result == ( holder != null )
	 */
	public boolean canHaveAsHolder(Creature holder)
	{
		return holder != null && !holder.isTerminated();
	}
	
	private boolean isTerminated = false;
	
	/**
	 * Check whether this anchor is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated()
	{
		return isTerminated;
	}
	
	/**
	 * Terminate this anchor.
	 * TODO commentaar
	 */
	void terminate()
	{
		getHolder().removeAnchor(this);
		removeItem();
		isTerminated = true;
	}

	private final Creature holder;
	
	/**
	 * Returns the holder of this anchor
	 */
	@Immutable
	public Creature getHolder() {
		return holder;
	}
	
	private final String name;
	
	/**
	 * Returns the name of this anchor
	 */
	@Immutable
	public String getName() {
		return name;
	}
	
	private Item item;
	/**
	 * Returns the item this anchor holds.
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * Sets the item this anchor holds.
	 * 
	 * @param  item
	 *         The new item to be set
	 * @post   The parent of this item is equal to this anchor
	 *         | item.getParent() == this
	 * @post   The item of this anchor equals the given item.
	 *         | new.getItem() == item
	 * @throws IllegalAddItemException
	 *         The given item can't be added to this anchor.
	 *         | !canAddItem(item)
	 */
	public void addItem(Item item) throws IllegalAddItemException {
		if(!canAddItem(item))
			throw new IllegalAddItemException(this, item);
		
		setItem(item);
		
		if(item instanceof ItemImplementation)
			((ItemImplementation)item).setParent(this);
	}
	
	/**
	 * Check whether the given item can be added to this anchor.
	 * 
	 * @return False if the given item is ineffective.
	 *         | if( item == null)
	 *         |    result == false
	 *         False if the item this anchor contains is effective.
	 *         | if (getItem() != null) then
	 *         |    result == false
	 *         Otherwise false if the given item is an instance of
	 *         item implementation and has a parent.
	 *         | if( item instanceof ItemImplementation && ((ItemImplementation)item).hasParent() ) then
	 *         |    result == false
	 *         Otherwise the result is equal to whether or not the item can be added to the holder of this anchor.
	 *         | result == getHolder().canAddItem(item)
	 *         
	 */
	@Override
	public boolean canAddItem(Item item)
	{
		if(item == null)
			return false;
		if(getItem() != null)
			return false;
		if( item instanceof ItemImplementation && ((ItemImplementation)item).hasParent() )
			return false;
		
		return getHolder().canAddItem(item);
	}
	
	/**
	 * TODO
	 * @param replacement
	 * @throws IllegalAddItemException
	 * @throws IllegalArgumentException
	 */
	public void swap(Item replacement) throws IllegalAddItemException, IllegalArgumentException
	{
		if(replacement == null)
			throw new IllegalArgumentException();
		Item oldItem = getItem();
		try
		{
		removeItem();
		addItem(replacement);
		}
		catch(IllegalAddItemException e)
		{
			addItem(oldItem);
			throw new IllegalAddItemException(e.getParent(), e.getItem());
		}
	}
	
	/**
	 * Sets the given item as this anchors item.
	 * 
	 * @param item
	 *        The new item of this anchorpoint
	 * @post  The item of this anchor is equal to the given item.
	 *        | getItem() == item
	 */
	@Model
	private void setItem(Item item)
	{
		this.item = item;
	}
	
	/**
	 * Removes the item this anchor holds.
	 * 
	 * @post If the item this anchor is an instance of ItemImplementation,
	 *       the parent of the item is equal to null.
	 *       | if(getItem() instanceof ItemImplementation) then
	 *		 |    ((ItemImplementation)getItem()).getParent() == null;
	 * @post The item this anchor holds is null.
	 *       | getItem() == null
	 */
	public void removeItem()
	{
		if(getItem() instanceof ItemImplementation)
			((ItemImplementation)getItem()).setParent(null);
		this.item = null;
	}
	
	/**
	 * Check whether this anchor contains the given item
	 * 
	 * @param  item
	 *         The item to check for
	 *         
	 * @return True if and only if the item this anchor contains is equal to the given item.
	 *         | result == ( getItem() == item )
	 * @throws IllegalArgumentException [must]
	 *         If the given item is not effective
	 *         | item == null
	 */
	@Override
	public boolean containsDirectItem(Item item) {
		if(item == null)
			throw new IllegalArgumentException();
		return getItem() == item;
		
	}

	
	/**
	 * Remove the given item from the content of this anchor
	 * 
	 * @param item
	 *        The item to remove from this anchor
	 * @post The content of this anchor doesn't contain the given item anymore
	 *       | !containsDirectItem(item)
	 * @post   If the item is an item implementation, the items parent is not effective.
	 *         | if(item instanceof ItemImplementation) then
	 *         |    item.getParent() == null
	 * @throws NoSuchItemException [must]
	 *             This anchor doesn't contain the given item
	 *             | !containsDirectItem(item)
	 * @throws IllegalArgumentException [must]
	 *             The given item is not effective
	 *             | item == null
	 */
	public void removeDirectItem(Item item) throws NoSuchItemException, IllegalArgumentException {
		if(item == null)
			throw new IllegalArgumentException();
		if (!containsDirectItem(item))
			throw new NoSuchItemException(item);
		setItem(null);
		if(item instanceof ItemImplementation)
			((ItemImplementation)item).setParent(null);
	}


}
