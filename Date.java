
import javax.swing.JOptionPane;

public class Date {
	
	private int day;
	private int month;
	private int year;
	
	public Date() {
		makeDate();
	}

	public Date(int day, int month, int year) {
		
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		
		String propmt = "Type here";
		String message = "";
		
		//makes sure that the limit of dates is not violated	
		switch(month) {
		//Special case
		
		case 2: //28 or 29 days
			boolean leap = isLeapYear();
			
			//if year is leap year
			if(leap == true) {
				while(day > 29 || day < 1) {
					message = "Please enter a valid number between 1 and 29...";
					day = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
					}
				
				}
			else {//if year is not leap year
				while(day > 28 || day < 1) {
					message = "Please enter a valid number between 1 and 28...";
					day = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
					}
			}	
			break;	
		case 1://31 days
		case 3://31 days	
		case 5://31 days
		case 7://31 days
		case 8://31 days
		case 10://31 days
		case 12://31 days	
			while(day > 31 || day < 1) {
				message = "Please enter a valid number for the month selected between 1 and 31...";
				day = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
				}
			break;
			
		default: 
			while(day > 30 || day < 1) {
				message = "Please enter a valid number for the month selected between 1 and 30...";
				day = Integer.parseInt(JOptionPane.showInputDialog(message, propmt));
				}
			break;
		}	
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		
		String message = "Please enter the month of your birth";
		String prompt = "Type here";
		boolean error = false;
		
		//month can only be between 1 and 12
		while(month > 12 || month < 1) {
			
			JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 12 for month");	
		do {
			
			try {//make sure the input entered is valid
				month = Integer.parseInt(JOptionPane.showInputDialog(message, prompt));
				error = false;
				
			}catch(java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid integer for month");
				error = true;
			}
		}while(error == true);
		
		}
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {

		this.year = year;
	}
	
	public String toString() {

		String result;
		if(month < 10) {
			result = "0" + month + "/";
		}
		else {
			result = month + "/";
		}
		if(day < 10) {
			result += "0" + day + "/" + year;
		}
		else {
			result += day + "/" + year;
		}
		return result;
	}
/////////////////////////////////////////////////////////////////////////////////////////////
	public void makeDate() {
		
		//variables
		String userInput = "";
		int integerMonth = 0;
		int integerYear = 0;
		int integerDay = 0;

		//return a valid string	of 10 digits
		userInput = getValidDateString();

	//parse string to get month, day and year in different variables as a integer
	//month
		integerMonth = validateMonth(userInput, integerMonth);
		setMonth(integerMonth);
		
	//year
		integerYear = validateYear(userInput, integerYear);
		setYear(integerYear);
		this.year = integerYear;//called this here to make leap year constrains work
		
	//day
		integerDay = validateDay(userInput, integerDay);
		setDay(integerDay);	
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	public String getValidDateString() {
	
	String userInput = "";
	int userInputLength = 0;
	String message = "";
	boolean error = false;
		//gets the date string of with a length of 10 characters
			 do {
				try {
					message = "";
					message = "Enter your birth date (mm/dd/yyyy): ";
					userInput = JOptionPane.showInputDialog(message);
					userInputLength = userInput.length();

				//makes sure it is entered in the correct format
					if(userInputLength != 10 || userInput.charAt(5) != '/' || userInput.charAt(2) != '/') {
						message = "Please enter a date in the format of 'MM/DD/YYYY'";
						JOptionPane.showMessageDialog(null, message);
							error = true;
					}
					else {
				//it makes sure that  element corresponding MM, DD and YYYY are all digits before going to each setter
						if((Character.isDigit(userInput.charAt(0)) && Character.isDigit(userInput.charAt(1))
								&& Character.isDigit(userInput.charAt(3)) && Character.isDigit(userInput.charAt(4))
								&& Character.isDigit(userInput.charAt(6)) && Character.isDigit(userInput.charAt(7)) 
								&& Character.isDigit(userInput.charAt(8)) && Character.isDigit(userInput.charAt(9))) == false) {
										
							message = "Please enter valid numbers in the format of 'MM/DD/YYYY'";
							JOptionPane.showMessageDialog(null, message);
							error = true;
						}else {
							error = false;
						}
					}
				}catch(java.lang.NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Enter a valid selection");
					error = true;
				}	 
			}while(error == true);		
		
		 return userInput;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	public boolean isLeapYear() {
		
		boolean isLeap = false;
		
		//if divisible by 4 check if divisible by 100
		if(year % 4 == 0) {
			
			//if divisible by 100 check if divisible by 400
			if(year % 100 == 0) {
				
				//if divisible by 400
				if(year % 400 == 0) {
					isLeap = true;
				}
				//if not, not a leap year
				else {
					isLeap = false;
				}
			}
			//if only divisible by four, its a leap year
			else {
				isLeap = true;
			}
		}
		//if not divisible by 4, not a leap year
		else {
			isLeap = false;
		}
		
		return isLeap;
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int validateMonth(String userInput, int integerMonth) {
	
		String month = "" + userInput.substring(0,2);
		integerMonth = Integer.parseInt(month);
				
		return integerMonth;
		}//end of validateMonth
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int validateYear(String userInput, int integerYear) {

			String year = "" + userInput.substring(6,10);
			integerYear = Integer.parseInt(year);
			
		return integerYear;
		}//end of validateYear
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int validateDay(String userInput, int integerDay) {
		
			String day = "" + userInput.substring(3,5);
			integerDay = Integer.parseInt(day);
				
		return integerDay;
		}//end of validateDay
	}