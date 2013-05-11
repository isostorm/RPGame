package rpg.creature;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import rpg.exception.IllegalNameException;
import rpg.item.Weight;
/**
 * A class of creatures involving a name, hitpoints,
 * strength, protection, capacity anda list of anchorpoints.
 * 
 *@invar Each creature has a valid maximum number of hitpoints.
 *       | hasValidMaximumHitpoints()
 *@invar Each creature has a valid name.
 *       | hasValidName()
 *@invar Each creature has a valid number of hitpoints.
 *       | hasValidHitpoints()
 *@invar The strength precision for each creature is a positive number.
 *       | getStrengthPrecision() >= 0
 *@invar Each creatures precision of its strength is equal to the strength precision.
 *       | Math.Round(getStrength()*Math.pow(10,getStrengthPrecision())) ==
 *       | getStrength()*Math.pow(10,getStrengthPrecision())
 *@invar Each creature has proper anchors associated with it.
 *       | hasProperAnchors()
 */
public abstract class Creature {
	private static int strengthPrecision = 2;
	/**
	 * Sets the precision of the strength to the given number for each creature.
	 * 
	 * @param precision
	 *        The precision of the strength
	 * @post  The strength precision is equal to the absolute value
	 *        of the given precision.
	 *        | getStrengthPrecision() == Math.abs( precision )
	 */
	public static void setStrengthPrecision(int precision)
	{
		strengthPrecision = Math.abs(precision);
	}
	
	/**
	 * Returns the strength precision for each creature.
	 */
	@Basic
	public static int getStrengthPrecision()
	{
		return strengthPrecision;
	}
	
	private static final double averageStrength = 10.00;
	//protected AnchorPoint body;
	private double strength;
	/**
	 * Returns the strength of this creature where the precision is equal to the strength precision.
	 * 
	 * @return The strength of this creature rounded down so the precision is equal to the
	 *         strength precision.
	 *         | result == (Math.round(strength*Math.pow(10, getStrengthPrecision()))
	 *                      /Math.pow(10, getStrengthPrecision()))
	 */
	public int getStrength()
	{
		return (int)(Math.round(strength*Math.pow(10, getStrengthPrecision()))
				    /Math.pow(10, getStrengthPrecision()));
	}
	
	/**
	 * Sets the strength to the given strength.
	 * 
	 * @param strength
	 *        The new strength of this creature.
	 */
	private void setStrength(double strength)
	{
		this.strength = strength;
	}
	
	/**
	 * Multiplies the strength of this creature with the given multiplier.
	 * 
	 * @param  multiplier
	 *         The number to multiply the strength with.
	 * @effect The strength is set to the old strength multiplied with the given multiplier.
	 *         | setStrength(getStrength()*multiplier)
	 */
	public void multiplyStrength(int multiplier)
	{
		setStrength(getStrength()*multiplier);
	}
	
	/**
	 * Divides the strength of this creature with the given divisor.
	 * 
	 * @param  divisor
	 *         The number to divide the strength with.
	 * @effect The strength is set to the old strength divided with the given divisor.
	 *         | setStrength(getStrength()/divisor)
	 */
	public void divideStrength(int divisor)
	{
		setStrength(getStrength()/divisor);
	}
	//TODO getTotalStrength() maken
	public double getTotalStrength()
	{
		return 0.0;
	}
	
	public abstract Weight getCapacity();
	
	public abstract int getProtection();
	
	private String name;
	
	/**
	 * Returns the name of this creature.
	 */
	@Basic @Raw
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
	@Raw
	private void setName(String name) throws IllegalNameException
	{
		if(!canHaveAsName(name))
			throw new IllegalNameException(name, this);
		this.name = name;
	}
	
	/**
	 * Checks whether this creature can have the given name as its name.
	 * 
	 * @param  name
	 *         The name to check.
	 * @return True if and only if the given name is effective.
	 *         | result == ( name != null )
	 */
	@Raw 
	protected boolean canHaveAsName(String name)
	{
		return name != null;
	}
	
	/**
	 * Checks whether this creature has a valid name.
	 * 
	 * @return True if the creature can have its name as its name.
	 *         | result == ( canHaveAsName( getName() ) )
	 */
	@Raw
	public boolean hasValidName()
	{
		return canHaveAsName(getName());
	}
	
	private int hitpoints;
	/**
	 * Return the number of hitpoints of this creature.
	 */
	public int getHitpoints() {
		return hitpoints;
	}
	
	/**
	 * TODO misschien niet protected
	 * @param hitpoints
	 *        The new number of hitpoints of this creature.
	 * @pre  The creature can have the given number of hitpoints as its hitpoints.
	 *       | canHaveAsHitpoints( hitpoints )
	 * @post The number of hitpoints of this creature is equal to the given number of hitpoints.
	 *       | getHitpoints() == hitpoints
	 */
	@Raw
	protected void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}
	
	/**
	 * Checks whether this creature can have the given number of hitpoints as its hitpoints.
	 * 
	 * @param  hitpoints
	 *         The hitpoints to check.
	 * @return True if and only if the given hitpoints greater than or equal to 0 and if
	 *         the hitpoints are less than or equal to the maximum number of hitpoints this
	 *         creature can have and the number of hitpoints is a prime number.
	 *         | result == ( (hitpoints >= 0)
	 *                       && ( hitpoints <= getMaximumHitpoints() )
	 *                       && ( isPrime(hitpoints) ) )
	 */
	public boolean canHaveAsHitpoints(int hitpoints)
	{
		return hitpoints >= 0 && hitpoints <= getMaximumHitpoints() && isPrime(hitpoints);
	}
	
	/**
	 * Checks whether this creature has a valid number of hitpoints.
	 * 
	 * @return True if and only if this creature can have its hitpoints as its hitpoints.
	 *         | result == ( canHaveAsHitpoints( getHitpoints() ) )
	 */
	public boolean hasValidHitpoints()
	{
		return canHaveAsHitpoints(getHitpoints());
	}
	private int maximumHitpoints;
	/**
	 * Sets the maximum number of hitpoints this creature can have.
	 * 
	 * @param maxHitpoints
	 *        The maximum number of hitpoints this creature can have.
	 * @pre The given maximum number of hitpoints, maxHitpoints must 
	 *      be a valid maximum number of hitpoints.
	 *      | isValidMaximumHitpoints(maxHitpoints) == true
	 * @post The maximum number of hitpoints of this creature has,
	 *       equals the given maximum number of hitpoints, maxHitpoints.
	 *       | getMaximumHitpoints() == maxHitpoints
	 */
	@Raw
	private void setMaximumHitpoints(int maxHitpoints)
	{
		this.maximumHitpoints =  maxHitpoints;
	}
	
	/**
	 * Returns the maximum number of hit points for this creature.
	 */
	@Basic @Raw
	public int getMaximumHitpoints()
	{
		return maximumHitpoints;
	}
	
	/**
	 * Check whether the given maximum number of hitpoints is a valid number of maximum hitpoints.
	 * 
	 * @param  maxHitpoints
	 *         The maximum number of hitpoints to check.
	 * @return True if and only if maxHitpoints is strictly positive and maxHitpoints is a prime.
	 *         | result == ( maxHitpoints > 0 && isPrime(maxHitpoints) )
	 */
	@Raw
	public static boolean isValidMaximumHitpoints(int maxHitpoints)
	{
		return maxHitpoints > 0 && isPrime(maxHitpoints);
	}
	

	/**
	 * Checks whether this creatures maximum number of hitpoints
	 * @return True if and only if the maximum number of hitpoints of this creature is valid.
	 *         | result == ( isValidMaximumHitpoints( getMaximumHitpoints() ) )
	 */
	@Raw
	public boolean hasValidMaximumHitpoints()
	{
		return isValidMaximumHitpoints(getMaximumHitpoints());
	}
	
	/**
	 * Checks whether the given number is a prime number.
	 * @param  number
	 *         The number to check.
	 * @return False if and only if there is a divisor of the number between 2 and the number minus one.
	 *         | result == ( for each integer in 2..(number-1): number%integer == 0 )
	 * @see    p.128 formal specification of for loops
	 */
	@Raw
	private static boolean isPrime(int number)
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
	@Raw
	private int nearestLargerPrime(int number)
	{
		int result = number+1;
		while(!isPrime(result)) result++;
		return result;
	}
	
	private final ArrayList<Anchor> anchors; // TODO initialize in constructor
	public int getNBAnchors()
	{
		return anchors.size();
	}
	/**
	 * Returns the anchor at the given index.
	 * 
	 * @param index
	 * @pre   The given index must be positive and smaller than
	 *        the number of anchors.
	 *        | index >= 0 && index < getNBAnchors()
	 */
	@Basic @Raw
	public Anchor getAnchorAt(int index)
	{
		return anchors.get(index);
	}
	
	/**
	 * Returns the first occurence of an anchor with the given name.
	 * 
	 * @param  name
	 *         The name of the anchor to retrieve.
	 * 
	 * @Return If and only if there is no anchor with the given name, the result is null.
	 *         | if ( for each anchor in anchors: !anchor.getName().equals(name) ) then
	 *         |    result == null
	 *         Otherwise the name of the resulting anchor is equal to the given name.
	 *         | else then
	 *         |    result.getName().equals(name)
	 */
	public Anchor getAnchor(String name)
	{
		for(Anchor anchor: anchors)
			if(anchor.getName().equals(name))
				return anchor;
		return null;
	}
	/**
	 * Adds the given anchor to its anchors.
	 * 
	 * @param anchor
	 * @pre  This creature must be able to have the given anchor as an anchor.
	 *       | canHaveAsAnchor( anchor )
	 * @post The given anchor
	 */
	void addAnchor(Anchor anchor)
	{
		anchors.add(anchor);
	}
	
	
	/**
	 * Checks whether this creature can have the given anchor as an anchor.
	 * 
	 * @param  anchor
	 *         The anchor to check.
	 * @return True if and only if the anchor is effective and the holder
	 *         if the anchor is equal to this creature.
	 *         | result == ( ( anchor != null) && ( anchor.getHolder() == this) )
	 */
	public boolean canHaveAsAnchor(Anchor anchor)
	{
		return anchor != null && anchor.getHolder() == this;
	}
	
	/**
	 * Checks whether the anchors this creature has are valid anchors.
	 * 
	 * @return True if and only if each anchor can have the anchor as an anchor.
	 *         | result == for each anchor in anchors: canHaveAsAnchor(anchor)
	 */
	public boolean hasProperAnchors()
	{
		for(Anchor anchor : anchors)
			if( !canHaveAsAnchor(anchor) )
				return false;
		return true;
	}
}
