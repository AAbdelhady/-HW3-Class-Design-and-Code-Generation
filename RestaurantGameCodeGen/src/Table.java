public class Table
{
	public int number;
	
	public Client clientsAtTable[];
	
	public Waiter assignedWaiter;
	
	
	public int receivePaymentForTable( )
	{
		int totalPayment=0;
		totalPayment+=clientsAtTable[0].payForOrder();
		totalPayment+=clientsAtTable[1].payForOrder();
		return totalPayment;
	}
	
	public int calcRepForTable(Experience chefExp, Experience barmanExp)
	{
		int repFromTable = 0;
		
		for(int i=0;i<2;i++)
		{
			if(clientsAtTable[i].isSatisfiedWithService(assignedWaiter.levelOfExperience))
			{
				repFromTable++;
			}
			else
			{
				repFromTable--;
			}
			
			if(clientsAtTable[i].isSatisfiedWithDish(chefExp))
			{
				repFromTable++;
			}
			else
			{
				repFromTable--;
			}
			
			if(clientsAtTable[i].isSatisfiedWithBeverage(barmanExp))
			{
				repFromTable++;
			}
			else
			{
				repFromTable--;
			}
		}
		
		return repFromTable;
	}
	
	
	
}

