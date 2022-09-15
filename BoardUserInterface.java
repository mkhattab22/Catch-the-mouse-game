import javax.swing.*;


import java.awt.*;

public class BoardUserInterface extends JPanel {

    //YOUR INSTANCE VARIABLES HERE
	//private ...
	//private ...
  private GameState gameState;
  private GameLogic gameLogic;
  private Cube[][] boxes;
  private JPanel[]panels;
  final private int EVEN_ROW = 5;
  final private int ODD_ROW = 25;
  final private int CUBE_SIZE= 40;
  


  


  public BoardUserInterface(GameState gameState, GameLogic gameLogic) {
		//Your code goes here
    this.gameLogic = gameLogic;
    this.gameState = gameState;
    boxes = new Cube[gameState.getSize()][gameState.getSize()];
    panels = new JPanel[gameState.getSize()];
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    //initiating the cubes by giving it the appropiate type according to the states array created at the start of the game and adding the acction listener to each
    for (int i = 0; i < gameState.getSize(); i++) {
      for (int j = 0; j < gameState.getSize(); j++) {
        boxes[i][j] = new Cube(i, j,gameState.getCurrentStatus(i, j));
        boxes[i][j].setPreferredSize(new Dimension(CUBE_SIZE, CUBE_SIZE));
        boxes[i][j].addActionListener(gameLogic);
        
      }
    }
    
    
    //creating a panel for each row and using the FlowLayout to make sure all cubes are next to each other in a row with having to gaps in between the rows and columns
    for (int k = 0; k < panels.length; k++) {
      panels[k] = new JPanel();
      panels[k].setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
      panels[k].setBackground(Color.WHITE);
      for (int n = 0; n < gameState.getSize(); n++) {
        panels[k].add(boxes[k][n]);
      }

      //creating the empty spaces according to whether the row is an even row or a odd by using the createEmptyBorder method in BorderFactory
      //and then adding each panel to the JFrame
      if(k%2 == 0){
        panels[k].setBorder(BorderFactory.createEmptyBorder(0, EVEN_ROW, 0, EVEN_ROW));
      }else{
        panels[k].setBorder(BorderFactory.createEmptyBorder(0, ODD_ROW, 0, ODD_ROW));
      }
      add(panels[k]);
    }

    


  }
  
  
  
  

    //updates the status of the board's cubes instances following the game state
	//calls setType() from the class Cube, as needed.
    public void update(){
		//Your code goes here

    //updating the types of the cubes by using the method setType in Cube class
    Cube current;
    for (int i = 0; i < gameState.getSize(); i++) {
      for (int j = 0; j < gameState.getSize(); j++) {
        current = boxes[i][j];
        current.setType(gameState.getCurrentStatus(i, j));  
        
      }
    }
  
    }

}
