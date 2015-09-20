package project;

import java.io.*;
import static project.CustInfo.*;

public class Sign {
    /** 
     * To get the information about books from databases;
     * Pre: null; 
     * Post: the information of books will be got from databases.
     */
    public void setArray(){
        File title = new File("E:\\JavaFiles\\BookDatabaseName.txt"); //database of book name
        File author = new File("E:\\JavaFiles\\BookDatabaseAuthor.txt"); //database of the name of author
        File genre = new File("E:\\JavaFiles\\BookDatabaseTheme.txt"); //database of genre of book
        File rating = new File("E:\\JavaFiles\\Rating.txt"); //database of ratings of book
        
        FileReader in;
        BufferedReader readFile;
        String lineOfText; //to store information that read from files
        try {
            in = new FileReader(title);
            readFile = new BufferedReader(in);
            for (int i = 0; i < 55; i++) {
                lineOfText = readFile.readLine();
                if (lineOfText != null) {
                    bookTitle[i] = lineOfText;
                }//store titles of books in an array
            }
            readFile.close();
            in.close();
            in = new FileReader(author);
            readFile = new BufferedReader(in);
            for (int i = 0; i < 55; i++) {
                lineOfText = readFile.readLine();
                if (lineOfText != null) {
                    bookAuthor[i] = lineOfText;
                }//store author names into array
            } 
            readFile.close();
            in.close();
            in = new FileReader(genre);
            readFile = new BufferedReader(in);
            for (int i = 0; i < 55; i++) {
                lineOfText = readFile.readLine();
                if (lineOfText != null) {
                    bookGenre[i] = lineOfText;
                }//store genres of book into array
            } 
            readFile.close();
            in.close();
            in = new FileReader(rating);
            readFile = new BufferedReader(in);
            for (int j = 0; j < 55; j++){
                ratingDataBase[1][j]=Integer.parseInt(readFile.readLine());
            }//read and store reader numbers of books
            for (int i = 0; i < 55; i++){
                ratingDataBase[0][i] = Integer.parseInt(readFile.readLine());
            }//read and store average ratings
        } catch (FileNotFoundException e){
            System.err.println("FileNotFoundExceotion:" + e.getMessage());
        } catch (IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
    }
}
