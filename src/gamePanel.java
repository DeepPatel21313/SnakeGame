import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.util.Random;

public class gamePanel extends JPanel implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS  = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY= 90;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int previousx;
	int previousy;
	private JButton newGameBtn;
	private JFrame frame2;
	private JPanel panel2;
	int bodyParts = 6;
	int foodEaten;
	int foodX;
	int foodY;
	int highscore=0;
	char direction;
	boolean moving = false;
	Timer timer;
	Random random;
	
			
	gamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(new Color(255,232,223));
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		
		timer=new Timer(DELAY,this);
		newGameBtn = new JButton();
		frame2 = new JFrame();
		panel2 = new JPanel();
		
		startGame();
		
		
	}
	public void startGame() {
		newfood();
		moving=true;
		timer.start();
		direction = 'R';
		
		
	}	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		
		
		for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
			
			for(int a=0;a<SCREEN_WIDTH/UNIT_SIZE;a++) {
			if(i%2!=0) {
				if(a%2!=0) {
					g.setColor(new Color(51,255,51));
					g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
					g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				}
				else {
				g.setColor(new Color(0,204,0));
				//g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				//g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH,i*UNIT_SIZE);
				g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				//g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				
			}
			else {
			if(a%2==0) {
				g.setColor(new Color(51,255,51));
				g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
			}
			else {
			g.setColor(new Color(0,204,0));
			//g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
			//g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH,i*UNIT_SIZE);
			g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
			g.fillRect(i*UNIT_SIZE, a*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
			//g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}}}
		}
		
		if(moving) {
			
			
			
			g.setColor(new Color(246,75,75));
			g.fillOval(foodX+3, foodY+3, UNIT_SIZE-3, UNIT_SIZE-3);
			g.setColor(new Color(87,48,58));
			g.fillRect(foodX+10, foodY, UNIT_SIZE/5-1, UNIT_SIZE/3+1);
			g.setColor(new Color(0,255,85));
			g.fillOval(foodX+14, foodY, UNIT_SIZE/5+1, UNIT_SIZE/4+1);
			
			for(int i=0;i<bodyParts;i++) {
				if (i==0) {
					g.setColor(new Color(0,0,255));
					g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE,100,100);
					
					if (direction =='R') {
						g.setColor(new Color(255,255,255));
						g.fillOval(x[i]+1,y[i]+1,UNIT_SIZE-15,UNIT_SIZE-15);
						g.fillOval(x[i]+1,y[i]+12,UNIT_SIZE-15,UNIT_SIZE-15);
						g.setColor(new Color(0,0,0));
						g.fillOval(x[i]+1,y[i]+1,UNIT_SIZE-18,UNIT_SIZE-18);
						g.fillOval(x[i]+1,y[i]+12,UNIT_SIZE-18,UNIT_SIZE-18);
						g.setColor(new Color(255,0,0));
						g.fillRoundRect(x[i]+20,y[i]+7,UNIT_SIZE-8,UNIT_SIZE-15,100,4);
						
					}
					else if (direction=='L') {
						g.setColor(new Color(255,255,255));
						g.fillOval(x[i]+10,y[i]+1,UNIT_SIZE-15,UNIT_SIZE-15);
						g.fillOval(x[i]+10,y[i]+12,UNIT_SIZE-15,UNIT_SIZE-15);
						g.setColor(new Color(0,0,0));
						g.fillOval(x[i]+10,y[i]+1,UNIT_SIZE-18,UNIT_SIZE-18);
						g.fillOval(x[i]+10,y[i]+12,UNIT_SIZE-18,UNIT_SIZE-18);
						g.setColor(new Color(255,0,0));
						g.fillRoundRect(x[i]-10,y[i]+7,UNIT_SIZE-8,UNIT_SIZE-15,100,4);
					}
					else if (direction=='U') {
						g.setColor(new Color(255,255,255));
						g.fillOval(x[i]+3,y[i]+10,UNIT_SIZE-15,UNIT_SIZE-15);
						g.fillOval(x[i]+15,y[i]+10,UNIT_SIZE-15,UNIT_SIZE-15);
						g.setColor(new Color(0,0,0));
						g.fillOval(x[i]+3,y[i]+10,UNIT_SIZE-18,UNIT_SIZE-18);
						g.fillOval(x[i]+15,y[i]+10,UNIT_SIZE-18,UNIT_SIZE-18);
						g.setColor(new Color(255,0,0));
						g.fillRoundRect(x[i]+9,y[i]-10,UNIT_SIZE-15,UNIT_SIZE-8,4,100);
					}
					else if (direction=='D') {
						g.setColor(new Color(255,255,255));
						g.fillOval(x[i]+3,y[i]+1,UNIT_SIZE-15,UNIT_SIZE-15);
						g.fillOval(x[i]+15,y[i]+1,UNIT_SIZE-15,UNIT_SIZE-15);
						g.setColor(new Color(0,0,0));
						g.fillOval(x[i]+3,y[i]+1,UNIT_SIZE-18,UNIT_SIZE-18);
						g.fillOval(x[i]+15,y[i]+1,UNIT_SIZE-18,UNIT_SIZE-18);
						g.setColor(new Color(255,0,0));
						g.fillRoundRect(x[i]-10,y[i]+7,UNIT_SIZE-15,UNIT_SIZE-8,4,100);
					}
					
				}
				else {
					g.setColor(new Color(0,0,255));
					//g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE,4,4);
					if(i==bodyParts) {
				
						if (direction =='R') {
							g.fillRoundRect(x[i], y[i], UNIT_SIZE/3, UNIT_SIZE,100,4);
						}
						else if (direction=='L') {
							g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE,4,4);
						}
						else if (direction=='U') {
							g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE,4,4);
						}
						else if (direction=='D') {
							g.fillRoundRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE,4,4);
						}
						
					}
					}
				}
			g.setColor(Color.RED);
			g.setFont(new Font("Ink Free",Font.BOLD,40));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+foodEaten, (SCREEN_WIDTH-metrics.stringWidth("Score: "+foodEaten))/2,g.getFont().getSize());
			}
		else {
			gameOver(g);
		}
		}
	public void newfood() {
		foodX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		foodY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	public void move() {
		for(int i = bodyParts; i>0; i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
					
		}
		switch(direction) {
		case 'U':
			y[0] = y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0]+UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0]-UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0]+UNIT_SIZE;
			break;
		}
		previousx=x[0];
		previousy=y[0];
	}
	public void checkFood() {
		if((x[0]==foodX)&&(y[0]==foodY)){
			bodyParts++;
			foodEaten++;
			if(highscore<foodEaten) {
				highscore=foodEaten;
			}
			
			newfood();
		}
	}
	public void checkCOllisions() {
		
		//check if head collides with body
		for(int i=bodyParts;i>0;i--) {
			if((x[0]==x[i]) && (y[0]==y[i])) {
				moving=false;
				
			}
		}
		//check if head touches left border
		if(x[0]<0) {
			//moving=false;
			x[0]=24*UNIT_SIZE;
			
		}
		//right border
		if(x[0]>SCREEN_WIDTH) {
			//moving=false;
			x[0]=0;
		}
		//top border
		if(y[0]<0) {
			//moving=false;
			y[0]=24*UNIT_SIZE;
		}
		//bottom border
		if(y[0]>SCREEN_HEIGHT) {
			//moving=false;
			y[0]=0;
		}
		if(!moving) {
			timer.stop();
		}
		
	}
	public void gameOver(Graphics g) {
		//game over dialog
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free",Font.BOLD,75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH-metrics.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
		
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free",Font.BOLD,40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: "+foodEaten, (SCREEN_WIDTH-metrics2.stringWidth("Score: "+foodEaten))/2,g.getFont().getSize());
		
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free",Font.BOLD,50));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
		g.drawString("High Score: "+highscore, (SCREEN_WIDTH-metrics3.stringWidth("High Score: "+highscore))/2,g.getFont().getSize()+400);
		
		
		newGameBtn.setText("New Game");
		newGameBtn.setFont(new Font("Comic Sans MS",Font.BOLD,60));
		newGameBtn.setSize(40, 40);
		Border border1=new LineBorder(new Color(0x16A4DD));
		newGameBtn.setBounds(100,60,450,100);
		newGameBtn.setForeground(Color.RED);
		newGameBtn.setBorder(border1);
		newGameBtn.setBackground(new Color(0,239,255));
		newGameBtn.addActionListener(this);
		panel2.add(newGameBtn);
		frame2.add(panel2);
		frame2.setTitle("Snake");
		frame2.setResizable(false);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.pack();
		frame2.setVisible(true);
		frame2.setLocation(600, 200);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (moving) {
			move();
			checkFood();
			checkCOllisions();
			 
		}
		
		Object selected = e.getSource();
		if(selected.equals(newGameBtn)) {
			frame2.setVisible(false);
			
			bodyParts=6;
			foodEaten=0;
			x[0]=UNIT_SIZE;
			y[0]=UNIT_SIZE;
			startGame();
			
			
		}
		repaint();
	}
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction!='R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_A:
				if(direction!='R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction!='L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_D:
				if(direction!='L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction!='D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_W:
				if(direction!='D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction!='U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_S:
				if(direction!='U') {
					direction = 'D';
				}
				break;
			}	 	 
		}
	
	}
}
