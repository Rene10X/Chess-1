
import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GamePanel gamePanel;
	
	GameFrame(){
		gamePanel = new GamePanel();
		
		this.add(gamePanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Chess");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}