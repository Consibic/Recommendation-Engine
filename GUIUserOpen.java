package project;

import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;  // Using AWT event classes and listener interfaces
import static project.CustInfo.*;

// An AWT program inherits from the top-level container java.awt.Frame
public class GUIUserOpen extends Frame implements ActionListener {
    private Label lblUserID, lblWelcome, lblPassword, logInError, nameExist, delete, invalidPass; // Declare component Label
    private TextField tfID, tfpassword; // Declare component TextField
    private Button btnCount, btnSignUp, findPass;   // Declare component Button
    Font f = new Font("Times New Roman", Font.BOLD ,20);
    static CustInfo engine = new CustInfo(); 
    static SignIn in = new SignIn();
    static SignUp up = new SignUp();
    static String UserID, password; //the user name and password of the user
    
    /** 
     * Constructor to setup GUI components and event handling;
     * Pre: checkDirection should be false if the user is deleted by GUIPassFind, otherwise it should be true; 
     * Post: the sign in or sign up process will be completed.
     * @param checkDirection should be false if the user is deleted by GUIPassFind, otherwise it should be true
     */
    public GUIUserOpen (boolean checkDirection) {
        in.setArray(); //Get the information of books
        setLayout(new FlowLayout());
        //"super" Frame sets its layout to FlowLayout, which arranges the components
        //from left-to-right, and flow to next row from top-to-bottom.
        lblWelcome = new Label("  Welcome to the Recommendation Engine!");
        lblWelcome.setFont(f);
        add(lblWelcome);
        lblUserID = new Label("User ID:");  // construct Label
        lblUserID.setFont(f);
        add(lblUserID);                    // "super" Frame adds Label
        tfID = new TextField("", 35); // construct TextField
        tfID.setEditable(true);       // set to read-only
        add(tfID);                     // "super" Frame adds tfCount
        lblPassword = new Label("Password:");
        lblPassword.setFont(f);
        add(lblPassword);
        tfpassword = new TextField(30);
        tfpassword.setEditable(true);
        tfpassword.setEchoChar('*'); //to hide the password
        add(tfpassword);
        logInError = new Label("The userID does not exist or the password entered is wrong.");
        nameExist = new Label("The name you entered has already existed, please choose a new name.");
        delete = new Label("You have failed at least 3 times, your account has been deleted.");
        invalidPass = new Label("You should enter a password with the length between 8 and 20.");
        findPass = new Button("Find Your Password"); // construct Button
        findPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                String userID = tfID.getText();
                engine.geneRan();
                GUIPassFind find = new GUIPassFind(userID);
            }
        });
        btnCount = new Button("Sign In");   // construct Button
        add(btnCount);                    // "super" Frame adds Button
        btnCount.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                engine.getCust(); //get the information of customers from database
                UserID = tfID.getText();
                password = tfpassword.getText();
                /*To check if the user has changed the ID in order to avoid mis-deleting*/
                if (! UserID.equals(IDStore)) {
                    countLogIn = 0;
                    countAnswer = 0;
                    engine.geneRan();
                    IDStore = UserID;
                }
                /*To check the existence of the userID*/
                if (in.signIn(UserID, password).equalsIgnoreCase("none")) {
                    setVisible(false);
                    remove(delete);
                    remove(nameExist);
                    remove(findPass);
                    remove(invalidPass);
                    add(logInError); //To show the sign to mention that it is failed to sign in
                    setVisible(true);
                } else {
                    /*To check the time of trying to log in*/
                    if (countLogIn < 2) {
                        if (in.signIn(UserID, password).equalsIgnoreCase("true")) {
                            setVisible(false);
                            countLogIn = 0;
                            countAnswer = 0;
                            GUISearch s = new GUISearch();
                        } else {
                            setVisible(false);
                            remove(delete);
                            remove(nameExist);
                            remove(invalidPass);
                            add(findPass);
                            add(logInError); //To show the sign to mention that it is failed to sign in
                            countLogIn++; //To count the time the user has tried
                            setVisible(true);
                        }
                    } else {
                        if (in.signIn(UserID, password).equalsIgnoreCase("true")) {
                            setVisible(false);
                            countLogIn = 0;
                            countAnswer = 0;
                            GUISearch s = new GUISearch();
                        } else {
                            setVisible(false);
                            remove(nameExist);
                            remove(logInError);
                            remove(findPass);
                            remove(invalidPass);
                            add(delete);
                            engine.deleteCust(UserID); //To delete the user
                            countLogIn = 0; //initialise countLogIn for the next user
                            setVisible(true);
                        }
                    }
                }
            }
        });
        btnSignUp = new Button("Sign Up");
        add(btnSignUp);
        btnSignUp.addActionListener(this);
        setTitle("Recommendation Engine");  // "super" Frame sets title
        setSize(430, 250);        // "super" Frame sets initial window size
        setLocationRelativeTo(null);
        setVisible(true);         // "super" Frame shows
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we) {
                System.exit(0); //to create the button for closing the program
            }
        });
        if (checkDirection == false) {
            remove(nameExist);
            remove(logInError);
            remove(findPass);
            remove(invalidPass);
            add(delete); //To show that the ID is deleted because of the wrong answers to safety questions
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        UserID = tfID.getText();
        password = tfpassword.getText();
        if (password.length() >= 8 && password.length() <= 20) {
            if (up.signUp(UserID, password) == true){
                setVisible(false);
                GUISafetyQues que = new GUISafetyQues(); //To let new users to set up their safety questions
            } else {
                setVisible(false);
                remove(logInError);
                remove(findPass);
                remove(delete);
                remove(invalidPass);
                add(nameExist); //To show that the name has existed
                setVisible(true);
            }
        } else {
            setVisible(false);
            remove(logInError);
            remove(findPass);
            remove(delete);
            remove(nameExist);
            add(invalidPass); //To show that the password is invalid
            setVisible(true);
        }
    }
}
