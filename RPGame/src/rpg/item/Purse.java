package rpg.item;

import be.kuleuven.cs.som.annotate.Model;
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
	 * @effect The fibonacci numbers are shifted.
	 *         | shiftFibonacciNumbers()
	 */
	public Purse(Weight weight, BackPack backpack, Creature holder,
			Weight capacity) {
		super(generateId(), weight, backpack, holder, capacity);
		shiftFibonacciNumbers();
		
	}
	
	/**
	 * Proceed in the row of fibonacci.
	 * 
	 * @post The new second last id is equal to the old last id.
	 *       | new.getSecondLastid() == new.getLastId()
	 * @Post The new last id is equal to the id.
	 *       | new.getLastId() == getId()
	 */
	@Model
	private void shiftFibonacciNumbers() {
		secondLastId = lastId;
		lastId = getId();
	}
	
	private static long secondLastId = 0;
	/**
	 * Returns the second last id.
	 */
	@Model
	private static long getSecondLastId()
	{
		return secondLastId;
	}
	
	private static long lastId = 0;
	/**
	 * Returns the last id.
	 */
	@Model
	private static long getLastId()
	{
		return lastId;
	}
	
	/**
	 * Generates the nth fibonacci number 
	 * Generates the next fibonacci number based on the last two ids.
	 * 
	 * @return One if the sum of the secondLastId plus the lastId is less than or equal to one.
	 *         | if(getSecondLastId() + getLastId() <= 1) then
	 *         |    result == 1
	 *         Otherwise return the same sum of the secondLastId and the lastId
	 *         | else then
	 *         |    result == getSecondLastId() + getLastId()
	 */
	@Model
	private static long generateId(){
		long nextId = secondLastId + lastId;
		if(nextId <= 1)
			nextId = 1;
		return nextId;
	}
	/**
	 * Checks whether this purse can have the given id as its id.
	 * 
	 * @param  id
	 *         The id to check.
	 * @return False if Item can't have the given id as its id.
	 *         | if(!super.canHaveAsId(id)) then
	 *         |    result == false
	 * @return True if and only if the given id is a fibonacci number.
	 *         | let
	 *         |    firstRoot = Math.sqrt(5*Math.pow(id,2) + 4);
	 *	       |    secondRoot = Math.sqrt(5*Math.pow(id,2) - 4);
	 *         | in
	 *         |    if(Math.round(firstRoot) == firstRoot || Math.round(secondRoot) == secondRoot) then
	 *         |       result == true;
	 *         |    else then
	 *         |       result == false;
	 */
	@Override
	public boolean canHaveAsId(long id)
	{
		if(!super.canHaveAsId(id))
			return false;
		double firstRoot = Math.sqrt(5*Math.pow(id,2) + 4);
		double secondRoot = Math.sqrt(5*Math.pow(id,2) - 4);
		if(Math.round(firstRoot) == firstRoot || Math.round(secondRoot) == secondRoot)
			return true;
		else
			return false;
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
