package rpg.item;

import rpg.creature.Creature;

/**
 * A class of purses.
 * a purse is a container that only contains dukats
 * @author Mathias, Frederic
 *
 */
public class Purse extends Container{
	
	/**
	 * @param  weight
	 * 		   The weight of this purse
	 * @param  backpack
	 * 		   The enclosing backpack of this purse
	 * @param  holder
	 * 		   The holder of this purse
	 * @effect A new container is initialized with 
	 * 		   the given a generated id, weight, backpack, holder and capacity
	 * 		   | super(generateId(), weight, backpack, holder, capacity)
	 */
	public Purse(Weight weight, BackPack backpack, Creature holder,
			Weight capacity) {
		super(generateId(), weight, backpack, holder, capacity);
		
	}
	
	//TODO
	private static long generateId(){
		return 0;
		
	}
	
	/**
	 * Add the given dukat to this purse
	 * 
	 * @param  dukat
	 * 		   The dukat to add to this purse
	 * @effect The dukat is added to the enclosing container of this purse
	 * 		   | super.addItem(dukat)
	 */
	public void addDukat(Dukat dukat){
		super.addItem(dukat);
	}
	
}
