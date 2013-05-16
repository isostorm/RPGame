package rpg.item;

import be.kuleuven.cs.som.annotate.Model;


/**
 * A class of purses.
 * a purse is a container that only contains dukats
 * @author Mathias, Frederic
 * 
 * @invar The capacity of this purse is a valid capacity.
 *        | hasValidCapacity()
 *
 */
public class Purse extends Container{
	
	/**
	 * @param  weight
	 * 		   The weight of this purse
	 * @param  amountOfDukats
	 *         The amount of dukats to be added to this purse.
	 * @effect A new container is initialized with 
	 * 		   the generated id and the given weight, parent and capacity
	 * 		   | super(generateId(), weight, parent, capacity)
	 * @effect The given amount of dukats are added to this purse.
	 *         | addDukats(amountOfDukats)
	 * @effect The fibonacci numbers are shifted.
	 *         | shiftFibonacciNumbers()
	 * @post   The purse contains the given
	 */
	public Purse(Weight weight, Weight capacity, int amountOfDukats) {
		super(generateId(), weight, capacity);
		addDukats(amountOfDukats);
		shiftFibonacciNumbers();
	}
	/**
	 * Adds the given amount of dukats to this purse.
	 * 
	 * @param amountOfDukats
	 *        The amount of dukats to add to this purse.
	 * @post  The number of dukats in this purse equals the sum of the given amount
	 *        of dukats and the dukats which were already in this purse.
	 *        | new.getDirectItems().size() == amountOfDukats + old.getDirectItems().size()
	 */
	@Model
	private void addDukats(int amountOfDukats)
	{
		for(int i = 0; i < amountOfDukats; i++)
			addDukat(new Dukat());
	}
	/**
	 * A variable storing the maximum capacity a purse can accept.
	 */
	// TODO nodig??
	public static final Weight MAX_CAPACITY = new Weight(5, WeightUnit.KG);
	
	/**
	 * Checks whether this purse can have the given capacity as its capacity.
	 * 
	 * @param  capacity
	 *         The capacity to check.
	 * @return True if and only if the given capacity is less than or equal to the maximum capacity.
	 *         | result == ( capacity.compareTo(MAX_CAPACITY) <= 0 )
	 */
	public boolean canHaveAsCapacity(Weight capacity)
	{
		return capacity.compareTo(MAX_CAPACITY) <= 0;
	}
	
	/**
	 * Checks whether this purse has a valid capacity.
	 * 
	 * @return True if this purse can have its capacity as its capacity.
	 *         | result == canHaveAsCapacity(getCapacity())
	 */
	public boolean hasValidCapacity()
	{
		return canHaveAsCapacity(getCapacity());
	}
	/**
	 * Sets the capacity of this purse.
	 * 
	 * @param capacity
	 *        The capacity to set.
	 * @post  If this purse can't have the given capacity as its capacity,
	 *        the capacity is equal to the maximum capacity.
	 *        | if(!canHaveAsCapacity(capacity)) then
			  |    new.getCapacity() == MAX_CAPACITY
			  Otherwise the capacity is equal to the given capacity.
			  | else then
			  |    new.getCapacity() == capacity
	 */
	@Override
	public void setCapacity(Weight capacity) {
		if(!canHaveAsCapacity(capacity))
			super.setCapacity(MAX_CAPACITY);
		super.setCapacity(capacity);
			
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
	 * @effect If the given dukat can't be added to this purse, this purse is terminated
	 * 		   | if(!canAddItem(dukat)) then
	 *	   	   |	terminate()
	 * 		   Otherwise,The dukat is added to the enclosing container of this purse
	 * 		   | else
	 *   	   |	super.addItem(dukat)
	 */
	public void addDukat(Dukat dukat){
		if(!canAddItem(dukat))
			terminate();
		else 
			super.addItem(dukat);
	}
	
}
