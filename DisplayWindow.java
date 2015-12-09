import java.awt.*;
import javax.swing.*;
/**
 *  A class that puts a graphics window on your display
*/
public  class DisplayWindow extends JFrame{
 /**
   * Content pane that will hold the added Jpanel
   */
  private Container c;
  /**
   * DisplayWindow constructor - no parameters
   */
  public DisplayWindow(){
    super("Display");
    c = this.getContentPane();
  }

  public DisplayWindow(String titleText){
    super(titleText);
    c = this.getContentPane();
  }
  
   /**
 * Adds panel to content pane
 * @parameter the panel to be added
 */
  public void addPanel(JPanel p){
    c.add(p);
  }
  /**
 * consolidates the frame, makes it visible,
 * causes program termination when window is closed manually
 */
  public void showFrame(){
    this.pack();
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}