package M1_IP;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    private GridLayout gridLayout;
    private ArrayList<JButton> buttons;
    private JButton homePageButton;
    private JComboBox comboBox;
    private String[] updateFrequencyOptions;
    SpecificCurrencyPanel specificCurrencyPanel;


    public MainPanel(){
        this.gridLayout = new GridLayout(7,10,5,5);
        this.buttons = new ArrayList<>();

        this.updateFrequencyOptions = new String[]{"  250ms" , "  500ms" , "  1 sec" , "  2 sec" , "  5 sec"};
        this.comboBox = new JComboBox<>(this.updateFrequencyOptions);

        this.homePageButton = new JButton("home page");

        setButtonsFunctions();
    }

    public void setButtonsFunctions(){
        this.setLayout(this.gridLayout);

        boolean middle = true;

        for (int i = 0 ; i < MainCalculation.getCurrencies().size() ; i++) {
            if (i == 34 && middle){
                this.comboBox.setSelectedIndex(2);
                this.comboBox.setFont(new Font("Times New Roman" , Font.PLAIN, 35));
                this.add(this.comboBox);
                i --;
                middle = false;
            }
            else {
                buttons.add(new JButton(MainCalculation.getCurrencies().get(i).getName()));
                buttons.get(i).setBackground(Color.CYAN);
                buttons.get(i).setFont(new Font("Times New Roman" , Font.PLAIN , 20));
                JButton button = buttons.get(i);
                buttons.get(i).addActionListener(e -> {
                    buttonClicked(MainCalculation.getCurrencies().get(this.buttons.indexOf(button)) , this.buttons.indexOf(button));
                });
                this.add(buttons.get(i));
            }
        }

        this.homePageButton.setBounds(70 , 70 , 200 , 40);
        this.homePageButton.setFont(new Font("Times New Roman" , Font.PLAIN , 30));
        this.homePageButton.setBackground(Color.CYAN);
        this.homePageButton.addActionListener(e -> {
            returnToHomePage();
        });
        this.homePageButton.setVisible(false);


    }

    public void buttonClicked(Currency currency , int index){
        MainCalculation.setUpdateTime(extractFrequencyOption(this.comboBox.getSelectedIndex()));

        for (JButton button : buttons) {
            button.setVisible(false);
        }
        this.comboBox.setVisible(false);

        this.setLayout(null);
        this.add(this.homePageButton);
        this.homePageButton.setVisible(true);
        this.specificCurrencyPanel = new SpecificCurrencyPanel(currency , index);
        this.specificCurrencyPanel.setBounds(0,0,Window.WINDOW_WIDTH,Window.WINDOW_HEIGHT);
        this.add(specificCurrencyPanel);


        this.repaint();
    }

    public void returnToHomePage(){
        for (JButton button : buttons) {
            button.setVisible(true);
        }
        this.comboBox.setVisible(true);

        this.remove(this.specificCurrencyPanel);
        this.homePageButton.setVisible(false);

        this.repaint();
    }

    public int extractFrequencyOption(int index){
        try {
            return Integer.parseInt(this.updateFrequencyOptions[index].substring(2 , this.updateFrequencyOptions[index].indexOf("s") -1));
        } catch(NumberFormatException e1) {

        }
        return 1000;
    }

}
