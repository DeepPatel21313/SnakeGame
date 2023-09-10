import javax.swing.JFrame;
import java.awt.*;
public class gameFrame extends JFrame{
	gameFrame(){
		this.add(new gamePanel());
		this.setTitle("Snake");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		
		
	}
}
