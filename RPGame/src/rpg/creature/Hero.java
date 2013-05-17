/**
 * 
 */
package rpg.creature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

import rpg.item.Armor;
import rpg.item.Item;
import rpg.item.Purse;
import rpg.item.Weapon;
import rpg.item.Weight;
import rpg.item.WeightUnit;

/**
 * @author Frederic
 *
 */
public class Hero extends Creature {

	/**
	 * Initializes a new hero with the given name, maximum hitpoints,
	 * protection and a variable amount of objects.
	 * 
	 * @param name
	 *        The name of this hero.
	 * @param maximumHitpoints
	 *        The maximum hitpoints of this hero.
	 * @param protection
	 *        The protection factor of this hero.
	 * @param items
	 *        The items this hero carries.
	 *        

	 * @post   Each anchor of this monster contains one of the given objects.
	 *         | for each object in objects:
	 *         |    for some anchor in getAnchors():
	 *         |       anchor.getItem() == object
	 * @post   The monster is initialized with an anchor called "body" that contains an armor
	 *         which functions as its skin with 0KG as its weight, 3 as its id, the given protection
	 *         as its protection and maximum protection.
	 *         | new.getAnchor("body") == Armor(3, new Weight(0, WeightUnit.KG), body, 0, protection, protection)
	 * @effect This monster is initialized as a new creature with the given 
	 *         name and maximumHitpoints.
	 *         | super(name, maximumhitpoints)
	 * @effect   The strength of this monster is set to a strength capable of carrying all the given objects.
	 *         | setStrength( getTotalWeight().getNumeral()/9 + 1 ) TODO commentaar
	 */
	@Raw
	public Hero(String name, int maximumHitpoints, int protection, Item ... items) {
		super(name, maximumHitpoints);
		setStrength(getAveragestrength());
		Armor armor = new Armor(2, new Weight(30, WeightUnit.KG), 500, 30, 40);
		new Anchor(this, "body", armor);
		Purse purse = new Purse(new Weight(5, WeightUnit.G), new Weight(550, WeightUnit.G), new Random().nextInt(101));
		new Anchor(this, "belt", purse);
		new Anchor(this, "leftHand");
		new Anchor(this, "rightHand");
		new Anchor(this, "back");
		
		for(Item item: items)
			for(Anchor anchor: getAnchors())
				if(anchor.canAddItem(item))
				{
					anchor.addItem(item);
					break;
				}
		
		Anchor body = new Anchor(this, "body");
		body.addItem(new Armor(3, new Weight(0, WeightUnit.KG), 0, protection, protection));
		addAnchor(body);
	}
	private static int standardProtection = 10;
	
	/**
	 * Checks whether the given standard protection is a valid standard protection.
	 * 
	 * @param standardProtection
	 *        The standard protection to check.
	 * @return True if and only if the given standard protection is positive.
	 *         | result == ( standardProtection >= 0 )
	 */
	@Raw // TODO raw?
	public static boolean isValidStandardProtection(int standardProtection)
	{
		return standardProtection >= 0;
	}
	
	/**
	 * Sets the standard protection of all heros.
	 * @param standardProtection
	 *        The standard protection to set.
	 * @post  The standard protection of the new hero is equal to the given standard protection.
	 *        | getStandardProtection() == standardProtection
	 */
	@Raw // TODO raw?
	public static void setStandardProtection(int standardProtection)
	{
		Hero.standardProtection = standardProtection;
	}
	
	/**
	 * Returns the standard protection of all heros.
	 */
	@Basic
	public static int getStandardProtection()
	{
		return Hero.standardProtection;
	}
	
	/**
	 * Returns the protection of this hero.
	 * 
	 * @return The sum of the standard protection and the protection of the enclosing creature.
	 *         | result == ( getStandardProtection() + super.getProtection() )
	 */
	@Override
	public int getProtection() {
		return getStandardProtection() + super.getProtection();
		
	}
	
	private static final ArrayList<Character> allowedCharacters = new ArrayList<Character>();
	
	/**
	 * Adds the given character to the list of allowed characters.
	 * 
	 * @param character
	 *        The character to add to the list of allowed characters.
	 */
	public static void addCharacter(char character)
	{
		allowedCharacters.add(character);
	}
	private static boolean allowApostrophes = true;
	/**
	 * Sets whether or not apostrophes are allowed in a heros name.
	 * 
	 * @param allowApostrophes
	 *        Whether or not to allow apostrophes in a heros name.
	 */
	public static void setAllowApostrophes(boolean allowApostrophes)
	{
		Hero.allowApostrophes = allowApostrophes;
	}
	/**
	 * Checks whether this hero can have the given name as its name.
	 * @return False if the given name is not effective.
	 *         | if ( name == null ) then
	 *         |    result == false
	 * @return False if the name doesn't start with a capital letter
	 *         or doesn't only contains upper and lower case alphabetical
	 *         characters, spaces or colons followed directly by a
	 *         space after removing all the allowed characters from the name.
	 *         | for each c in allowedCharacters:
	 *		   |    name = name.replace(c.toString(), "")
	 *         | if(!name.matches("[A-Z]([A-Za-z ]|: )*")) then
	 *         |    result == false
	 *         Otherwise false if and only if the given name contains more than
	 *         2 apostrophes and apostrophes are allowed
	 *         | if(allowApostrophes && name.replaceAll("[^']", "").length() > 2) then
	 *         |    result == false
	 */
	@Override
	protected boolean canHaveAsName(String name) {
		if(!super.canHaveAsName(name))
			return false;
		for(Character c : allowedCharacters)
			name = name.replace(c.toString(), "");
		if(!name.matches("[A-Z]([A-Za-z ]|: )*"))
			return false;
		if(allowApostrophes && name.replaceAll("[^']", "").length() > 2)
			return false;
		return true;
	}
	/**
	 * Returns the capacity of this hero.
	 * 
	 * @return The capacity corresponding to the rounded strength of this hero.
	 *         | let
	 *         |    strength = Math.round(getStrength())
	 *         | in
	 *         |    result == getCapacity(strength)
	 */
	@Override
	public Weight getCapacity() {
		int strength = (int)Math.round(getStrength());
		return getCapacity(strength);
		
	}
	/**
	 * Returns the capacity for the given strength.
	 * 
	 * @param strength
	 *        The strength for which the capacity must be calculated.
	 * @return If the strength is less than or equal to 0, the numeral of
	 *         the capacity is equal to 0.
	 *         | if(strength <= 0) then
	 *         |    result.getNumeral() == 0
	 *         Otherwise if the strength is less than or equal to 10,
	 *         the numeral of the capacity is equal to 10 multiplied by the strength of this hero.
	 *         | else if (strength <= 10) then
	 *         |    result.getNumeral() == 10*getStrength()
	 *         Otherwise if the strength is less than or equal to 20 the numeral is equal to
	 *         the appropriate numeral.
	 *         | else if (strength <= 20) then
	 *         |    let
	 *         |       numerals = {115, 130, 150, 175, 200, 230, 260, 300, 350, 400}
	 *         |    in
	 *         |       result.getNumeral() == numerals[strength - 11]
	 *         Otherwise the numeral of the capacity is equal to the numeral of the
	 *         capacity for the given strength minus 10, multiplied by 4.
	 *         | else then
	 *         |    result == getCapacity(strength-10).multiply(4)
	 * @return The unit of the resulting capacity is KG.
	 *         | result.getUnit() == WeightUnit.KG
	 */
	@Model
	private Weight getCapacity(int strength)
	{
		if(strength <= 0)
			return new Weight(0, WeightUnit.KG);
		if(strength <= 10)
			return new Weight(10, WeightUnit.KG).multiply(getStrength());
		if(strength <= 20)
		{
			int[] numerals = {115, 130, 150, 175, 200, 230, 260, 300, 350, 400};
			return new Weight(numerals[strength - 11], WeightUnit.KG);
		}
		return getCapacity(strength-10).multiply(4);
	}
	
	/**
	 * Calculates the total strength of this hero
	 * 
	 * @return The sum of the strength of this hero
	 *         and the damage of the weapons in his hands.
	 *         | let
	 *         |    retStrength = getStrength()
	 *         |    leftHandItem = getAnchor("leftHand").getItem()
	 *         |    rightHandItem = getAnchor("rightHand").getItem()
	 *         | in
	 *         |    if(leftHandItem instanceof Weapon)
	 *         |       retStrength += ((Weapon)leftHandItem).getDamage()
	 *         |    if(rightHandItem instanceof Weapon)
	 *         |       retStrength += ((Weapon)rightHandItem).getDamage()
	 *         |    result == retStrength
	 */
	public int getTotalStrength()
	{
		int retStrength = getStrength();
		
		Item leftHandItem = getAnchor("leftHand").getItem();
		if(leftHandItem instanceof Weapon)
			retStrength += ((Weapon)leftHandItem).getDamage();
		
		Item rightHandItem = getAnchor("rightHand").getItem();

		if(rightHandItem instanceof Weapon)
			retStrength += ((Weapon)rightHandItem).getDamage();
		
		return retStrength;
	}
	
	/**
	 * TODO
	 */
	public void hit(Creature other)
	{
		if(! (other instanceof Monster) )
			return;
		int randomNumber = new Random().nextInt(21);
		if(randomNumber >= other.getProtection())
		{
			int damage = (int)(getTotalStrength()-10)/2;
			if(damage > 0)
			{
				boolean otherIsDead = other.weaken(damage);
				if(otherIsDead)
				{
					heal();
				}
			}
		}
	}
	/**
	 * TODO
	 */
	public void heal()
	{
		int healAmount = (int)(Math.random()*(getMaximumHitpoints()-getHitpoints()) + 0.5);
		int nextHP = healAmount + getHitpoints();
		if(!isPrime(nextHP))
			nextHP = nearestLargerPrime(nextHP);
		setHitpoints(nextHP);
	}

}
