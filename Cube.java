import javax.swing.JButton;
import javax.swing.ImageIcon;


/********************************************************************
 * The Cube is a type of JButton that represents a cube in the game 
*********************************************************************/

public class Cube extends JButton {

    // ADD YOUR INSTANCE VARIABLES HERE
    private int row;
    private int column;
    private int type;
    private ImageIcon cubeType;

    /**
     * Constructor*/

    public Cube(int row, int column, int type) {
		//YOUR CODE HERE
    
    this.row = row;
    this.column = column;
    this.type = type;
    this.setBorder(null);
    /*
    we created a private method that checks the type of the cube and changes the icon of it accordingly 
    */
    setCubeIcon(type);
    
    
    }


     //Sets the type of a square. 
	 //sets the icon of the square.
	
    public void setType(int type) {
		//Your code here
    this.type = type;
    setCubeIcon(type);
    }

 
    // Getter method for the attribute row.
    public int getRow() {
		return this.row;
    }

    //Getter method for the attribute column.
    public int getColumn() {
		return this.column; 
    }

    /*
    checks the type of the cube and changes the icon of it accordingly 
    */
    private void setCubeIcon(int type){
      switch(type){
        case 0:
          this.cubeType = new ImageIcon("icons\\square-0.png");
          break;
        case 1:
          this.cubeType = new ImageIcon("icons\\square-1.png");
          break;
        case 2:
          this.cubeType = new ImageIcon("icons\\square-2.png");
          break;
      }
      this.setIcon(cubeType);
    }
}
