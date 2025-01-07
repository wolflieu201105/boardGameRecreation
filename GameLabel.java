import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Font;

public class GameLabel extends JLabel implements Runnable{
	// the dimensions of the main label
	final private int width_ratio = 320;
	final private int height_ratio = 200;

	// the current scale
	private int scale;

	// the current width or height after resizing
	private int width;
	private int height;

	// different Labels for better management
	PlayerLabel playerLabel;
	CardLabel cardLabel;
	BossLabel bossLabel;

	// player turn text
	private String playerTurnString = "Player's turn: \n";
	private JTextPane playerTurnTextPane;
	private int playerTurn_x_start_ratio = 270;
	private int playerTurn_y_start_ratio = 0;
	private int playerTurn_width_ratio = 50;
	private int playerTurn_height_ratio = 20;
	private int playerTurnSize = 7;

	// next turn button
	String nextTurnString = "End turn";
	JButton nextButton = new JButton(nextTurnString);
	int button_x_start_ratio = 270;
	int button_y_start_ratio = 20;
	int button_width_ratio = 50;
	int button_height_ratio = 10;
	int text_size_ratio = 8;

	// stamina of players text
	private String staminaString = "Stamina: ";
	private JTextPane staminaTextPane;
	private int stamina_x_start_ratio = 270;
	private int stamina_y_start_ratio = 40;
	private int stamina_width_ratio = 50;
	private int stamina_height_ratio = 20;
	private int staminaSize = 7;
	public int currentStamina;

	// turn of players
	int turn = 0;

	// phase of game
	int phase = 5;

	// make a draw card deck and a deck to put cards away
	CardDeck drawDeck;
	CardDeck disposalDeck;

	public GameLabel(int newScale) {
		scale = newScale;
		width = width_ratio * scale;
		height = height_ratio * scale;

		// adding it's own label
		playerLabel = new PlayerLabel(scale, this);
		cardLabel = new CardLabel(scale, this);
		bossLabel = new BossLabel(scale, this);
		this.add(playerLabel);
		this.add(cardLabel);
		this.add(bossLabel);

		// setting the player turn text
		playerTurnTextPane = new JTextPane();
		playerTurnTextPane.setBounds(playerTurn_x_start_ratio * scale, playerTurn_y_start_ratio * scale, playerTurn_width_ratio * scale, playerTurn_height_ratio * scale);
		playerTurnTextPane.setFont(new Font("Arial", Font.PLAIN, playerTurnSize * scale));
		playerTurnTextPane.setEditable(false);
		playerTurnTextPane.setBackground(new Color(255, 248, 178));
		playerTurnTextPane.setOpaque(true);
		this.add(playerTurnTextPane);

		// setting bounds for button
		nextButton.setBounds(button_x_start_ratio * scale, button_y_start_ratio * scale, button_width_ratio * scale, button_height_ratio * scale);
		nextButton.setFont(new Font("Arial", Font.PLAIN, text_size_ratio * scale));
		nextButton.setBackground(new Color(255,255,255));
		this.add(nextButton);
		nextButton.addActionListener(e -> {endTurn();});

		// setting stamina text
		staminaTextPane = new JTextPane();
		staminaTextPane.setBounds(stamina_x_start_ratio * scale, stamina_y_start_ratio * scale, stamina_width_ratio * scale, stamina_height_ratio * scale);
		staminaTextPane.setFont(new Font("Arial", Font.PLAIN, staminaSize * scale));
		staminaTextPane.setEditable(false);
		staminaTextPane.setBackground(new Color(255, 248, 178));
		staminaTextPane.setOpaque(true);
		this.add(staminaTextPane);

		// setting the new bounds for the label
		this.setBounds(0, 0, width, height);

		// set color back ground and visibility of the color
		this.setBackground(new Color(255, 248, 178));
		this.setOpaque(true);

		// setting deck to get ready to play
		for (int i = 0; i < cardLabel.cardTypes.length; i++) {
			cardToNum.put(cardLabel.cardTypes[i].name, i);
		}
		makeNewDeck();
		startGameThread();
	}

	// rewrites the stamina
	public void setStaminaText() {
		staminaTextPane.setText(staminaString + currentStamina + "/" + playerLabel.players[turn].maxStamina);
	}

	// creates a deck that has every card in it and a disposal deck in order to mimick the real world
	private void makeNewDeck() {
		System.out.println("yes");
		drawDeck = new CardDeck();
		disposalDeck = new CardDeck();
		for (int i = 0; i < cardLabel.cardNum; i++) {
			for (int y = 0; y < cardLabel.remaining[i]; y++) {
				drawDeck.insertCard(cardLabel.cardTypes[i]);
				drawDeck.putInDeck();
			}
		}
		for (int i = 0; i < bossLabel.cardPhases.get(phase-1).size(); i++) {
			for (int y = 0; y < bossLabel.cardNums.get(phase-1).get(i); y++){
				drawDeck.insertCard(bossLabel.cardPhases.get(phase-1).get(i));
				drawDeck.putInDeck();
			}
		}
	}

	List<CardTypes> cardsDrawn = new ArrayList<CardTypes>();
	private void startTurn() {
		playerTurnTextPane.setText(playerTurnString + playerLabel.players[turn].name);
		currentStamina = playerLabel.players[turn].maxStamina;
		setStaminaText();
		cardsDrawn.clear();
		for (int i = playerLabel.getNumCards(turn); i > 0; i--) {
			cardsDrawn.add(drawACard());
		}
		cardLabel.start(cardsDrawn);
		switch(phase){
			case 1:
				checkForCardOnField("Vo de");
				break;
			case 5:
				checkForCardOnField("Chat doc mau da cam");
				break;
			default:
				break;
		}
	}

	private void checkForCardOnField(String name){
		for (int i = 0; i < cardLabel.numberOfCards; i++) {
			if (cardLabel.cardsInPlay[i].cardTypes.name.equals(name)){
				cardLabel.cardsInPlay[i].choosen = true;
				cardLabel.cardPlayed(i);
				break;
			}
		}
	}

	private CardTypes drawACard() {
		CardTypes newCard = drawDeck.drawCard();
		if (newCard == null) {
			System.out.println("noDeck");
			drawDeck = disposalDeck;
			disposalDeck = new CardDeck();
			newCard = drawDeck.drawCard();
		}
		return newCard;
	}

	HashMap<String, Integer> cardToNum = new HashMap<String, Integer>();
	public void cardUsed(int index) {
		CardTypes cardDrawn = cardsDrawn.get(index);
		if (currentStamina < cardDrawn.staminaCost){
			cardLabel.cardsInPlay[index].choosen = false;
			for (int i = 0; i < cardLabel.numberOfCards; i++) {
				cardLabel.cardsInPlay[i].choosable = true;
			}
			return;
		}
		currentStamina -= cardDrawn.staminaCost;
		setStaminaText();
		String name = cardDrawn.name;
		cardsDrawn.remove(index);
		// if this is null, it is a card that is exclusive for that phase
		if (cardToNum.get(name) == null) {
			PhaseCardFunction(cardDrawn);
		}
		else {
			NormalCardFunction(cardDrawn);
		}
	}

	// this is exclusive for phase 4
	boolean bevandan = false;

	// this is exclusive for phase 5
	int sungPhongKhongNum = 0;

	public void NormalCardFunction(CardTypes cardDrawn){
		int card = cardToNum.get(cardDrawn.name);
		if (card < 6){
			disposalDeck.insertCard(cardDrawn);
		}
		switch (card) {
			case 0:
				switch(phase){
				case 2:
					if (bossLabel.bossTurn == 1 && (turn == 0 || turn == 1)){
						break;
					}
					bossLabel.normalAttack(2);
					break;
				case 4:
					if (bevandan){
						bossLabel.normalAttack(4);
					}
					else {
						bossLabel.normalAttack(2);
					}
					break;
				default:
					bossLabel.normalAttack(2);
					break;
				}
				break;
			case 1:
				switch(phase){
					case 4:
						if (bevandan){
							int muaTenWaitTime = 500;
							Timer muaTenTimer = new Timer(muaTenWaitTime, new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {            
									bossLabel.muaTen(3);
								}
							});
							muaTenTimer.setRepeats(false);
							muaTenTimer.start();
						}
						else {
							int muaTenWaitTime = 500;
							Timer muaTenTimer = new Timer(muaTenWaitTime, new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {            
									bossLabel.muaTen(1);
								}
							});
							muaTenTimer.setRepeats(false);
							muaTenTimer.start();
						}
					default:
						int muaTenWaitTime = 500;
						Timer muaTenTimer = new Timer(muaTenWaitTime, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {            
								bossLabel.muaTen(1);
							}
						});
						muaTenTimer.setRepeats(false);
						muaTenTimer.start();
						break;
				}
				break;
			case 2:
				playerLabel.changePlayersState(card, turn);
				break;
			case 3:
				switch(phase){
				case 2:
					if (bossLabel.bossTurn == 1 && (turn == 0 || turn == 1)){
						break;
					}
					bossLabel.normalAttack(4);
					playerLabel.players[turn].loseHP(2);
					break;
				case 4:
					if (bevandan){
						bossLabel.normalAttack(6);
						playerLabel.players[turn].loseHP(2);
					}
					else {
						bossLabel.normalAttack(4);
						playerLabel.players[turn].loseHP(1);
					}
					break;
				default:
					bossLabel.normalAttack(4);
					playerLabel.players[turn].loseHP(2);
					break;
				}
				break;
			case 4:
				switch(phase){
					case 3:
						if (bossLabel.bossTurn == 1){
							cardsDrawn.add(drawACard());
						}
						break;
					default:
						break;
				}
				cardsDrawn.add(drawACard());
				cardsDrawn.add(drawACard());
				int dieuBinhKhienTuongWaitTime = 500;
				Timer dieuBinhKhienTuongTimer = new Timer(dieuBinhKhienTuongWaitTime, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {            
						continueGame();
						switch(phase){
							case 1:
								checkForCardOnField("Vo de");
								break;
							case 5:
								checkForCardOnField("Chat doc mau da cam");
								break;
							default:
								break;
						}
					}
				});
				dieuBinhKhienTuongTimer.setRepeats(false);
				dieuBinhKhienTuongTimer.start();
				break;
			case 5:
				playerLabel.changePlayersState(card, turn);
				break;
			case 6:
				playerLabel.players[turn].buffs.add(new PlayerBuffs(scale, cardDrawn, 1));
				int phongThuWaitTime = 500;
				Timer phongThuTimer = new Timer(phongThuWaitTime, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						playerLabel.players[turn].drawBuffs();
						playerLabel.afterCardFuntion();
					}
				});
				phongThuTimer.setRepeats(false);
				phongThuTimer.start();
				break;
			case 7:
				playerLabel.buffUsed = cardDrawn;
				playerLabel.changePlayersState(card, turn);
				break;
			default:
				System.out.println("Through");
				continueGame();
				break;
		}
	}

	public void PhaseCardFunction(CardTypes cardDrawn){
		switch (phase){
			case 1:
				if (cardDrawn.name.equals("Dap de")){
					playerLabel.buffUsed = cardDrawn;
					playerLabel.phasePlayerState(-1,-1);
				}
				else{
					int voDeWaitTime = 500;
					Timer voDeTimer = new Timer(voDeWaitTime, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						for(int i = 0; i < playerLabel.players[turn].buffs.size(); i++){
							if (playerLabel.players[turn].buffs.get(i).cardTypes.name.equals("Dap de")){
								disposalDeck.insertCard(playerLabel.players[turn].buffs.get(i).cardTypes);
								disposalDeck.putInDeck();
								playerLabel.players[turn].remove(playerLabel.players[turn].buffs.get(i));
								playerLabel.players[turn].buffs.remove(i);
								playerLabel.players[turn].drawBuffs();
								break;
							}
						}
						disposalDeck.insertCard(cardDrawn);
						disposalDeck.putInDeck();
						playerLabel.afterCardFuntion();
						checkForCardOnField("Vo de");
					}
					});
					voDeTimer.setRepeats(false);
					voDeTimer.start();
				}
				break;
			case 2:
				playerLabel.buffUsed = cardDrawn;
				if (cardDrawn.name.equals("Coc")){
					for (int i = 0; i < bossLabel.bossInPhases.get(phase - 1).size(); i++) {
						if (bossLabel.bossInPhases.get(phase - 1).get(i).getName().equals("Warship")){
							bossLabel.bossInPhases.get(phase - 1).get(i).setChoosable(true);
						}
					}
				}
				break;
			case 3:
				playerLabel.buffUsed = cardDrawn;
				playerLabel.phasePlayerState(-1,-1);
				break;
			
			case 4:
				playerLabel.players[turn].buffs.add(new PlayerBuffs(scale, cardDrawn, 1));
				int waitTime = 500;
				Timer timer = new Timer(waitTime, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						playerLabel.players[turn].drawBuffs();
						playerLabel.afterCardFuntion();
					}
				});
				timer.setRepeats(false);
				timer.start();
				break;
			case 5:
				if (cardDrawn.name.equals("Chat doc mau da cam")){
					playerLabel.players[turn].buffs.add(new PlayerBuffs(scale, cardDrawn, 1));
					int cdmdcWaitTime = 500;
					Timer cdmdcTimer = new Timer(cdmdcWaitTime, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						playerLabel.players[turn].drawBuffs();
						playerLabel.afterCardFuntion();
					}
					});
					cdmdcTimer.setRepeats(false);
					cdmdcTimer.start();
				}
				else if (cardDrawn.name.equals("Du kich")){
					bossLabel.normalAttack(0);
				}
				else{
					playerLabel.players[turn].buffs.add(new PlayerBuffs(scale, cardDrawn, 1));
					int spkWaitTime = 500;
					Timer spkTimer = new Timer(spkWaitTime, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						sungPhongKhongNum++;
						playerLabel.players[turn].drawBuffs();
						playerLabel.afterCardFuntion();
					}
					});
					spkTimer.setRepeats(false);
					spkTimer.start();
				}
				break;
		}
	}


	public void resetLabelState(){
		playerLabel.notClickablePlayer = -1;
		playerLabel.clickable = false;
		for (int i = 0; i < bossLabel.bossInPhases.get(phase - 1).size(); i++) {
			bossLabel.bossInPhases.get(phase - 1).get(i).choosable = false;
		}
		for (int i = 0; i < cardLabel.numberOfCards; i++) {
			cardLabel.cardsInPlay[i].choosen = false;
		}
	}


	public void endTurn() {
		resetLabelState();
		turn++;
		if (turn == 4){
			playerLabel.changePosition();
			bossLabel.bossTurn();
			bossLabel.bossTurn++;
			if(bossLabel.bossTurn == 3){
				bossLabel.bossTurn = 0;
			}
			for (int i = 0; i < playerLabel.players.length; i++){
				for(int y = 0; y < playerLabel.players[i].buffs.size(); y++){
					if (playerLabel.players[i].buffs.get(y).cardTypes.name.equals("BaoHoDongMinh")){
						playerLabel.players[i].remove(playerLabel.players[i].buffs.get(y));
						playerLabel.players[i].buffs.remove(y);
						y--;
					}
				}
				playerLabel.players[i].drawBuffs();
			}
			switch(phase){
				case 4:
					for (int i = 0; i < playerLabel.players.length; i++){
						for(int y = 0; y < playerLabel.players[i].buffs.size(); y++){
							boolean xdtCheck = false;
							if (playerLabel.players[i].buffs.get(y).cardTypes.name.equals("Xe dap tho")){
								xdtCheck = true;
								disposalDeck.insertCard(playerLabel.players[i].buffs.get(y).cardTypes);
								disposalDeck.putInDeck();
								playerLabel.players[i].remove(playerLabel.players[i].buffs.get(y));
								playerLabel.players[i].buffs.remove(y);
								y--;
							}
							if (xdtCheck){
								playerLabel.players[i].maxStamina = 4;
								playerLabel.players[i].cardsNextTurn = 4;
								continue;
							}
							else {
								playerLabel.players[i].maxStamina = 3;
								playerLabel.players[i].cardsNextTurn = 3;
							}

							boolean bvdCheck = false;
							if (playerLabel.players[i].buffs.get(y).cardTypes.name.equals("Be van dan")){
								bvdCheck = true;
								disposalDeck.insertCard(playerLabel.players[i].buffs.get(y).cardTypes);
								disposalDeck.putInDeck();
								playerLabel.players[i].remove(playerLabel.players[i].buffs.get(y));
								playerLabel.players[i].buffs.remove(y);
								y--;
							}

							if (bvdCheck){
								bevandan = true;
								continue;
							}
							else {
								bevandan = false;
							}
						}
						playerLabel.players[i].drawBuffs();
					}
					break;
			}
			turn = 0;
		}
		for (int i = 0; i < cardsDrawn.size(); i++) {
			disposalDeck.insertCard(cardsDrawn.get(i));
			disposalDeck.putInDeck();
		}
		cardsDrawn.clear();
		startTurn();
	}


	public void endGamePhase(){
		resetLabelState();
		for (int i = 0; i < bossLabel.bossInPhases.get(phase - 1).size(); i++) {
			bossLabel.remove(bossLabel.bossInPhases.get(phase - 1).get(i));
		}
		for (int i = 0; i < cardLabel.numberOfCards; i++) {
			cardLabel.remove(cardLabel.cardsInPlay[i]);
		}
		for (int i = 0; i < playerLabel.players.length; i++) {
			for (int y = playerLabel.players[i].buffs.size() - 1; y >= 0 ; y--) {
				playerLabel.players[i].remove(playerLabel.players[i].buffs.get(y));
			}
			playerLabel.players[i].buffs = new ArrayList<PlayerBuffs>();
			playerLabel.players[i].maxStamina = 3;
			playerLabel.players[i].cardsNextTurn = 3;
		}
		phase++;
		turn = 0;
		makeNewDeck();
		bossLabel.bossTurn = 0;
		bossLabel.startPhase(phase);
		startTurn();
	}


	public void continueGame() {
		disposalDeck.putInDeck();
		cardLabel.start(cardsDrawn);
	}

	// FPS implementation

	final private int FPS = 60;

	Thread gameThread;

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		startTurn();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {
		playerLabel.update();
		cardLabel.update(FPS);
		bossLabel.update(FPS);
	}
}
