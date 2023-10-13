package BattleShip;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameBoard extends JPanel
{
	int rowCount = 10;
	int colCount = 10;
	int cellWidth = 50;
	
	final String LINE_END = System.getProperty("line.separator"); 
	
	ArrayList< ArrayList< Cell > > cells;
	ArrayList< Ship > myShips = new ArrayList<Ship>();
	
	public GameBoard( int rowCount, int colCount )
	{
		this.rowCount = rowCount;
		this.colCount = colCount;

		cells = new ArrayList< ArrayList< Cell > >();
		for( int i = 0; i < this.rowCount; ++i )
			cells.add( new ArrayList< Cell >() );
		
		for( int rows = 0; rows < cells.size(); ++rows )
			for( int cols = 0; cols < this.colCount; ++cols )
				cells.get(rows).add( cols, new Cell() );
		
		//create the 2D array of cells
	}
	
	// public String draw()
	// {

	// 	//draw the entire board... I'd use a StringBuilder object to improve speed
	// 	//remember - you must draw one entire row at a time, and don't forget the
	// 	//pretty border...
	// 	return "this";
	// }
	
	//add in a ship if it fully 1) fits on the board and 2) doesn't collide w/
	//an existing ship.
	//Returns true on successful addition; false, otherwise
	public boolean addShip( Ship s , Position sternLocation, HEADING bowDirection )
	{
		myShips.add(s);
		cells.get(sternLocation.x-1).get(sternLocation.y-1).setShip(s);
		s.setPosition(cells.get(sternLocation.x-1).get(sternLocation.y-1));
		System.out.println("Lenghth of " + s.getName() + " is " + Integer.toString(s.getLength()));
		for (int i = 1; i < s.getLength(); i++)
		{
			switch(bowDirection) 
			{
				case NORTH:
				  // code block
				  cells.get(sternLocation.x-1).get(sternLocation.y-1+i).setShip(s);
				  s.setPosition(cells.get(sternLocation.x-1).get(sternLocation.y-1+i));
				  break;
				case SOUTH:
				  // code block
				  cells.get(sternLocation.x-1).get(sternLocation.y-1-i).setShip(s);
				  s.setPosition(cells.get(sternLocation.x-1).get(sternLocation.y-1-i));
				  break;
				case WEST:
				  // code block
				  cells.get(sternLocation.x-1-i).get(sternLocation.y-1).setShip(s);
				  s.setPosition(cells.get(sternLocation.x-1-i).get(sternLocation.y-1));
				  break;
				case EAST:
				  // code block
				  cells.get(sternLocation.x-1+i).get(sternLocation.y-1).setShip(s);
				  s.setPosition(cells.get(sternLocation.x-1+i).get(sternLocation.y-1));
				  break;
			}
		}		

		return true;
	}
	
	//Returns A reference to a ship, if that ship was struck by a missle.
	//The returned ship can then be used to print the name of the ship which
	//was hit to the player who hit it.
	//Ensure you handle missiles that may fly off the grid
	public void fireMissle( Position coordinate )
	{
		
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);

		draw(g);
    }

	public void draw(Graphics g) {	
		g.setColor(Color.WHITE);		
        for (int i = 1; i < rowCount; i++)
		{
			g.drawLine(0, cellWidth*i, rowCount*cellWidth, cellWidth*i);
			g.drawLine(cellWidth*i, 0, cellWidth*i, colCount*cellWidth);
		}

		for (int j = 0; j < rowCount; j++)
		{
			for (int k = 0; k < colCount; k++)
			{
				if (cells.get(j).get(k).getShip() != null)
				{
					g.setColor(Color.WHITE);
					g.drawRect(j*cellWidth+5, (9-k)*cellWidth+5, 40, 40);
				}
				if (cells.get(j).get(k).struckByMissle)
				{
					g.setColor(Color.RED);
					g.drawRect(j*cellWidth+5, (9-k)*cellWidth+5, 40, 40);
					g.fillOval(j*cellWidth+10, (9-k)*cellWidth+10, 30, 30);
				}
				if (cells.get(j).get(k).shotAt)
				{
					g.setColor(Color.GREEN);
					g.fillRect(j*cellWidth+10, (9-k)*cellWidth+10, 30, 30);
				}
				
			}
		}
			
    }

	//Here's a simple driver that should work without touching any of the code below this point
	public static void main( String [] args )
	{
		// System.out.println( "Hello World." );
		// GameBoard b = new GameBoard( 10, 10 );	
		
		// SwingUtilities.invokeLater(() -> {
        //     b.setBackground(Color.BLACK.darker());
			
        //     var frame = new JFrame("Battleship Board");
        //     frame.setSize(b.rowCount*b.cellWidth, b.colCount*b.cellWidth+30);
        //     frame.setLocation(1000, 100);
		// 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //     frame.getContentPane().add(b, BorderLayout.CENTER);
        //     frame.setVisible(true);
        // });
		
		// Ship s = new Cruiser( "Cruiser" );
		// if( b.addShip(s, new Position(3,6), HEADING.WEST ) )
		// 	System.out.println( "Added " + s.getName() + "Location is " );
		// else
		// 	System.out.println( "Failed to add " + s.getName() );
		
		// s = new Destroyer( "Vader" );
		// if( b.addShip(s, new Position(3,5), HEADING.NORTH ) )
		// 	System.out.println( "Added " + s.getName() + "Location is " );
		// else
		// 	System.out.println( "Failed to add " + s.getName() );
		
		// System.out.println( b.draw() );
		
		// b.fireMissle( new Position(3,5) );
		// System.out.println( b.draw() );
		// b.fireMissle( new Position(3,4) );
		// System.out.println( b.draw() );
		// b.fireMissle( new Position(3,3) );
		// System.out.println( b.draw() );
		
		// b.fireMissle( new Position(0,6) );
		// b.fireMissle( new Position(1,6) );
		// b.fireMissle( new Position(2,6) );
		// b.fireMissle( new Position(3,6) );
		// System.out.println( b.draw() );
		
		// b.fireMissle( new Position(6,6) );
		// System.out.println( b.draw() );
	}

}
