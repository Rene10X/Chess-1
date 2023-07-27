
import java.awt.Graphics;

public class Pawn {
	
	int x;
	int y;
	int width = 30;
	int height = 50;
	String team;
	
	Pawn(int x, int y, String team){
		this.x = x + 35;
		this.y = y + 30;	
		this.team = team;
	}
	

}