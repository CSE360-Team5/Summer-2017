package CSE360;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Team8Ghost extends JPanel implements Runnable {

    private JLabel ghostLabel;

    public Team8Ghost() {
        Initialize();
    }

    private void Initialize() {

        Integer gs = 30;
        String path = "Team8Image/Ghost.png";
        ImageIcon ghostImage;

        this.setSize(super.getWidth(), super.getHeight());
        this.setLayout(null);
        URL url = getClass().getResource(path);
        Image image = null;
        try {
            image = ImageIO.read(url);
        } catch (IOException ex) {
        }

        ghostImage = new ImageIcon(resizeImage(image, gs, gs));

        ghostLabel = new JLabel();
        ghostLabel.setIcon(ghostImage);
        ghostLabel.setSize(gs, gs);
    }

    @Override
    public void run() {

        Integer xStart = 0, yStart = 30,
                xLimit = 250, yLimit = 100;

        int[] position = {xStart, yStart, xLimit, yLimit};
        while (true) {
            position = moveGhost(position[0], position[1], position[2], position[3]);
        }
    }

    //moving ghost
    private int[] moveGhost(int x, int y, int xL, int yL) {

        int position[] = new int[4];

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.exit(1);
        }

        if (x == xL) x = 0;
        else x++;
        /*
        if(y == yL)y = 0;
        else y++;
        */
        updateGhostPosition(x, y);
        position[0] = x;
        position[1] = y;
        position[2] = xL;
        position[3] = yL;
        return position;
        //moveGhost(x, y, xL, yL);
    }

    //update ghost position
    private void updateGhostPosition(int x, int y) {
        this.removeAll();
        ghostLabel.setLocation(x, y);
        this.add(ghostLabel);
        super.revalidate();
        super.repaint();
    }

    private Image resizeImage(Image Img, int width, int height) {
        BufferedImage newImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = newImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(Img, 0, 0, width, height, null);
        g2.dispose();

        return newImg;
    }
}
