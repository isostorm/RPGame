package rpg.item;
import rpg.creature.Creature;
import rpg.exception.IllegalAddItemException;
import rpg.exception.NoSuchItemException;

public interface Parent {
	
	/**
	 * Return the direct or indirect creature who is the holder of this parent
	 */
	public Creature getHolder();
	
	/**
	 * Check whether the given item can be added to this parent.
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
	 *         Otherwise false if the holder can't accept the given item.
	 *         | if( !getHolder().canAddItem(item) ) then
	 *         |    result == false
	 *         
	 */
	public boolean canAddItem(Item item);
	
	/**
	 * Check whether this parent contains the given item
	 * 
	 * @param  item
	 *         The item to check for
	 * @throws IllegalArgumentException [must]
	 *         If the given item is not effective
	 *         | item == null
	 */
	public boolean containsDirectItem(Item item);
	
	/**
	 * Remove the given item from the content of this parent
	 * 
	 * @param  item
	 *         The item to remove from the content of this parent
	 * @post   The content of this parent doesn't contain the given item anymore
	 *         | !containsDirectItem(item)
	 * @post   If the item is an item implementation, the items parent is not effective.
	 *         | if(item instanceof ItemImplementation) then
	 *         |    item.getParent() == null
	 * @throws NoSuchItemException [must]
	 *         This anchor doesn't contain the given item
	 *         | !contains(item)
	 * @throws IllegalArgumentException [must]
	 *         The given item is not effective or the given item is an item
	 *         implementation and has a parent.
	 *         | item == null
	 */
	public void removeDirectItem(Item item) throws NoSuchItemException, IllegalArgumentException;

}
