package rpg.creature;

import java.util.ArrayList;

import rpg.item.Armor;
import rpg.item.Weight;
import rpg.item.WeightUnit;

public class Monster extends Creature {

	public Monster(double strength, String name, int maximumHitpoints,
			ArrayList<Anchor> anchors, int protection) {
		super(strength, name, maximumHitpoints, anchors);
		skin = new Armor(3, new Weight(0, WeightUnit.KG), , maxProtection)
		
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
