package rpg.creature;

import rpg.item.ItemImplementation;
import rpg.item.Parent;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;

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
	 * @param holder
	 *        The holder of this new anchor
	 * @param name
	 *        The name of this new anchor
	 * @param item
	 *        The item of this new anchor
	 * @pre   The given holder must be effective.
	 *        | holder != null
	 * @post  The holder of this new anchor equals the given holder
	 * 		  | new.getHolder() == holder
	 * @post  The name of this new anchor equals the given name
	 * 		  | new.getName() == name
	 * @post  The item of this new anchor equals the given item
	 *        | new.getItem() == item
	 */
	public Anchor(Creature holder, String name, Object item)
	{
		this.holder = holder;
		this.name = name;
		setItem(item);
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
	public Anchor(Creature holder, String name) {
		this(holder, name, null);
	}
	
	/**
	 * Initialize a new anchor with the given holder.
	 * 
	 * @param  holder
	 *         The holder of this new anchor
	 * @effect This new anchor is initialized with the given holder as its holder,
	 *         the given item as its object andnull as its name.
	 *         | this(holder, null, item)
	 */
	public Anchor(Creature holder, Object item) {
		this(holder, null, item);
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
	
	private Object item;
	/**
	 * Returns the item this anchor holds.
	 */
	public Object getItem() {
		return item;
	}
	/**
	 * Sets the item this anchor holds.
	 * 
	 * @param item
	 *        The new item to be set
	 * @post  If the given item is an instance of ItemImplementation,
	 *        the parent of this item is equal to this anchor.
	 *        | if(item instanceof ItemImplementation) then
	 *        |    ((ItemImplementation)item).getParent() == this
	 * @post  The item of this anchor equals the given item.
	 *        | new.getItem() == item
	 */
	public void setAnchorItem(Object item) {
		removeItem();
		setItem(item);
		if(item instanceof ItemImplementation)
			((ItemImplementation)item).setParent(this);
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
	private void setItem(Object item)
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


}
