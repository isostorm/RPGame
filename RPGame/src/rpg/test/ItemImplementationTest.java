
package rpg.test;

import static org.junit.Assert.*;
import rpg.item.*;
import rpg.creature.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Mathias, Frederic
 *
 */
public class ItemImplementationTest {
	
	ItemImplementation item1, item2;
	static BackPack backpack1, backpack2;
	Creature holder;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		backpack1 = new BackPack(new Weight(5, WeightUnit.G), new Weight(100, WeightUnit.KG), 10);
		backpack2 = new BackPack(new Weight(5, WeightUnit.G), new Weight(0, WeightUnit.KG), 20);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		
		item1 = new Weapon(new Weight(500, WeightUnit.G), 50);
		backpack1.addItem(item1);
		//holder = new Hero("Hero", 50, backpack);
		item2 = new Armor(5, new Weight(50, WeightUnit.KG), 40, 50, 60);
	}


	/**
	 * Test method for {@link rpg.item.ItemImplementation#canHaveAsValue(int)}.
	 */
	@Test
	public void testCanHaveAsValue() {
		assertFalse(item1.canHaveAsValue(-2));
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#hasValidValue()}.
	 */
	@Test
	public void testHasValidValue() {
		assertTrue(item1.hasValidValue());
		assertTrue(item2.hasValidValue());
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#canHaveAsId(long)}.
	 */
	@Test
	public void testCanHaveAsId() {
		assertFalse(item1.canHaveAsId(-12));
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#hasValidId()}.
	 */
	@Test
	public void testHasValidId() {
		assertTrue(item1.hasValidId());
		assertTrue(item2.hasValidId());
		
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#getParent()}.
	 */
	@Test
	public void testGetParent() {
		assertEquals(backpack1, item1.getParent());
		assertEquals(null, item2.getParent());
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#hasParent()}.
	 */
	@Test
	public void testHasParent() {
		assertTrue(item1.hasParent());
		assertFalse(item2.hasParent());
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#hasHolder()}.
	 */
	@Test
	public void testHasHolder() {
		assertTrue(item1.hasHolder());
		assertFalse(item2.hasHolder());
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#getHolder()}.
	 */
	@Test
	public void testGetHolder() {
		assertEquals(item2.getHolder(), null);
		assertEquals(item1.getHolder(), holder);
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#terminate()}.
	 */
	@Test
	public void testTerminate() {
		Parent parent = item1.getParent();
		System.out.println(item1.getParent());
		item1.terminate();
		
		assertTrue(item1.isTerminated());
		assertFalse(parent.containsDirectItem(item1));
		
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#canHaveAsParent(rpg.item.Parent)}.
	 */
	@Test
	public void testCanHaveAsParent() {
		assertTrue(item1.canHaveAsParent(null));
		assertTrue(item2.canHaveAsParent(backpack1));
		assertFalse(item2.canHaveAsParent(backpack2));
		
	}

}
