import java.util.ArrayList;
import java.util.Random;

public class Client
{
	private String name;
	
	private String surname;
	
	private String telephoneNum;
	
	private String taxCode;
	
	public ArrayList<Order> orders;
	
	Client()
	{
		orders = new ArrayList<Order>();
	}
	
	public boolean isSatisfiedWithService( Experience waiterExperience )
	{
		Random rnd = new Random();
		int chance = rnd.nextInt(11);
		if(waiterExperience==Experience.HIGH)
		{
			if(chance<9)
				return true;
			else
				return false;
		}
		else if(waiterExperience==Experience.MEDIUM)
		{
			if(chance<8)
				return true;
			else
				return false;
		}
		else
		{
			if(chance<6)
				return true;
			else
				return false;
		}
	}
	
	public boolean isSatisfiedWithDish(Experience chefExperience)
	{
		Menu_Item dishOrdered = orders.get(orders.size()-1).itemsOrderd[0];
		int bonusForQuality = 0;
		int deductionForPrice = (dishOrdered.price-dishOrdered.cost)/3;
		
		if(dishOrdered.qualityLevel==Quality.HIGH)
			bonusForQuality = 2;
		
		int propOfSat;
		
		if(chefExperience==Experience.HIGH)
		{
			propOfSat = 8;
		}
		else if(chefExperience==Experience.HIGH)
		{
			propOfSat = 6;
		}
		else
		{
			propOfSat = 4;
		}
		
		propOfSat = propOfSat + bonusForQuality - deductionForPrice;
		
		Random rnd = new Random();
		int chance = rnd.nextInt(11);
		
		if(chance<propOfSat)
			return true;
		else	
			return false;
	}
	
	public boolean isSatisfiedWithBeverage(Experience barmanExperience)
	{
		Menu_Item beverageOrdered = orders.get(orders.size()-1).itemsOrderd[0];
		int bonusForQuality = 0;
		int deductionForPrice = (beverageOrdered.price-beverageOrdered.cost)/3;
		
		if(beverageOrdered.qualityLevel==Quality.HIGH)
			bonusForQuality = 2;
		
		int propOfSat;
		
		if(barmanExperience==Experience.HIGH)
		{
			propOfSat = 8;
		}
		else if(barmanExperience==Experience.HIGH)
		{
			propOfSat = 6;
		}
		else
		{
			propOfSat = 4;
		}
		
		propOfSat = propOfSat + bonusForQuality - deductionForPrice;
		
		Random rnd = new Random();
		int chance = rnd.nextInt(11);
		
		if(chance<propOfSat)
			return true;
		else	
			return false;
	}
	
	public void serveClient( )
	{
		
	}
	
	public void deliverMenuItems( )
	{
		
	}
	
	

	public void occupyTable( Table table )
	{
		
	}
	
	

	public void placeOrder( )
	{
		Order currentOrder = new Order();
		currentOrder.itemsOrderd = new Menu_Item[2]; //Assuming order consists only of 1 dish and 1 beverage
		Random rand = new Random();
		currentOrder.itemsOrderd[0] = Restaurant.dishes[rand.nextInt(5)];
		currentOrder.itemsOrderd[1] = Restaurant.beverages[rand.nextInt(5)];
		currentOrder.orderPrice = currentOrder.itemsOrderd[0].price + currentOrder.itemsOrderd[1].price;
		orders.add(currentOrder);
	}
	
	

	public int calcRepFromClient( )
	{
		return 0;
	}
	
	public int payForOrder()
	{
		return orders.get(orders.size()-1).orderPrice;
	}
}
