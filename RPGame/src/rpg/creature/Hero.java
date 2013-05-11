/**
 * 
 */
package rpg.creature;

import java.util.ArrayList;
import java.util.Arrays;

import be.kuleuven.cs.som.annotate.Model;

import rpg.item.Weight;
import rpg.item.WeightUnit;

/**
 * @author Frederic
 *
 */
public class Hero extends Creature {

	/**
	 * 
	 */
	public Hero() {
		
		
	}

	@Override
	public int getProtection() {
		// TODO Auto-generated method stub
		return 0;
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

}
