
/**
 *   Name:     Trinh, Michael
 *   Homework: 1
 *   Due:      Monday January 22, 2018
 *   Course:   cs24501-w18
 *   Description:
 *             Creates a frame work for a tic-tac-toe game board
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.*;

public class JTicTacToe implements ActionListener {
    // used for actionPerformed
    private JButton button;
    // set turn order, where T=X and F=O
    private boolean turnOrder = true;
    
    JTicTacToe() {
        // create new frame container
        JFrame frame = new JFrame("Tic-Tac-Toe");
        // specify grid layout
        frame.setLayout(new GridLayout(3,3));
        // set initial size
        frame.setSize(300, 300);
        // terminate program when red X is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create 9 buttons for each button in tictactoe
        JButton topLeft = new JButton("blank");
        JButton top = new JButton("blank");
        JButton topRight = new JButton("blank");
        JButton left = new JButton("blank");
        JButton center = new JButton("blank");
        JButton right = new JButton("blank");
        JButton bottomLeft = new JButton("blank");
        JButton bottom = new JButton("blank");
        JButton bottomRight = new JButton("blank");
        
        // Add action listeners for the buttons
        topLeft.addActionListener(this);
        top.addActionListener(this);
        topRight.addActionListener(this);
        left.addActionListener(this);
        center.addActionListener(this);
        right.addActionListener(this);
        bottomLeft.addActionListener(this);
        bottom.addActionListener(this);
        bottomRight.addActionListener(this);
        
        // Add the buttons to frame
        frame.add(topLeft);
        frame.add(top);
        frame.add(topRight);
        frame.add(left);
        frame.add(center);
        frame.add(right);
        frame.add(bottomLeft);
        frame.add(bottom);
        frame.add(bottomRight);
        
        // display frame
        frame.setVisible(true);
    }// end constructor

    // Handle button events
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "blank": {
                // gets the source of the action and sets button to that source 
                button = (JButton)ae.getSource();
                if (turnOrder)
                    button.setText("X");
                else 
                    button.setText("O");
                // switch turn
                turnOrder = !turnOrder;
                break;
            } 
            // do nothing when button with X or O is clicked
            case "X":  
            case "O": 
        } // end switch
        //str = new StringBuilder(button.setText("X"));
    } // end actionPerformed
    
    public static void main(String[] args) {
        System.out.println("Mtrinh's Tic Tac Toe");
        SwingUtilities.invokeLater(new Runnable() {                 
            public void run() {
                new JTicTacToe();
            } 
        });
    }// end main
    
}// end JTicTacToe
