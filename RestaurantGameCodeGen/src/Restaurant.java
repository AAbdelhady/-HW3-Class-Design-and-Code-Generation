import java.io.IOException;


public class Restaurant
{
	
	public String name;
	
	
	public String address;
	
	
	public String city;
	
	
	public int budget;
	
	
	private int reputationPoints = 15;
	
	
	private Table restaurantTables[];
	
	
	private People population;
	
	
	public static Dish dishes[];
	
	
	public static Beverage beverages[];
	
	
	private Employee employees[];
	
	
	private int noOfOccupiedTables;
	
	private Reputation reputation = Reputation.MEDIUM;
	
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
	
	
	public void payMonthlyCosts( )
	{
		
	}
	
	
	private void initDishes( )
	{
		dishes = new Dish[5];
		for(int i=0;i<dishes.length;i++)
		{
			System.out.println("Enter Dish" + (i+1) + " Name:");
			dishes[i].name = System.console().readLine();
			
			System.out.println("Enter Dish" + (i+1) + " Quality(L/H):");
			if((System.console().readLine().equals("H"))||(System.console().readLine().equals("h")))
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
			dishes[i].price = Integer.parseInt(System.console().readLine());
			
			System.out.println("Enter Dish" + (i+1) + " Calorie Count:");
			dishes[i].calories = Integer.parseInt(System.console().readLine());
		}
	}
	
	
	private void initBeverages( )
	{
		beverages = new Beverage[5];
		
		for(int i=0;i<beverages.length;i++)
		{
			System.out.println("Enter Dish" + (i+1) + " Name:");
			beverages[i].name = System.console().readLine();
			
			System.out.println("Enter Dish" + (i+1) + " Quality(L/H):");
			if((System.console().readLine().equals("H"))||(System.console().readLine().equals("h")))
			{
				beverages[i].qualityLevel = Quality.HIGH;
				beverages[i].cost = 3;
			}
			else
			{
				beverages[i].qualityLevel = Quality.LOW;
				beverages[i].cost = 1;
			}
			
			System.out.println("Enter Dish" + (i+1) + " Price:");
			beverages[i].price = Integer.parseInt(System.console().readLine());
			
			System.out.println("Enter Dish" + (i+1) + " Volume:");
			beverages[i].volume = Integer.parseInt(System.console().readLine());
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
	
	private int readIntFromConsole()
	{
		try {
			return System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	
	public void assignTablesToWaiters( )
	{
		System.out.println("Please assign waiters to tables:");
		
		for(int i=0;i<noOfOccupiedTables;i++)
		{
			System.out.println("Please enter the number of one of the availble waiters to assgin to tabel #" + (i+1) + ":");
			
			Waiter w;
			int numPicked;
			
			System.out.println("1: Name: " + employees[0].name + ", Experience:" + employees[0].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[0]).noOfTablesServed);
			System.out.println("2: Name: " + employees[1].name + ", Experience:" + employees[1].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[1]).noOfTablesServed);
			System.out.println("3: Name: " + employees[2].name + ", Experience:" + employees[2].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[2]).noOfTablesServed);
				
			numPicked = readIntFromConsole();
			w = (Waiter)employees[numPicked-1];
			
			
			while(w.noOfTablesServed>=3)
			{
				System.out.println("This waiter has reached the maximum number of tables to serve, please pick another one.");
				System.out.println("1: Name: " + employees[0].name + ", Experience:" + employees[0].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[0]).noOfTablesServed);
				System.out.println("2: Name: " + employees[1].name + ", Experience:" + employees[1].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[1]).noOfTablesServed);
				System.out.println("3: Name: " + employees[2].name + ", Experience:" + employees[2].levelOfExperience + ", # of Tables Serving:" + ((Waiter)employees[2]).noOfTablesServed);
				numPicked = readIntFromConsole();
				w = (Waiter)employees[numPicked-1];
			}
			
			w.noOfTablesServed++;
				
			employees[numPicked-1] = w;
			restaurantTables[i].assignedWaiter = (Waiter)employees[numPicked-1];
		}
	}
	
	
	public boolean isBudgetSufficient( int cost )
	{
		if(cost<budget)
			return true;
		else
			return false;
	}
	
	
	public Experience checkExperience( )
	{
		return null;
	}
	
	
	public void getClientsForNewDay( )
	{
		for(int i=0;i<noOfOccupiedTables;i++)
		{
			restaurantTables[i].clientsAtTable = new Client[2];
			
			restaurantTables[i].clientsAtTable[0] = population.pickRndClient();
			restaurantTables[i].clientsAtTable[0].placeOrder();
			
			restaurantTables[i].clientsAtTable[1] = population.pickRndClient();
			restaurantTables[i].clientsAtTable[1].placeOrder();
		}
	}
	
	
	public void upgradeEmployee(  )
	{
		System.out.println("Please enter the number of the employee to train");
		
		int numPicked;
		for(int i=0;i<employees.length;i++)
		{
			System.out.println((i+1)+": Name: " + employees[i].name + ", Experience:" + employees[i].levelOfExperience);
		}	
		numPicked = readIntFromConsole();
		
		
		while(employees[numPicked-1].levelOfExperience==Experience.HIGH)
		{
			System.out.println("This employee has reached the maximum level of experience, please pick another one.");
			for(int i=0;i<employees.length;i++)
			{
				System.out.println((i+1)+": Name: " + employees[i].name + ", Experience:" + employees[i].levelOfExperience);
			}	
			numPicked = readIntFromConsole();
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
		chef.name = System.console().readLine();
				
		System.out.println("Enter Chef Surname:");
		chef.surname = System.console().readLine();
				
		System.out.println("Enter Chef Tax Code:");
		chef.taxCode = System.console().readLine();
				
		chef.levelOfExperience = Experience.LOW;
		chef.salary = 300;
				
		employees[0] = chef;
				
		//Details for the barman
		System.out.println("Enter Barman Name:");
		employees[1].name = System.console().readLine();
				
		System.out.println("Enter Barman Surname:");
		employees[1].surname = System.console().readLine();
				
		employees[1].levelOfExperience = Experience.LOW;
		employees[1].salary = 300;
				
		//Details of waiters
		for(int i=0;i<2;i++)
		{
			System.out.println("Enter Waiter" + (i+1) + " Name:");
			employees[2+i].name = System.console().readLine();
					
			System.out.println("Enter Waiter" + (i+1) + " Surname:");
			employees[2+i].surname = System.console().readLine();
					
			employees[2+i].levelOfExperience = Experience.LOW;
			employees[2+i].salary = 200;
		}		
	}
	
	
	public void paySalaries( )
	{
		int totalSalaries = 0;
		for(int i=0;i<employees.length;i++)
		{
			totalSalaries += employees[i].salary;
		}
		budget -= totalSalaries;
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
			restaurantTables[i].number = i+1;
	}

	public void calcDayOrderCosts( )
	{
		int totalMenuItemsCosts=0;
		for(int i=0;i<noOfOccupiedTables;i++)
		{
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[0].orders.get(restaurantTables[i].clientsAtTable[0].orders.size()).itemsOrderd[0].cost;
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[0].orders.get(restaurantTables[i].clientsAtTable[0].orders.size()).itemsOrderd[1].cost;
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[1].orders.get(restaurantTables[i].clientsAtTable[1].orders.size()).itemsOrderd[0].cost;
			totalMenuItemsCosts += restaurantTables[i].clientsAtTable[1].orders.get(restaurantTables[i].clientsAtTable[1].orders.size()).itemsOrderd[1].cost;
		}
		
		weeklyOrderCosts += totalMenuItemsCosts;
	}
	
	public void paySuppliers()
	{
		budget -= weeklyOrderCosts;
		weeklyOrderCosts=0;
	}
}
