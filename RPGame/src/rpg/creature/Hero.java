/**
 * 
 */
package rpg.creature;

import java.util.ArrayList;
import java.util.Arrays;

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
	 * Checks whether the name of this hero is a valid name.
	 * 
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
	protected boolean isValidName(String name) {
		for(Character c : allowedCharacters)
			name = name.replace(c.toString(), "");
		if(!name.matches("[A-Z]([A-Za-z ]|: )*"))
			return false;
		if(allowApostrophes && name.replaceAll("[^']", "").length() > 2)
			return false;
		return true;
	}

}
