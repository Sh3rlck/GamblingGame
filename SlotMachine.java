import java.text.DecimalFormat;

import javax.swing.JOptionPane;

//coded by Jean and Anh
public class SlotMachine {
	
	//format for money ammounts
	 DecimalFormat df = new DecimalFormat("#,###,###$");
	 //format for quantity ammounts
	 DecimalFormat df1 = new DecimalFormat("#,###,###");
	 
	private String name;
	private int machineBalance;
	private int jackpotOdds;
	private int jackpotPayout;
	private int regularOdds;
	private int regularPayouts;
	private int numJackpots;
	private int numRegs;
	
	public SlotMachine() {
		makeSlotMachine();
	}

	public SlotMachine(String name, int machineBalance, int jackpotOdds, int jackpotPayout, int regularOdds, int regularPayouts, int numJackpots, int numRegs) {
		
		setName(name);
		setMachineBalance(machineBalance);
		setJackpotOdds(jackpotOdds);
		setJackpotPayout(jackpotPayout);
		setRegularOdds(regularOdds);
		setRegularPayouts(regularPayouts);
		setNumJackpots(numJackpots);
		setNumRegs(numRegs);
	
	}

	public SlotMachine(String name, int machineBalance, int jackpotOdds, int jackpotPayout, int regularOdds, int regularPayouts) {
		
		setName(name);
		setMachineBalance(machineBalance);
		setJackpotOdds(jackpotOdds);
		setJackpotPayout(jackpotPayout);
		setRegularOdds(regularOdds);
		setRegularPayouts(regularPayouts);
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		//capitalize the first letter of  name and leave the rest in lower case
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		
		this.name = name;
	}

	public int getMachineBalance() {	
		return machineBalance;
	}
	
	public void setMachineBalance(int machineBalance) {
		
		String prompt = "Type Here";
		
		//ask machine balance again if user enter a negative number
		while(machineBalance <= 0) {
			
			try {//makes sure input is valid
				machineBalance = Integer.parseInt(JOptionPane.showInputDialog("Enter a POSITIVE value for Machine balance:",prompt));
				
				if(machineBalance < 1) {
					JOptionPane.showMessageDialog(null, "Please enter a Positive integer.");
				}
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer");
			}
		}
		
		this.machineBalance = machineBalance;
	}

	public int getJackpotOdds() {
		return jackpotOdds;
	}

	public void setJackpotOdds(int jackpotOdds) {
		
		String prompt = "Type Here";
		
		//ask machine balance again if user enter a negative number
		while(jackpotOdds < 1) {
			
			try {//makes sure input is valid
				jackpotOdds = Integer.parseInt(JOptionPane.showInputDialog("Enter a POSITIVE value for jackpot odds:",prompt));
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer");
			}
		}
		
		this.jackpotOdds = jackpotOdds;
	}

	public int getJackpotPayout() {
		return jackpotPayout;
	}

	public void setJackpotPayout(int jackpotPayout) {
		
		String prompt = "Type Here";
		
		while(jackpotPayout < 1) {
			
			try {//makes sure input is valid
				jackpotPayout = Integer.parseInt(JOptionPane.showInputDialog("Enter a POSITIVE value for jackpot payout:",prompt));
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer");
			}
		}
		
		this.jackpotPayout = jackpotPayout;
	}

	public int getRegularOdds() {
		return regularOdds;
	}

	public void setRegularOdds(int regularOdds) {
		
		String prompt = "Type Here";
		
		while(regularOdds < 1) {
			
			try {//makes sure input is valid
				regularOdds = Integer.parseInt(JOptionPane.showInputDialog("Enter a POSITIVE value for regular odds:",prompt));
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer");
			}
		}
		
		this.regularOdds = regularOdds;
	}

	public int getRegularPayouts() {	
		return regularPayouts;
	}
	
	public void setRegularPayouts(int regularPayouts) {
		
		String prompt = "Type Here";
		
		while(regularPayouts < 1) {
			
			try {//makes sure input is valid
				regularPayouts = Integer.parseInt(JOptionPane.showInputDialog("Enter a POSITIVE value for regular payout:",prompt));
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer");
			}
		}
		
		this.regularPayouts = regularPayouts;
	}

	public int getNumJackpots() {
		return numJackpots;
	}


	public void setNumJackpots(int numJackpots) {
		this.numJackpots = numJackpots;
	}

	public int getNumRegs() {
		return numRegs;
	}

	public void setNumRegs(int numRegs) {
		this.numRegs = numRegs;
	}

	
	public String toString() {
		
		
		String message = "";
		
		message += "Slot Machine's name: "+name+"\n";
		message += "Slot Machine's Balance: "+df.format(machineBalance)+"\n";
		message += "Slot Machine's Jackpot Odds: 1 in "+df1.format(jackpotOdds)+"\n";
		message += "Slot Machine's Jackpot Payout: "+df.format(jackpotPayout)+"\n";
		message += "Slot Machine's Regular Odds: 1 in "+df1.format(regularOdds)+"\n";
		message += "Slot Machine's Regular Payout: "+df.format(regularPayouts)+"\n";
	
		return message;
	}

	public void makeSlotMachine() {
		
		//validation is not neccesary for name
		name = validateName(name);
		setName(name);
		
		//enter valid machine balance
		machineBalance = validateMachineBalance(machineBalance);
		setMachineBalance(machineBalance);
		
		//enter valid jackpot odds value
		jackpotOdds = validateJackpotOdds(jackpotOdds);
		setJackpotOdds(jackpotOdds);
		
		//enter valid jackpot payout value
		jackpotPayout = validateJackpotPayout(jackpotPayout);
		setJackpotPayout(jackpotPayout);
		
		//enter valid regular odds value
		regularOdds = validateRegularOdds(regularOdds);
		setRegularOdds(regularOdds);
		
		//enter valid regular payout value
		regularPayouts = validateRegularPayout(regularPayouts);
		setRegularPayouts(regularPayouts);
		
	}//end of makeSlotMachine
	
	//checks every char in a string with the regex expression and makes sure all of them are alphanumeric characters
	public static boolean isStringOnlyAlphanumeric(String name) { 
			
		return ((!name.equals("")) && (name != null) && (name.matches("^[a-zA-Z0-9]*$"))); 
	} 
	
	//make sure the name of the slot machine is alphanumeric
	public String validateName(String name) {
		
		String propmt = "Type here";
		String message = "Enter the name of the Slot Machine:";
		boolean isOnlyAlphanumeric;
		boolean error = false;
		
		do {
			try {
				name = JOptionPane.showInputDialog(message, propmt);
				
				//checks for only alphanumeric values
				isOnlyAlphanumeric = isStringOnlyAlphanumeric(name);
				
				//if only alphanumeric is entered, move on if not let the user know
				if(isOnlyAlphanumeric == false) {
					JOptionPane.showMessageDialog(null, "Please enter a name that contains only letters and numbers.");
					error = true;
				}
				else {
					error = false;
				}
				
			}catch(java.lang.NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid selection");
				error = true;
			}
		}while(error == true);

		return name;
	}
	
	//validate machine balance
	public int validateMachineBalance(int machineBalance) {
		
		String propmt = "Type here";
		String message = "Enter the money balance of the Slot Machine:";
		
		boolean error = false;
		
		//handle format mismatch exception
		do {
			try {
				machineBalance = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
				error = false;
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
				error = true;
			}
		}while(error == true);
	
		return machineBalance;
	}
	
	//validate jackpot odds 
	public int validateJackpotOdds(int jackpotOdds) {
		
		String propmt = "Type here";
		String message = "Enter the jackpot Odds of the Slot Machine:";
		
		boolean error = false;
		
		do {
			//handle format mismatch exception
			try {
				jackpotOdds = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
				error = false;
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
				error = true;
			}
		}while(error == true);

		return jackpotOdds;
	}
	
	//validate jackpot payouts
	public int validateJackpotPayout(int jackpotPayout) {
		
		String propmt = "Type here";
		String message = "Enter the jackpot payout of the Slot Machine:";
		
		boolean error = false;
		
		do {
			//handle format mismatch exception
			try {
				jackpotPayout = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
				error = false;
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
				error = true;
			}
		}while(error == true);

		return jackpotPayout;
	}
	
	//validate regular odds
	public int validateRegularOdds(int regularOdds) {
		
		String propmt = "Type here";
		String message = "Enter the regular Odds of the Slot Machine:";
		
		boolean error = false;
		
		do {
			//handle format mismatch exception
			try {
				regularOdds = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
				error = false;
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
				error = true;
			}
		}while(error == true);

		return regularOdds;
	}
	
	//validate regular payouts
	public int validateRegularPayout(int regularPayout) {
		
		String propmt = "Type here";
		String message = "Enter the regular payout of the Slot Machine:";
		
		boolean error = false;
		
		do {
			//handle format mismatch exception
			try {
				regularPayout = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
				error = false;
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
				error = true;
			}
		}while(error == true);

		return regularPayout;
	}
}//end of SlotMachine
