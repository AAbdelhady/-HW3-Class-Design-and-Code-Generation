import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleInputReader {
	public static String readLineFromConsole()
	{	
		try {
			  InputStreamReader isr = new InputStreamReader(System.in);
		      BufferedReader br = new BufferedReader(isr);
		      return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static int readIntFromConsole()
	{
		String s = readLineFromConsole();
		
		while(!s.matches("\\d+"))
		{
			System.out.print("Error: The input is not a valid numeric value!, try again");
			s = readLineFromConsole();
		}
		return Integer.parseInt(s);
			
	}
	
	
	public static boolean isYes()
	{
		String s = readLineFromConsole();
		
		if(s.equals(""))
			return false;
		
		char consoleC = s.charAt(0);
		if((consoleC=='Y')||(consoleC=='y'))
			return true;
		else
			return false;
	}
	
	public static boolean isHigh()
	{
		String s = readLineFromConsole();
		
		if(s.equals(""))
			return false;
		
		char consoleC = s.charAt(0);
		if((consoleC=='H')||(consoleC=='h'))
			return true;
		else
			return false;
	}
}
