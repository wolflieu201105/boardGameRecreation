package InGame.Boss;

public class ThuyTinh extends Bosses{
    // the name of the boss
	String Name = "Thuy Tinh";

	// the source of the image to navigate
	String ImageSource = "Assets/BossCards/Phase_1/Thuy_Tinh.png";

	// whether the boss can be chosen
	boolean choosable = true;

	// the position, width, height of the boss
	// initial position
	int x;
	int y;
	// width
	int width;
	// height
	int height;

	// the health bar of the boss
	int health = 35;
	BossHealthBar healthBar;

	public ThuyTinh() {
		
	}

	// the skill of the boss
	void Skill(int turn){
        
    }

	// when hovering over the boss, the info about the boss
	// should appear along with how it attack
	void HoverForInfo(){

    }
}
