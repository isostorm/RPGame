package rpg.creature;

import java.util.ArrayList;
import java.util.Random;

import be.kuleuven.cs.som.annotate.Raw;

import rpg.item.Armor;
import rpg.item.Container;
import rpg.item.Item;
import rpg.item.Weapon;
import rpg.item.Weight;
import rpg.item.WeightUnit;

public class Monster extends Creature {

	/**
	 * Initializes a new monster with the given name, maximum hitpoints,
	 * protection and a variable amount of objects.
	 * 
	 * @param name
	 *        The name of this monster.
	 * @param maximumHitpoints
	 *        The maximum hitpoints of this monster.
	 * @param protection
	 *        The protection factor of this monster.
	 * @param objects
	 *        The objects this monster carries.
	 *        
	 * @pre    The given damage must be a valid damage for a weapon.
	 *         | Weapon.isValidDamage(damage)
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
	 *         | setStrength( getTotalWeight().getNumeral()/9 + 1 ) TODO commentaar fixen
	 */
	@Raw
	public Monster(String name, int maximumHitpoints, int damage, int protection, Item ... items) {
		super(name, maximumHitpoints);
		Weight retWeight = new Weight(0, WeightUnit.KG);
		for(Item item: items)
		{
			if(item instanceof Container)
				retWeight.add( ((Container)item).getTotalWeight() );
			retWeight.add(item.getWeight());
		}
		
		// plus one to make sure the capacity is big enough in case of loss of precision
		setStrength(retWeight.getNumeral()/9 + 1); 
		
		for(Item item: items)
			addAnchor(new Anchor(this, item));
		
		skin = new Armor(3, new Weight(0, WeightUnit.KG), 0, protection, protection);
		this.damage = new Weapon(new Weight(0, WeightUnit.KG), damage);
	}
	
	private final Armor skin;
	private final Weapon damage;
	
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
	
	/**
	 * Checks whether this hero can have the given name as its name.
	 * @return False if the given name is not effective.
	 *         | if ( name == null ) then
	 *         |    result == false
	 * @return False if the name doesn't start with a capital letter
	 *         or doesn't only contains upper and lower case alphabetical
	 *         characters, spaces or apostrophes after removing all the 
	 *         allowed characters from the name.
	 *         | for each c in allowedCharacters:
	 *		   |    name = name.replace(c.toString(), "")
	 *         | if(!name.matches("[A-Z][A-Za-z ']*")) then
	 *         |    result == false
	 */
	@Override
	protected boolean canHaveAsName(String name) {
		if(!super.canHaveAsName(name))
			return false;
		for(Character c : allowedCharacters)
			name = name.replace(c.toString(), "");
		if(!name.matches("[A-Z][A-Za-z ']*"))
			return false;
		return true;
	}

	/**
	 * Returns the capacity of this monster.
	 * 
	 * @return The product of a weight of 9KG multiplied with the strength of this monster.
	 *         | result == ( ( new Weight(9, WeightUnit.KG) ).multiply( getStrength() ) )
	 */
	@Override
	public Weight getCapacity() {
		Weight capacity = new Weight(9, WeightUnit.KG);
		return capacity.multiply(getStrength());
	}
	
	/**
	 * TODO
	 */
	@Override
	public int getProtection()
	{
		return skin.getProtection();
	}
	/**
	 * TODO
	 * 
	 * @return
	 */
	public int getDamage()
	{
		return damage.getDamage();
	}
	/**
	 * TODO
	 */
	public void hit(Creature other)
	{
		int randomNumber = new Random().nextInt(101);
		int number = (randomNumber >= getHitpoints()) ? getHitpoints() : randomNumber;
		if(number >= other.getProtection())
		{
			int damage = getDamage() + (int)(getStrength()-5)/3;
			if(damage > 0)
			{
				boolean otherIsDead = other.weaken(damage);
				if(otherIsDead)
				{
					collect(other.getTreasure());
				}
			}
		}
	}
	/**
	 * TODO
	 */
	private void collect();
}
