import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

public class PlayerBuffs extends JLabel{
    private int newScale;
    static public int width_ratio = 14;
    static public int height_ratio = 21;

    // card type
    public CardTypes cardTypes;

    public PlayerBuffs(int scale, CardTypes cardDrawn){
        newScale = scale;
        Image cardImage = cardDrawn.image;
        cardImage = cardImage.getScaledInstance(width_ratio * scale, height_ratio * scale, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(cardImage);
        this.setIcon(scaledIcon);
        this.setOpaque(false);
    }

    public void setPositions(int x, int y){
        this.setBounds(x*newScale, y*newScale, width_ratio*newScale, height_ratio*newScale);
        this.setOpaque(true);
    }
}
