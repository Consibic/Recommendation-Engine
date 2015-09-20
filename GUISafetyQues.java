package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static project.GUIUserOpen.*;
import static project.CustInfo.*;

public class GUISafetyQues {
    JLabel ques1, ques2, ques3, info, warning, nullWarning; //Declare component Label
    JPanel contentPane; //Declare component panel
    JFrame frame; //Declare JFrame
    JButton finish, setNull; //Declare component buttons
    JTextField as1, as2, as3; //Declare component textfields
    Font f = new Font("Times New Roman", Font.BOLD, 15); //define font
    
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: null; 
     * Post: the safety questions will be set and stored into database.
     */
    public GUISafetyQues() {
        frame = new JFrame("Safety Questions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS)); //To use BoxLayout to keep items in order
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //To create an empty border around items 
        ques1 = new JLabel(safetyQues[0]);
        ques1.setFont(f); //To keep the font of texts on items the same
        ques1.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        ques1.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        ques2 = new JLabel(safetyQues[1]);
        ques2.setFont(f);
        ques2.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        ques2.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        ques3 = new JLabel(safetyQues[2]);
        ques3.setFont(f);
        ques3.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        ques3.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        info = new JLabel("Please answer the three safety questions below: ");
        info.setFont(f);
        info.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        info.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        as1 = new JTextField(40);
        as1.setEditable(true); //To allow the textfield to be editted
        as2 = new JTextField(40);
        as2.setEditable(true);
        as3 = new JTextField(40);
        as3.setEditable(true);
        warning = new JLabel("Please provide an unique answer to each question.");
        warning.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        warning.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        nullWarning = new JLabel("The answers should be longer than 3 characters.");
        nullWarning.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        nullWarning.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        finish = new JButton("Save");
        finish.setFont(f);
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans1 = as1.getText();
                String ans2 = as2.getText();
                String ans3 = as3.getText();
                if (ans1.length() < 3 || ans2.length() < 3 || ans3.length() < 3) {
                    frame.setVisible(false);
                    frame.remove(warning);
                    frame.add(nullWarning);
                    frame.setSize(480, 310);
                    frame.setVisible(true);
                } else {
                    if (ans1.equals(ans2) || ans2.equals(ans3) || ans1.equals(ans3)) {
                        frame.setVisible(false);
                        frame.remove(nullWarning);
                        frame.add(warning);
                        frame.setSize(480, 310);
                        frame.setVisible(true);
                    } else {
                        up.addCust(UserID, password); //To ensure that the user finished safety questions and add the customer in
                        engine.SafetyQuestion(ans1, ans2, ans3); //To write the answers into file
                        frame.setVisible(false);
                        GUIUserOpen open = new GUIUserOpen(true); //Go to the main page to let the new user log in again
                    }
                }
            }
        });
        finish.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        finish.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        setNull = new JButton("Clean");
        setNull.setFont(f);
        setNull.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false); //Close the current window
                GUISafetyQues que = new GUISafetyQues(); //Create a new window for enter the answers again
            }
        });
        setNull.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        setNull.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        contentPane.add(info);
        contentPane.add(ques1);
        contentPane.add(as1);
        contentPane.add(ques2);
        contentPane.add(as2);
        contentPane.add(ques3);
        contentPane.add(as3);
        contentPane.add(finish);
        contentPane.add(setNull);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true); //Open the pop-up window
        frame.setLocationRelativeTo(null); //Set the location of the pop-up window at the centre of the window of computer
    }
    private void runGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        GUISafetyQues que = new GUISafetyQues();
    }
}
