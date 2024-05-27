package lesser.finalproject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ImageFrame extends JFrame {

    public ImageFrame(String imageUrl, String title) {
        setSize(800, 600);
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        try {
            URL url = new URL(imageUrl);
            Image image = ImageIO.read(url);
            ImageIcon imageIcon = new ImageIcon(image);

            Image scaledImage = image.getScaledInstance(800, -1, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);

            JLabel label = new JLabel(imageIcon);
            JScrollPane scrollPane = new JScrollPane(label);

            panel.add(scrollPane, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        setContentPane(panel);
        setVisible(true);
    }
}
