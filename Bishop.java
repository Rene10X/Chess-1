

public class Bishop {
	int x;
	int y;
	int width = 25;
	int height = 70;
	String team;
	
	Bishop(int x, int y, String team){
		this.x = x + 35;
		this.y = y + 20;	
		this.team = team;
	}
}