package rpg.item;

import java.util.*;

import rpg.creature.Creature;
import rpg.exception.IllegalAddItemException;
import rpg.exception.NoSuchItemException;

/**
 * An abstract class of containers. A container is an item that consists of
 * other items
 * 
 * @author Mathias, Frederic
 * 
 * @invar The content list is effective.
 *        | content != null
 * @see p.417 invariant for private variable
 */
public abstract class Container extends ItemImplementation implements Parent {

	/**
	 * @param id
	 *            The id for this container
	 * @param weight
	 *            The weight of this container
	 * @param backpack
	 *            The enclosing backpack of this container
	 * @effect A new item implementation is initialized with the given id,
	 *         weight, parent | super(id, weight, parent)
	 * @effect The capacity of this container is set to the given capacity |
	 *         setCapacity(capacity)
	 */
	public Container(long id, Weight weight, Weight capacity) {
		super(id, weight);
		setCapacity(capacity);
		content = new HashMap<Long, ArrayList<Item>>();
	}

	private Weight capacity;

	/**
	 * Return the capacity of this container
	 */
	public Weight getCapacity() {
		return capacity;
	}

	/**
	 * Set the capacity of this container to the given capacity
	 * 
	 * @param capacity
	 *            The capacity to set
	 * @post The new capacity of this container equals the given capacity |
	 *       new.getCapacity == capacity
	 */
	protected void setCapacity(Weight capacity) {
		this.capacity = capacity;
	}

	/**
	 * Gets the total weight of this container
	 * 
	 * @return The sum of the weight of this container the weights of all the
	 *         direct or indirect items in this container.
	 *         | let
	 *         | resultWeight = new Weight(0, WeightUnit.KG)
	 *         | itemEnumeration = getItems()
	 *         | in | for each item in itemEnumeration: |
	 *         resultWeight.add(item.getWeight()) |
	 *         resultWeight.add(getWeight()) | result == resultWeight
	 * @return The unit of the resulting weight is KG. | result.getUnit() ==
	 *         WeightUnit.KG
	 */
	public Weight getTotalWeight() {
		Weight resultWeight = new Weight(0, WeightUnit.KG);
		Enumeration<Item> itemEnumeration = getItems();
		while (itemEnumeration.hasMoreElements()) {
			Item item = itemEnumeration.nextElement();
			resultWeight.add(item.getWeight());
		}
		resultWeight.add(getWeight());
		return resultWeight;
	}

	/**
	 * Gets the total value of this container.
	 * 
	 * @return The sum of the value of this container and the value of all the
	 *         direct or indirect items which are not purses in this container.
	 *         | let | totalValue = 0 | itemEnumeration = getItems() | in | for
	 *         each item in itemEnumeration: | if ( ! (item instanceof Purse) )
	 *         then | totalValue += item.getValue | result == ( totalValue +
	 *         getValue() )
	 * 
	 */
	public int getTotalValue() {
		int totalValue = 0;
		Enumeration<Item> itemEnumeration = getItems();
		while (itemEnumeration.hasMoreElements()) {
			Item item = itemEnumeration.nextElement();
			if (!(item instanceof Purse)) // otherwise the value of each dukat
											// inside a purse is counted twice
				totalValue += item.getValue();
		}
		totalValue += getValue();
		return totalValue;
	}

	private final HashMap<Long, ArrayList<Item>> content;

	/**
	 * Add the given item to the content of this container
	 * 
	 * @param item
	 *            The item to add
	 * @post If this item could be added to the container, the content of this
	 *       container contains the given item | if( canAddItem(item) ) then |
	 *       new.contains(item)
	 * @throws IllegalAddItemException(this, item)
	 *         If the item can't be added to this container
	 *         | !canAddItem(item)
	 */
	protected void addItem(Item item) {
		if(!canAddItem(item))
			throw new IllegalAddItemException(this, item);
		if (!content.containsKey(item.getId())) {
			ArrayList<Item> items = new ArrayList<Item>();
			items.add(item);
			content.put(item.getId(), items);
		} else
			content.get(item.getId()).add(item);
	}

	/**
	 * Checks whether the given item can be added to this container.
	 * 
	 * @param item
	 *            The item to check
	 * @return True if and only if the sum of the total weight of this container
	 *         and the weight of the given item is less than or equal to the
	 *         capacity of this container. | result == (
	 *         getTotalWeight().add(item.getWeight()).compareTo(getCapacity())
	 *         <= 0 )
	 */
	public boolean canAddItemToContainer(Item item) {
		return getTotalWeight().add(item.getWeight()).compareTo(getCapacity()) <= 0;
	}

	/**
	 * Checks whether the given item can be added to this container and the
	 * holder of this container.
	 * 
	 * @param item
	 *            The item to check.
	 * @return False if this container is terminated.
	 *         | if(isTerminated())
	 *         |    result == false
	 *         Otherwise false if the given item is ineffective.
	 *         | if( item == null )
	 *         |    result == false
	 *         Otherwise false if the given item is an item implementation and
	 *         the item has a parent or the item is terminated.
	 *         | if(item instanceof ItemImplementation
	 *              && ( ((ItemImplementation)item).getParent() != null || isTerminated()) ) then
	 *         |    result == false
	 *         Otherwise true if and only if the item can be added to this container and
	 *         if there is a holder, the holder is able to carry the item. |
	 *         result == ( (!hasHolder() || getHolder().canAddItem(item)) &&
	 *         canAddItemToContainer(item) ) TODO proper maken
	 */
	@Override
	public boolean canAddItem(Item item) {
		if(isTerminated())
			return false;
		if(item == null)
			return false;
		if(item instanceof ItemImplementation
				&& ( ((ItemImplementation)item).getParent() != null || isTerminated()) )
			return false;
		return (!hasHolder() || getHolder().canAddItem(item))
				&& canAddItemToContainer(item);
	}

	/**
	 * Check whether this container contains the given item
	 * 
	 * @param item
	 *            The item to check for
	 * @return True if and only if the id of the given item exists as a key and
	 *         if list stored under the key of the given item in the content of
	 *         this container, contains the given item | result ==
	 *         content.containsKey(item.getId()) || |
	 *         content.get(item.getId()).contains(item)
	 * @throws IllegalArgumentException [must]
	 *         If the given item is not effective
	 *         | item == null
	 */
	public boolean containsDirectItem(Item item)
			throws IllegalArgumentException {
		if (item == null)
			throw new IllegalArgumentException();
		if (content.containsKey(item.getId())
				&& content.get(item.getId()).contains(item))
			return true;

		return false;
	}

	/**
	 * Remove the given item from the content of this container
	 * 
	 * @param item
	 *            The item to remove from the content of this container
	 * @post The content of this container doesn't contain the given item
	 *       anymore | !containsDirectItem(item)
	 * @post   If the item is an item implementation, the items parent is not effective.
	 *         | if(item instanceof ItemImplementation) then
	 *         |    item.getParent() == null
	 * @throws NoSuchItemException [must]
	 *         This container doesn't contain the given item 
	 *         | !containsDirectItem(item)
	 * @throws IllegalArgumentException [must]
	 *         The given item is not effective
	 *         | item == null
	 */
	public void removeDirectItem(Item item) throws NoSuchItemException,
			IllegalArgumentException {

		if (item == null)
			throw new IllegalArgumentException();

		if (!containsDirectItem(item))
			throw new NoSuchItemException(item);
		if(item instanceof ItemImplementation)
			((ItemImplementation)item).setParent(null);
		content.get(item.getId()).remove(item);
	}

	/**
	 * Returns the direct items of this container.
	 * 
	 * @return A list of all the direct items in the content. | for each list in
	 *         content.values(): | for each item in list: |
	 *         result.contains(item)
	 */
	public ArrayList<Item> getDirectItems() {
		ArrayList<Item> retValue = new ArrayList<Item>();

		for (ArrayList<Item> itemList : content.values())
			retValue.addAll(itemList);
		return retValue;
	}

	/**
	 * Returns a Enumeration of type Item.
	 * 
	 * 
	 * @return The resulting directory enumerator will effective. | result !=
	 *         null
	 * @return All the items in this enumeration are direct or indirect items of
	 *         this container.
	 */
	public Enumeration<Item> getItems() {
		return new Enumeration<Item>() {
			private int curIndex;

			private ArrayList<Item> items;

			/**
			 * Instantiates the items and the current index.
			 * 
			 * @post Items is a list containing all the direct or indirect items
			 *       of this container. | items ==
			 *       generateItemList(getDirectItems())
			 * @post The current index is equal to -1. | curIndex == -1
			 */
			{
				items = generateItemList(getDirectItems());
				curIndex = -1;
			}

			/**
			 * Generates a list of all the the items and their direct or
			 * indirect sub items.
			 * 
			 * @param items
			 *            The list of items to explore.
			 * @return A list of all the the items and their direct or indirect
			 *         sub items | | for each item in items: | if (item
			 *         instanceof Container) then | result.containsAll(
			 *         generateItemList( ((Container)item).getDirectItems() ) )
			 *         | result.contains( item )
			 */
			private ArrayList<Item> generateItemList(ArrayList<Item> items) {
				ArrayList<Item> retList = new ArrayList<Item>();
				for (Item item : items) {
					if (item instanceof Container)
						retList.addAll(generateItemList(((Container) item)
								.getDirectItems()));
					retList.add(item);
				}
				return retList;
			}

			/**
			 * Checks whether this iterator has more elements.
			 * 
			 * @return True if and only if the current index is strictly less
			 *         than the total number of items in the list minus one. |
			 *         result == ( items.size() - 1 > curIndex )
			 */
			@Override
			public boolean hasMoreElements() {

				return items.size() - 1 > curIndex;
			}

			/**
			 * Returns the next item in this iterator.
			 * 
			 * @see Enumeration
			 */
			@Override
			public Item nextElement() throws NoSuchElementException {
				if (!hasMoreElements())
					throw new NoSuchElementException();
				return items.get(++curIndex);
			}
		};
	}
	/**
	 * Terminates this container and drops all items.
	 * 
	 * @post   The number of items in this container is equal to 0.
	 *         | getDirectItems().size() == 0
	 * @effect Terminates the enclosing ItemImplementation.
	 *         | super.terminate()
	 * @effect Each item is removed from this container.
	 *         | for each item in getDirectItems():
	 *         |    removeDirectItem(item)
	 */
	@Override
	public void terminate()
	{
		super.terminate();
		for( Item item: new ArrayList<Item>(getDirectItems()) )
			removeDirectItem(item);
	}
}
