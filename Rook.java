

public class Rook {
	int x;
	int y;
	int width = 45;
	int height = 50;
	String team;
	
	Rook(int x, int y, String team){
		this.x = x + 35;
		this.y = y + 20;	
		this.team = team;
	}
}