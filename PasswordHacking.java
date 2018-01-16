/**
 * PasswordHacking class is the main class that checks possible characters from the ASCII table
 * from Decimal value 33 to 122 (from ! to z). The characters include major symbols, numbers, and alphabets
 * including upper and lower class. The class can find password up to 6 characters long. The characters are: 
 * !"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\]^_`abcdefghijklmnopqrstuvwxyz 
 * @author Rajesh
 *
 */
public class PasswordHacking {
	
	public static String password;   //"!!!!!" will take 66347101 attempts
	
	final static int SIZE = 6;
	static int attempt = 0;
	int increaseNumber = 0;
	static int maxAttempt = 1_000_000_000;
	static String searchPassword = "";
	static boolean found = false;
	static char [] passwordArray = {' ', ' ', ' ', ' ', ' ', ' '};

	/***
	 * main entry point
	 * @param args
	 */
	public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        if(args.length > 0)
            password = args[0];
        else
            password = "!!!!!";
		System.out.println("Extracting the password...");
		
	    searchPassword = getPassword();
		
	    System.out.println("Your password is: " + searchPassword);
	    System.out.println("It took " + attempt + " attempts.");
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Runtime: " + (totalTime)/1000 + "s");
	}

	/***
	  * getPassword() calls setPassword() which provides a possible matching password
	  * and checks with the actual password. The password matching process continues until
	  * it reaches the maxAttempt or find the password.
	  * @return a String (searchPassword)
	  */
	private static String getPassword() {
		while (!found)
	    {
	        searchPassword = setPassword();
	        if (searchPassword.equals(password)){
	            found = true;
	            System.out.println("Password found.");
	        }
	        else if (attempt == maxAttempt){
	        	System.out.println("Max attempts (" + maxAttempt + ") reached.");
	            System.out.println("Exiting..." );
	            System.exit(0);
	        }
	        else{
	            attempt++;
	            modifyPassword ();
	        }
	    }
	    return searchPassword;
	}
	
	/***
	  * setPassword() converts an array of char to string and returns the string
	  * @return a String (tempPassword)
	  */
	private static String setPassword() {
		String tempPassword = "";
	    for (int i = 0; i < SIZE; i++)
	        if (passwordArray[i] != ' ')
	            tempPassword += passwordArray[i];
	    //System.out.println("TempPassword: " + tempPassword);
	    return tempPassword;
	}

	/***
	  * modifyPassword() changes the characters of the array. 
	  * Basically, the last index of the array will be increased 
	  * by one ASCII value between the range of '!' to 'a' (33 to 122)	 
	  */
	private static void modifyPassword() {
		int index = 1;

	    passwordArray[SIZE-1] = (char) (passwordArray[SIZE-1]+1);

	    while (index != SIZE-1)
	    {                                  // 123 = { after 'a'
	        if (passwordArray[SIZE-index] == 123 && passwordArray[SIZE-index-1] == ' ')
	        {
	            passwordArray[SIZE-index-1] = 33; // 33 = '!' which is after space (32)
	        }
	        index++;
	    }
	    index = 1;
	    while (index != SIZE-1)
	    {
	        if (passwordArray[SIZE-index] == 123){
	            passwordArray[SIZE-index-1] = (char) (passwordArray[SIZE-index-1]+1);
	            passwordArray[SIZE-index] = 33; // 33 = '!' 32 = ' '
	        }
	        index++;
	    }
	}
}