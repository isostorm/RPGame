/**
 * 
 */
package rpg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rpg.item.Dukat;
import rpg.item.Purse;
import rpg.item.Weight;
import rpg.item.WeightUnit;

/**
 * @author Frederic
 *
 */
public class PurseTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		purse1 = new Purse(null, null, null, null); //TODO niet null null null
		purse2 = new Purse(null, null, null, null);
		purse3 = new Purse(null, null, null, null);
		purse4 = new Purse(null, null, null, null);
	}
	static Purse purse1, purse2, purse3, purse4;
	Purse purseA;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		purseA = new Purse(null, null, null, new Weight(1, WeightUnit.KG)); //TODO niet null waarden?
	}
	@Test
	public void testGenerateId()
	{
		assertEquals(1, purse1.getId());
		assertEquals(1, purse2.getId());
		assertEquals(2, purse3.getId());
		assertEquals(3, purse4.getId());
	}
	/**
	 * 
	 */
	@Test
	public void testIsValidId()
	{
		assertTrue(purse1.canHaveAsId(1));
		assertTrue(purse1.canHaveAsId(2));
		assertTrue(purse1.canHaveAsId(3));
		assertTrue(purse1.canHaveAsId(987));
	}
	/**
	 * Test method for {@link rpg.item.Purse#Purse(rpg.item.Weight, rpg.item.BackPack, rpg.creature.Creature, rpg.item.Weight)}.
	 */
	@Test
	public void testPurse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Purse#addDukat(rpg.item.Dukat)}.
	 */
	@Test
	public void testAddDukat() {
		Dukat dukat1 = new Dukat();
		Dukat dukat2 = new Dukat();
		purseA.addDukat(dukat1);
		assertTrue(purseA.contains(dukat1));
		assertFalse(purseA.contains(dukat2));
		//TODO checken of burst ook werkt
	}

}
