package rpg.creature;

import rpg.exception.IllegalNameException;
import rpg.item.Weight;

public abstract class Creature {
	
	private int strenghtPrecision;
	protected static final double averageProtection = 10.00;
	//protected AnchorPoint body;
	private int hitpoints;
	/**
	 * Return the number of hitpoints of this creature.
	 */
	public int getHitpoints() {
		return hitpoints;
	}
	/**
	 * TODO misschien niet protected test test
	 * @param hitpoints
	 *        The new number of hitpoints of this creature.
	 * @post The number of hitpoints of this creature is equal to the given number of hitpoints.
	 *       | getHitpoints() == hitpoints
	 */
	protected void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	public int getStrength()
	{
		//TODO formule
		return 0;
	}
	public Weight getCapacity()
	{
		//TODO formule;
		return null;
	}
	public abstract int getProtection();
	private String name;
	public String getName()
	{
		return name;
	}
	/**
	 * Sets the name of this creature.
	 * @param name
	 *        The new name of this creature.
	 * @post The new name of this creature is equal to the given name.
	 *       | getName() == name
	 * @throws IllegalNameException(name, this)
	 *         This creature cannot set its name to the given name
	 *         because the given name is invalid.
	 *         | ! isValidName(name)
	 */
	private void setName(String name) throws IllegalNameException
	{
		if(!isValidName(name))
			throw new IllegalNameException(name, this);
		this.name = name;
	}
	/**
	 * TODO commentaar
	 * @param name
	 * @return
	 */
	protected abstract boolean isValidName(String name);
	
	private int maximumHitpoints;
	/**TODO volgorde van commentaar... eerst @pre??
	 * Sets the maximum number of hitpoints this creature can have.
	 * 
	 * @param maxHitpoints
	 *        The maximum number of hitpoints this creature can have.
	 * @pre The given maximum number of hitpoints, maxHitpoints must be a valid maximum number of hitpoints.
	 *      | isValidMaximumHitpoints(maxHitpoints) == true
	 * @post The maximum number of hitpoints of this creature has,
	 *       equals the given maximum number of hitpoints, maxHitpoints.
	 *       | getMaximumHitpoints() == maxHitpoints
	 */
	private void setMaximumHitpoints(int maxHitpoints)
	{
		this.maximumHitpoints =  nearestLargerPrime(maxHitpoints);
	}
	/**
	 * Check whether the given maximum number of hitpoints, maxHitpoint is a valid number of maximum hitpoints.
	 * 
	 * @param  maxHitpoints
	 *         The maximum number of hitpoints to check.
	 * @return True if and only if maxHitpoints is strictly positive and maxHitpoints is a prime.
	 *         | result == ( maxHitpoints > 0 && isPrime(maxHitpoints) )
	 */
	public boolean isValidMaximumHitpoints(int maxHitpoints)
	{
		return maxHitpoints > 0 && isPrime(maxHitpoints);
	}
	/**
	 * Checks whether the given number is a prime number.
	 * @param  number
	 *         The number to check.
	 * @return False if and only if there is a divisor of the number between 2 and the number minus one.
	 *         | result == ( for each integer in 2..(number-1): number%integer == 0 )
	 * @see    p.128 formal specification of for loops
	 */
	private boolean isPrime(int number)
	{
		for(int i = 2; i < number; i++)
			if(number%i == 0)
				return false;
		return true;
	}
	/**
	 * TODO commentaar
	 * @param number
	 * @return
	 */
	private int nearestLargerPrime(int number)
	{
		int result = number+1;
		while(!isPrime(result)) result++;
		return result;
	}
	
}
