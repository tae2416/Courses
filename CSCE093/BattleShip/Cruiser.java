package BattleShip;

public class Cruiser extends Ship
{	
	public Cruiser( String name )
	{
		super(name);
	}
	
	public char drawShipStatusAtCell( boolean isDamaged )
    {
        return 'c';
    }
	
	public int getLength() {return 3;}

}