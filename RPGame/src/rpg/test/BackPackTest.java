/**
 * 
 */
package rpg.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Enumeration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rpg.item.BackPack;
import rpg.item.Dukat;
import rpg.item.Item;
import rpg.item.Weapon;
import rpg.item.Weight;
import rpg.item.WeightUnit;

/**
 * @author Mathias
 *
 */
public class BackPackTest {

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
	 * Test method for {@link rpg.item.BackPack#BackPack(rpg.item.Weight, rpg.item.BackPack, java.lang.Character, rpg.item.Weight)}.
	 */
	@Test
	public void testBackPack() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#Container(long, rpg.item.Weight, rpg.item.BackPack, java.lang.Character, rpg.item.Weight)}.
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
		Weapon weapon1 = new Weapon(new Weight(800, WeightUnit.G), 20);
		backpack2.addItem(weapon1);
		backpack1.addItem(backpack2);
		System.out.println(backpack1.getTotalWeight());
		assertTrue(backpack1.getTotalWeight().
				compareTo(backpack1.getWeight().
						add(backpack2.getWeight()).
						add(weapon1.getWeight())) == 0);
	}

	@Test
	public void testGetItems()
	{
		Weapon weapon1 = new Weapon(new Weight(800, WeightUnit.G), 20);
		backpack2.addItem(backpack3);
		backpack3.addItem(weapon1);
		Dukat dukat1, dukat2;
		dukat1 = new Dukat();
		dukat2 = new Dukat();
		backpack2.addItem(dukat1);
		backpack3.addItem(dukat2);
		backpack1.addItem(backpack2);
		Enumeration<Item> enumeration = backpack1.getItems();
		ArrayList<Item> allItems = new ArrayList<Item>();
		
		while(enumeration.hasMoreElements())
			allItems.add(enumeration.nextElement());
		
		assertTrue(allItems.contains(weapon1));
		assertTrue(allItems.contains(backpack2));
		assertTrue(allItems.contains(dukat1));
		assertTrue(allItems.contains(dukat2));
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
	 * Test method for {@link rpg.item.Container#removeItem(Item)}.
	 */
	@Test
	public void testRemoveItem() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.Container#getNbItems()}.
	 */
	@Test
	public void testGetNbItems() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#ItemImplementation(long, rpg.item.Weight, rpg.item.BackPack, java.lang.Character)}.
	 */
	@Test
	public void testItemImplementation() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#setValue(int)}.
	 */
	@Test
	public void testSetValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#getValue()}.
	 */
	@Test
	public void testGetValue() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#getWeight()}.
	 */
	@Test
	public void testGetWeight() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#setId(long)}.
	 */
	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#getId()}.
	 */
	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#setHolder(java.lang.Character)}.
	 */
	@Test
	public void testSetHolder() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#getHolder()}.
	 */
	@Test
	public void testGetHolder() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#setBackPack(rpg.item.BackPack)}.
	 */
	@Test
	public void testSetBackPack() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link rpg.item.ItemImplementation#getBackPack()}.
	 */
	@Test
	public void testGetBackPack() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#Object()}.
	 */
	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#getClass()}.
	 */
	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#clone()}.
	 */
	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#notify()}.
	 */
	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#notifyAll()}.
	 */
	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long)}.
	 */
	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait(long, int)}.
	 */
	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#wait()}.
	 */
	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link java.lang.Object#finalize()}.
	 */
	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}

}
