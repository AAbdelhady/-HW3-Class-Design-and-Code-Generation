public class Restaurant
{
	
	public String name;
	
	
	public String address;
	
	
	public String city;
	
	
	public int budget;
	
	
	public int reputationPoints = 15;
	
	
	private Table restaurantTables[];
	
	
	private People population;
	
	
	public static Dish dishes[];
	
	
	public static Beverage beverages[];
	
	
	private Employee employees[];
	
	
	private int noOfOccupiedTables;
	
	public Reputation reputation = Reputation.MEDIUM;
	
	private int weeklyOrderCosts = 0;
	
	public void receivePaymentForOrders( )
	{
		int totalRevenue=0;
		for(int i=0;i<noOfOccupiedTables;i++)
		{
			totalRevenue+=restaurantTables[i].receivePaymentForTable();
		}
		budget+=totalRevenue;
	}
	
		
	private void initDishes( )
	{
		dishes = new Dish[5];
		System.out.println("\nPlease configure you menu main dishes");
		for(int i=0;i<dishes.length;i++)
		{
			dishes[i]= new Dish();
			
			dishes[i].name = "Dish"+i;
			
			System.out.println("\nEnter Dish" + (i+1) + " Quality(L/H):");
			
			if(ConsoleInputReader.isHigh())
			{
				dishes[i].qualityLevel = Quality.HIGH;
				dishes[i].cost = 10;
			}
			else
			{
				dishes[i].qualityLevel = Quality.LOW;
				dishes[i].cost = 3;
			}
			
			System.out.println("Enter Dish" + (i+1) + " Price:");
			dishes[i].price = ConsoleInputReader.readIntFromConsole();
			
			System.out.println("Enter Dish" + (i+1) + " Calorie Count:");
			dishes[i].calories = ConsoleInputReader.readIntFromConsole();
		}
	}
	
	
	private void initBeverages( )
	{
		beverages = new Beverage[5];
		System.out.println("\nPlease configure you menu beverages");
		for(int i=0;i<beverages.length;i++)
		{
			beverages[i] = new Beverage(); 
			
			beverages[i].name = "Beverage"+i;
			
			System.out.println("Enter Beverage" + (i+1) + " Quality(L/H):");
			if(ConsoleInputReader.isHigh())
			{
				beverages[i].qualityLevel = Quality.HIGH;
				beverages[i].cost = 3;
			}
			else
			{
				beverages[i].qualityLevel = Quality.LOW;
				beverages[i].cost = 1;
			}
			
			System.out.println("Enter Beverage" + (i+1) + " Price:");
			beverages[i].price = ConsoleInputReader.readIntFromConsole();
			
			System.out.println("Enter Beverage" + (i+1) + " Volume:");
			beverages[i].volume = ConsoleInputReader.readIntFromConsole();
		}
	}
	
	
	public void initMenuItemsWithCorresPrices( )
	{
		initDishes();
		initBeverages();
	}

	
	public void calcReputation( )
	{
		int netRepFlow=0;
		for(int i=0;i<noOfOccupiedTables;i++)
		{
			netRepFlow += restaurantTables[i].calcRepForTable(employees[0].levelOfExperience, employees[1].levelOfExperience);
		}
		reputationPoints += netRepFlow;
		
		if(reputationPoints>=30)
		{
			reputation = Reputation.HIGH;
		}
		else if(reputationPoints>=15)
		{
			reputation = Reputation.MEDIUM;
		}
		else
		{
			reputation = Reputation.LOW;
		}
			
	}

	
	public void assignTablesToWaiters( )
	{
		System.out.println("Please assign waiters to tables:");
		
		((Waiter)employees[2]).noOfTablesServed=0;
		((Waiter)employees[3]).noOfTablesServed=0;
		((Waiter)employees[4]).noOfTablesServed=0;
		
		for(int i=0;i<noOfOccupiedTables;i++)
		{
			System.out.println("Please enter the number of one of the availble waiters to assgin to tabel #" + (i+1) + ":");
			
			Waiter w;
			int numPicked;
			
			System.out.println("1: Name: " + employees[2].name + ", Experience:" + employees[2].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[2]).noOfTablesServed);
			System.out.println("2: Name: " + employees[3].name + ", Experience:" + employees[3].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[3]).noOfTablesServed);
			System.out.println("3: Name: " + employees[4].name + ", Experience:" + employees[4].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[4]).noOfTablesServed);
				
			numPicked = ConsoleInputReader.readIntFromConsole();
			while((numPicked>3)||(numPicked<1))
			{
				System.out.print("Please pick 1,2, or 3");
				numPicked = ConsoleInputReader.readIntFromConsole();
			}
			w = (Waiter)employees[numPicked+1];
			
			
			while(w.noOfTablesServed>=3)
			{
				System.out.println("This waiter has reached the maximum number of tables to serve, please pick another one.");
				System.out.println("1: Name: " + employees[2].name + ", Experience:" + employees[2].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[2]).noOfTablesServed);
				System.out.println("2: Name: " + employees[3].name + ", Experience:" + employees[3].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[3]).noOfTablesServed);
				System.out.println("3: Name: " + employees[4].name + ", Experience:" + employees[4].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[4]).noOfTablesServed);
				numPicked = ConsoleInputReader.readIntFromConsole();
				while((numPicked>3)||(numPicked<1))
				{
					System.out.print("Please pick 1,2, or 3");
					numPicked = ConsoleInputReader.readIntFromConsole();
				}
				w = (Waiter)employees[numPicked+1];
			}
			
			w.noOfTablesServed++;
				
			employees[numPicked+1] = w;
			restaurantTables[i].assignedWaiter = (Waiter)employees[numPicked+1];
		}
	}
	
	
	public boolean isBudgetSufficient( int cost )
	{
		if(cost<budget)
			return true;
		else
			return false;
	}
	
	
	public void getClientsForNewDay( )
	{
		for(int i=0;i<noOfOccupiedTables;i++)
		{	
			restaurantTables[i].clientsAtTable[0] = population.pickRndClient();
			restaurantTables[i].clientsAtTable[0].placeOrder();
			
			restaurantTables[i].clientsAtTable[1] = population.pickRndClient();
			restaurantTables[i].clientsAtTable[1].placeOrder();
		}
	}
	
	
	public void upgradeEmployee(  )
	{
		boolean anyAvailbeForTraining = false;
		for(int i=0;i<employees.length;i++)
		{
			if(employees[i].levelOfExperience != Experience.HIGH) 
			{
				anyAvailbeForTraining = true;
				break;
			}
		}
		
		if(!anyAvailbeForTraining)
		{
			System.out.println("\nAll Employees have reached the maximum level of experience!\n");
			return;
		}
		
		
		System.out.println("Please enter the number of the employee to train");
		
		int numPicked;
		for(int i=0;i<employees.length;i++)
		{
			System.out.println((i+1)+": Name: " + employees[i].name + ", Experience:" + employees[i].levelOfExperience + ", Role: " + employees[i].getEmployeeType());
		}	
		numPicked = ConsoleInputReader.readIntFromConsole();
		
		
		while(employees[numPicked-1].levelOfExperience==Experience.HIGH)
		{
			System.out.println("This employee has reached the maximum level of experience, please pick another one.");
			for(int i=0;i<employees.length;i++)
			{
				System.out.println((i+1)+": Name: " + employees[i].name + ", Experience: " + employees[i].levelOfExperience + ", Role: " + employees[i].getEmployeeType());
			}	
			numPicked = ConsoleInputReader.readIntFromConsole();
		}
		
		if(employees[numPicked-1] instanceof Waiter)
		{
			if(!isBudgetSufficient(800))
			{
				System.out.println("insufficient funds!");
				return;
			}
		}
		else
		{
			if(!isBudgetSufficient(1200))
			{
				System.out.println("insufficient funds!");
				return;
			}
		}
		
		if(employees[numPicked-1].levelOfExperience==Experience.LOW)
		{
			employees[numPicked-1].levelOfExperience=Experience.MEDIUM;
		}
		else
		{
			employees[numPicked-1].levelOfExperience=Experience.HIGH;
		}
		employees[numPicked-1].calcSalary();
		
		System.out.println("\nEmployee Trained Successfully!\n");
	}
	
	
	public void initEmployees( )
	{
		employees = new Employee[5];
		
		employees[0] = new Chef();
		employees[1] = new Barman();
		employees[2] = new Waiter();
		employees[3] = new Waiter();
		employees[4] = new Waiter();
		
		//Details for the chef
		Chef chef = new Chef();
		System.out.println("Enter Chef Name:");
		chef.name = ConsoleInputReader.readLineFromConsole();
				
		System.out.println("Enter Chef Surname:");
		chef.surname = ConsoleInputReader.readLineFromConsole();
				
		System.out.println("Enter Chef Tax Code:");
		chef.taxCode = ConsoleInputReader.readLineFromConsole();
				
		chef.levelOfExperience = Experience.LOW;
		chef.salary = 300;
				
		employees[0] = chef;
				
		//Details for the barman
		System.out.println("Enter Barman Name:");
		employees[1].name = ConsoleInputReader.readLineFromConsole();
				
		System.out.println("Enter Barman Surname:");
		employees[1].surname = ConsoleInputReader.readLineFromConsole();
				
		employees[1].levelOfExperience = Experience.LOW;
		employees[1].salary = 300;
				
		//Details of waiters
		for(int i=0;i<3;i++)
		{
			System.out.println("Enter Waiter" + (i+1) + " Name:");
			employees[2+i].name = ConsoleInputReader.readLineFromConsole();
					
			System.out.println("Enter Waiter" + (i+1) + " Surname:");
			employees[2+i].surname = ConsoleInputReader.readLineFromConsole();
					
			employees[2+i].levelOfExperience = Experience.LOW;
			employees[2+i].salary = 200;
		}		
	}
	
	
	public int paySalaries( )
	{
		int totalSalaries = 0;
		for(int i=0;i<employees.length;i++)
		{
			totalSalaries += employees[i].salary;
		}
		budget -= totalSalaries;
		return totalSalaries;
	}
	
	
	public void calcNumOfOccupiedTables( )
	{
		if(reputation == Reputation.HIGH)
		{
			noOfOccupiedTables = 9;
		}
		else if(reputation == Reputation.MEDIUM)
		{
			noOfOccupiedTables = 5;
		}
		else
		{
			noOfOccupiedTables = 2;
		}
	}

	
	public void initTables( )
	{
		restaurantTables  = new Table[9];
		
		for(int i=0;i<restaurantTables.length;i++)
		{	
			restaurantTables[i] = new Table();
			restaurantTables[i].number = i+1;
			
			restaurantTables[i].clientsAtTable = new Client[2];
			restaurantTables[i].clientsAtTable[0] = new Client();
			restaurantTables[i].clientsAtTable[1] = new Client();
		}
	}

	public void calcDayOrderCosts( )
	{
		int totalMenuItemsCosts=0;
		for(int i=0;i<noOfOccupiedTables;i++)
		{
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[0].orders.get(restaurantTables[i].clientsAtTable[0].orders.size()-1).itemsOrderd[0].cost;
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[0].orders.get(restaurantTables[i].clientsAtTable[0].orders.size()-1).itemsOrderd[1].cost;
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[1].orders.get(restaurantTables[i].clientsAtTable[1].orders.size()-1).itemsOrderd[0].cost;
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[1].orders.get(restaurantTables[i].clientsAtTable[1].orders.size()-1).itemsOrderd[1].cost;
		}
		
		weeklyOrderCosts += totalMenuItemsCosts;
	}
	
	public int paySuppliers()
	{
		budget -= weeklyOrderCosts;
		int tmp = weeklyOrderCosts;
		weeklyOrderCosts=0;
		return tmp;
	}
	
	public void initPopulation()
	{
		population = new People();
		population.population = new Client[18];
		
		for(int i=0;i<18;i++)
		{
			population.population[i] = new Client();
			population.population[i].name = "Client "+(i+1);
		}
	}
	
	public void showStatistics()
	{
		System.out.println("\n Game Statistics");
		System.out.println("----------------\n");
		
		
		
		for(int i=0;i<population.population.length;i++)
		{
			
			System.out.println("\n"+population.population[i].name+":");
			
			int dishAvgCalorieCount=0;
			int bevAvgVolume=0;
			int moneySpent=0;
			
			for(int j=0;j<population.population[i].orders.size();j++)
			{
				dishAvgCalorieCount += ((Dish)population.population[i].orders.get(j).itemsOrderd[0]).calories;
				bevAvgVolume += ((Beverage)population.population[i].orders.get(j).itemsOrderd[1]).volume;
				moneySpent += population.population[i].orders.get(j).orderPrice;
			}
			
			dishAvgCalorieCount = dishAvgCalorieCount/population.population[i].orders.size();
			bevAvgVolume = bevAvgVolume/population.population[i].orders.size();
			
			System.out.println("Dishes Average Calorie Count ="+dishAvgCalorieCount);
			System.out.println("Beverages Average Volume ="+bevAvgVolume);
			System.out.println("Total Money Spent ="+moneySpent);
		}
	}
}
