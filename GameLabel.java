import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

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

	// next turn button
	String nextTurnString = "End turn";
	JButton nextButton = new JButton(nextTurnString);
	int button_x_start_ratio = 270;
	int button_y_start_ratio = 10;
	int button_width_ratio = 50;
	int button_heigth_ratio = 10;
	int text_size_ratio = 8;

	// turn of players
	int turn = 0;

	// phase of game
	int phase = 1;

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

		// setting bounds for button
		nextButton.setBounds(button_x_start_ratio * scale, button_y_start_ratio * scale, button_width_ratio * scale, button_heigth_ratio * scale);
		nextButton.setFont(new Font("Arial", Font.PLAIN, text_size_ratio * scale));
		nextButton.setBackground(new Color(255,255,255));
		this.add(nextButton);
		nextButton.addActionListener(e -> {endTurn();});

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

	// creates a deck that has every card in it and a disposal deck in order to mimick the real world
	private void makeNewDeck() {
		drawDeck = new CardDeck();
		disposalDeck = new CardDeck();
		for (int i = 0; i < cardLabel.cardNum; i++) {
			for (int y = 0; y < cardLabel.remaining[i]; y++) {
				drawDeck.insertCard(cardLabel.cardTypes[i]);
			}
		}
		for (int i = 0; i < bossLabel.cardPhases.get(phase-1).size(); i++) {
			for (int y = 0; y < bossLabel.cardNums.get(phase-1).get(i); y++){
				drawDeck.insertCard(bossLabel.cardPhases.get(phase-1).get(i));
			}
		}
	}

	List<CardTypes> cardsDrawn = new ArrayList<CardTypes>();
	private void startTurn() {
		cardsDrawn.clear();
		for (int i = playerLabel.getNumCards(turn); i > 0; i--) {
			cardsDrawn.add(drawACard());
		}
		cardLabel.start(cardsDrawn);
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
		String name = cardDrawn.name;
		cardsDrawn.remove(index);

		// if this is null, it is a card that is exclusive for that phase
		if (cardToNum.get(name) == null) {
		}
		else {
			if(cardToNum.get(name) < 6) {
				disposalDeck.insertCard(cardDrawn);
			}
			NormalCardFunction(cardToNum.get(name));
		}
	}

	public void NormalCardFunction(int card){
		switch (card) {
			case 0:
				bossLabel.normalAttack(2);
				break;
			case 1:
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
			case 2:
				playerLabel.changePlayersState(card, turn);
				break;
			case 3:
				bossLabel.normalAttack(4);
				playerLabel.players[turn].loseHP(2);
				break;
			case 4:
				cardsDrawn.add(drawACard());
				cardsDrawn.add(drawACard());
				int dieuBinhKhienTuongWaitTime = 500;
				Timer dieuBinhKhienTuongTimer = new Timer(dieuBinhKhienTuongWaitTime, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {            
						bossLabel.muaTen(1);
					}
				});
				dieuBinhKhienTuongTimer.setRepeats(false);
				dieuBinhKhienTuongTimer.start();
				break;
			case 5:
				playerLabel.changePlayersState(card, turn);
				break;
			default:
				System.out.println("Through");
				break;
		}
	}

	public void endTurn() {
		for (int i = 0; i < cardsDrawn.size(); i++) {
			disposalDeck.insertCard(cardsDrawn.get(i));
		}
		turn++;
		if (turn == 4){
			turn = 0;
		}
		System.out.println(turn);
		startTurn();
	}

	public void continueGame() {
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
