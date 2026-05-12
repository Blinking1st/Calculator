import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.*;;

public class Calculator {
    int boardWidth = 360;
    int boardHeight = 540;

    Color customBlue = new Color(39, 142, 228);
    Color customRed = new Color(234, 70, 71);
    Color customYellow = new Color(241, 202, 49);
    Color customGreen = new Color(121, 241, 240);
    Color customOrange = new Color(249, 82, 21);
    Color customPurple = new Color(93, 62, 204);

    String[] buttonValues = {
        "AC", "+/-", "%", "x^2","÷",
        "7", "8", "9","Del", "×",
        "4", "5", "6","", "-",
        "1", "2", "3","", "+",
        "0", ".", "√","", "=",
        };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%","x^2"};
        
    String A = "0";
    String operator = null;
    String B = null;

    JFrame frame = new JFrame("Calculator");
    // We are placing the text inside the label
    //We are then placing the label inside the panel
    //Then the panel inside the window
    JLabel displayLabel = new JLabel();
    JPanel disJPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();


    Calculator(){
        
        frame.setSize(boardWidth, boardHeight);
        //Centers window
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        //Set window Colors and font
        displayLabel.setBackground(customBlue);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN,80));
        //Display text to the right
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        //The default text the label will show
        displayLabel.setText("0");
        displayLabel.setOpaque(true);
        //Put label containing input text
        disJPanel.setLayout(new BorderLayout());
        disJPanel.add(displayLabel);
        frame.add(disJPanel, BorderLayout.NORTH);


        buttonsPanel.setLayout(new GridLayout(5, 5));
        buttonsPanel.setBackground(customRed);
        frame.add(buttonsPanel);

        for(int i = 0; i < buttonValues.length;i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            //Set font of buttons
            button.setFont(new Font("Arial", Font.PLAIN, 30));   
            button.setText(buttonValue);   
            button.setFocusable(false);
            button.setBorder(new LineBorder(customYellow));
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customGreen);
                button.setForeground(Color.white);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            }
            else {
                button.setBackground(customPurple);
                button.setForeground(Color.white);
            }

                buttonsPanel.add(button); 

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        //e is the action event and getSource is where the event comes from
                        //The event event is a click and the source of that click is the JButton
                        JButton button = (JButton) e.getSource();
                        //We need to get the symbol of the clicked button
                        String buttonValue = button.getText();
                        if(Arrays.asList(rightSymbols).contains(buttonValue)){
                            if(buttonValue.equals ("=")){
                                if(A != null){
                                    B = displayLabel.getText();
                                    long numA = (long)Double.parseDouble(A);
                                    long numB = (long)Double.parseDouble(B);

                                    if(operator == "+"){
                                        displayLabel.setText(removeZeroDecimal(numA+numB));
                                    }

                                    else if(operator == "-"){
                                        displayLabel.setText(removeZeroDecimal(numA-numB));
                                    }
                                    else if (operator == "×"){
                                        displayLabel.setText(removeZeroDecimal(numA*numB));
                                    }
                                    else if (operator == "÷"){
                                        displayLabel.setText(removeZeroDecimal(numA/numB));
                                    }
                                    clearAll();
                                }
                            }
                            else if("+-×÷".contains(buttonValue)){
                                if(operator == null){
                                    A = displayLabel.getText();
                                    displayLabel.setText("0");
                                    B = "0";
                                }
                                operator = buttonValue;
                            }
                        }
                        else if(Arrays.asList(topSymbols).contains(buttonValue)){
                            if(buttonValue.equals("AV")){
                                clearAll();
                                displayLabel.setText("0");
                            }

                            else if (buttonValue.equals("+/-")){
                                long numDisplay = (long)Double.parseDouble(displayLabel.getText());
                                numDisplay *= -1;
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }

                            else if(buttonValue.equals("%")){
                                long numDisplay = (long)Double.parseDouble(displayLabel.getText());
                                numDisplay /= 100;
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }

                            else if(buttonValue.equals("x^2")){
                                long numDisplay = (long)Double.parseDouble(displayLabel.getText());
                                numDisplay *= numDisplay; 
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }

                        }
                        else{ //Everything else including digts and '.' symbol
                            if(buttonValue.equals(".") ){
                                if(!displayLabel.getText().contains(buttonValue)){
                                    displayLabel.setText(displayLabel.getText() + buttonValue);
                                }
                            }

                            else if(buttonValue.equals( "√")){
                                double numDisplay = Double.parseDouble(displayLabel.getText());
                                numDisplay = Math.sqrt(numDisplay);
                                displayLabel.setText(removeZeroDecimal(numDisplay));
                            }

                            else if("0123456789".contains(buttonValue)){
                               if(displayLabel.getText() == "0"){
                                  displayLabel.setText(buttonValue); // We get the value instead of the value + 0
                               }
                               else{
                                    displayLabel.setText(displayLabel.getText() + buttonValue);
                               }
                            }
                        }
                    }
                });
                frame.setVisible(true);
            }
        }
        void clearAll(){
            A = "0";
            operator = null;
            B = null;
        }

        String removeZeroDecimal(double numDisplay){
            if(numDisplay % 1 == 0){
                return Double.toString((long) numDisplay);
            }
            return Double.toString(numDisplay);
        }
}
