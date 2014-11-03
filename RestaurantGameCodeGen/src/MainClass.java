public class MainClass
{	
	private static Game currentGame;
	
	
	private static Ranking_List rankingList;
	
	
	
	public static void main( String args[] )
	{
		rankingList = new Ranking_List();
		
		char c=0;
		
		do
		{
			currentGame = new Game();
		
			System.out.println("\n\nStarting New Game");
			System.out.println("\nEnter Player Name:");
		
		
			currentGame.createNewGame(ConsoleInputReader.readLineFromConsole());
		
			System.out.println("Enter Restaurant Name:");
			String restaurantName = ConsoleInputReader.readLineFromConsole();
		
			System.out.println("Enter Restaurant Address:");
			String restaurantAddress = ConsoleInputReader.readLineFromConsole();
		
			System.out.println("Enter Restaurant City:");
			String restaurantCity = ConsoleInputReader.readLineFromConsole();
		
			boolean gameResult = currentGame.startGame(restaurantName, restaurantAddress, restaurantCity);
		
			if(gameResult)
			{
				System.out.println("\n	  You Win! 	  ");
				System.out.println("	--------	\n");
			
				currentGame.restaurant.showStatistics();
			
				rankingList.addScore(currentGame.player.name, currentGame.score);
			}
			else
			{
				System.out.println("\n	  Game Over!  ");
				System.out.println("	----------	\n");
			}
			
			System.out.println("\n\n\nEnter Y to play new game, R for rankings or any other character to exit");
			c = ConsoleInputReader.readLineFromConsole().charAt(0);
			
		}while((c=='Y')||(c=='y'));
		
		if((c=='R')||(c=='r'))
		{
			rankingList.displayScores();
		}
	}
	
}
