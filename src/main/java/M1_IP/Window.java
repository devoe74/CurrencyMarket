package M1_IP;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public static final int WINDOW_WIDTH = 1900;
    public static final int WINDOW_HEIGHT = 1000;
    private JLabel welcomeLabel;
    private MainPanel mainPanel;
    public Window() {
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(WINDOW_WIDTH , WINDOW_HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(0 , 0 , WINDOW_WIDTH , WINDOW_HEIGHT);
        this.setVisible(true);

        this.welcomeLabel = new JLabel("Welcome to my currency market");
        this.welcomeLabel.setFont(new Font("Times New Roman" , Font.PLAIN , 100));
        this.welcomeLabel.setBounds(300 , 350 , 1400 , 110);
        this.welcomeLabel.setVisible(true);
        this.add(this.welcomeLabel);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.welcomeLabel.setVisible(false);

        this.mainPanel = new MainPanel();
        this.mainPanel.setBounds(0 , 0 , WINDOW_WIDTH - 20 , WINDOW_HEIGHT - 45);
        this.add(mainPanel);
        this.setVisible(true);

    }
}
