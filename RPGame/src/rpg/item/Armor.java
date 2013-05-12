/**
 * 
 */
package rpg.item;

import rpg.creature.Creature;

/**
 * A class of armors
 * Armors are a special type of itemimplementation, involving aditionally 
 * a maximum value and a protection
 * 
 * @author Mathias, Frederic
 * 
 * @invar Each armor has a valid maximum value
 * 	      | hasValidMaxValue()
 * @invar Each armor has a valid maximum protection
 * 	      | hasValidMaxProtection()
 *
 */
public class Armor extends ItemImplementation{

	public Armor(long id, Weight weight, BackPack backpack, Creature holder,
			int maxValue, int maxProtection) {
		super(id, weight, backpack, holder, value);

		this.maxValue = maxValue;
		this.maxProtection = maxProtection;
	}
	


	private final int maxValue;
	
	/**
	 * Return the maximum value of this armor
	 */
	public int getMaxValue() {
		return maxValue;
	}
	
	/**
	 * Check whether the given maximum value is valid
	 * 
	 * @param  value
	 * 		   The value to check
	 * @return True if and only if the given maximum value is positive
	 * 		   and less than or equal to 1000
	 * 		   | result == value>=0 && value<=1000
	 */
	public static boolean isValidMaxValue(int value){
		return value>=0 && value<=1000;
	}
	 
	/**
	  * Checks whether the maximum value of this armor is valid.
	  * 
	  * @return True if and only if the maximum value of this armor 
	  * 		is a valid maximum value.
	  *         | result == isValidMaxValue(getMaxValue())
	  */
	 public boolean hasValidMaxValue()
	 {
		return isValidMaxValue(getMaxValue()); 
	 }
	 
	 /**
	  * 
	  */
	 @Override
	 public int getValue(){
		return maxValue;
		 
	 }
	 
	 private final int maxProtection;
	 
	 /**
	  * Return the maximum protection factor of this armor
	  */
	 public int getMaxProtection() {
			return maxProtection;
	 }
	 
	 /**
		 * Check whether the given maximum protection is valid
		 * 
		 * @param  maxProtection
		 * 		   The maximum protection to check
		 * @return True if and only if the given maximum protection is 
		 * 		   greater than or equal to 1 and less than or equal to 100
		 * 		   | result == value>=1 && value<=100
		 */
		public static boolean isValidMaxProtection(int maxProtection){
			return maxProtection>=1 && maxProtection<=100;
		}
		 
		/**
		  * Checks whether the maximum protection of this armor is valid.
		  * 
		  * @return True if and only if the maximum protection of this armor 
		  * 		is a valid maximum protection.
		  *         | result == isValidMaxProtection(getMaxProtection())
		  */
		 public boolean hasValidMaxProtection()
		 {
			return isValidMaxProtection(getMaxProtection()); 
		 }
		 
		 /**
		 * Return the protection factor of this armor
		 */
		public int getProtection() {
			return protection;
		}

		/**
		 * Set the protection factor of this armor to the given protection
		 * 
		 * @param protection 
		 * 		  The protection to set
		 * @post  The protection factor of this armor equals the given protection
		 * 		  | new.getProtection() == protection
		 */
		public void setProtection(int protection) {
			this.protection = protection;
		}

		

		private int protection;
		 
}
