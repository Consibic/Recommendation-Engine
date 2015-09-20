package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static project.CustInfo.*;
import static project.GUIUserOpen.*;

public class GUIPassFind {
    JFrame frame;//Declare JFrame
    JPanel contentPane;//Declare component panel
    JLabel info;//Declare component Label
    JButton confirm, back;//Declare component buttons
    JTextField ques, ans;//Declare component textfields
    Font f = new Font("Times New Roman", Font.BOLD, 20);//define font
    
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: name should not be null; 
     * Post: the user will be allowed to use safety questions to change the password.
     * @param name is the name of the user.
     */
    public GUIPassFind(String name) {
        frame = new JFrame("Safety Questions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS)); //To use BoxLayout to keep items in order
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //To create an empty border around items 
        info = new JLabel("Please answer the question given below: ");
        info.setFont(f); //To keep the font of texts on items the same
        info.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        info.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        /*To give the user three chances of answer their question, and the question at each time should not be the same*/
        int[] tempRan = random;
        if (countAnswer == 0) {
            ques = new JTextField(safetyQues[tempRan[0]], 10);
        } else if (countAnswer == 1) {
            ques = new JTextField(safetyQues[tempRan[1]], 10);
        } else {
            ques = new JTextField(safetyQues[tempRan[2]], 10);
        } ques.setEditable(false); //To prevent the textfield from being editted
        ques.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        ques.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        ans = new JTextField(40);
        ans.setEditable(true); //To allow the textfield to be editted
        ans.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        ans.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        confirm = new JButton("OK"); //To check the answer
        confirm.setFont(f); 
        confirm.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        confirm.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answer = ans.getText(); //for converting the answer of user to string
                /*To check the time that the user tried to answer questions*/
                if (countAnswer < 2) {
                    /*To check if the answer is correct*/
                    if (engine.CheckQues(name, tempRan[countAnswer], answer) == true) {
                        frame.setVisible(false);
                        GUIChangePass change = new GUIChangePass(name); //let the user to change the password
                    } else {
                        frame.setVisible(false);
                        countAnswer++;
                        GUIPassFind find = new GUIPassFind(name); //let the user to answer the next question if the user failed to answer one.
                    }
                } else {
                    if (engine.CheckQues(name, tempRan[countAnswer], answer) == true) {
                        frame.setVisible(false);
                        GUIChangePass change = new GUIChangePass(name); //let the user to change the password
                    } else {
                        engine.deleteCust(name);
                        engine.geneRan();
                        frame.setVisible(false); //close the page
                        GUIUserOpen open = new GUIUserOpen(false); //goes back to log-in page and mention that the account has been deleted
                    }
                }
            }
        });
        back = new JButton("Log In"); //To let the user go back to the main page
        back.setFont(f); 
        back.setAlignmentX(JLabel.CENTER_ALIGNMENT); 
        back.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                GUIUserOpen open = new GUIUserOpen(true); //goes back to log-in page
            }
        });
        contentPane.add(info);
        contentPane.add(ques);
        contentPane.add(ans);
        contentPane.add(confirm);
        contentPane.add(back);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true); //Open the pop-up window
        frame.setLocationRelativeTo(null); //Set the location of the pop-up window at the centre of the window of computer
    }
    private void runGUI(String name) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        GUIPassFind find = new GUIPassFind(name);
    }
}
