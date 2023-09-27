package BattleShip;
import BattleShip.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameManager
{
	private ArrayList<Client> clients = new ArrayList<Client>();
	private ServerSocket listener = null;
	
	public GameManager()
	{		
	}
	
	//Returns a client reference to the opponent. This way, we can inspect attributes
	//and send messages between clients... Each client has a reference to the GameManager
	//so a client is able to use this method to get a reference to his opponent
	public Client getOpponent( Client me )
	{
		return me;
	}
	
	//In a asychronous nature, begin playing the game. This should only occur after 
	//the players have been fully initialized.
	public void playGame() throws IOException
	{
		//Each player may begin firing missiles at the other player. First player to lose all ships is the loser.
		//Asynchronously process missile fire commands from each player		
		// clients.parallelStream().forEach( client -> 
		// {
		// 	try{ client.playGame(); }
		// 	catch( IOException e ) { e.printStackTrace(); } 
		// } );
		String strikeXY = "Play";
		clients.get(0).playGame();
		
		while (!(strikeXY.equals("exit") || strikeXY.equals("Exit")))
		{
			System.out.println("\n");
			System.out.println("Let's shoot at them!"+"\n");

			System.out.print("Where do you want to shoot?: ");
			strikeXY = System.console().readLine();
			System.out.println(strikeXY);
			System.out.println(!strikeXY.equals("exit"));

			if (!(strikeXY.equals("exit") || strikeXY.equals("Exit")))
			{
				String [] s = strikeXY.split( " ", 3 );
				int x = Integer.parseInt( s[0] );
				int y = Integer.parseInt( s[1] );

				GameBoard b = clients.get(0).getGameBoard();
				GameBoard t = clients.get(0).getTargetBoard();
				if (b.cells.get(x-1).get(y-1).ship == null)
				{
					if (b.cells.get(x-1).get(y-1).shotAt)
						System.out.println("You already shot that spot, dummy!!");
					else
					{
						b.cells.get(x-1).get(y-1).shotAt = true;
						System.out.println("You missed, loser!");
					}

				}
				else
				{
					if (b.cells.get(x-1).get(y-1).struckByMissle)
						System.out.println("You already shot that spot, dummy!!");
					else
					{
						b.cells.get(x-1).get(y-1).struckByMissle = true;
						System.out.println("It's a hit!");	
					}

					if (!b.cells.get(x-1).get(y-1).ship.isAlive())
						if (b.cells.get(x-1).get(y-1).ship.getLength()<4)
							System.out.println("Cruiser sunk!");
						else
							System.out.println("Destroyer sunk!");
				}
				clients.get(0).boardF.repaint();
			}
			
		}
		
		
	}
	
	//Create a server listener socket and wait for two clients to connect.
	//Use the new client socket to create a PrintWriter and BufferedReader
	//so you can pass these two streams into the constructor of a new client.
	//Don't forget about try/finally blocks, if needed
	boolean waitFor2PlayersToConnect() throws IOException
	{
		// Socket clientSocket = listener.accept();
		// PrintWriter out = new PrintWriter( clientSocket.getOutputStream(), true );
		// BufferedReader in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream() ) );
		// Client c = new Client( in, out, this );
		Client c = new Client(this);
		clients.add(c);
		
		return true;
	}
	
	//let players initialize their name, and gameboard here. This should be done asynchronously
	void initPlayers() throws IOException
	{
		Client c = clients.get(0);
		System.out.print("Enter your player name: ");
		String nickname = System.console().readLine();

		c.setName(c.getName() + " " + Integer.toString(clients.indexOf(c)+1) + ":  " + nickname);
		c.initPlayer();
		System.out.println("New player has joined...");
	}
	
	
	//Main driver for the program... Hit Crtl-F11 in eclipse to launch the server...
	//Of course, it has to compile first...
	public static void main( String [] args ) throws IOException
	{
		GameManager m = new GameManager();
		
		System.out.println( "<<<---BattleShip--->>>" );
		System.out.println( "Waiting for two players to connect to TCP:10000" );

		// GameBoard b = new GameBoard( 10, 10 );	
		
		// b.setBackground(Color.BLACK.darker());
		
		// var frame = new JFrame("Battleship Board");
		// frame.setSize(b.rowCount*b.cellWidth, b.colCount*b.cellWidth+30);
		// frame.setLocation(1000, 100);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.getContentPane().add(b, BorderLayout.CENTER);
		// frame.setVisible(true);
	
		m.waitFor2PlayersToConnect();
		System.out.println( "Clients have joined!!!");		
		m.initPlayers();
		// System.out.println( m.clients.get(0).getName());// + " vs " + m.clients.get(1).getName() + " Let's Rumble..." );
		m.playGame();		
		System.out.println( "Shutting down server now... Disconnecting Clients..." );
	}

}
