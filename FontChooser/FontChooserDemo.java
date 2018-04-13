//
// Name: Trinh, Michael
// Project: 2
// Due: March 7,2018
// Course: CS-245-01-w18
//
// Description:
//          Creates a dialog JFontChooser that can be called and has name, 
//          style, size, and color 
//
import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.*;

public class FontChooserDemo {
    // initialize the font and color
    Font font = new Font("Courier New",Font.ITALIC,28);
    Color color = Color.RED;
        FontChooserDemo() {
            // create frame
            JFrame parent = new JFrame("Font Chooser Demo");
            parent.setLayout(new FlowLayout());
            parent.setSize(400,300);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // create button to open font chooser
            JButton button = new JButton("Font Chooser");
            button.setPreferredSize(new Dimension(350,250));
            button.setFont(new Font("Arial",Font.BOLD,48));
            // ok to set to same char mneumonic as the one in dialog since modal
            button.setMnemonic('F');
            JFontChooser jfontc = new JFontChooser();
            button.addActionListener(e ->{
                jfontc.setDefault(font);
                jfontc.setDefault(color);
                if(jfontc.showDialog(parent)){
                    // save font when exiting dialog
                    font = jfontc.getFont();
                    color = jfontc.getColor();
                }
            });
            parent.add(button);
            parent.setLocationRelativeTo(null);
            parent.setVisible(true);
        }
        
        public static void main(String[] args) {
        System.out.println("M. Trinh's FontChooserDemo");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FontChooserDemo();
            }
        }); 
    } // end main
} // end FontChooserDemo
