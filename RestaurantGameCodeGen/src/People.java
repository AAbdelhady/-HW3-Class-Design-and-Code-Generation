import java.util.Random;

public class People
{
	public Client population[];
	
	public Client pickRndClient( )
	{
		Random rand = new Random();
		return population[rand.nextInt(18)];
	}
	
	
}
