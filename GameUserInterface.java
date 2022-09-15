import javax.swing.*;
import java.awt.*;

 /*the class GameUserInterface provides the user interface of the Game. It extends
 JFrame and lays out an instance of BoardUserInterface and  two instances of JButton: one to reset and the second the quit the game at any time.*/

public class GameUserInterface extends JFrame {

	private JButton reset;
	private JButton quit;
	// ADD YOUR INSTANCE VARIABLES HERE
	//ALL PRIVATE
	private GameState state;
	private GameLogic gameLogic;
	private BoardUserInterface userInterface;

 
    /* Constructor 
	 * initializes the board
     * takes as parameters the state of the game and the gameLogic */

    public GameUserInterface(GameState state, GameLogic gameLogic) {
		//Your code here
		setBackground(Color.WHITE);
		
		//setting the title of the game and the JFrame 
		setTitle("Game - Catch the Mouse!");
		this.state = state;
		this.gameLogic = gameLogic;
		setResizable(false);
		
		this.setLayout(new BorderLayout() );
		setSize((state.getSize()*40)+34,(state.getSize()*40)+75);
		JPanel quitAndReset = new JPanel();
		quitAndReset.setLayout(new FlowLayout());
		quitAndReset.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reset = new JButton("Reset");
		quit = new JButton("Quit");
		reset.addActionListener(gameLogic);
		quit.addActionListener(gameLogic);
		quitAndReset.add(reset);
		quitAndReset.add(quit);
		userInterface = new BoardUserInterface(state,gameLogic);
		//adding all the rows 'panels' to the JFrame
		this.add(userInterface, BorderLayout.NORTH);
		this.add(quitAndReset,BorderLayout.CENTER);
		
		//setting JFrame to visible
		this.setVisible(true);
		
		

    }


	public JButton getReset(){
		return this.reset;
	}
	public JButton getQuit(){
		return this.quit;
	}
    public BoardUserInterface getBoardUserInterface(){
		//YOUR CODE HERE
		return this.userInterface;
   }

}
