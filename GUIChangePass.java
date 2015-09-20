package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static project.GUIUserOpen.*;
import static project.CustInfo.*;

public class GUIChangePass {
    JLabel info, invalid;//Declare component label
    JPanel contentPane;//Declare component panel
    JFrame frame; //declare JFrame
    JPasswordField newPass;//Declare component passwordField
    JButton ok;//Declare component button
    Font f = new Font("Times New Roman", Font.BOLD, 20);
    
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: name should not be null; 
     * Post: the new password of the user will be set.
     * @param name is the name of the user.
     */
    public GUIChangePass(String name) {
        frame = new JFrame("Safety Questions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS)); //To use BoxLayout to keep items in order
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //To create an empty border around items 
        info = new JLabel("Please enter your new password: ");
        info.setFont(f); //To keep the font of texts on items the same
        info.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        info.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        invalid = new JLabel("You should enter a password with the length between 8 and 20.");
        invalid.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        invalid.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        newPass = new JPasswordField(40);
        newPass.setEditable(true); //To allow the textfield to be editted
        newPass.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        newPass.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        ok = new JButton("Save and Sign In"); //To check the answer
        ok.setFont(f); 
        ok.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        ok.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = newPass.getText(); //to convert the new password got from the textfield to string
                if (pass.length() >= 8 && pass.length() <= 20) {
                    engine.changePass(name, pass); //to change the password
                    frame.setVisible(false); //close the window
                    countLogIn = 0;
                    countAnswer = 0;
                    GUIUserOpen open = new GUIUserOpen(true); //to let the user use the new password to sign in
                } else {
                    frame.setVisible(false);
                    frame.add(invalid); //to show that the password is invalid
                    frame.setSize(500, 180); //change the size of the frame after adding the invalid label
                    frame.setVisible(true);
                }
            }
        });
        contentPane.add(info);
        contentPane.add(newPass);
        contentPane.add(ok);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true); //Open the pop-up window
        frame.setLocationRelativeTo(null); //Set the location of the pop-up window at the centre of the window of computer
    }
    private void runGUI(String name) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        GUIChangePass change = new GUIChangePass(name);
    }
}
