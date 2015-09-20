package project;

import static project.CustInfo.*;

public class SignIn extends Sign {
    /**
     * To be used to sign in for customers
     * pre: customers' username and password
     * post: String "true" is returned when the username matchs up the password, "false" will be returned if not, and "none" will be returned if the name is wrong.
     * @param username is the name of user that user entered
     * @param password is the password that user entered
     * @return the situation of sign in will be returned
     */
    public String signIn(String username, String password){
        String tempName = null;//Store the username if username is correct
        String exist = "true"; //to show the situation of the user
        /*To search whether there is a username that is the same as the user prompts.*/
        for (int i = 0; i < custName.size(); i++){
            if (username.equals(custName.get(i))){
                tempName = username;
                logInIndex = i;
            }
        }
        /*When there is no such username*/
        if (tempName == null) {
            exist = "none";
        } else {
            /*When the user promts the correct username*/
            if (!password.equals(custPsw.get(logInIndex))){
                exist = "false";
            }
        }        
        return exist;
    }
}
