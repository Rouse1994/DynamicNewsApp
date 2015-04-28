package rouse.dynamicnewsapp;

/**
 * Created by Rouse on 4/27/2015.
 */
public class UserDatabaseTest extends junit.framework.TestCase  {

    public void AddFriendTest() throws Exception {
        String output = UserDatabase.AddFriend("Admin", "NoSuchUser");
        assertFalse(output.equals("NoSuchUser is now your friend.")); //NoSuchUser does not exist
    }

    public void makeUserTest() throws Exception {
        String output = UserDatabase.makeUser(new User("Admin", "Password"));
        assertEquals("New user added", output); //Admin already exists, should fail.
    }

    public void submitFeedbackTest() throws Exception {
        String output = UserDatabase.submitFeedback("");
        assertFalse(output.equals("Feedback submitted.")); //Feedback message is blank, this will return a error on server
    }

    public void shareTest() throws Exception {
        String output = UserDatabase.Share("Admin2", "ArticleName");
        assertFalse(output.equals("articleName has been shared with Admin2.")); //should fail since Admin2 doesn't exist
    }

}
