/**   The driver class that instantiates the DisplayWindow
 *    and SaucerPanel, adds the panel to the window, then
 *    makes the call to showFrame to display the panel.
*/
public class SnakeDriver{

  public static void main(String[] args){
    DisplayWindow d = new DisplayWindow("Saucer");
    Snake p = new Snake();
    d.addPanel(p);
    d.showFrame();
  }
}