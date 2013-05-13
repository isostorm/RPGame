package rpg.item;

import rpg.creature.Creature;
import be.kuleuven.cs.som.annotate.*;

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
	 * @param  parent
	 * 		   The parent of this backpack
	 * @effect A new container is initialized with 
	 * 		   the given a generated weight, parent and capacity
	 * 		   | super(generateId(), weight, parent, capacity)
	 * @post   The number of backpacks is increased by one
	 * 		   | new.getNbOfBackPacks() == old.getNbOfBackPacks() + 1
	 */
	@Raw
	public BackPack(Weight weight, Parent parent, Weight capacity) {
		super(generateId(), weight, parent, capacity);
		nbOfBackPacks++;
	}
	
	/**
	 * A variable storing the total number of created backpacks.
	 */
	private static int nbOfBackPacks = 0;
	/**
	 * Returns the number of backpacks that have been created.
	 */
	@Model
	private static int getNbOfBackPacks()
	{
		return nbOfBackPacks;
	}
	
	/**
	 * Generates the id for the n-th backpack.
	 * 
	 * @return Return the sum of all binominal coefficients between 1 and the total number of backpacks.
	 *         | let
	 *         |    returnValue = 1
	 *         | in
	 *         |    for each I in 1..getNbOfBackPacks():
	 *         |       returnValue *= (getNbOfBackPacks()-I)/(I+1)
	 * 		   |    result == returnValue
	 */
	@Model
	private static long generateId(){
		long result = 1; // 0 nCr n = 1
		for(int i = 1; i <= getNbOfBackPacks(); i++)
			result *= (getNbOfBackPacks()-i)/(i+1);
		return result;
	}
	
	/**
	 * Add an item to this backpack
	 * 
	 * @effect Add an item to the enclosing container of this backpack
	 * 		   | super.addItem(item)
	 */
	@Override
	public void addItem(Item item){
		super.addItem(item);
	}
	
	
	/**
	 * Checks whether this backpack can have the given value as its value.
	 * 
	 * @return True if and only if the given value is less than or equal 
	 *         to 500 and greater than or equal to 0.
	 *         | result == ( (value <= 500) && (value >= 0) )
	 */
	public boolean canHaveAsValue(int value)
	{
		return value <= 500 && value >= 0;
	}
	
}

	
