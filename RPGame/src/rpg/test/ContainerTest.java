/**
 * 
 */
package rpg.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rpg.item.*;

/**
 * @author Frederic
 *
 */
public class ContainerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	BackPack backpack1, backpack2, backpack3;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		backpack1 = new BackPack(new Weight(500, WeightUnit.G), new Weight(100, WeightUnit.KG));
		backpack2 = new BackPack(new Weight(500, WeightUnit.G), new Weight(100, WeightUnit.KG));
		backpack3 = new BackPack(new Weight(500, WeightUnit.G), new Weight(100, WeightUnit.KG));
	}

	/**
	 * Test method for {@link rpg.item.Container#Container(long, rpg.item.Weight, rpg.item.Parent, rpg.item.Weight)}.
	 */
	@Test
	public void testContainer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#getCapacity()}.
	 */
	@Test
	public void testGetCapacity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#setCapacity(rpg.item.Weight)}.
	 */
	@Test
	public void testSetCapacity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#getTotalWeight()}.
	 */
	@Test
	public void testGetTotalWeight() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#getTotalValue()}.
	 */
	@Test
	public void testGetTotalValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#addItem(rpg.item.Item)}.
	 */
	@Test
	public void testAddItem() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#contains(rpg.item.Item)}.
	 */
	@Test
	public void testContains() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#removeItem(rpg.item.Item)}.
	 */
	@Test
	public void testRemoveItem() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#getDirectItems()}.
	 */
	@Test
	public void testGetDirectItems() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#getItems()}.
	 */
	@Test
	public void testGetItems() {
		fail("Not yet implemented");
	}

}
