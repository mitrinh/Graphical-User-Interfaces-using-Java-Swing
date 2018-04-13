//
//    Name:         Trinh, Michael
//    Homework:     2
//    Due:          Monday February 12,2018
//    Course:       cs-245-01-w18
//
//    Description:
//                  Displays a FontSampler that contains a list of fonts
//                  and a sample text which changes when text in textfield
//                  changes as well as the font in the list of fonts.
//

import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.*;

public class FontSampler {
    
    public FontSampler() {
        this(null);
    } // end default constructor
    
    public FontSampler(String arg) {
        /* create frame */
        JFrame frame = new JFrame("Font Sampler");
        frame.setLayout(new FlowLayout());
        frame.setSize(200,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        /* create scrollPane components */
        // get all fonts and put it in an array for list
        String fontList[] = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames();
        JList list = new JList(fontList);
        
        /* create frame components */ 
        JLabel label = new JLabel("Sample text:");
        // initialize textField to name
        JTextField textField = new JTextField("Michael Trinh",15);
        // set textField to command line argument
        if (arg != null)
            textField.setText(arg);
        JLabel fontLabel = new JLabel("Fonts:");
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(175,150));
        JLabel sampleText = new JLabel(textField.getText());
        // initialize font and color of sample text
        sampleText.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
        sampleText.setForeground(Color.BLUE);
        /* add key listener for text field */
        textField.addKeyListener(new KeyAdapter(){
            // keylistener methods
            public void keyPressed(KeyEvent ke) {
                sampleText.setText(textField.getText());
            }
            public void keyReleased(KeyEvent ke) {
                sampleText.setText(textField.getText());
            }
        });
        
        /* add selection listener for list of fonts */
        list.addListSelectionListener(e -> {
            // set text to new font
            sampleText.setFont(new Font(fontList[list.getSelectedIndex()],
                    Font.BOLD,18));
        });
        
        /* add frame components */
        frame.add(label);
        frame.add(textField);
        frame.add(fontLabel);
        frame.add(scrollPane);
        frame.add(sampleText);
        frame.setVisible(true);
    } // end constructor
    
    public static void main(String[] args) {
        System.out.println("M. Trinh's Font Sampler");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (args.length == 1)
                    new FontSampler(args[0]);
                else
                    new FontSampler();
            }
        }); 
    } // end main
} // end FontSampler
