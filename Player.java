
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class Player {
	
	private Name name;
	private Date dob;
	private int moneyBalance;
	
	
	public Player() {
		makePlayer();
	}
	
	public Player(Name name, Date dob, int moneyBalance) {
		
		setName(name);
		setDob(dob);
		setMoneyBalance(moneyBalance);
		
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getMoneyBalance() {
		return moneyBalance;
	}

	public void setMoneyBalance(int moneyBalance) {
		
		String message = "";
		String prompt = "Type Here";

		//if money available is negative ask for a positive value
		while(moneyBalance <= 0) {
			
			//make sure that the answer is a valid input
			try {
				moneyBalance = Integer.parseInt(JOptionPane.showInputDialog("Enter a POSITIVE money balance", prompt));
				
				if(moneyBalance < 1) {
					JOptionPane.showMessageDialog(null, "Please enter a Positive integer.");
				}
				
			}catch(java.lang.NumberFormatException e) {
				message = "Please enter a valid integer";
				JOptionPane.showMessageDialog(null, message);
			}	
		}
		
		this.moneyBalance = moneyBalance;
	}
	
	public String toString() {
		
		//format for money balance
		DecimalFormat df = new DecimalFormat("#,###,###$");
		String message = "";
		
		//player information displayed
		message += "Player's Name: "+name+"\n";
		message += "Player's Date of Birth: "+dob+"\n";
		message += "Player's Money Balance: "+ df.format(moneyBalance) +"\n";
		
		return message;
	}

	public  void makePlayer() {
		
		boolean exit = false;
		String message = "";
		String propmt = "Type Here";
		
			//generates a name for the player
			name = new Name();
			setName(name);
			
			//generates a date of birth for the player
			dob = new Date();
			setDob(dob);
		
			//adds money balance to the player
			do {
				//makes sure a valid number is entered
				try {
					message = "";
					message = "Enter your money balance";
					moneyBalance = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
					exit = false;
					
				}catch(java.lang.NumberFormatException e) {
					message = "Please enter a valid number for money balance";
					JOptionPane.showMessageDialog(null, message);
					exit = true;
				}
		
			}while(exit == true);
			
			//validates money balances to make sure it is > 0
			setMoneyBalance(moneyBalance);
		}
	
	}// end Player
