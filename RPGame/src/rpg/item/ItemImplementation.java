package rpg.item;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class, involving an id, a weight, a backpack, a holder
 * and a value
 * 
 * @author Mathias, Frederic
 *
 */
public class ItemImplementation implements Item {
	/**
	 * Initialize this new item with the given id, weight, backpack and holder
	 * 
	 * @param  id
	 * 		   The id for this item
	 * @param  weight
	 * 		   The weight of this item
	 * @param  backpack
	 * 		   The enclosing backpack of this item
	 * @param  holder
	 * 		   The holder of this item
	 * @post   The weight of this item equals the given weight
	 * 		   | new.getWeight() == weight
	 * @effect The id of this item is set to the given id
	 * 		   | setId(id)
	 * @effect The backpack of this item is set to the given backpack
	 * 		   | setBackPack(backpack)
	 * @effect The holder of this item is set to the given holder
	 * 		   | setHolder(holder)
	 */
	@Raw
	public ItemImplementation(long id, Weight weight, BackPack backpack, Character holder){
		setId(id);
		this.weight = weight;
		setBackPack(backpack);
		setHolder(holder);
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
	
	private long id;
	/**
	 * Set the id of this item to the given id
	 * 
	 * @param id
	 * 		  The new id for this item
	 * @post  The id of this item equals the given id
	 * 		  | new.getId() == id
	 */
	protected void setId(long id){
		this.id = id;
	}
	
	/**
	 * @see Interface Item
	 */
	@Override @Basic
	public long getId() {
		return id;
	}
	
	private Character holder;
	
	/**
	 * Set the holder of this item to the given holder
	 * 
	 * @param holder
	 * 		  The new holder for this item
	 * @post  The holder of this item equals the given holder
	 * 		  | new.getHolder() == holder
	 */
	public void setHolder(Character holder){
		this.holder = holder;
	}
	
	/**
	 * Return the holder of this item
	 */
	@Basic
	public Character getHolder(){
		return holder;
	}
	
	private BackPack backPack;
	
	/**
	 * Set the backpack which contains this item to the given backpack
	 * 
	 * @param backPack
	 * 		  The backpack that contains this item
	 * @post  The backpack of this item equals the given backpack
	 * 		  | new.getBackPack == backPack
	 * 
	 */
	public void setBackPack(BackPack backPack){
		this.backPack = backPack;
	}
	
	/**
	 * Return the enclosing backpack of this item
	 */
	@Basic
	public BackPack getBackPack(){
		return backPack;
	}

}
