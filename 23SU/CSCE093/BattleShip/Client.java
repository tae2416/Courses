package BattleShip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Client
{
	final String NEWL = System.getProperty("line.separator");
	
	private String name = "Player";
	JFrame boardF, targetsF;
	PrintWriter out = null;
	BufferedReader in = null;
	GameManager man = null;
	GameBoard board = new GameBoard(10,10);
	GameBoard targets = new GameBoard(10,10);
	
	Client( GameManager manager)
	{
		man = manager;
	}

	Client( BufferedReader in, PrintWriter out, GameManager manager )
	{
		this.in = in;
		this.out = out;
		man = manager;
	}
	
	public void playGame() throws IOException
	{
		// this.out.println( NEWL + NEWL + "   Missiles Away! Game has begun" );
		// this.out.println( "   To Launch a missle at your enemy:" );
		// this.out.println( "F 2 4" );
		// this.out.println( "Fires a missile at coordinate x=2, y=4." );
		
		// while( // put Code Here to process in game commands, after each command, print the target board and game board w/ updated state )
		// {
		// 	out.println( "------------------------" );
		// 	out.println( "Target Board:" + this.targets.draw() );
		// 	out.println( "Your Ships: " + this.board.draw() );
		// 	out.println( "   Waiting for Next Command...\n\n" );
		// 	out.flush();
		board.setBackground(Color.BLACK.darker());
		
		boardF = new JFrame(name + " Board");
		boardF.setSize(board.rowCount*board.cellWidth, board.colCount*board.cellWidth+30);
		boardF.setLocation(1300, 100);
		boardF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardF.getContentPane().add(board, BorderLayout.CENTER);
		boardF.setVisible(true);

		// targets.setBackground(Color.BLACK.darker());
		// targetsF = new JFrame(name + " Targets");
		// targetsF.setSize(board.rowCount*board.cellWidth, board.colCount*board.cellWidth+30);
		// targetsF.setLocation(1200, 500);
		// targetsF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// targetsF.getContentPane().add(board, BorderLayout.CENTER);
		// targetsF.setVisible(true);


			//Perform test here to see if we have run or lost
		// }
	}
	
	//Returns a bool, true iff all of this client's ships are destroyed
	boolean allMyShipsAreDestroyed()
	{
		return true;
	}

	//Returns a bool, true iff all of the opponent's ships are destroyed
	boolean allEnemyShipsAreDestroyed()
	{
		return true;
	}

	//"F 2 4" = Fire command
	//"C Hello world, i am a chat message"
	//"D" - Redraw the latest game and target boards
	boolean processCommand() throws IOException
	{
		return true;
	}
	
	//When a fire command is typed, this method parses the coordinates and launches a missle at the enemy
	boolean processFireCmd( String [] s )
	{
		return true;
	}
	
	//Send a message to the opponent
	boolean processChatCmd( String s )
	{
		return true;
	}
	
	GameBoard getGameBoard() { return this.board; }

	GameBoard getTargetBoard() { return this.targets; }
	
	public void initPlayer() throws IOException
	{
		//1.Get player name
		//2.Print out instructions

		//Here's some nice instructions to show a client		
		System.out.println(NEWL);
		System.out.println("You will now place 2 ships. You may choose between either a Cruiser (C) " );
		System.out.println("and Destroyer (D)...\n");
		System.out.println("Enter Ship info. An example input looks like:\n");
		System.out.println("   D 2 4 S USS MyBoat\n");
		System.out.println("The above line creates a Destroyer with the stern located at x=2 (col)," );
		System.out.println("y=4 (row) and the front of the ship will point to the SOUTH (valid" );
		System.out.println("headings are N, E, S, and W.\n" );
		System.out.println("The name of the ship will be \"USS MyBoat\"\n\n");
		
		int numShipsAdded = 0;
		while (numShipsAdded < 2)
		{
			numShipsAdded++;
			System.out.print("Enter Ship " + Integer.toString(numShipsAdded) + " information: " );

			String userInput = System.console().readLine();
			String [] s = userInput.split( " ", 5 );
			String type = s[0];
			int x = Integer.parseInt( s[1] );
			int y = Integer.parseInt( s[2] );
			String heading = s[3];
			System.out.println(NEWL);

			HEADING h;
			if( heading.equals( "N") ) h = HEADING.NORTH;
			else if( heading.equals( "E" ) ) h = HEADING.EAST;
			else if( heading.equals( "S" ) ) h = HEADING.SOUTH;
			else h = HEADING.WEST;

			Ship ship = null;
			if( type.equals( "C" ) )
				ship = new Cruiser( s[4] );
			else if( type.equals( "D" ) )
				ship = new Destroyer( s[4] );
			else
			{
				System.out.println( "Invalid command" );
				System.out.println( "Invalid command...");
				System.out.flush();
			}

			if( this.board.addShip( ship, new Position(x, y), h ) )
			{
				// numShipsAdded++;
				System.out.println("Added ship " + ship.getName() + " successfully..." );
			}
			else
				System.out.println("FAILED TO ADD SHIP - It either exits game board OR collides with exsting ship!" );
		}	
		
		//Get ship locations from the player for all 2 ships (or more than 2 if you're using more ships)
				
		//After all game state is input, draw the game board to the client
		
		
		System.out.println( "Waiting for other player to finish their setup, then war will ensue!" );
	}
	
	String getName() { return this.name; }
	
	void setName(String name) 
	{
		this.name = name;
	}

	public static void main( String [] args )
	{
		
		
	}
}
