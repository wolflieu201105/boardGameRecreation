package InGame.Cards;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardInPlay extends JLabel implements MouseListener {
    private int width;
    private int height;

    // position of card
    private int initialX;
    private int initialY;
    public int x;
    public int y;

    // vector of card
    private double dx;
    private double dy;
    private int speed_scale = 10;

    // whether the card is being shown
    boolean choosen = false;

    // whether the cards can be chosen
    boolean choosable = true;

    // whether the mouse is inside this
    boolean mouseInside = false;

    // card type
    public CardTypes cardTypes;

    public CardInPlay(int Width, int Height) {
        width = Width;
        height = Height;
        this.setOpaque(true);
        this.addMouseListener(this);
    }

    public void viewAble(int X, int Y) {
        x = X;
        y = Y;
        initialX = X;
        initialY = Y;
        this.setBounds(x, y, x + width, y + width);
        ImageIcon scaledIcon = new ImageIcon(cardTypes.image);
        this.setIcon(scaledIcon);
    }

    public void moveTo(int x_stop, int y_stop) {
        dx = (x_stop - x) / speed_scale;
        x += dx;
        dy = (y_stop - y) / speed_scale;
        y += dy;
        this.setBounds(x, y, x + width, y + height);
    }

    public void update() {
        if (choosen) {
            this.moveTo(x, initialY + height);
        } else {
            if (mouseInside) {
                this.moveTo(initialX, initialY + height / 2);
            } else {
                this.moveTo(initialX, initialY);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseIn!");
        mouseInside = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInside = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }
}
