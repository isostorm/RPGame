package rpg;

import rpg.creature.Hero;
import rpg.item.BackPack;
import rpg.item.Purse;
import rpg.item.Weapon;
import rpg.item.Weight;
import rpg.item.WeightUnit;

public class main {

	public main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BackPack heroBP = new BackPack(new Weight(100,WeightUnit.G), new Weight(100,WeightUnit.KG));
		Weapon heroWeapon = new Weapon(new Weight(1, WeightUnit.KG), 60);
		Purse heroPurse = new Purse(new Weight(11, WeightUnit.G), new Weight(3, WeightUnit.KG), 20);
		heroBP.addItem(heroPurse);
		
		Hero hero = new Hero("James", 103, heroBP);
		System.out.println(heroBP.getTotalWeight());
		System.out.println(hero.getCapacity());
		System.out.println(hero.getTotalWeight());
		hero.getAnchor("leftHand").addItem(heroWeapon);
		//System.out.println(hero.getTotalValue());
	}

}
