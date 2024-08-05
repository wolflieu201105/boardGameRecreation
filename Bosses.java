import javax.swing.JLabel;

public abstract class Bosses extends JLabel {
	// the name of the boss
	String Name;

	// the source of the image to navigate
	String ImageSource;

	// the skill of the boss
	abstract void Skill(int Phase);

	// the health bar of the boss
	int maxHealth;
	int health;

	// the position, width, height of the boss
	// initial position
	int x;
	int y;
	// width
	int width;
	// height
	int height;
	// choosable
	boolean choosable;

	// when hovering over the boss, the info about the boss
	// should appear along with how it attack
	abstract void HoverForInfo();

	// showing boss with x, y, etc
	abstract void showBoss(int X, int Y, int scale);

	// setting choosable
	abstract void setChoosable();

	// losing hp
	abstract void loseHP(int hp);

	// update the boss
	abstract void update(int FPS);
}
