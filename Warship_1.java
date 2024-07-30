import javax.swing.ImageIcon;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Warship_1 extends Bosses implements MouseListener{
    // the name of the boss
	String Name = "Thuy Tinh";

	// the source of the image to navigate
	String ImageSource = "Assets/BossCards/Phase_2/Warship_1.png";

	// whether the boss can be chosen
	boolean choosable = false;

	// the position, width, height of the boss
	// initial position
    private int initialX;
    private int initialY;
	public int x;
	public int y;
	// width
	private int width;
	// height
	private int height;

	// the health bar of the boss
	int maxHealth = 35;
	int health = maxHealth;
	int text_size = 10;
	JTextPane healthBar;

	// the parent label of boss
	BossLabel parent;

	public Warship_1(int Width, int Height, int scale, BossLabel thisParent) {
		width = Width * scale;
        height = Height * scale;
		bound = width/20;
		parent = thisParent;
        this.setOpaque(false);
		healthBar = new JTextPane();
		healthBar.setBounds(0, width * 3/2, width, height - width*3/2);
		healthBar.setFont(new Font("Arial", Font.PLAIN, text_size*scale));
		healthBar.setText(health + "/" + maxHealth);
		healthBar.setEditable(false);
		healthBar.setOpaque(false);
		this.add(healthBar);
		this.addMouseListener(this);
	}

	// the skill of the boss
	void Skill(int turn){
        
    }

	// when hovering over the boss, the info about the boss
	// should appear along with how it attack
	void HoverForInfo(){

    }

	// showing boss with x, y, etc
	void showBoss(int X, int Y, int scale){
		x = X * scale;
        y = Y * scale;
        initialX = x;
        initialY = y;
        this.setBounds(x, y, width, height);
		ImageIcon newIcon = new ImageIcon(ImageSource);
        Image cardImage = newIcon.getImage();
		cardImage = cardImage.getScaledInstance(width, width * 3 / 2, Image.SCALE_SMOOTH);
		newIcon = new ImageIcon(cardImage);
        this.setIcon(newIcon);
		this.setVerticalAlignment(ThuyTinh.TOP);
	}
	
	boolean shaking = false;
	int bound;
	int shake_time = 4;
	int k = 1000;
	int friction = 500;
	double length;
	double velocity;
	double accelaration;
	private void shake(int FPS) {
		boolean check1 = length > 0;
		accelaration = -length * k;
		if (velocity < 0){
			accelaration += friction;
		}
		else {
			accelaration -= friction;
		}
		velocity += accelaration * 1/FPS;
		length += velocity * 1/FPS;
		if ((length < 0 && check1) || (length > 0 && !check1)){
			shake_time--;
			if (shake_time == 0){
				this.setLocation(initialX, initialY);
				shaking = false;
			}
		}
		this.setLocation(initialX + (int)length, initialY);
	}

	// set choosable to true
	public void setChoosable(){
		choosable = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (choosable){
			health -= parent.damageDealt;
			choosable = false;
			healthBar.setText(health + "/" + maxHealth);
			
		}
		else {
			this.setLocation(initialX + bound, initialY);
			length = bound;
			velocity = 0;
			shake_time = 6;
			shaking = true;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public void update(int FPS){
		if (shaking){
			shake(FPS);
		}
	}
}
