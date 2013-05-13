package rpg.item;

import be.kuleuven.cs.som.annotate.*;

public class Dukat implements Item{

	
	public Dukat(){
		
	}
	/**
	 * Return the value of this dukat, which is always 0
	 */
	@Override @Basic @Immutable
	public long getId() {
		return 0;
	}
	
	/**
	 * Return the value of this dukat, which is always 1
	 */
	@Override @Basic @Immutable
	public int getValue() {
		return 1;
	}
	
	private static final Weight WEIGHT = new Weight(50, WeightUnit.G);
	
	/**
	 * Return the weight of this dukat which is always 50 gram
	 */
	@Override @Basic @Immutable
	public Weight getWeight() {
		return WEIGHT;
	}
	
	/**
	 * Checks whether this dukat can have the given id as its id.
	 * 
	 * @return True if and only if the given id is equal to 0.
	 *         | result == ( id == 0 )
	 */
	@Override
	public boolean canHaveAsId(long id) {
		return id == 0;
	}
	
	/**
	 * Checks whether this dukat has a valid id.
	 * 
	 * @return True if and only if this dukat can have its id as its id.
	 *         | result == canHaveAsId( getId() )
	 * 
	 */
	@Override
	public boolean hasValidId() {
		return canHaveAsId(getId());
	}

	
}
