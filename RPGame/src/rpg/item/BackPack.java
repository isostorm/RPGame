package rpg.item;
/**
 * A class of backpacks
 * @author Mathias
 *
 */
public class BackPack extends Container{

	/**
	 * @param  weight
	 * 		   The weight of this backpack
	 * @param  backpack
	 * 		   The enclosing backpack of this backpack
	 * @param  holder
	 * 		   The holder of this backpack
	 * @effect A new container is initialized with 
	 * 		   the given a generated weight, backpack, holder and capacity
	 * 		   | super(generateId(), weight, backpack, holder, capacity)
	 */
	public BackPack(Weight weight, BackPack backpack,
			Character holder, Weight capacity) {
		super(generateId(), weight, backpack, holder, capacity);
		
	}
	
	//TODO
	private static long generateId(){
		return 0;
	}
	
	
}

	
