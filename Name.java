
import javax.swing.JOptionPane;


	public class Name {
		
	  private String firstName;
	  private char middleInitial;
	  private String lastName;
	  
	public Name() {
		getName();
	}
	
	public Name(String firstName) {
		
	}
	
	public Name(String firstName, char middleInitial, String lastName) {
		
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		 
	//capitalize the first letter of first name and leaves the rest in lower case
		firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
		
		this.firstName = firstName;
	}

	public char getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(char middleInitial) {

		//checks to see if the player has a middle name or not
		if(middleInitial == 'X') {
			middleInitial = ' ';
			
		}
		
		this.middleInitial = middleInitial;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {

	//capitalize the first letter of last name and leaves the rest in lower case
		lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
		
		this.lastName = lastName;
	}

	public String toString() {
		
		String message = "";

		//if middle name is inexistent, print a blank space
		if(middleInitial == ' ') {
			message += firstName + middleInitial + lastName;
		}
		else {//if exists, print first letter followed by a dot
			message += firstName + " " + middleInitial +"." + " " + lastName;
		}
		
		return message;
	}

	public void getName() {
		
		firstName = validateFirstName(firstName);
		setFirstName(firstName);
		
		middleInitial = validateMiddleInitial(middleInitial);
		setMiddleInitial(middleInitial);
		
		lastName = validateLastName(lastName);
		setLastName(lastName);
	
	}
	//checks every char in a string with the regex expression and makes sure all of them are alphabet letters
	//I added the ' sign and 'space' to be a valid char because many names use it
	public static boolean isStringOnlyAlphabet(String name) { 
		
		return ((!name.equals("")) && (name != null) && (name.matches("^[a-zA-Z'\' ']*$"))); 
	} 
	
	//validate first name
	public static String validateFirstName(String firstName) {
		
		String message = "What is your first name: ";
		boolean isOnlyAlphabet = true;
		
		 do{
			 try {//catches if user clics cancel or X
				 firstName = JOptionPane.showInputDialog(message);
				 
			//checks if all elements of the string are letters
				isOnlyAlphabet = isStringOnlyAlphabet(firstName);
			
					//lets the user know they did not enter letters
					if(isOnlyAlphabet == false) {
						JOptionPane.showMessageDialog(null, "Please enter a name that contains only letters.");
					}
					else {
						//if they entered space as the first letter, make them enter the name again
						if(firstName.charAt(0) == ' ') {
							JOptionPane.showMessageDialog(null, "First letter of your name cannot be a space");
							isOnlyAlphabet = false;
						}
					}
		 		}catch(java.lang.NullPointerException e) {
		 			JOptionPane.showMessageDialog(null, "Please enter a valid selection");
		 			isOnlyAlphabet = false;
				 }

		}while(isOnlyAlphabet == false);
		
		return firstName;
	}
	
	//validate middleInitial
	public static char validateMiddleInitial(char middleInitial) {
		
		String message ="What is your middle name: Enter X if inexistent ";
		String tempString = "";
		boolean isOnlyAlphabet = true;
		
		 do{
			 try {//catches if user clics cancel or X
				 
				 //take all of the characters entered to check if they are all letters
				 tempString = JOptionPane.showInputDialog(message);
				
				//checks if all elements of the string are letters
				isOnlyAlphabet = isStringOnlyAlphabet(tempString);
				
				//lets the user know they did not enter letters
				if(isOnlyAlphabet == false) {
					JOptionPane.showMessageDialog(null, "Please enter a name that contains only letters.");
				}
				else {// if they are all letters, take the first letter
					
					//if they entered space as the first letter, make them enter the name again
					if(tempString.charAt(0) == ' ') {
						JOptionPane.showMessageDialog(null, "First letter of your name cannot be a space");
						isOnlyAlphabet = false;
					}
					else {
						middleInitial = tempString.toUpperCase().charAt(0);
					}
				}
				
			 }catch(java.lang.NullPointerException e) {
				 JOptionPane.showMessageDialog(null, "Please enter a valid selection");
				 isOnlyAlphabet = false;
			 }
			
		}while(isOnlyAlphabet == false);
		
		return middleInitial;
	}
	
	//validate last Name
	public static String validateLastName(String lastName) {
		
		String message = "What is your last name: ";	
		boolean isOnlyAlphabet = true;

		 do{
			 try {//catches if user clics cancel or X
				 lastName = JOptionPane.showInputDialog(message);
				 
					//checks if all elements of the string are letters
					isOnlyAlphabet = isStringOnlyAlphabet(lastName);
					
					//lets the user know they did not enter letters
					if(isOnlyAlphabet == false) {
						JOptionPane.showMessageDialog(null, "Please enter a name that contains only letters.");
					}
					else {
						//if they entered space as the first letter, make them enter the name again
						if(lastName.charAt(0) == ' ') {
							JOptionPane.showMessageDialog(null, "First letter of your name cannot be a space");
							isOnlyAlphabet = false;
						}
					}
				 }catch(java.lang.NullPointerException e) {
					 JOptionPane.showMessageDialog(null, "Please enter a valid selection");
					 isOnlyAlphabet = false;
			 }
			
		}while(isOnlyAlphabet == false);
		
		return lastName;
	}

}