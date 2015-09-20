package project;

import java.awt.*;
import java.awt.event.*;
import static project.CustInfo.*;

public class GUISearch extends Frame implements ActionListener{
    Label lblTitle,lblInfo,warning;  //Declare component Label
    TextField tfTitle;  //Declare coponent TextField
    Button btSearch;  //Declare component Button
    Font f = new Font("F",Font.BOLD ,20);  //Define font F
    
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: null; 
     * Post: the book that the user needed will be found in the database.
     */
    public GUISearch(){
        setLayout(new FlowLayout());  //arranges the components from left-to-right, and flow to next row from top-to-bottom.
        lblTitle = new Label("Search a book");  //construct Label
        lblTitle.setFont(f);  //change font
        add(lblTitle);  //adds Label to the frame
        lblInfo = new Label("(enter the title of a book in the box below)"); //cosntruct Label
        lblInfo.setFont(f);  //change font
        add(lblInfo);  // adds to the frame
        tfTitle = new TextField(50);  //construct text filed
        tfTitle.setEditable(true);  //set to editable
        add(tfTitle);  //adds to the frame
        btSearch = new Button("Search");  //construct Button
        add(btSearch);  //adds to the frame
        setTitle("Search");  //set title
        setSize(450,200);  //set initial window size
        setLocationRelativeTo(null);  //set location of the window center
        setVisible(true);  //show the frame
        warning = new Label("The book you tried to search dose not exist."); //construct Label
        btSearch.addActionListener(this);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        }); //set the X button to stop the program
    }
    @Override
    public void actionPerformed(ActionEvent evt){
        String Title = tfTitle.getText();
        int index = -1;
        int custrating;
        for (int i = 0; i < 55; i++){
            if (Title.equalsIgnoreCase(bookTitle[i])) {
                index = i;
                break; 
            }  
        } //find the index of the book
        if (index != -1) {
            String ratings = custRating.get(logInIndex);
            custrating = ratings.charAt(index*2)-48;  //get past rating
            GUIBookInfo info = new GUIBookInfo(bookTitle[index], bookAuthor[index], bookGenre[index], ratingDataBase[0][index], ratingDataBase[1][index], custrating);  //construct GUIBookInfo
            setVisible(false);  //close this frame
        } else {
            add(warning);  //add Label to the frame
            setVisible(true);  //still show the frame
        }
    }
}

