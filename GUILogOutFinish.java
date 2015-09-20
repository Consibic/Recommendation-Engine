package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static project.CustInfo.*;

public class GUILogOutFinish {
    JLabel finish;//Declare component label
    JButton close, logIn; //Declare component buttons
    JFrame frame;//Declare JFrame
    JPanel contentPane;//Declare component panel
    Font f = new Font("Times New Roman", Font.BOLD, 20);
    
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: null; 
     * Post: the user will be mentioned that the logging out process has completed.
     */
    public GUILogOutFinish() {
        frame = new JFrame("Log out Successful!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS)); //To use BoxLayout to keep items in order
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //To create an empty border around items
        finish = new JLabel("You have successfully logged out!");
        finish.setFont(f);
        finish.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        finish.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        close = new JButton("End the Program");
        close.setFont(f);
        close.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        close.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        logIn = new JButton("Main Page");
        logIn.setFont(f);
        logIn.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        logIn.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }
        });
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                countLogIn = 0;
                GUIUserOpen open = new GUIUserOpen(true);
            }
        });
        contentPane.add(finish);
        contentPane.add(close);
        contentPane.add(logIn);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    private void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        GUILogOutFinish finish = new GUILogOutFinish();
    }
}
