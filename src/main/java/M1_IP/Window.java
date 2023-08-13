package M1_IP;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public static final int WINDOW_WIDTH = 1900;
    public static final int WINDOW_HEIGHT = 1000;

    public Window() {
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(WINDOW_WIDTH , WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(0 , 0 , WINDOW_WIDTH , WINDOW_HEIGHT);
        this.setVisible(true);

        JLabel welcomeLabel = new JLabel("Welcome to my currency market");
        welcomeLabel.setFont(new Font("Times New Roman" , Font.PLAIN , 100));
        welcomeLabel.setBounds(300 , 350 , 1400 , 110);
        welcomeLabel.setVisible(true);
        this.add(welcomeLabel);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        welcomeLabel.setVisible(false);

        MainPanel mainPanel = new MainPanel();
        mainPanel.setBounds(0 , 0 , WINDOW_WIDTH - 20 , WINDOW_HEIGHT - 45);
        this.add(mainPanel);
        this.setVisible(true);

    }
}
