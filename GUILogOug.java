package project;
 
import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import static project.CustInfo.*;

// An AWT program inherits from the top-level container java.awt.Frame
public class GUILogOut extends Frame implements ActionListener {
    Label ThankYou, YourRating, Average, Suggest;    // Declare component Label
    Button btnCount, btnCount2, rec1, rec2, rec3;   // Declare component Button
    TextField custRate, avgRate; // Declare output TextField
    int count = 0;     // Counter's value
 
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: CustomerRating and AverageRating should not be null, each index of booklist should not be null; 
     * Post: the information of the book will be displayed.
     * @param CustomerRating is the rate that the user has given.
     * @param AverageRating is the average rate of this book.
     * @param booklist is the array for storing books that will be recommended.
     */
    public GUILogOut (String CustomerRating , String AverageRating, String[] booklist) {
        setLayout(new FlowLayout());
        // "super" Frame sets its layout to FlowLayout, which arranges the components
        //  from left-to-right, and flow to next row from top-to-bottom.
        ThankYou = new Label("Thank you for your rating!");  // construct Label
        add(ThankYou);                    // "super" Frame adds Label
        YourRating = new Label("Your rating is:");  // construct Label
        add(YourRating);                   
        custRate = new TextField(CustomerRating, 5); // construct TextField for displaying the rate that the customer gave
        custRate.setEditable(false);       // set to read-only
        add(custRate);                     
        Average = new Label("Average Rating:");
        add(Average);                     
        avgRate = new TextField(AverageRating, 5); // construct TextField for displaying the average rate
        avgRate.setEditable(false);     
        add(avgRate);
        Suggest = new Label("According to your rating, we suggest the following books for you: ");
        add(Suggest);  // "super" Frame adds tfCount
        rec1 = new Button("1. " + booklist[0]); //construct button for displaying recommended books and let the user to know more about the book  
        add(rec1);
        rec1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
                searchBook(booklist[0]); //Search the book and lead the button to the information displaying of this book
            }
        });
        rec2 = new Button("2. " + booklist[1]); 
        add(rec2);
        rec2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
                searchBook(booklist[1]);
            }
        });
        rec3 = new Button("3. " + booklist[2]); 
        add(rec3);
        rec3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
                searchBook(booklist[2]);
            }
        });
        btnCount = new Button("Return to search");   // construct Button
        add(btnCount);                    // "super" Frame adds Button\
        btnCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                setVisible(false);
                GUISearch search1 = new GUISearch();
            }
        });
        btnCount2 = new Button("Log out");   // construct Button
        add(btnCount2);                    // "super" Frame adds Button
        btnCount2.addActionListener(this);
        // Clicking Button source fires ActionEvent
        // btnCount registers this instance as ActionEvent listener
        setTitle("Recommendation");  // "super" Frame sets title
        setSize(400, 250);        // "super" Frame sets initial window size
        setVisible(true);         // "super" Frame shows
        setLocationRelativeTo(null); //set the original location of the pop-up window at the centre of the window of computer
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
    }    
    @Override
    /** ActionEvent handler - Called back upon button-click. */
    public void actionPerformed(ActionEvent evt) {
        setVisible(false);
        GUILogOutFinish app = new GUILogOutFinish();
    }
    /**
     * To search the books required and enter the information pages for those books,
     * Pre: name should not be null and should be the book name in BookTitle[],
     * Post: the page of information of the book will be poped-up.
     * @param name is the name of book required.
     */
    public void searchBook(String name) {
        int index = -1;
        for (int i = 0; i < 55; i++) {
            if (name.equalsIgnoreCase(bookTitle[i])) {
                index = i;
                break; 
            }  
        } 
        int custrating = custRating.get(logInIndex).charAt(index*2)-48; 
        GUIBookInfo info = new GUIBookInfo(bookTitle[index], bookAuthor[index], bookGenre[index], ratingDataBase[0][index], ratingDataBase[1][index], custrating);
        setVisible(false);
    }
}
