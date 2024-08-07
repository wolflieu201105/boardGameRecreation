import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.List;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Image;

public class BossLabel extends JLabel{
	// the dimensions of the main label
	final private int width_ratio = 220;
	final private int height_ratio = 80;
	final private int x_start_ratio = 50;
	final private int y_start_ratio = 0;
    
	// the current scale
	private int scale;

	// the current width or height after resizing
	private int width;
	private int height;
    private int x_start;
    private int y_start;

	// width and height of boss cards
	private int boss_width_ratio = 40;
	private int boss_height_ratio = 75;

	// added cards for each phase
	List<List<String>> cardNames = new ArrayList<List<String>>();
	List<List<Integer>> cardNums = new ArrayList<List<Integer>>();
	List<List<String>> imageSources = new ArrayList<List<String>>();
	List<List<String>> descriptions = new ArrayList<List<String>>();

	List<List<CardTypes>> cardPhases = new ArrayList<List<CardTypes>>();

	// game label
	GameLabel parent;

	List<List<Bosses>> bossInPhases = new ArrayList<List<Bosses>>();
	
    public BossLabel(int newScale, GameLabel thisParent) {
		parent = thisParent;
        scale = newScale;
        width = width_ratio * scale;
        height = height_ratio * scale;
        x_start = x_start_ratio * scale;
        y_start = y_start_ratio * scale;

		// setting the new bounds for the label
		this.setBounds(x_start, y_start, width, height);

		// set the visibility of the color
		this.setBackground(new Color(255, 255, 70));
		this.setOpaque(true);

		List<Bosses> bossThisPhase = new ArrayList<Bosses>();

		// making cards
		List<String> cardName = new ArrayList<String>();
		List<Integer> cardNum = new ArrayList<Integer>();
		List<String> imageSource = new ArrayList<String>();
		List<String> description = new ArrayList<String>();
		
		// phase 1
		cardName.add("Dap de");
		cardNum.add(4);
		imageSource.add("Assets/phase1/Dap_de.png");
		description.add("If you have this card, you won't be affected by Thuy Tinh's flood effect");
		cardName.add("Vo de");
		cardNum.add(3);
		imageSource.add("Assets/phase1/Vo_de.png");
		description.add("If you have this card, the card will be used immidiately and lose Vo de card");
		cardNames.add(cardName);
		cardNums.add(cardNum);
		imageSources.add(imageSource);
		descriptions.add(description);
		cardName = new ArrayList<String>();
		cardNum = new ArrayList<Integer>();
		imageSource = new ArrayList<String>();
		description = new ArrayList<String>();

		bossThisPhase.add(new ThuyTinh(boss_width_ratio, boss_height_ratio, scale, this));
		bossInPhases.add(bossThisPhase);
		bossThisPhase = new ArrayList<Bosses>();

		// phase 2
		cardName.add("Coc");
		cardNum.add(5);
		imageSource.add("Assets/phase2/Coc.png");
		description.add("When the tide goes low, deals 1 damage to all boats");
		cardNames.add(cardName);
		cardNums.add(cardNum);
		imageSources.add(imageSource);
		descriptions.add(description);
		cardName = new ArrayList<String>();
		cardNum = new ArrayList<Integer>();
		imageSource = new ArrayList<String>();
		description = new ArrayList<String>();
		
		bossThisPhase.add(new Warship_1(boss_width_ratio, boss_height_ratio, scale, this));
		bossThisPhase.add(new LuuHoangThao(boss_width_ratio, boss_height_ratio, scale, this));
		bossThisPhase.add(new Warship_2(boss_width_ratio, boss_height_ratio, scale, this));
		bossInPhases.add(bossThisPhase);
		bossThisPhase = new ArrayList<Bosses>();

		// phase 3
		cardName.add("Vuon khong nha trong");
		cardNum.add(5);
		imageSource.add("Assets/phase3/Vuon_khong_nha_trong.png");
		description.add("If you have this card, you will no longer be stolen, if all 5 cards are gathered, boss won't heal in turn 3");
		cardNames.add(cardName);
		cardNums.add(cardNum);
		imageSources.add(imageSource);
		descriptions.add(description);
		cardName = new ArrayList<String>();
		cardNum = new ArrayList<Integer>();
		imageSource = new ArrayList<String>();
		description = new ArrayList<String>();

		bossThisPhase.add(new QuanNguyenMong(boss_width_ratio, boss_height_ratio, scale, this));
		bossInPhases.add(bossThisPhase);
		bossThisPhase = new ArrayList<Bosses>();

		// phase 4
		cardName.add("Phan Dinh Giot");
		cardNum.add(1);
		imageSource.add("Assets/phase1/Phan_Dinh_Giot.png");
		description.add("stop the boss from performing in one turn");
		cardName.add("Be Van Dan");
		cardNum.add(2);
		imageSource.add("Assets/phase1/Be_Van_Dan.png");
		description.add("Add 2 damages to all type of attacks in the next turn");
		cardName.add("Xe dap tho");
		cardNum.add(3);
		imageSource.add("Assets/phase1/Xe_dap_tho.png");
		description.add("Next turn draw 4 cards instead of 3, your stamina is also increased to 4");
		cardNames.add(cardName);
		cardNums.add(cardNum);
		imageSources.add(imageSource);
		descriptions.add(description);
		cardName = new ArrayList<String>();
		cardNum = new ArrayList<Integer>();
		imageSource = new ArrayList<String>();
		description = new ArrayList<String>();

		bossThisPhase.add(new QuanPhap(boss_width_ratio, boss_height_ratio, scale, this));
		bossThisPhase.add(new QuanPhap(boss_width_ratio, boss_height_ratio, scale, this));
		bossThisPhase.add(new QuanPhap(boss_width_ratio, boss_height_ratio, scale, this));
		bossInPhases.add(bossThisPhase);
		bossThisPhase = new ArrayList<Bosses>();

		// phase 5
		cardName.add("Du kich");
		cardNum.add(3);
		imageSource.add("Assets/phase1/Du_Kich.png");
		description.add("Deals 5 damage without getting counter");
		cardName.add("Sung phong khong");
		cardNum.add(3);
		imageSource.add("Assets/phase1/Sung_Phong_Khong.png");
		description.add("Collect 3 cards and then you can use mua ten to deal 5 damages to B52");
		cardName.add("Chat doc mau da cam");
		cardNum.add(3);
		imageSource.add("Assets/phase1/Chat_Doc_Mau_Da_Cam.png");
		description.add("Deals damage to every players 1 damage/turn");
		cardNames.add(cardName);
		cardNums.add(cardNum);
		imageSources.add(imageSource);
		descriptions.add(description);
		cardName = new ArrayList<String>();
		cardNum = new ArrayList<Integer>();
		imageSource = new ArrayList<String>();
		description = new ArrayList<String>();

		bossThisPhase.add(new QuanMy_1(boss_width_ratio, boss_height_ratio, scale, this));
		bossThisPhase.add(new B52(boss_width_ratio, boss_height_ratio, scale, this));
		bossThisPhase.add(new QuanMy_2(boss_width_ratio, boss_height_ratio, scale, this));
		bossInPhases.add(bossThisPhase);
		bossThisPhase = new ArrayList<Bosses>();

		for (int i = 0; i < cardNames.size(); i++){
			List<CardTypes> thisPhase = new ArrayList<CardTypes>();
			int length = cardNames.get(i).size();
			for (int y = 0; y < length; y++){
				ImageIcon newIcon = new ImageIcon(imageSources.get(i).get(y));
				Image cardImage = newIcon.getImage();
				cardImage = cardImage.getScaledInstance(CardLabel.card_width_ratio * scale, CardLabel.card_height_ratio * scale, Image.SCALE_SMOOTH);
				thisPhase.add(new CardTypes(cardNames.get(i).get(y), cardNums.get(i).get(y), cardImage, descriptions.get(i).get(y)));
			}
			cardPhases.add(thisPhase);
		}

		startPhase(1);
    }

	public void startPhase(int phase) {
		int margin = 10;
		int distance = (width_ratio - margin * 2)/(bossInPhases.get(phase - 1).size()+1);
		for (int i = 0; i < bossInPhases.get(phase - 1).size(); i++) {
			bossInPhases.get(phase - 1).get(i).showBoss(margin + distance*(i+1) - boss_width_ratio/2, height_ratio - boss_height_ratio, scale);
			this.add(bossInPhases.get(phase - 1).get(i));
		}
	}

	int damageDealt = 0;

	public void normalAttack(int damage) {
		damageDealt = damage;
		switch (parent.phase) {
			case 1:
				bossInPhases.get(parent.phase - 1).get(0).setChoosable();
				break;
		}
	}

	public void muaTen(int damage) {
		switch (parent.phase) {
			case 1:
				bossInPhases.get(parent.phase - 1).get(0).loseHP(damage);
				break;
		}
	}

	public void update(int FPS){
		for (int i = 0; i < bossInPhases.get(parent.phase - 1).size(); i++){
			bossInPhases.get(parent.phase - 1).get(i).update(FPS);
		}
	}
}
