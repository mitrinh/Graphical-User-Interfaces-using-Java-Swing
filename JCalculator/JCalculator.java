/**
 *   Name:     Trinh, Michael
 *   Project: 1
 *   Due:      Wednesday January 31, 2018
 *   Course:   cs-245-01-w18
 *   Description:
 *             Create a working simple integer calculator
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.*;

public class JCalculator implements ActionListener{
    // used for actionPerformed and calculation
    JButton button;
    JLabel display;
    int result = 0;
    String operator = null;
    String oldDisplayText;
    boolean operandSide = false; // false for left operand; true for right operand 
                                 // used so display replaces left operand with 
                                 // right operand digit
    JCalculator() {
        // create new frame container
        JFrame frame = new JFrame("Calculator");
        // specify grid layout
        frame.setLayout(new GridLayout(2,1));
        // set initial size
        frame.setSize(350,300);
        // terminate program when red X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set program icon to JCalculator.png
        Image icon = Toolkit.getDefaultToolkit().getImage("JCalculator.png");
        frame.setIconImage(icon);
        // puts calculator in the center of the screen
        frame.setLocationRelativeTo(null);
            
        // make a 4x4 panel for the calculator buttons
        JPanel panel = new JPanel(new GridLayout(4,4));
        
        // create 16 buttons for each button in a calculator
        JButton num0 = new JButton("0");
        JButton num1 = new JButton("1");
        JButton num2 = new JButton("2");
        JButton num3 = new JButton("3");
        JButton num4 = new JButton("4");
        JButton num5 = new JButton("5");
        JButton num6 = new JButton("6");
        JButton num7 = new JButton("7");
        JButton num8 = new JButton("8");
        JButton num9 = new JButton("9");
        JButton clear = new JButton("C");
        JButton equal = new JButton("=");
        JButton add = new JButton("+");
        JButton subtract = new JButton("-");
        JButton multiply = new JButton("*");
        JButton divide = new JButton("/");
        
        // set default button to = as ENTER
        frame.getRootPane().setDefaultButton(equal);
        
        // add actionlisteners for each button
        num0.addActionListener(this);
        num1.addActionListener(this);
        num2.addActionListener(this);
        num3.addActionListener(this);
        num4.addActionListener(this);
        num5.addActionListener(this);
        num6.addActionListener(this);
        num7.addActionListener(this);
        num8.addActionListener(this);
        num9.addActionListener(this);
        clear.addActionListener(this);
        equal.addActionListener(this);
        add.addActionListener(this);
        subtract.addActionListener(this);
        multiply.addActionListener(this);
        divide.addActionListener(this);
        
        // set display to 0
        display = new JLabel("0",JLabel.RIGHT);
        // set font to Courier, bold, and size 24
        display.setFont(new Font ("Courier", Font.BOLD, 24));
        // add a blue border of size 30 around display
        display.setBorder(BorderFactory.createLineBorder(Color.BLUE, 30));
        
        // add display to frame
        frame.add(display);
        
        // add panel to frame
        frame.add(panel);
        
        // add buttons to panel
        panel.add(num7);
        panel.add(num8);
        panel.add(num9);
        panel.add(divide);
        panel.add(num4);
        panel.add(num5);
        panel.add(num6);
        panel.add(multiply);
        panel.add(num1);
        panel.add(num2);
        panel.add(num3);
        panel.add(subtract);
        panel.add(clear);
        panel.add(num0);
        panel.add(equal);
        panel.add(add);
        
        // display frame
        frame.setVisible(true);
    } // end constructor
    
    public void actionPerformed(ActionEvent ae) {
        // only makes "C" button to work when in error state or copyright state
        if (display.getText().equals("Overflow") || 
                display.getText().equals("Div by 0") || 
                display.getText().equals("(c) 2018 Michael Trinh")) {
            if (ae.getActionCommand().equals("C")) {
                // restores current display when "C" pressed
                if (display.getText().equals("(c) 2018 Michael Trinh"))
                    display.setText(oldDisplayText);
                // clears error and resets calculator if error
                else {
                    display.setText("0");
                    result = 0;
                    operator = null;
                }
            } // end if
        } // end if        
        // display copyright via ctrl + shift + "C" <- mouse click
        else if ((ae.getActionCommand().equals("C")) && (ae.getModifiers() == 
                ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK + ActionEvent.MOUSE_EVENT_MASK)) { 
            oldDisplayText = display.getText();
            display.setText("(c) 2018 Michael Trinh");
        }
        // if not in error or copyright state work regularly
        else {
            // displays operand if it is a digit and has less than 10 digits
            if ((Character.isDigit(ae.getActionCommand().charAt(0))) && 
                    ((display.getText().length() < 10) || operandSide)) {
                // replaces number if displays 0 or if on right operand
                if (display.getText().equals("0") || operandSide) {
                    operandSide = false;
                    display.setText(ae.getActionCommand());
                }
                // else append display with digit
                else 
                    display.setText(display.getText() + ae.getActionCommand());
            } // end if

            // either sets operator or calculates expression
            switch (ae.getActionCommand()) {
                // clear button
                case "C":
                    display.setText("0");
                    break;
                // sets operator and sets result to the display as left operand
                case "+":
                case "-":
                case "*":
                case "/":
                    if (operator != null) {
                        operator = ae.getActionCommand();
                    }
                    else {
                        // Long data type will be used to prematurely test for overflow
                        if (Long.parseLong(display.getText()) > Integer.MAX_VALUE
                                || Long.parseLong(display.getText()) < Integer.MIN_VALUE) {
                            display.setText("Overflow");
                        } else {
                            // if in range, parse String->Integer, set operator, move to right side
                            result = Integer.parseInt(display.getText());
                            operator = ae.getActionCommand();
                        }
                    }
                    operandSide = true;
                    break;                      
                // calculates expression
                case "=":
                    // checks if operator is set
                    if (operator != null) {
                        switch (operator) {
                            // Long will be used to arithmatically test for overflow
                            case "+":
                                if ((result + Long.parseLong(display.getText())) > Integer.MAX_VALUE)
                                    display.setText("Overflow");
                                else
                                    result += Integer.parseInt(display.getText());
                                break;
                            case "-":
                                if ((result - Long.parseLong(display.getText())) < Integer.MIN_VALUE)
                                    display.setText("Overflow");
                                else
                                    result -= Integer.parseInt(display.getText());
                                break;
                            case "*":
                                if (((result * Long.parseLong(display.getText())) > Integer.MAX_VALUE) 
                                    || (result * Long.parseLong(display.getText())) < Integer.MIN_VALUE)
                                    display.setText("Overflow");
                                else
                                    result *= Integer.parseInt(display.getText());
                                break;
                            case "/":
                                // checks if dividing by 0
                                if (display.getText().equals("0")) {
                                    display.setText("Div by 0");
                                    break;
                                }
                                else {
                                    result /= Integer.parseInt(display.getText());
                                    break;    
                                }     
                        } // end switch
                        // displays result if no error
                        if (!(display.getText().equals("Div by 0") || 
                                display.getText().equals("Overflow"))) { 
                            display.setText(String.valueOf(result));
                            // next digit replaces number when finish calculation
                            operandSide = true;
                            // reset operator
                            operator = null;
                        } // end if
                    } // end if
            } // end switch
        } // end else
    } // end actionPerformed
    
    public static void main(String[] args) {
        System.out.println("M. Trinh's JCalculator");
        SwingUtilities.invokeLater(new Runnable() {                 
            public void run() {
                new JCalculator();
            } 
        });
    } // end main 
} // end JCalculator 
