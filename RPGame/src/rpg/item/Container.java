package rpg.item;

import java.util.*;

import rpg.exception.NoSuchItemException;


/**
 * An abstract class of containers.
 * A container is an item that consists of other items
 * 
 * @author Mathias, Frederic
 *
 */
public abstract class Container extends ItemImplementation{
	
	/**
	 * @param  id
	 * 		   The id for this container
	 * @param  weight
	 * 		   The weight of this container
	 * @param  backpack
	 * 		   The enclosing backpack of this container
	 * @param  holder
	 * 		   The holder of this container
	 * @effect A new item implementation is initialized with 
	 * 		   the given id, weight, backpack and holder
	 * 		   | super(id, weight, backpack, holder)
	 * @effect The capacity of this container is set to the given capacity
	 * 		   | setCapacity(capacity)
	 */
	public Container(long id, Weight weight, BackPack backpack, Creature holder, Weight capacity) {
		super(id, weight, backpack, holder);
		setCapacity(capacity);
		content = new HashMap<Long, ArrayList<Item> >();
	}
	
	private Weight capacity;
	
	/**
	 * Return the capacity of this container
	 */
	public Weight getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity 
	 * 		  The capacity to set
	 * @post  The new capacity of this container equals the given capacity
	 * 		  | new.getCapacity == capacity
	 */
	protected void setCapacity(Weight capacity) {
		this.capacity = capacity;
	}
	
	//TODO
	public Weight getTotalWeight(){
		Weight resultWeight = new Weight(0, WeightUnit.KG);
		Enumeration<Item> itemEnumeration = getItems();
		while(getItems().hasMoreElements())
		{
			Item item = itemEnumeration.nextElement();
			resultWeight.add(item.getWeight());
		}
		return resultWeight;
	}
	
	//TODO
	public int getTotalValue(){
		int totalValue = 0;
		Enumeration<Item> itemEnumeration = getItems();
		while(getItems().hasMoreElements())
		{
			Item item = itemEnumeration.nextElement();
			totalValue += item.getValue();
		}
		return totalValue;
	}
	
	private HashMap<Long, ArrayList<Item> > content ;
	
	/**
	 * Add the given item to the content of this container
	 * @param item
	 * 		  The item to add
	 * @post  The content of this container contains the given item 
	 *        | contains(item)
	 */
	public void addItem(Item item){
		if(content.containsKey(item.getId())){
			ArrayList <Item> items = new ArrayList<Item>();
			items.add(item);
			content.put(item.getId(), items);
		}else{
			content.get(item.getId()).add(item);
		}
			
	}
	
	/**
	 * Check whether this container contains the given item
	 * 
	 * @param  item
	 * 		   The item to check for
	 * @return True if and only if the id of the given item exists as a key 
	 * 		   and if list stored under the key of the given item in the content of this container,
	 * 		   contains the given item
	 *         | result == content.containsKey(item.getId()) ||
	 *         | 	content.get(item.getId()).contains(item)
	 */
	public boolean contains(Item item){
		if(content.containsKey(item.getId()) && content.get(item.getId()).contains(item))
			return true;
		
		return false;
	}
	
	/**
	 * Remove the given item from the content of this container
	 * 
	 * @param  item
	 * 		   The item to remove from the content of this container
	 * @post   The content of this container doesn't contain the given item anymore
	 * 		   | !contains(item)
	 * @throws NoSuchItemException
	 * 		   This container doesn't contain the given item
	 * 		   | !content.containsKey(item.getId()) ||
	 * 		   |	!content.get(item.getId()).contains(item)
	 * @throws IllegalArgumentException
	 * 		   The given item is not effective
	 * 		   | item == null
	 */
	public void removeItem(Item item) throws NoSuchItemException, IllegalArgumentException{
		
		if(item == null)
			throw new IllegalArgumentException();
		
		if(!content.containsKey(item.getId()) || !content.get(item.getId()).contains(item))
			throw new NoSuchItemException(item);
		
		content.get(item.getId()).remove(item);
	}
	//TODO commentaar? zie mail
	public ArrayList<Item> getDirectItems()
	{
		ArrayList<Item> retValue = new ArrayList<Item>();
		for(ArrayList<Item> itemList : content.values())
			retValue.addAll(itemList);
		return retValue;
	}
	public Enumeration<Item> getItems()
	{
		return new Enumeration<Item>() {
			private int curIndex;
			private ArrayList<Item> items;
			
			{
				items = generateItemlist();
				curIndex = -1;
			}
			private ArrayList<Item> generateItemlist()
			{
				return generateItemListRecursive(getDirectItems());
			}
			/**
			 * Generates a list of items in 
			 * 
			 * @param items
			 *        The list of items to explore.
			 * @return The items 
			 */
			private ArrayList<Item> generateItemListRecursive(ArrayList<Item> items)
			{
				ArrayList<Item> retList = new ArrayList<Item>();
				for(Item item: items)
				{
					if(item instanceof Container)
						retList.addAll( generateItemListRecursive( ((Container)item).getDirectItems() ) );
					retList.add(item);
				}
				return retList;
			}
			/**
			 * Checks whether this iterator has more elements.
			 * 
			 * @return True if and only if the current index is strictly 
			 *         less than the total number of items in the list minus one.
			 *         | result == ( items.size() - 1 > curIndex )
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
				return items.get(++curIndex);
			}
		};
	}
}