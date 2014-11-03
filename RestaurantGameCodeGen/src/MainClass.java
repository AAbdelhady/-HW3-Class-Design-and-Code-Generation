import java.io.IOException;
import java.io.InputStream;



public class MainClass
{	
	private static Game currentGame;
	
	
	private static Ranking_List rankingList;
	
	
	public static void main( String args[] )
	{
		currentGame = new Game();
		rankingList = new Ranking_List();
		
		System.out.println("Starting New Game");
		System.out.println("Enter Player Name:");
		
		currentGame.createNewGame(System.console().readLine());
		
		System.out.println("Enter Restaurant Name:");
		String restaurantName = System.console().readLine();
		
		System.out.println("Enter Restaurant Address:");
		String restaurantAddress = System.console().readLine();
		
		System.out.println("Enter Restaurant City:");
		String restaurantCity = System.console().readLine();
		
		boolean gameResult = currentGame.startGame(restaurantName, restaurantAddress, restaurantCity);
		
		
	}
	
	
}
