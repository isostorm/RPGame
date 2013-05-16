package rpg.creature;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Raw;

import rpg.item.Armor;
import rpg.item.Item;
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
	 *         | setStrength( getTotalWeight().getNumeral()/9 + 1 )
	 */
	@Raw
	public Monster(String name, int maximumHitpoints, int protection, Item ... items) {
		super(name, maximumHitpoints);
		
		for(Item obj: items)
			addAnchor(new Anchor(this, obj));
		
		Anchor body = new Anchor(this, "body");
		new Armor(3, new Weight(0, WeightUnit.KG), body, 0, protection, protection);
		addAnchor(body);
		
		// plus one to make sure the capacity is big enough in case of loss of precision
		setStrength(getTotalWeight().getNumeral()/9 + 1); 
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
	
	@Override
	public int getProtection() {
		// TODO Auto-generated method stub
		return 0;
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

}
