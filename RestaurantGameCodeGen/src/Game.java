public class Game
{
	private int gameId;
	
	private int score;
	
	private Statistics stats;
	
	private Player player;
	
	private static int nextGameID = 0;
	
	private int dayNumber;
	
	private Restaurant restaurant;
	
	Game()
	{
		stats = new Statistics();
	}
	
	public void createNewGame( String playerName )
	{
		player = new Player();
		player.name = playerName;
		gameId = nextGameID++;
	}
	
	public boolean startGame( String restaurantName, String restaurantAddress, String restaurantCity )
	{
		restaurant = new Restaurant();
		
		restaurant.name = restaurantName;
		restaurant.address = restaurantAddress;
		restaurant.city  =restaurantCity;
		
		dayNumber = 0;
		
		restaurant.budget = 10000;//initial budget
		
		restaurant.initEmployees();
		restaurant.initMenuItemsWithCorresPrices();
		
		for(int i=0;i<30;i++)
		{
			boolean hasPositiveBudget = startNewDay();
			
			if(!hasPositiveBudget)
			{
				return false;
			}
		}
		
		//deduct additonal costs
		restaurant.budget -= 4000;
		
		if(restaurant.budget<=0)
			return false;
		else
			return true;
		
	}
	
	public boolean startNewDay( )
	{
		dayNumber++;
		
		System.out.println("New day, would you like to train any employee before the day starts?(Y/N)");
		
		if((System.console().readLine().equals("Y"))||(System.console().readLine().equals("y")))
		{
			restaurant.upgradeEmployee();
		}
		
		restaurant.calcNumOfOccupiedTables();
		
		restaurant.assignTablesToWaiters();
		
		restaurant.getClientsForNewDay();
		
		restaurant.receivePaymentForOrders();
		
		if((dayNumber%7)==0)
		{
			restaurant.paySalaries();
			
			restaurant.paySuppliers();
			
			if(restaurant.budget<=0)
				return false;
		}
		
		
		
		return true;
	}

}
