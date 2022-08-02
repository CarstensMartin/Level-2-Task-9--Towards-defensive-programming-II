package passwordAuthenticator;

//Import all the packages required
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PasswordAuthenticator {

	public static void main(String[] args) {

		// File location of where user names & passwords are saved
		String fileLocation = "passwordDocument.txt";
		// Method to read the text from file
		String Passwords = PasswordTextFileReader(fileLocation);

		// Ask user for their name(userName)
		String userName = JOptionPane.showInputDialog("What is your Name?");

		// Ask until user enters a valid user name and not the cancel button
		while (userName == null) {
			// Ask user for their name(userName)
			userName = JOptionPane.showInputDialog("Have to completer before going to next step. \nWhat is your Name?");
		}

		// Ask user for password & confirm password
		String userPassword = JOptionPane.showInputDialog("Please enter a password?");
		String userPasswordConfirm = JOptionPane.showInputDialog("Please re-enter the same password?");

		// Run the Method to test if it is a valid password
		boolean passwordSuccessfull = PasswordTest(userPassword, userPasswordConfirm);

		// If not a valid Password - Keep asking user until valid password is given that
		// meets minimum requirements.
		while (passwordSuccessfull == false) {

			// Ask for password & confirm password
			userPassword = JOptionPane
					.showInputDialog("Password minimum requirements not met. \nPlease enter a password?");
			userPasswordConfirm = JOptionPane.showInputDialog("Please re-enter the same password?");

			// Run the Method to test if it is a valid password and if the While loop should
			// continue to run.
			passwordSuccessfull = PasswordTest(userPassword, userPasswordConfirm);
		}

		// If password passes the authentication process
		if (passwordSuccessfull == true) {

			// Create a new String with the user name of the person and the password that is
			// authenticated
			String newPasswordLine = userName + ", " + userPassword;

			// Add to the existing passwords string the new person and password
			Passwords += newPasswordLine;
			// Add a new line
			Passwords += "\r\n";

			// Run method to create the files where the passwords are stored in
			FileCreatorMethod(fileLocation, Passwords);
		}
	}

	// Method to test if the Password authenticating was successful
	public static boolean PasswordTest(String userPassword, String userPasswordConfirm) {

		// Start with false it does not contain any numbers
		boolean containsNumber = false;

		// Open a boolean statement for the testing of the 1st and 2nd password matches
		// Start with false until proven true
		boolean passwordSuccessfull = false;

		// If the user clicks the cancel button with null input
		if (userPassword == null) {

			// Display as an alert with a prompt to the user the message
			JOptionPane.showMessageDialog(new JFrame(), "The password has a Null value", "Dialog",
					JOptionPane.ERROR_MESSAGE);
			// Display console
			System.out.println("\nThe password has a Null value\n");

			// Return the false statement for loop to keep running
			return passwordSuccessfull;

		} else {
			// Run a for loop to test each character if it is a number
			for (int x = 0; x < userPassword.length(); x++) {
				// Convert the character to a number
				int asciiValueChar = userPassword.charAt(x);

				// Use the ascii value of the character to determine if the character is a
				// number.
				if ((48 <= asciiValueChar) && (asciiValueChar <= 57)) {
					// If a number is contained, change the false statement to a true statement
					containsNumber = true;
				}
			}

			// If the 1st and 2nd password is exactly the same & it contains a number
			if ((userPassword.equals(userPasswordConfirm)) && (containsNumber == true)) {
				// Update passwordSuccessfull to True - To be used as Return Statement
				passwordSuccessfull = true;
				// Display on console that it was successful
				System.out.println("\nYour Password is successful\n");
			}

			// Else if criteria is met.
			else {
				// Display password was unsuccessful
				System.out.println("\nYour Password is Unsuccessful:");

				// Update passwordSuccessfull to False - To be used as Return Statement
				passwordSuccessfull = false;

				// Determine which criteria was not met, and let the user know.
				if (containsNumber == false) {
					// Message to user that it did not contain a number
					String message = "- Your password does not contain a number.";
					// Display as an alert with a prompt to the user the message
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
					// Display in the console the message
					System.out.println(message);
				}
				if (!(userPassword.equals(userPasswordConfirm))) {
					// Message to user that the 2 passwords did not match
					String message = "- First password and second password does not match.";
					// Display as an alert with a prompt to the user the message
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
					// Display in the console the message
					System.out.println(message);
				}
			}
			// Return True or False if the While loop should continue running
			return passwordSuccessfull;
		}
	}

	// Method to read the current Names of user and passwords saved
	public static String PasswordTextFileReader(String fileLocation) {
		// Declare the String to save the existing files in
		String Passwords = "";

		// Try Scan the document
		try {
			File file = new File(fileLocation);
			Scanner lineScan = new Scanner(file);

			// While there is a next line continue scanning
			while (lineScan.hasNextLine()) {

				// Add to the String the line
				Passwords += lineScan.nextLine();
				// Add (\r\n) to indicate it should form a new line when writing the file later
				Passwords += "\r\n";
			}
			// Close the line scanner
			lineScan.close();
		}
		// If problem with executing scanner, give error message
		catch (FileNotFoundException e) {
			System.out.println("Error reading file with previous usernames and passwords");
		}

		// Return the String with data of previous user names and passwords saved
		return Passwords;
	}

	// Method to create files - No return statement is required
	public static void FileCreatorMethod(String writingFileLocation, String outputMessage) {

		try {
			// Over Write the existing password file
			Formatter newFileWrite = new Formatter(writingFileLocation);

			// Write the new String text inside
			newFileWrite.format("%s", outputMessage);
			// Close the file writer
			newFileWrite.close();

			// Display that writing was successful
			System.out.println("Your password have been successfully saved to the Text file.");
		}

		// If there was a problem creating the file - display the below error message
		catch (FileNotFoundException e) {

			// Display on console that there was a problem writing the file
			System.out.println("Your password was unsuccessfully saved to a Text File");
			e.printStackTrace();
		}
	}
}
