package rpg.item;
import rpg.creature.Creature;

public interface Parent {
	/**
	 * Return the direct or indirect creature who is the holder of this parent
	 */
	public Creature getHolder();

}
