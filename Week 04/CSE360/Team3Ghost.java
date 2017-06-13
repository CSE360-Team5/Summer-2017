/**
 * Created by User on 6/12/2017.
 */

// specify which package the file is in
package CSE360;

// Team Member Names:
// Devyn Hedin
// Thunpisit Amnuaikiatloet
// Jonathan Proctor
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;




public class Team3Ghost extends JPanel implements Runnable
{

    Thread runner;
    private BufferedImage image;


    public Team3Ghost() throws IOException {
        runner = new Thread(this);
        runner.start();
        image = ImageIO.read(new File("Team3Images/team3ghost.png"));
        JLabel picLabel = new JLabel(new ImageIcon(image));
        add(picLabel);
        repaint();
    }
    public void run() {


    }
}
