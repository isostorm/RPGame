package rpg.item;

import be.kuleuven.cs.som.annotate.Model;

public class main {
	public static void main(String[] args) {
		for(int i = 0; i <= 1000; i++)
		{
			System.out.println("test" + new Weapon(null, null, null).getId());
		}
	}

}
