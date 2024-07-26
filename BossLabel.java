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

	// added cards for each phase
	List<List<String>> cardNames = new ArrayList<List<String>>();
	List<List<Integer>> cardNums = new ArrayList<List<Integer>>();
	List<List<String>> imageSources = new ArrayList<List<String>>();
	List<List<CardTypes>> cardPhases = new ArrayList<List<CardTypes>>();
	List<List<String>> descriptions = new ArrayList<List<String>>();

	// game label
	GameLabel parent;

	ThuyTinh newBoss;
	
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
		newBoss = new ThuyTinh(40, 75, scale);
		this.add(newBoss);
		newBoss.showBoss(5, 5, scale);

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
		cardName.clear();
		cardNum.clear();
		imageSource.clear();
		description.clear();

		// phase 2
		cardName.add("Coc");
		cardNum.add(5);
		imageSource.add("Assets/phase2/Coc.png");
		description.add("When the tide goes low, deals 1 damage to all boats");
		cardName.clear();
		cardNum.clear();
		imageSource.clear();
		description.clear();

		// phase 3
		cardName.add("Vuon khong nha trong");
		cardNum.add(5);
		imageSource.add("Assets/phase3/Vuon_khong_nha_trong.png");
		description.add("If you have this card, you will no longer be stolen, if all 5 cards are gathered, boss won't heal in turn 3");
		cardName.clear();
		cardNum.clear();
		imageSource.clear();
		description.clear();

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
		cardName.clear();
		cardNum.clear();
		imageSource.clear();
		description.clear();

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
		cardName.clear();
		cardNum.clear();
		imageSource.clear();
		description.clear();

		for (int i = 0; i < cardNames.size(); i++){
			List<CardTypes> thisPhase = new ArrayList<CardTypes>();
			int length = cardNames.get(i).size();
			for (int y = 0; y < length; y++){
				ImageIcon newIcon = new ImageIcon(imageSources.get(i).get(y));
				Image cardImage = newIcon.getImage();
				cardImage = cardImage.getScaledInstance(CardLabel.card_width_ratio * scale, CardLabel.card_height_ratio * scale, Image.SCALE_SMOOTH);
				thisPhase.add(new CardTypes(cardNames.get(i).get(y), cardNums.get(i).get(y), cardImage, descriptions.get(i).get(y)));
			}
		}
    }

	public void update(int FPS){
		newBoss.update(FPS);
	}
}
