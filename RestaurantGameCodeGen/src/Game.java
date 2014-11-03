public class Game
{
	public int gameId;
	
	public int score;
	
	public Player player;
	
	private static int nextGameID = 0;
	
	private int dayNumber;
	
	public Restaurant restaurant;
	
	
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
		restaurant.initTables();
		restaurant.initPopulation();
		
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
		{
			score = restaurant.budget; 
			return true;
		}
			
		
	}
	
	public boolean startNewDay( )
	{
		dayNumber++;
		
		System.out.println("Day:"+dayNumber+" starting, would you like to train any employee before the day starts?(Y/N)");
		
		if(ConsoleInputReader.isYes())
		{
			restaurant.upgradeEmployee();
		}
		
		restaurant.calcNumOfOccupiedTables();
		
		restaurant.assignTablesToWaiters();
		
		restaurant.getClientsForNewDay();
		
		restaurant.receivePaymentForOrders();
		
		restaurant.calcDayOrderCosts( );
		
		restaurant.calcReputation();
		
		if((dayNumber%7)==0)
		{
			System.out.println("\n++Budget Before Deductions = " + restaurant.budget);
			
			int salaries = restaurant.paySalaries();
			System.out.println("\n++Salaries for week"+dayNumber/7+ " = " + salaries +  " has been deducted from budget");
			
			int supplies = restaurant.paySuppliers();
			System.out.println("\n++Supplies costs for week"+dayNumber/7+ " = " + supplies +  " has been deducted from budget");
			
			if(restaurant.budget<=0)
				return false;
		}
		
		String EOD_msg = "** Day" + dayNumber + " is over, the budget is: " + restaurant.budget + ", reputation points:" + restaurant.reputationPoints + ", restaurant reputation is:" + restaurant.reputation + " **";
		String sep = "";
		
		for(int i = 0;i<EOD_msg.length();i++)
			sep+="*";
		System.out.println("\n"+sep);
		System.out.println(EOD_msg);
		System.out.println(sep+"\n");
		
		return true;
	}

}
