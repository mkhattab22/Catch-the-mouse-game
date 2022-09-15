import java.util.Random;


public class GameState {


		//static final variables public
		public static final int FREE_CUBE = 0;
		public static final int SELECTED = 1;
		public static final int RED_CUBE = 2;
		public static final int MAX_SELECTED = 5;
		
		//non-final variables private
		private int boardSize;
		private Point redCube;
		// YOUR INSTANCE VARIABLES HERE
    private int[][] states;
    private  Random rand;

    /**
     * Constructor 
	 * initializes the state to the size of board
     *  The parameter size is the size of the board
     */
    public GameState(int size) {
	// YOUR CODE HERE
      this.boardSize = size;
      states = new int[size][size];
      rand = new Random();
    
    }

	//



    public  int getSize(){
	//YOUR CODE HERE
		return boardSize;
   }

    /**
     * returns the current status (FREE_CUBE, SELECTED or RED_CUBE) of a given cube in the game
     * 
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     * return the status of the cube at location (i,j)
     */   
    public int getCurrentStatus(int i, int j){
		return states[i][j];
    }


    /**
     * Sets the status of the cube at coordinate (i,j) to SELECTED
     * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */   
    public void select(int i, int j){
	//YOUR CODE HERE

  //checks for the odd case that is only used in the begging of the game to randomly select the snakes by each cube having a 10% chance of becoming a snake
  //only a maximum of 5 snakes is created
  if ( i == -195 && j == -195){
    int[] prob = new int[10];
    for (int k = 0; k < prob.length; k++) {
      if(k == 5){
        prob[k] = 1;
      }else{
        prob[k]= 0;
      }
    }
    int counter = 0;
    for (int k = 0; k < states.length; k++) {
      for (int k2 = 0; k2 < states.length; k2++) {
        if (counter != 5){
          int num = rand.nextInt(10);
          if(getCurrentStatus(k, k2) == 0){
          states[k][k2] = prob[num];
          if (prob[num] == 1)
            counter++;
          }
        }
      }
    }
  }//else it simply changes the status of the the given row,col to 1 which indicates a snake
  else{
  states[i][j] = 1;
  }
    }

    /**
     * Puts the red cube at coordinate (i,j). Clears the previous location 
     * of the red cube.
     *
      * i is the x coordinate of the cube
     * j is the y coordinate of the cube
     */   
    public void setCube(int i, int j){
	//YOUR CODE HERE

  //checks for the odd case that is only used in the begging of the game to randomly select a location for the mouse which is located in the center of the board 
  //that is would be different for an even sized board or a odd one
  if (i == -195 && j == -195) {
      if(boardSize %2 == 0){
        
        int x1 = generateRandom((boardSize/2)-1, boardSize/2);
        int y1 = generateRandom((boardSize/2)-1, boardSize/2);
        states[x1][y1] = 2;
        redCube = new Point(x1, y1);
      }else{
        
        int x2 = generateRandom((boardSize/2)-1, (boardSize/2)+1);
        int y2 = generateRandom((boardSize/2)-1, (boardSize/2)+1);
        states[x2][y2] = 2;
        redCube = new Point(x2, y2);
      }
  }// else it changes the current location of the mouse to the location at (i,j) and making the current location of the mouse a free cube
  //and setting the x and y variables of the redCube by calling reset(int row,int col)
  else{
  
    states[redCube.getX()][redCube.getY()] = 0;
    states[i][j] = 2;
    redCube.reset(i, j);
  
  }
  
  
   }

   //method used for generating a number between a min and max. mainly used for randomly locating the red cube and the start of the game 
   public int generateRandom(int min, int max){
		//aRandom.nextInt(n) will generate a random number between 0 and n-1
		//so we adjust to get a random between min and max
		int randomNumber = rand.nextInt((max-min)+1)+min;
		return randomNumber;
	}

  //a public method that is using the Random nextInt(int bound) method that randomly chooses a number between 0 and bound(exclusive)
  //mainly used in locating the new location of the red cube whenever a valid free cube turns into a snake
  public int pubRand(int bound){
    return rand.nextInt(bound);
  }

    /* Getter method for the current red cube
     * return the location of the curent red cube */   
    public Point getCurrentCube(){
		return redCube;
    }

    }

