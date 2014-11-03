public abstract class Employee
{
	public String name;
	
	public String surname;
	
	public Experience levelOfExperience;
	
	public int salary;
	
	public void calcSalary( )
	{
		if(this instanceof Waiter)
		{
			if(levelOfExperience==Experience.MEDIUM)
			{
				salary = 300;
			}
			else if(levelOfExperience==Experience.HIGH)
			{
				salary = 400;
			}
		}
		else
		{
			if(levelOfExperience==Experience.MEDIUM)
			{
				salary = 400;
			}
			else if(levelOfExperience==Experience.HIGH)
			{
				salary = 500;
			}
		}
		
	}
	
	public String getEmployeeType( )
	{
		if(this instanceof Chef)
			return "Chef";
		else if(this instanceof Barman)
			return "Barman";
		else
			return "Waiter";
	}
	

	
	
}

