package rpg.item;

import java.util.*;

import exception.NoSuchItemException;

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
	public Container(long id, Weight weight, BackPack backpack, Character holder, Weight capacity) {
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
		return;
	}
	
	//TODO
	public int getTotalValue(){
		return;
	}
	
	private HashMap<Long, ArrayList<Item> > content ;
	
	/**
	 * Add the given item to the content of this container
	 * @param item
	 * 		  The item to add
	 * @post  The list stored under the key of the given item in the content of this container,
	 * 		  contains the given item
	 *        | content.get(item.getId()).contains(item)
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
	public boolean exists(Item item){
		if(content.containsKey(item.getId()) || content.get(item.getId()).contains(item))
			return true;
		
		return false;
	}
	
	/**
	 * Remove the given item from the content of this container
	 * 
	 * @param  item
	 * 		   The item to remove from the content of this container
	 * @posts  The list stored under the key of the given item in the content of this container,
	 * 		   doesn't contain the given item anymore
	 * 		   | !content.get(item.getId()).contains(item)
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
	
	/*
	 * Return the number of items in this container
	 */
	public int getNbItems(){
		return content.size();
	}
	
}
