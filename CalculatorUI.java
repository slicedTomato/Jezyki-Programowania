import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI extends JFrame{

    Calculator calculator = new Calculator();
    public JTextField display;

    public CalculatorUI(){
        setTitle("Kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 500);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        display.setText(null);

//        JPanel topPanel = new JPanel();
//        topPanel.setLayout(new GridLayout(1, 2, 5, 5));
//
//        JButton widebutton1 = new JButton("C");
//        JButton widebutton2 = new JButton("Del");
//
//        widebutton2.setFont(new Font("Arial", Font.PLAIN, 18));
//        widebutton2.addActionListener(new ButtonClickListener());
//
//        widebutton1.setFont(new Font("Arial", Font.PLAIN, 18));
//        widebutton1.addActionListener(new ButtonClickListener());
//
//        topPanel.add(widebutton1);
//        topPanel.add(widebutton2);

//        add(topPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "C", "sqrt", "sqr", "Del",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels){
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e){

            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (buttonText.equals("=")){
                if (calculator.A == null){return;}

                if (calculator.operand != "=") {
                    calculator.save(Double.parseDouble(display.getText()));
                }

                if(calculator.B == 0 && calculator.operand.equals("/")){
                    display.setText("Nie dziel przez zero lol");
                    calculator.clearAll();
                    return;
                }

                if(calculator.A != null && calculator.B != null && calculator.operand != null) {
                    calculator.performCalc();
                    display.setText(calculator.resultString(calculator.result));
                } else if (calculator.A != null && calculator.operand == "sqrt") {
                    calculator.performCalc();
                    display.setText(calculator.resultString(calculator.result));
                } else if (calculator.A != null && calculator.operand == null){
                    display.setText(calculator.resultString(calculator.A));
                }

                calculator.setOperand("=");

            }

            if (buttonText.equals("+")){
                if (display.getText().isEmpty() || !isConvertible(display.getText())){return;}

                if (calculator.A == null) {
                    calculator.save(Double.parseDouble(display.getText()));
                }
                calculator.performCalc();
                calculator.setOperand(buttonText);
                display.setText("");
            }

            if (buttonText.equals("-")){
                if (display.getText().isEmpty() || !isConvertible(display.getText())){return;}

                if (calculator.A == null) {
                    calculator.save(Double.parseDouble(display.getText()));
                }
                calculator.performCalc();
                calculator.setOperand(buttonText);
                display.setText("");
            }

            if (buttonText.equals("*")){
                if (display.getText().isEmpty() || !isConvertible(display.getText())){return;}

                if (calculator.A == null) {
                    calculator.save(Double.parseDouble(display.getText()));
                }
                calculator.performCalc();
                calculator.setOperand(buttonText);
                display.setText("");
            }

            if (buttonText.equals("/")){
                if (display.getText().isEmpty() || !isConvertible(display.getText())){return;}

                if (calculator.A == null) {
                    calculator.save(Double.parseDouble(display.getText()));
                }
                calculator.performCalc();
                calculator.setOperand(buttonText);
                display.setText("");
            }

            if (buttonText.equals("sqrt")){
                if (display.getText().isEmpty() || !isConvertible(display.getText())){return;}

                if (calculator.A == null) {
                    calculator.save(Double.parseDouble(display.getText()));
                }
                calculator.performCalc();
                calculator.setOperand(buttonText);
                calculator.performCalc();
                calculator.setOperand(null);
                display.setText(calculator.resultString(calculator.result));
            }

            if (buttonText.equals("sqr")){
                if (display.getText().isEmpty() || !isConvertible(display.getText())){return;}

                if (calculator.A == null) {
                    calculator.save(Double.parseDouble(display.getText()));
                }
                calculator.performCalc();
                calculator.setOperand(buttonText);
                calculator.performCalc();
                calculator.setOperand(null);
                display.setText(calculator.resultString(calculator.result));
            }

            if (buttonText.equals("C")){
                calculator.clearAll();
                display.setText("");
            }

            if (buttonText.equals("Del")){
                if (!isConvertible(display.getText())){return;}

                 String text = display.getText();
                 if(!text.isEmpty()){
                 text = text.substring(0, text.length() - 1);
                 display.setText(text);
                 }
            }

            if (buttonText.equals(".")){
                if (!display.getText().isEmpty() && !isConvertible(display.getText())){return;}

                if (display.getText().isEmpty()){
                    display.setText("0.");
                } else {
                    if (!display.getText().contains(String.valueOf('.')) && calculator.operand != "=") {
                        display.setText(display.getText() + buttonText);
                    }
                }
            }

            if (isInt(buttonText)){
                if (!display.getText().isEmpty() && !isConvertible(display.getText())){return;}

                if (buttonText.equals("0") && display.getText() != null && display.getText().equals("0")){return;}
                if (calculator.operand != null && calculator.operand.equals("=")){return;}

                display.setText(display.getText() + buttonText);
            }
        }

        private static boolean isConvertible(String string){
            try{
                double result = Double.parseDouble(string);
                return true;
            } catch (NumberFormatException e){
                return false;
            }
        }

        private static boolean isInt(String str){
            try{
                int intValue = Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e){
                return false;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {

        CalculatorUI calc = new CalculatorUI();
    }
}
