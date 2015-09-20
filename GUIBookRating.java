package project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static project.GUIUserOpen.engine;
import static project.GUIBookInfo.*;

public class GUIBookRating {
    JFrame frame;//Declare JFrame
    JPanel contentPane;//Declare component panel
    JLabel label;//Declare component label
    JComboBox ratingList;//Declare component combobox
    JButton submit, rankStop;//Declare component buttons
    Font f = new Font("Times New Roman", Font.BOLD, 20);
    
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: name, author and theme should not be null, avg and pop should not be 0; 
     * Post: the user will successfully rate the book.
     * @param bookName is the name of the book. 
     * @param author is the author of the book.
     * @param genre is the genre of the book.
     * @param avg is the average score of the book.
     * @param pop is the population of people that have rated the book.
     */
    public GUIBookRating(String bookName, String author, String genre, int avg, int pop) {
        frame = new JFrame("Book Rating");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS)); //To use BoxLayout to keep items in order
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //To create an empty border around items
        label = new JLabel("Please give rank to the book " + bookName + ":");
        label.setFont(f); //To keep the font of texts on items the same
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT); //To line the items up at the centre of the window
        label.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        submit = new JButton("Submit");
        submit.setFont(f);
        submit.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        submit.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        rankStop = new JButton("Not Now");
        rankStop.setFont(f);
        rankStop.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        rankStop.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        String[] list = {"1 Awful >_<", "2 Bad T_T", "3 So So -_-", "4 Good ^_^", "5 Perfect ^3^"}; //To create a list for user to choose rate from 1 to 5
        ratingList = new JComboBox(list);
        ratingList.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        ratingList.setSelectedIndex(0); //Set the original rate to be 1
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rankGet = (String)ratingList.getSelectedItem();
                /*To transfer the rate user chosen in the combobox to the number of rankings in the database*/
                if (rankGet.equalsIgnoreCase("1 Awful >_<")) {
                    engine.rankGive(bookName, 1);
                    GUILogOut out = new GUILogOut(String.valueOf(1), String.valueOf(avg), engine.customerBasedRec(bookName));
                } else if (rankGet.equalsIgnoreCase("2 Bad T_T")) {
                    engine.rankGive(bookName, 2);
                    GUILogOut out = new GUILogOut(String.valueOf(2), String.valueOf(avg), engine.customerBasedRec(bookName));
                } else if (rankGet.equalsIgnoreCase("3 So So -_-")) {
                    engine.rankGive(bookName, 3);
                    GUILogOut out = new GUILogOut(String.valueOf(3), String.valueOf(avg), engine.customerBasedRec(bookName));
                } else if (rankGet.equalsIgnoreCase("4 Good ^_^")) {
                    engine.rankGive(bookName, 4);
                    GUILogOut out = new GUILogOut(String.valueOf(4), String.valueOf(avg), engine.bookBasedRec(bookName));
                } else {
                    engine.rankGive(bookName, 5);
                    GUILogOut out = new GUILogOut(String.valueOf(5), String.valueOf(avg), engine.bookBasedRec(bookName));
                }
                frame.setVisible(false); //Close the window after going to the next step
            }
        });
        rankStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIBookInfo info = new GUIBookInfo(bookName, author, genre, avg, pop, custrating); //To review the information of the book if the user do not want to rate the book
                frame.setVisible(false);
            }
        });
        contentPane.add(label);
        contentPane.add(ratingList);
        contentPane.add(submit);
        contentPane.add(rankStop);
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true); //Open the pop-up window
        frame.setLocationRelativeTo(null);  //Set the location of the pop-up window at the centre of the window of computer
    }
    private void runGUI(String bookName, String author, String genre, int avg, int pop) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        GUIBookRating rate = new GUIBookRating(bookName, author, genre, avg, pop);
    }
}
