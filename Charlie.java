
/*
 * Created by: Jean Hernandez
 * 				Anh Minh Tran
 * 				lauren Alamo
 * 
 * purpose: Uncle charlie goes to Vegas
 * Date: 10/25/2020
 */

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Charlie {

	 static DecimalFormat df = new DecimalFormat("#,###,###$");
	
	public static void main(String[] args) {
		
		int numPlayers = 1;//eSize of player array
		int numSlots = 3;//eSize of slotMachines array
		String message = "";
		int choice;
		
		//store all players and slot machines
		Player []player = new Player[51];
		SlotMachine []slotMachines = new SlotMachine[51];
		
		//default Player = Charlie
		createDefaultPlayer(player);
		
		//default slot machines = lucky 7, lucky lotto and purple people eater
		createDefaultSlotMachine(slotMachines);
	
	do {
		 choice = getUserMenuChoice();
		 
		switch(choice) {
			
		case 1://add a player
			
				//check if array is full first
				if(numPlayers == 51) {
					JOptionPane.showMessageDialog(null, "No more players can be added.");
				}
				else {//if not create a new player
					numPlayers = addAPlayer(player, numPlayers);
					JOptionPane.showMessageDialog(null, "Player #"+(numPlayers-1)+" was added. There are "+(51-numPlayers)+" spots available.");
				}
			break;
			
		case 2://add a slot machine
			
				//check if array is full first
				if(numSlots == 51) {
					JOptionPane.showMessageDialog(null, "No more slot machines can be added.");
				}
				else {//if not, create a new slot machine
					numSlots = addASlotMachine(slotMachines, numSlots); 
					JOptionPane.showMessageDialog(null, "Slot Machine #"+(numSlots-1)+" was added. There are "+(51-numSlots)+" spots available.");	
				}
				
			break;
			
		case 3://gamble
			
			 gamble(player, numPlayers, slotMachines, numSlots);
			
			break;
			
		case 4://check player balance
			message = "Please enter the number of the player you want to check balance: (Enter 0 to use Default Player)";
			checkPlayerBalance(player, numPlayers, message);
			break;
		
		case 5://delete player
			message = "Please enter the Player number you would like to delete:";
			numPlayers = deletePlayer(player, numPlayers, message);
			break;
			
		case 6: //delete slot machine
			message = "Please enter the slot machine number you would like to delete:";
			numSlots = deleteSlotMachine(slotMachines, numSlots, message);
			break;
			
		case 7://quit
			message = "Thanks for using my program..";
			JOptionPane.showMessageDialog(null, message);
			break;
			
		default://if any other character is entered besides the menu
			message = "Invalid selection, try again";
			JOptionPane.showMessageDialog(null, message);
			break;
			}
		
		}while(choice != 7);
	}//end of main
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void createDefaultPlayer(Player []player) {
		
		Name charlieName = new Name("Charlie", 'A', "Smith");
		Date charlieDob = new Date(01,01,1975);
		player[0] = new Player(charlieName,charlieDob, 5);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void createDefaultSlotMachine(SlotMachine []slotMachines) {
		
		slotMachines[0] = new SlotMachine("Lucky 7", 7000, 10000, 5000, 10, 5);
		slotMachines[1] = new SlotMachine("Lucky Lotto", 55000, 100000, 75000, 50, 25);
		slotMachines[2] = new SlotMachine("Purple People Eater", 1000, 50, 40, 5, 2);
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int getUserMenuChoice() {
		
		boolean error = false;
		String result = "";
		int userChoice = 0;
		String message = "";
	
	do {
		//displays the menu and validates the answer
		try {
			message = "";
			message += "** MAIN MENU **\n";
			message += "[1]add a new player\n";
			message += "[2]add a new slot machine\n";
			message += "[3]Gamble (1$ per play)"+"\n";
			message += "[4]Check Player Balance"+"\n";
			message += "[5]Delete a Player"+"\n";
			message += "[6]Delete a Slot Machine"+"\n";
			message += "[7]Quit";
			
			String prompt = "enter your choice";
			
			result = JOptionPane.showInputDialog(message, prompt);
			userChoice = Integer.parseInt(result);
			error = false;	
			
		}catch(java.lang.NumberFormatException e) {
			message = "please enter a valid number..";
			JOptionPane.showMessageDialog(null, message);
			error = true;
		}
		
	}while(error == true);
		
		return userChoice;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int addAPlayer(Player []player, int numPlayers) {
		
		//adds a new player to the player array
		player[numPlayers] = new Player();

		//increments the quantity of players
		numPlayers++;
		
		return numPlayers;
	}//end of AddAPlayer
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static int addASlotMachine(SlotMachine []slotMachines, int numSlots) {
		
		
		//adds a new player to the player array
		slotMachines[numSlots] = new SlotMachine();

		//increments the quantity of players
		numSlots++;
		
		return numSlots;
	}//end of AddASlotMachine
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void gamble(Player[] player, int numPlayers, SlotMachine []slotMachines, int numSlots) {
		
		int selectedPlayerNumber = 0;
		int selectedSlotMachineNumber = 0;
		String message = "";
		char letsPlay = ' ';
		int numJackpots = 0;
		int numRegs = 0;
		
		//request user's player choice and select the user's slot machine choice to play
		selectedPlayerNumber = selectPlayer(player, numPlayers);
		int playerCurrentBalance = player[selectedPlayerNumber].getMoneyBalance(); 
		
		////request user's Slot Machine choice and select the user's choice to play
		selectedSlotMachineNumber = selectSlotMachine(slotMachines, numSlots);
		int slotMachineCurrentBalance = slotMachines[selectedSlotMachineNumber].getMachineBalance();
		
		//random numbers and range variables
		int LB = 1, UBJ = slotMachines[selectedSlotMachineNumber].getJackpotOdds();//selected slot machine jackpot odds
		int UBR = slotMachines[selectedSlotMachineNumber].getRegularOdds();//selected slot machine regular odds
		
	do {	
		//if player runs out of money, go back to the main menu
		if(playerCurrentBalance == 0) {
			message = "Oops! It looks like that you do not have money\n";
			message += "Please Add more money to the Player";
			JOptionPane.showMessageDialog(null, message);
			letsPlay = 'N';//exit the loop
		}
		else if(slotMachineCurrentBalance <= 0) {
			message = "Oops! It looks like the slot you does not have money\n";
			message += "Please Add more money to the Slot Machine";
			JOptionPane.showMessageDialog(null, message);
			letsPlay = 'N';//exit the loop
		}
		else {
			//generate a random number 
			int randNumberJackpot = (int)(Math.random() * (UBJ - LB +1) + LB);
			int randNumberRegular = (int)(Math.random() * (UBR - LB +1) + LB);
			
			//lets start actually gambling
			JOptionPane.showMessageDialog(null, "Lets start Gambling!");
		
		//jackpot
			if(randNumberJackpot == 1) {
				//increase number of jackpots won
				numJackpots++;
				
				//update player's current balance
				playerCurrentBalance += slotMachines[selectedSlotMachineNumber].getJackpotPayout();
				
		 		//deduct payment to player from slot machine
				slotMachineCurrentBalance -= slotMachines[selectedSlotMachineNumber].getJackpotPayout();
					
				//if they hit the jackpot
				message = "Congratulations!! You have earned the Jackpot payout\n";
				message += "your current Balance is " + df.format((playerCurrentBalance))+ "\n";
				message += "the Slot Machine's current balance is " + df.format(slotMachineCurrentBalance)+"\n";
				JOptionPane.showMessageDialog(null, message);
					
					//if after the new changes to balance, the slot runs out of money
					if(slotMachineCurrentBalance <= 0) {
						JOptionPane.showMessageDialog(null, "Oops it looks like the Slot Machine has ran out of Money\nPlease enter more money to use this slot machine later");
						letsPlay = 'N';//exit the loop
					}
					else {
						//ask if player wants to play again with the same Player and Slot Machine
						message = "Would you like to play again with the same Player and Slot Machine: (Y/N)";
						letsPlay = playAgainOrNot(message);
				}
			}//regular
			else if(randNumberRegular == 1) {
				//increase number of regular won
				numRegs++;
				
				//update player's current balance
				playerCurrentBalance += slotMachines[selectedSlotMachineNumber].getRegularPayouts();
				
				//deduct payment to player from slot machine
				slotMachineCurrentBalance -= slotMachines[selectedSlotMachineNumber].getRegularPayouts();
				
				//if they hit the regular payout
				message = "Congratulations!! You have earned the Regular payout\n";
				message += "your current Balance is " + df.format(playerCurrentBalance)+ "\n";
				message += "the Slot Machine's current balance is " + df.format(slotMachineCurrentBalance)+"\n";
					
				JOptionPane.showMessageDialog(null, message);
					
				//if after the new changes to balance, the slot runs out of money
					if(slotMachineCurrentBalance <= 0) {
						JOptionPane.showMessageDialog(null, "Oops it looks like the Slot Machine has ran out of Money\nPlease enter more money to be able to use this slot machine later");
						letsPlay = 'N';//exit the loop
					}
					else {
					//ask if player wants to play again with the same Player and Slot Machine
						message = "Would you like to play again with the same Player and Slot Machine: (Y/N)";
						letsPlay = playAgainOrNot(message);
				}
			}
			else { 
				//update player's current balance by 1$
				playerCurrentBalance--;
				
				//increase slotMachine current balance by 1$
				slotMachineCurrentBalance++;
				
				//if nothing is earned
				message = "Unfortunately, you did not win anything this round\n";
				message += "Your new balance is " + df.format(playerCurrentBalance)+"\n";
				message += "the Slot Machine's current balance is " + df.format(slotMachineCurrentBalance)+"\n";
				JOptionPane.showMessageDialog(null, message);
				
				message = "Would you like to play again with the same Player and Slot Machine: (Y/N)";
				letsPlay = playAgainOrNot(message);
			}
		}
		
	}while(letsPlay == 'Y');
	
	//sets new balance to all players but defaults, so that they can be later used again
	if(selectedPlayerNumber != 0) {
		player[selectedPlayerNumber].setMoneyBalance(playerCurrentBalance);
	}
	//sets new balance to all slots but defaults, so that they can be later used again
	if(selectedSlotMachineNumber != 0 && selectedSlotMachineNumber != 1 && selectedSlotMachineNumber != 2) {
		slotMachines[selectedSlotMachineNumber].setMachineBalance(slotMachineCurrentBalance);
	}
	
	//update numJackpots value in SlotMachine class
	slotMachines[selectedSlotMachineNumber].setNumJackpots(numJackpots);
	
	//update numRegs value in SlotMachine class
	slotMachines[selectedSlotMachineNumber].setNumRegs(numRegs);
	}//end of gamble
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static char playAgainOrNot(String message) {
		
		char result = ' ';
		boolean error = false;
		
		do {
			try {//makes sure they do not enter an empty string
				error = false;
				result = JOptionPane.showInputDialog(null, message).toUpperCase().charAt(0);
				
				if(result != 'Y' && result != 'N') {
					JOptionPane.showMessageDialog(null, "Please enter Y to play again or N to go back to the main menu");
				}
				
			}catch(java.lang.StringIndexOutOfBoundsException | java.lang.NullPointerException  e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid character");
				error = true;
			}
		}while((result != 'Y' && result != 'N') || error == true);
		
		return result;
	}//end of playAgainOrNot
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int getPlayerOrSlotChoice(int numPlayers, String message) {
		
		String prompt = "Type here";
		int userPlayerNum = 0;
		boolean error = false;
		
		do {
			try {//ask for user's choice
				userPlayerNum = Integer.parseInt(JOptionPane.showInputDialog(message, prompt));
				error = false;
				
			//throw exception if the input is invalid
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
				error = true;
			}	
		}while(error == true);
		
		return userPlayerNum;
	}//end of getPlayerChoice
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int selectPlayer(Player []player, int numPlayers) {
		
		boolean error = false;
		int userPlayerNum = 0;
		String message = "";
		
		do {	
			message = "";
			message = "Please enter the player number you would like to use (Enter 0 to use Default Player):";
			 userPlayerNum = getPlayerOrSlotChoice(numPlayers, message);
			
			//if the user enters a outOfBounds index for the player array, let them know and go enter the selection again
				if(numPlayers > userPlayerNum) {
						
					message = "You have selected Player #"+userPlayerNum+"\n";
					message += "  *** Player's Overview ***  \n";
					
					//select default player
					if(userPlayerNum == 0) {
						message = "You have selected a Default Player \n";
						message += "  *** Player's Overview ***  \n";
					}
					
					//make sure the user does not enter 0 or a negative number
					if(userPlayerNum < 0) {
						JOptionPane.showMessageDialog(null, "Please enter Player number between 0 and 50.");
						error = true;
					}
					else {
						//display player selection and player overview
						JOptionPane.showMessageDialog(null,message + player[userPlayerNum].toString());
						error = false;
					}	
				}
			else {
				JOptionPane.showMessageDialog(null, "Please enter Player number that has already been added or add a new Player.");
				error = true;
			}
			
		}while(error == true);	
		return userPlayerNum;
	}//end of selectPlayer
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int selectSlotMachine(SlotMachine []slotMachines, int numSlots) {
		
		boolean error = false;
		int userSlotMachineNum = 0;
		String message = "";
		
		do {	
			message = "";
			message = "Please enter the Slot Machine number you would like to use (Enter 0, 1 or 2 to use Default Slot Machine):";
			//get the user's slot machine selection
			 userSlotMachineNum = getPlayerOrSlotChoice(numSlots, message);
			
			//if the user enters a outOfBounds index for the slotMachines array, let them know and enter the selection again
			if(numSlots > userSlotMachineNum) {
				message = "You have selected Slot Machine #"+userSlotMachineNum+"\n";
				message += "  *** Slot Machine's Overview ***  \n";
				
				//selects default slot machines
				if(userSlotMachineNum == 0 || userSlotMachineNum == 1 || userSlotMachineNum == 2) {
					message = "You have selected a Default Slot Machine \n";
					message += "  *** Slot Machine's Overview ***  \n";
				}
				
				//make sure the user does not enter 0 or a negative number
				if(userSlotMachineNum < 0) {
					JOptionPane.showMessageDialog(null, "Please enter a Slot Machine number between 0 and 50.");
					error = true;
				}
				else {
					//display player selection and player overview//
					JOptionPane.showMessageDialog(null,message + slotMachines[userSlotMachineNum].toString());
					error = false;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Please enter Slot Machine number that has already been added or add a new Slot Machine.");
				error = true;
			}
			
		}while(error == true);
		
		return userSlotMachineNum;
	}//end of selectSlotMachine
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void checkPlayerBalance(Player []player, int numPlayers, String message) {
		
		int playerBalance;
		boolean error = false;
		int userInput;  
	
	do {	
		//get the choice to see the balance of the selected player
		userInput = getPlayerOrSlotChoice(numPlayers, message);
		
		////if the user enters a outOfBounds index for the player array, let them know and enter the selection again
		if(numPlayers > userInput) {
			
			//if negative number is entered, let the user know
			if(userInput < 0) {
				JOptionPane.showMessageDialog(null, "Please enter Player number between 0 and 50.");
				error = true;
			}
			else {//if not, check the balance
				playerBalance = player[userInput].getMoneyBalance();
				 message = "The player's balance is: " + df.format(playerBalance);
				JOptionPane.showMessageDialog(null,	message);
				error = false;
			}		
		}
		else {
			JOptionPane.showMessageDialog(null, "Please enter Player number that has already been added or select Default player.");
			error = false;
		}
	}while(error == true);	
	
	}//end of checkPlayerBalance

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int deleteSlotMachine(SlotMachine []slotMachines, int numSlots, String message) {
		
		boolean error = false;
		
		do {
			//get the choice to check which slot machine is going to be deleted
			int userInput = getPlayerOrSlotChoice(numSlots, message);
				
				//if negative number is entered, let the user know
				if(userInput < 0 || userInput > 50) {
					JOptionPane.showMessageDialog(null, "Please enter Slot Machine number between 0 and 50.");
					error = true;
				}
				else {
					//let the user know default slots cannot be deleted
					if(userInput == 0 || userInput == 1 || userInput == 2) {
						JOptionPane.showMessageDialog(null, "Default Slot Machines cannot be deleted.");
						error = true;
					}
					else {//if the slot machine does not exist, go back to main menu
						if(slotMachines[userInput] == null) { 
							JOptionPane.showMessageDialog(null, "Slot Machine #"+userInput+" does not exist.");
							error = false;
						}
						else {
							//assigns the values of the player above the one deleted
							//Ex: Player[1] gets the values of Player[2] and so on
							for(int i = userInput; i < (numSlots -1); i++) {
								slotMachines[i] = new SlotMachine(slotMachines[i+1].getName(),
										slotMachines[i+1].getMachineBalance(), slotMachines[i+1].getJackpotOdds(), 
										slotMachines[i+1].getJackpotPayout(), slotMachines[i+1].getRegularOdds(), 
										slotMachines[i+1].getRegularPayouts());
							}
							//deletes last slotMachine
							slotMachines[numSlots -1] = null;
							JOptionPane.showMessageDialog(null, "Slot Machine #"+userInput+" has been deleted.\nAll Slot Machine numbers added after the one deleted will decrease by 1.");
							numSlots--;
							error = false;
						}
					}
				}
		}while(error == true);
		return numSlots;
	}//end of delete slotMachine
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int deletePlayer(Player []player, int numPlayers, String message) {
		
		boolean error = false;
		
		do {
			//get the choice to check which player is going to be deleted
			int userInput = getPlayerOrSlotChoice(numPlayers, message);
				
					//if negative number is entered, let the user know
				if(userInput < 0 || userInput > 50) {
					JOptionPane.showMessageDialog(null, "Please enter Player number between 0 and 50.");
					error = true;
				}	
				else {
					//let the user know default player cannot be deleted
					if(userInput == 0) {
						JOptionPane.showMessageDialog(null, "Default Player cannot be deleted.");
						error = true;
					}
					else {
						if(player[userInput] == null) {//if the slot machine does not exist, go back to main menu
							JOptionPane.showMessageDialog(null, "Player #"+userInput+" does not exist.");
							error = false;
						}
						else {
							//assigns the values of the player above the one deleted
							//Ex: Player[1] gets the values of Player[2] and so on
							for(int i = userInput; i < (numPlayers -1); i++) {
								player[i] = new Player(player[i+1].getName(), player[i+1].getDob(), player[i+1].getMoneyBalance());
							}
							//deletes last player
							player[numPlayers - 1] = null;
							JOptionPane.showMessageDialog(null, "Player #"+userInput+" has been deleted.\nAll Player numbers added after the one deleted will decrease by 1.");
							numPlayers--;
							error = false;
							}
						}
					}
		}while(error == true);
		return numPlayers;
	}//end of delete player
}//end of charlie
