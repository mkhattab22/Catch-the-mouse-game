

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;

//This class implements the interface ActionListener so that it is called when the player makes a move. 
//It calculates the next step of the game
//It updates state and userInterface.


public class GameLogic implements ActionListener {

 // ADD YOUR INSTANCE VARIABLES HERE

 private GameState state;
 private GameUserInterface gui;
 private BoardUserInterface UI;
 private int size;
 private boolean win = false;
 private Cube selected;


    public GameLogic(int size) { //The parameter size is the size of the board on which the game will be played
	// YOUR CODE HERE
	// It creates the game's userInterface and the game's state instances
        state = new GameState(size);
        this.size = size;
        
}

  
    /**
     * Starts the game
     */
    public void start(){
	// YOUR CODE HERE
        
    //Randomly selecting the snakes and the mouse by calling the methods with a special row and col numbers that we made sure wouldn't be used by the user
        state.setCube(-195, -195);
        state.select(-195, -195);
        gui = new GameUserInterface(state, this);
        UI = gui.getBoardUserInterface();
    }

 
    /**
     * resets the game
     */
    public void reset(){
	// YOUR CODE HERE

    /*
    getting rid of the JFrame components by calling the method dispose() and getting rid of the current GameState object by setting it to null and 
    setting a new GameState object and calling start() to start the game once again     
    */
    gui.setVisible(false);
    gui.dispose();
    state = null;
    state = new GameState(size);
    start();

    }


    public void actionPerformed(ActionEvent e) {
	//the logic of the game goes in this method        
	// YOUR CODE HERE
    
    //checking if there was any previous selected cubes so we can get rid of the border around it and setting the new selected cube to its own border after
    if(selected != null){
        selected.setBorder(null);
    }
    if(e.getSource() == gui.getReset()){
        reset();
    }else if(e.getSource() == gui.getQuit()){
        gui.setVisible(false);
        gui.dispose();
    }else if(e.getSource() instanceof Cube){
        selected = (Cube)e.getSource();
        //making sure the player can extinguish which cube is selected by setting a border to the current selected cube only
        selected.setBorder(BorderFactory.createEtchedBorder(1));
        int row = selected.getRow();
        int col = selected.getColumn();

        //checking if selected cube is a free cube, snake or a mouse
        if(state.getCurrentStatus(row, col) == 2){
            /*
            if mouse is selected and one of the six cubes around it is a snake the player wins
            we created a private method validate(int row, int col) to check if selected cube is next to a snake. if its next to one it returns true 
            otherwise it returns false
            */
            if(validate(row, col) == true){

                JOptionPane.showMessageDialog(gui, "You win the game!");
                reset();
                win = false;
            }
        }else if(state.getCurrentStatus(row, col) == 0){
            /*
            checks if a free cube is next to a snake and if true it changes the status of it to a snake and then randomly choose a new location 
            to the mouse and checks if that new location is a free cube and if true it changes the location of the cube to it
            and checking if the new location is on the edge of the board and if true the game ends as it is declared to be a loss and then it resets
            otherwise the game contiues
            */
            if(validate(row, col) == true){
                win = false;
                state.select(row, col);
                int x = state.pubRand(size);
                int y = state.pubRand(size);
                while(state.getCurrentStatus(x, y) !=0){
                    x = state.pubRand(size);
                    y = state.pubRand(size);
                }
           
            state.setCube(x, y);
            
            UI.update();
            if(state.getCurrentCube().getY() == 0 || (state.getCurrentCube().getX() == 0 || state.getCurrentCube().getX() == size-1) ||   state.getCurrentCube().getY() == size-1 ){
                JOptionPane.showMessageDialog(gui, "You lost the Game. Give it another try.");
                reset();
            }

            }
        }
    
    }
    }
    /*
    checks if a cube is next to a snake. returns true if it is next to one and false otherwise
    */ 
    private boolean validate(int row, int col) {
        if (row % 2 == 0){
            if(row-1 > -1){
                if (state.getCurrentStatus(row-1,col) == 1){
                    win = true;
                }
                if (col-1 > -1){
                    if (state.getCurrentStatus(row-1,col-1) == 1){
                        win = true;
                }
                }
            }
            if (row +1 <size){
                if (state.getCurrentStatus(row+1,col) == 1){
                    win = true;
                }
                if(col-1 > -1){
                    if (state.getCurrentStatus(row+1,col-1) == 1){
                        win = true;
                    }
                }
            }
            if(col+1 < size){
                if (state.getCurrentStatus(row,col+1) == 1){
                    win = true;
                }
            }
            if(col-1 > -1){
                if (state.getCurrentStatus(row,col-1) == 1){
                    win = true;
                }
            }
    }else if (row % 2 != 0){
        if(row-1 > -1){
            if (state.getCurrentStatus(row-1,col) == 1){
                win = true;
            }
            if (col+1 <size){
                if (state.getCurrentStatus(row-1,col+1) == 1){
                    win = true;
            }
            }
        }
        if (row +1 <size){
            if (state.getCurrentStatus(row+1,col) == 1){
                win = true;
            }
            if(col+1 <size){
                if (state.getCurrentStatus(row+1,col+1) == 1){
                    win = true;
                }
            }
        }
        if(col+1 < size){
            if (state.getCurrentStatus(row,col+1) == 1){
                win = true;
            }
        }
        if(col-1 > -1){
            if (state.getCurrentStatus(row,col-1) == 1){
                win = true;
            }
        }
    }
    if (win == true){
        return true;
    }else{
        return false;
    }
    }
}
