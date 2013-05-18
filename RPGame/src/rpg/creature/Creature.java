package rpg.creature;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.*;
import rpg.exception.IllegalNameException;
import rpg.item.*;
/**
 * A class of creatures involving a name, hitpoints,
 * strength, protection, capacity and a list of anchorpoints.
 * 
 * @invar Each creature has a valid maximum number of hitpoints.
 *       | hasValidMaximumHitpoints()
 * @invar Each creature has a valid name.
 *       | hasValidName()
 * @invar Each creature has a valid number of hitpoints.
 *       | hasValidHitpoints()
 * @invar The strength precision for each creature is a positive number.
 *       | getStrengthPrecision() >= 0
 * @invar Each creatures precision of its strength is equal to the strength precision.
 *       | Math.Round(getStrength()*Math.pow(10,getStrengthPrecision())) ==
 *       | getStrength()*Math.pow(10,getStrengthPrecision())
 * @invar Each creature has proper anchors associated with it.
 *       | hasProperAnchors()
 *       
 * @author Mathias, Frederic
 */
public abstract class Creature{
	
	/**
	 * Initializes a new creature with the given name and maximum hitpoints and
	 * sets the hitpoints of this creature to the maximum hitpoints.
	 * 
	 * @param  name
	 *         The name of this creature.
	 * @param  maximumHitpoints
	 *         The maximum hitpoints of this creature.
	 * @effect The name of this creature is set to the given name.
	 *         | setName(name)
	 * @effect The number of hitpoints of this creature is set to the given maximum hitpoints.
	 *         | sethitpoints(maximumHitpoints)
	 * @effect The maximum hitpoints of this creature is set to the given maximum hitpoints.
	 *         | setMaximumHitpoints(strength)
	 *        
	 */
	@Raw
	public Creature(String name, int maximumHitpoints) {
		setName(name);
		setMaximumHitpoints(maximumHitpoints);
		setHitpoints(maximumHitpoints);
		this.anchors = new ArrayList<Anchor>();
		treasure =  new ArrayList<Item>();
	}
	
	/**
	 * Returns all the anchors which belong to this creature.
	 * @return The length of the resulting arraylist is equal to the 
	 *         number of anchors that belong to this creature.
	 *         | result.size() == getNbAnchors()
	 * @return Each element in the resulting arraylist is equal to 
	 *         the anchor at the corresponding index.
	 *         | for each i in 0..(result.size()-1):
	 *         |    result.get(i).equals(getAnchorAt(i))
	 */
	public ArrayList<Anchor> getAnchors() {
		return new ArrayList<Anchor>(this.anchors);
	}
	
	/**
	 * Returns the number of anchors of this creature.
	 */
	@Basic @Raw @Immutable
	public int getNbAnchors() {
		return anchors.size();
	}
	
	/**
	 * Returns the anchor at the given index.
	 * 
	 * @param index
	 *        The index of the anchor to return.
	 * @pre The given index must be positive and must be less than
	 *      the number of anchors of this creature.
	 *      | index >= 0 && index < getNbAnchors()
	 */
	@Basic @Raw
	public Anchor getAnchorAt(int index)
	{
		return anchors.get(index);
	}
	
	
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
	
	/**
	 * Returns the average strength of a creature.
	 */
	protected static double getAveragestrength() {
		return averageStrength;
	}
	
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
	protected void setStrength(double strength)
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
	
	public abstract Weight getCapacity();
	
	/**
	 * Returns the total weight of all the items in the anchors of this creature.
	 * 
	 * @return The sum of all the total weights of the elements in the anchorpoints.
	 *         | let
	 *         |    retWeight = new Weight(0, WeightUnit.KG)
	 *         | in
	 *         |    for each anchor in getAnchors():
	 *         |       if( anchor.getItem() instanceof Container ) then
	 *         |          retWeight.add( ((Container)object).getTotalWeight() )
	 *         |       else if (object instanceof Item) then
	 *         |          retWeight.add( ((Container)object).getWeight() )
	 *         |    result == retWeight
	 * @return The weight unit of the resulting weight is KG.
	 *         | result.getUnit() == WeightUnit.KG
	 */
	public Weight getTotalWeight()
	{
		Weight retWeight = new Weight(0, WeightUnit.KG);
		for(Anchor anchor : getAnchors())
		{
			Object object = anchor.getItem();
			if(object instanceof Container)
				retWeight.add( ((Container)object).getTotalWeight() );
			else if (object instanceof Item)
				retWeight.add( ((Container)object).getWeight() );
		}
		return retWeight;
	}
	
	/**
	 * Returns the protection factor of this creature.
	 * 
	 * @return The result is greater than or equal to the protection of the armor of this creature.
	 *         | if(getAnchor("body").getItem() instanceof Armor) then
	 * 	       |    result >= ((Armor)getAnchor("body").getItem()).getProtection()
	 *         Otherwise the protection of this creature is greater than or equal to 0.
	 * 	       | else then
	 * 	       |    result >= 0
	 */
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
	 * @throws IllegalNameException(name, this) [must]
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
	/**
	 * Hits the given creature.
	 */
	public abstract void hit(Creature other);
	
	private ArrayList<Item> treasure;
	
	/**
	 * Return the treasure of this creature
	 * The treasure contains the items of this creature 
	 * that can be taken by other creatures
	 */
	public ArrayList<Item> getTreasure()
	{
		return treasure;
	}
	
	/**
	 * Empty the treasure of this creature
	 * 
	 * @post The treasure of this creature doesn't contain any items anymore
	 * 		 | getTreasure() is empty TODO
	 */
	public void destroyTreasure()
	{
		treasure.clear();
	}
	
	/**
	 * Create the treasure of a creature when he dies
	 * 
	 * @post The treasure of this creature contains some items 
	 * 		 that this creature carries in his anchors
	 * 		 | for some anchor in getAncors():
	 * 		 | 		getTreasure().contains(anchor.getItem()) TODO
	 */
	protected void makeTreasure()
	{
		for(Anchor anchor: getAnchors())
			treasure.add(anchor.getItem());
	}
	
	/**
	 * Weaken this creature with the given amount of hitpoints
	 * TODO Keer checken
	 * @param  damage
	 * 		   The amount of hitpoints to subtract
	 * @post   If the subtraction of the hitpoints of this monster and the 
	 * 		   given damage is strictly positive, the hitpoints of this creature equal this subtraction
	 * 		   | if ( (getHitpoints() - damage) > 0 ) then
	 * 		   | 		getHitpoints() == (getHitpoints() - damage)
	 * @post   The hitpoints of this creature are again a prime number
	 * 		   | isPrime(getHitpoints()) == true
	 * @effect If the subtraction of the hitpoints of this monster and the 
	 * 		   given damage is negative, this creature is terminated and its treasure is made
	 * 		   | if ( (getHitpoints() - damage) <= 0 ) then
	 * 		   | 		makeTreasure() && terminate()
	 * @return True if and only if the hitpoints of this creature are negative
	 * 		   | result == ( (getHitpoints() - damage) <= 0 )
	 */
	public boolean weaken(int damage)
	{
		int newHitpoints = getHitpoints() - damage;
		if(newHitpoints <= 0)
		{
			makeTreasure();
			
			terminate();
			return true;
		}
		else if (!isPrime(newHitpoints))
			setHitpoints(nearestLargerPrime(newHitpoints));
		else
			setHitpoints(newHitpoints);
		
		return false;
	}
	
	/**
	 * Collect the given items from a death creature
	 * TODO pre isDood()?
	 * @param other
	 * 		  The creature to collect the treasure from
	 * @param itemsToCollect
	 * 		  The specific items to collect from this creature
	 * @post  The items that are both in the list of items to collect and in the treasure of the other 
	 * 		  creature, are added to a free anchor of this creature.
	 * 		  | for each item in intersect (itemsToColect && other.getTreasure):
	 * 		  | 	for each anchor in getAnchors():
	 *		  |			anchor.addItem(item)
	 */
	protected void collect(Creature other, ArrayList<Item> itemsToCollect)
	{
		for(Item item: other.getTreasure())
			if(itemsToCollect.contains(item))
				for(Anchor anchor: getAnchors())
					if(anchor.canAddItem(item))
					{
						// TODO dubbele check?
						anchor.addItem(item);
						break;
					}
	}
	private int maximumHitpoints;
	/**
	 * Sets the maximum number of hitpoints this creature can have.
	 * 
	 * @param maxHitpoints
	 *        The maximum number of hitpoints this creature can have.
	 * @pre   The given maximum number of hitpoints, maxHitpoints must 
	 *        be a valid maximum number of hitpoints.
	 *        | isValidMaximumHitpoints(maxHitpoints) == true
	 * @post  The maximum number of hitpoints of this creature has,
	 *        equals the given maximum number of hitpoints, maxHitpoints.
	 *        | getMaximumHitpoints() == maxHitpoints
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
	protected
	static boolean isPrime(int number)
	{
		for(int i = 2; i < number; i++)
			if(number%i == 0)
				return false;
		return true;
	}
	/**
	 * Return the nearest prime number that is larger than this number 
     *
	 * @param   number
	 * 		    The number to get get the nearest larger prime of
	 * @return	The result is a prime number and greater than the given number
	 * 			| isPrime(result) && result > number
	 */
	@Raw
	protected int nearestLargerPrime(int number)
	{
		int result = number+1;
		while(!isPrime(result)) result++;
		return result;
	}
	
	private final ArrayList<Anchor> anchors;
	
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
	@Raw
	void addAnchor(Anchor anchor)
	{
		anchors.add(anchor);
	}
	/**
	 * Checks whether the given item can be added to this creature.
	 * 
	 * @param  item
	 *         The item to check
	 * @return True if and only if the sum of the total weight of this
	 *         creature and the weight of the given item is less than or equal to the capacity of this creature.
	 *         | result == ( getTotalWeight().add(item.getWeight()).compareTo(getCapacity()) <= 0 )
	 */
	public boolean canAddItem(Item item)
	{
		return getTotalWeight().add(item.getWeight()).compareTo(getCapacity()) <= 0;
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
	
	/**
	 * Remove the given anchor as an anchor for this creature.
	 * 
	 * @param  anchor
	 *         The anchor to be removed.
	 * @pre    This creature contains the given anchor.
	 *         | hasAsAnchor(anchor)
	 * @post   This creature doesn't have the given anchor as an anchor anymore.
	 *         | !hasAsAnchor(anchor)
	 * @effect The given anchor is terminated.
	 *         | anchor.terminate()
	 * @see p.409
	 */
	void removeAnchor(Anchor anchor)
	{
		anchors.remove(anchor);
	}
	
	/**
	 * Check whether this creature has the given anchor as one of its anchors.
	 * 
	 * @param anchor
	 *        The anchor to check
	 * @return True if and only if this creature has the given anchor as one of its anchors at some index.
	 *         | result == for some I in 1..getNbAnchors():
	 *         |              ( getAnchorAt(I) == anchor )
	 */
	public boolean hasAsAnchor(Anchor anchor)
	{
		return anchors.contains(anchor);
	}
	
	private boolean isTerminated = false;
	
	/**
	 * Check whether this creature is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated()
	{
		return isTerminated;
	}
	
	/**
	 * Terminate this creature and its anchors.
	 * 
	 * @post All the anchors of this creature are terminated.
	 *       | for each I in 0..getNbAnchors():
	 *       |    getAnchorAt(I).isTerminated()
	 * @post This creature is terminated.
	 *       | isTerminated()
	 */
	public void terminate()
	{
		for(Anchor anchor: new ArrayList<Anchor>(anchors))
			anchor.terminate();
		isTerminated = true;
	}
	

}
