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
	 * @param  holder
	 * 		   The holder of this backpack
	 * @effect A new container is initialized with 
	 * 		   the given a generated weight, backpack, holder and capacity
	 * 		   | super(generateId(), weight, backpack, holder, capacity)
	 * @post   The number of backpacks is increased by one
	 * 		   | nbOfBackPacks++
	 */
	@Raw
	public BackPack(Weight weight, BackPack backpack,
			Creature holder, Weight capacity) {
		super(generateId(), weight, backpack, holder, capacity);
		nbOfBackPacks++;
	}
	
	/**
	 * A variable storing the total number of created backpacks.
	 */
	private static int nbOfBackPacks = 0;
	
	
	/**
	 * Generates the id for the n-th backpack.
	 * 
	 * @return Return the sum of all binominal coefficients between 1 and the total number of backpacks.
	 *         | let
	 *         |    returnValue = 1
	 *         | in
	 *         |    for each I in 1..nbOfBackPacks:
	 *         |       returnValue *= (nbOfBackPacks-I)/(I+1)
	 * 		   |    result == returnValue
	 */
	@Model
	private static long generateId(){
		long result = 1; // 0 nCr n = 1
		for(int i = 1; i <= nbOfBackPacks; i++)
			result *= (nbOfBackPacks-i)/(i+1);
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
	
	
}

	
