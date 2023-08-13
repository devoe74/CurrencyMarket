package M1_IP;

import javax.swing.*;
import java.awt.*;

public class SpecificCurrencyPanel extends JPanel {

    private double reverseValue;
    private JButton switchCurrenciesButton;
    private JButton convertValueButton;
    private JLabel fromNameLabel;
    private JLabel toNameLabel;
    private JLabel valueLabel;
    private JLabel valueAfterConversionLabel;
    private JTextField convertValueField;
    private Currency currency;
    private int indexOfCurrency;

    public SpecificCurrencyPanel(Currency currency ,int index){
        this.currency = currency;
        this.indexOfCurrency = index;

        this.reverseValue = 1 / this.currency.getValue();


        this.setLayout(null);

        this.fromNameLabel = new JLabel(this.currency.getFromName());

        this.switchCurrenciesButton = new JButton(">>>");

        this.toNameLabel = new JLabel(this.currency.getToName());

        this.valueLabel = new JLabel(String.valueOf(this.currency.getValue()));

        this.convertValueField = new JTextField();

        this.convertValueButton = new JButton("=>");

        this.valueAfterConversionLabel = new JLabel("value in: " + this.currency.getToName());

        whatToDisplay(0);
        updateCurrency();

    }


    public void whatToDisplay(int type){
        if (type == 0){
            this.fromNameLabel.setBounds(400 , 120 , 400 ,160);
            this.fromNameLabel.setFont(new Font("Times New Roman" , Font.PLAIN , 140));
            this.add(this.fromNameLabel);

            this.switchCurrenciesButton.setBounds(840,140,210,110);
            this.switchCurrenciesButton.setFont(new Font("Times New Roman" , Font.BOLD , 100));
            this.switchCurrenciesButton.addActionListener(e -> {
                if (this.switchCurrenciesButton.getText().equals(">>>")){
                    whatToDisplay(1);
                }
                else {
                    whatToDisplay(2);
                }
            });
            this.add(this.switchCurrenciesButton);

            this.toNameLabel.setBounds(1250 , 120 , 400 ,160);
            this.toNameLabel.setFont(new Font("Times New Roman" , Font.PLAIN , 140));
            this.add(this.toNameLabel);

            this.valueLabel.setBounds(730 , 400 , 1000 , 160);
            this.valueLabel.setFont(new Font("Times New Roman" , Font.PLAIN , 150));
            this.add(valueLabel);

            this.convertValueField.setBounds(200 , 800 , 500 , 70);
            this.convertValueField.setFont(new Font("Times New Roman" , Font.PLAIN , 40));
            this.add(this.convertValueField);

            this.convertValueButton.setBounds(840 , 800 , 210 , 70);
            this.convertValueButton.setFont(new Font("Times New Roman" , Font.BOLD , 50));
            this.convertValueButton.addActionListener(e -> {
                try {
                    double userRequest = Double.parseDouble(this.convertValueField.getText());
                    calculateConversion(this.switchCurrenciesButton.getText().equals(">>>") , userRequest);
                } catch(NumberFormatException e1) {
                    this.convertValueField.setText("invalid!");
                }
            });
            this.add(convertValueButton);

            this.valueAfterConversionLabel.setBounds(1200 , 800 , 500 , 70);
            this.valueAfterConversionLabel.setFont(new Font("Times New Roman" , Font.BOLD , 50));
            this.add(this.valueAfterConversionLabel);

        }

        if (type == 1){
            this.switchCurrenciesButton.setText("<<<");
            this.valueLabel.setText(String.format("%.5f" , reverseValue));
            this.convertValueField.setBounds(1200 , 800 , 500 , 70);
            this.valueAfterConversionLabel.setBounds(300 , 800 , 500 , 70);
            this.valueAfterConversionLabel.setText("value in: " + this.currency.getFromName());
            this.convertValueButton.setText("<=");
        }

        if (type == 2){
            this.switchCurrenciesButton.setText(">>>");
            this.valueLabel.setText(String.valueOf(this.currency.getValue()));
            this.convertValueField.setBounds(200 , 800 , 500 , 70);
            this.valueAfterConversionLabel.setBounds(1200 , 800 , 500 , 70);
            this.valueAfterConversionLabel.setText("value in: " + this.currency.getToName());
            this.convertValueButton.setText("=>");
        }
    }

    public void calculateConversion (boolean fromTo , double userRequest){
        if (fromTo){
            this.valueAfterConversionLabel.setText(String.format("%.5f" , userRequest * this.currency.getValue()) + " " + this.currency.getToName());
        }
        else {
            this.valueAfterConversionLabel.setText(String.format("%.5f" , userRequest * reverseValue) + " " + this.currency.getFromName());
        }

    }

    public void updateCurrency(){
        new Thread(()->{
            while (true){
                if (!MainCalculation.getCurrencies().isEmpty()){
                    this.currency = MainCalculation.getCurrencies().get(this.indexOfCurrency);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (this.switchCurrenciesButton.getText().equals(">>>")){
                    this.valueLabel.setText(String.valueOf(this.currency.getValue()));
                }else {
                    this.reverseValue = 1 / this.currency.getValue();
                    this.valueLabel.setText(String.format("%.5f" , reverseValue));
                }

            }
        }).start();
    }

}
