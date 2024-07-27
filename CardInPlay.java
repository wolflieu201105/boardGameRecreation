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
    private double dummyX;
    private double dummyY;
    private int speed_scale = 10;

    // whether the card is being shown
    boolean choosen = false;

    // whether the cards can be chosen
    boolean choosable = true;

    // whether the mouse is inside this
    boolean mouseInside = false;

    // card type
    public CardTypes cardTypes;

    // parent
    private CardLabel parent;
    public void getCreator (CardLabel creator){
        parent = creator;
    }

    public CardInPlay(int Width, int Height) {
        width = Width;
        height = Height;
        this.setOpaque(true);
        this.addMouseListener(this);
    }

    public void viewAble(int X, int Y, int scale) {
        x = X * scale;
        y = Y * scale;
        dummyX = x;
        dummyY = y;
        initialX = x;
        initialY = y;
        this.setBounds(x, y, width, height);
        ImageIcon scaledIcon = new ImageIcon(cardTypes.image);
        this.setIcon(scaledIcon);
    }

    public void moveTo(int x_stop, int y_stop, int FPS) {
        dx = (x_stop - dummyX) / (FPS / speed_scale);
        dummyX += dx;
        x = (int)dummyX;
        dy = (y_stop - dummyY) / (FPS / speed_scale);
        dummyY += dy;
        y = (int)dummyY;
        this.setLocation(x, y);
    }

    public void update(int FPS) {
        if (choosen) {
            this.moveTo(initialX, initialY - (3 * height / 4), FPS);
        } else {
            if (mouseInside) {
                this.moveTo(initialX, initialY - (height / 4), FPS);
            } else {
                this.moveTo(initialX, initialY, FPS);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseInside = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseInside = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            if (choosable) {
                choosen = true;
                parent.cardPlayed(cardTypes.name);
            }
        }
        if (e.getButton() == MouseEvent.BUTTON3){
            System.out.println(cardTypes.name);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
