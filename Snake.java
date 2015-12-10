import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Snake extends JPanel implements KeyListener,ActionListener
{

	//Constant declarations
	private final static int panelWidth=500;
  	private final static int panelHeight=400;
  	private final static Color snakeColor=Color.red;
  	private final static Color insectColor=Color.black;
  	private final static Color backgroundColor=Color.white;
  	private final static int snakeLength=25;
  	private final static int refreshRate=17;
  	private final static int snakeSpeed=20;
  	private final static int locationWidth=5;

//--------------------------------------------------------------------------------
//Attribute and variables for the snake class
	private class Location
	{
		int x;
		int y;
		Location next;
		Location rear;

		public Location()
		{
			x=0;
			y=0;
			next=null;
			rear=null;
		}

		public Location(int x, int y)
		{
			this.x=x;
			this.y=y;
			next=null;
			rear=null;
		}

		public void display(Graphics g)
		{g.drawString("*", x, y);}

		public void link(Location front, Location back)
		{
			front.rear = back;
			back.next = front;			
		}

		public void move()
		{
			if(next!=null)
			{
				x=next.x;
				y=next.y;	
			}
			else
			{
				System.out.println("Error : cannot find the next member");
			}
		}
	}

	Location head;
	Location tail;
	Location insect;

	JButton reset;

	private Timer frameRefresh = new Timer(refreshRate,this);
	private Timer snakeMove = new Timer(snakeSpeed,this);

  	private String snakeDirection;

  	private Random r = new java.util.Random(); 

//--------------------------------------------------------------------------------

  	//Initialize "snake" as a linked list
  	public void initializeSnake()
  	{
  		head = new Location(panelWidth/2, panelHeight/2);
  		tail = head;
  		Location temp;

  		for(int i=1;i<snakeLength; i++)
  		{
  			temp = new Location(tail.x, tail.y+locationWidth*i);
  			tail.link(tail,temp);
  			tail=temp;
  		}	
  	}

  	//Constructor
  	public Snake()
  	{
 		reset = new JButton("Reset");
 		this.add(reset);
 		reset.addKeyListener(this);

 		initializeSnake();
  		insect = new Location(r.nextInt(panelWidth), r.nextInt(panelHeight));
  		snakeDirection = new String("up");

  		snakeMove.start();
  		frameRefresh.start();
  	}
	
  	public void changeDirection(String direction)
  	{
  		if(direction.equals("down") && (!snakeDirection.equals("up")))
  		{
  			snakeDirection = direction;
  		}

  		else if(direction.equals("up") && (!snakeDirection.equals("down")))
  		{
  			snakeDirection = direction;
  		}

  		else if(direction.equals("right") && (!snakeDirection.equals("left")))
  		{
  			snakeDirection = direction;
  		}

  		else if(direction.equals("left") && (!snakeDirection.equals("right")))
  		{
  			snakeDirection = direction;
  		}
  	}

  	public void drawSnake(Graphics g)
  	{
  		g.setColor(snakeColor);
  		Location pointer = head;
  		int offset=0;
  		while(pointer!=null)
  		{
  			pointer.display(g);
  			offset+=100;
  			pointer = pointer.rear;  			
  		}
  		g.setColor(backgroundColor);
  	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		setBackground(Color.white);
		setOpaque(true);

		//Draw the snake
		drawSnake(g);

		//Draw the insect
		g.setColor(insectColor);
		g.drawString("*",insect.x,insect.y);
		g.setColor(backgroundColor);
	}
  	
  	public void gameOver()
  	{
  		frameRefresh.stop();
  		snakeMove.stop();
  	}

  	public boolean hasEatenItself()
  	{
  		boolean flag=false;

  		Location pointer = head.rear;

  		while(pointer!=null)
  		{
  			if(Math.abs(head.x-pointer.x)<locationWidth && Math.abs(head.y-pointer.y)<locationWidth)
  			{
  				flag=true;
  				break;
  			}
  			else
  			{
  				pointer=pointer.rear;
  			}
  		}

  		return flag;
  	}

  	public void hasEatenInsect()
  	{
  		if(Math.abs(head.x - insect.x)<locationWidth && Math.abs(head.y - insect.y)<locationWidth)
  		{
  			insect.x = r.nextInt(panelWidth);
  			insect.y = r.nextInt(panelHeight);
  			
  			Location temp = new Location(tail.x, tail.y);
  			tail.link(tail,temp);
  			tail=temp;
  		}

  	}

  	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_W) changeDirection("up");
		else if(e.getKeyCode() == KeyEvent.VK_S) changeDirection("down");
		else if(e.getKeyCode() == KeyEvent.VK_D) changeDirection("right");
		else if(e.getKeyCode() == KeyEvent.VK_A) changeDirection("left");
	}

	public void keyReleased(KeyEvent e) 
	{}
	public void keyTyped(KeyEvent e) 
	{}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==frameRefresh)
			repaint();

		else if(e.getSource()==snakeMove)
		{
			//Move the snake head
			if(snakeDirection.equals("up"))
			{
				if(head.y<=0) head.y=panelHeight;
				else head.y-=locationWidth;
			}
			else if(snakeDirection.equals("down"))
			{
				if(head.y>=panelHeight) head.y=0;
				else head.y+=locationWidth;
			}
			else if(snakeDirection.equals("right"))
			{
				if(head.x>=panelWidth) head.x=0;
				else head.x+=locationWidth;
			}
			else if(snakeDirection.equals("left"))
			{
				if(head.x<=0) head.x=panelWidth;
				else head.x-=locationWidth;
			}

			//Conditionals
			if(hasEatenItself()) gameOver();
			hasEatenInsect();

			//Move the snake body
			{
				Location pointer = tail;
				while(pointer!=head)
				{
					pointer.move();
					pointer = pointer.next;
				}
			}
		}

		else if(e.getSource()==reset)
		{
			
		}
	}

}//Class definition Ends