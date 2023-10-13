package BattleShip;

public class Destroyer extends Ship
{	
	public Destroyer( String name )
	{
		super(name);
	}
	
	public char drawShipStatusAtCell( boolean isDamaged )
    {
        return 'd';
    }
	
	public int getLength() {return 4;}

}