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
	
	/**
	 * Return the weight of this dukat which is always 50 gram
	 */
	@Override @Basic @Immutable
	public Weight getWeight() {
		return new Weight(50, WeightUnit.G);
	}
	@Override
	public boolean canHaveAsId(long id) {
		return id == 0;
	}
	
}
