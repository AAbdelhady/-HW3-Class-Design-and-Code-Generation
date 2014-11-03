import java.util.Random;

public class People
{
	private Client population[];
	
	People()
	{
		population = new Client[18];
	}
	
	public Client pickRndClient( )
	{
		Random rand = new Random();
		return population[rand.nextInt(18)];
	}
	
	
}
