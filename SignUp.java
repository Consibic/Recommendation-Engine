package project;

import java.io.*;
import java.util.ArrayList;

public class SignUp extends Sign{
    static File custData = new File("E:\\JavaFiles\\CustomerDatabase.txt");
    /**
     * To add new customer into the database;
     * Pre: user and psw should not be null;
     * Post: the new customer's name, password and original ranking for books (0) will be write to the file of database.
     * @param name is the customer's name;
     * @param psw is the customer's password.
     */
    public void addCust(String name, String psw) {
        FileWriter in;
        BufferedWriter writeFile;
        try {
            in = new FileWriter(custData, true);
            writeFile = new BufferedWriter(in);
            writeFile.write(name); //To write the name of the customer into the database
            writeFile.newLine(); 
            writeFile.write(psw); //To write the password of the customer to the next line after the name in the database
            writeFile.newLine();
            /*To create 55 0s to represent the original ranking for books.*/
            for (int i = 0; i < 55; i++) {
                writeFile.write("0 ");
            }
            writeFile.newLine();
            writeFile.close();
            in.close();
        } catch (IOException e) {
            System.out.println("The file is not possible to be written.");
            System.out.println(e.getMessage());
        }
    }
    /**
     * To let new customer to sign up;
     * Pre: user and psw should not be null;
     * Post: the new customer will be given to addCust() for adding to database, true will be returned if the customer can be created.
     * @param name is the customer's name.
     * @param psw is the customer's password.
     * @return will be "true" if the customer can be successfully created, otherwise it will be "false".
     */
    public boolean signUp(String name, String psw) {
        ArrayList<String> custName = new ArrayList<>();
        FileReader out;
        BufferedReader readFile;
        String nameGot = null; //To randomly reserve the name got from the database
        try {
            out = new FileReader(custData);
            readFile = new BufferedReader(out);
            /*To read the information of customers from the database and add it into arraylists*/
            do {
                nameGot = readFile.readLine();
                custName.add(nameGot);
            } while (nameGot != null);
            custName.remove(custName.size() - 1); //remove the null string that is got at the end
            readFile.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file does not exist.");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("The file is not possible to be read.");
            System.out.println(e.getMessage());
        }
        int indexName = -1; //initialise the index of user name
        /*To check if the name user chose has existed*/
        for (int i = 0; i < custName.size(); i++) {
            if (name.equals(custName.get(i))) {
                indexName = i;
                break;
            }
        }
        if (indexName != -1) {
            return false; 
        } else {
            return true;
        }
    }
}
