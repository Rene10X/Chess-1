
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, MouseListener{
	
	final int  screenWidth = 800;
	final int  screenHeight = 800;
	int delay = 100;
	public int[][] boardPieces = new int[8][8];
	public int[][] boardLocationsX = new int[8][8];
	public int[][] boardLocationsY = new int[8][8];
	public int[][] selectableLocations = new int[8][8];
	Color lightGreen = new Color(0x80FF00);
	public boolean firstClick = true;
	int selectedLocationX;
	int selectedLocationY;
	int[] selectableLocationsX = new int[8];
	int[] selectableLocationsY = new int[8];
	int piece;
	int possibleMoves;
	
	Color blackSquare = new Color(118,150,86);
	Color whiteSquare = new Color(238,238,210);
	Color selectedSquareColor = new Color(186,202,68);
	
	Pawn pawn;
	Knight knight;
	Rook rook;
	Bishop bishop;
	Queen queen;
	King king;
	
	Timer timer = new Timer(delay, this);
	
	GamePanel(){
		
		// locations logic ------------------------------------
		
		int boardLocationX = 0;
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				boardLocationsX[j][i] = boardLocationX;
				if(boardLocationX < 700) {
					boardLocationX += 100;
				} else {
					boardLocationX = 0;
				}
			}
		}
		
		int boardLocationY = 0;
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				boardLocationsY[i][j] = boardLocationY;
				if(boardLocationY < 700) {
					boardLocationY += 100;
				} else {
					boardLocationY = 0;
				}
			}
		}
		
		//-------------------------------------------------------------
		//turing all 0 to 50 in selected locations X and Y !!!!DELETE LATER!!!!
		
		for(int i = 0; i < 8; i++) {
			if(selectableLocationsX[i] == 0) {
				selectableLocationsX[i] = 50;
			} else if(selectableLocationsY[i] == 0) {
				selectableLocationsY[i] = 50;
			}
		}
		
		// initializePieces
		
		//pawns
		for(int i = 0; i <= 7; i++) {
			boardPieces[i][1] = 1;
		}
		for(int i = 0; i <= 7; i++) {
			boardPieces[i][6] = 2;
		}
		//knights
		boardPieces[1][0] = 3;
		boardPieces[6][0] = 3;
		
		boardPieces[1][7] = 4;
		boardPieces[6][7] = 4;
		//rooks
		boardPieces[0][0] = 5;
		boardPieces[7][0] = 5;
		
		boardPieces[0][7] = 6;
		boardPieces[7][7] = 6;
		//bishops
		boardPieces[2][0] = 7;
		boardPieces[5][0] = 7;
		
		boardPieces[2][7] = 8;
		boardPieces[5][7] = 8;
		//queen
		boardPieces[3][0] = 9;
		
		boardPieces[3][7] = 10;
		//king
		boardPieces[4][0] = 11;
		
		boardPieces[4][7] = 12;
		
		timer.start();
		
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		
		//drawing squares
		
		//black squares
		for(int i = 0; i <= 7; i += 2) {
			for(int j = 1; j <= 7; j += 2) {
				g.setColor(blackSquare);
				g.fillRect(j  * 100, i * 100, 100, 100);
			}
		}
		for(int i = 1; i <= 7; i += 2) {
			for(int j = 0; j <= 7; j += 2) {
				g.setColor(blackSquare);
				g.fillRect(j  * 100, i * 100, 100, 100);
			}
		}
		
		//white squares
		for(int i = 0; i <= 7; i += 2) {
			for(int j = 0; j <= 7; j += 2) {
				g.setColor(whiteSquare);
				g.fillRect(j  * 100, i * 100, 100, 100);
			}
		}
		for(int i = 1; i <= 7; i += 2) {
			for(int j = 1; j <= 7; j += 2) {
				g.setColor(whiteSquare);
				g.fillRect(j  * 100, i * 100, 100, 100);
			}
		}
		
	
		// drawing selectable locations --------------------------------------
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(selectableLocations[i][j] == 1) {
					g.setColor(selectedSquareColor);
					g.fillRect(boardLocationsX[i][j], boardLocationsY[i][j], 100, 100);
				}
			}
		}
				
		
		//drawing board pieces -----------------------------------------------
		// pawn = 1; knight = 3; rook = 5; bishops = 7; queens = 9; kings = 11; + 1 = white team
		
		//pawns
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(boardPieces[i][j] == 1) {
					g.setColor(Color.black);
					pawn = new Pawn(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(pawn.x, pawn.y, pawn.width, pawn.height);
				} else if(boardPieces[i][j] == 2) {
					g.setColor(Color.black);
					g.drawRect((i * 100) + 34, (j * 100) + 29, pawn.width  + 1, pawn.height + 1);
					g.setColor(Color.white);
					pawn = new Pawn(boardLocationsX[i][j], boardLocationsY[i][j], "white");
					g.fillRect(pawn.x, pawn.y, pawn.width, pawn.height);
				}
			}
		}
		
		//knights
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(boardPieces[i][j] == 3) {
					g.setColor(Color.black);
					knight = new Knight(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(knight.x, knight.y, knight.width, knight.height);
				} else if(boardPieces[i][j] == 4) {
					g.setColor(Color.black);
					g.drawRect((i * 100) + 34, (j * 100) + 19, knight.width  + 1, knight.height + 1);
					g.setColor(Color.white);
					knight = new Knight(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(knight.x, knight.y, knight.width, knight.height);
				}
			}
		}
		
		
		//rooks
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(boardPieces[i][j] == 5) {
					g.setColor(Color.black);
					rook = new Rook(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(rook.x, rook.y,rook.width, rook.height);
				}else if(boardPieces[i][j] == 6) {
					g.setColor(Color.black);
					g.drawRect((i * 100) + 34, (j * 100) + 19, rook.width  + 1, rook.height + 1);
					g.setColor(Color.white);
					rook = new Rook(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(rook.x, rook.y,rook.width, rook.height);			
				}
			}
		}
		
		//bishops
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(boardPieces[i][j] == 7) {
					g.setColor(Color.black);
					bishop = new Bishop(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(bishop.x, bishop.y, bishop.width, bishop.height);
				}else if(boardPieces[i][j] == 8) {
					g.setColor(Color.black);
					g.drawRect((i * 100) + 34, (j * 100) + 19, bishop.width  + 1, bishop.height + 1);
					g.setColor(Color.white);
					bishop = new Bishop(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(bishop.x, bishop.y, bishop.width, bishop.height);
				}
				
			}
		}
		
		// queens
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(boardPieces[i][j] == 9) {
					g.setColor(Color.black);
					queen = new Queen(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(queen.x, queen.y, queen.width, queen.height);
				}else if(boardPieces[i][j] == 10) {
					g.setColor(Color.black);
					g.drawRect((i * 100) + 34, (j * 100) + 19, queen.width  + 1, queen.height + 1);
					g.setColor(Color.white);
					queen = new Queen(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(queen.x, queen.y, queen.width, queen.height);
				}
			}
		}
		
		// kings
		
		for(int i = 0; i <= 7; i++) {
			for(int j = 0; j <= 7; j++) {
				if(boardPieces[i][j] == 11) {
					g.setColor(Color.black);
					king = new King(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(king.x, king.y, king.width, king.height);
				}else if(boardPieces[i][j] == 12) {
					g.setColor(Color.black);
					g.drawRect((i * 100) + 29, (j * 100) + 19, king.width  + 1, king.height + 1);
					g.setColor(Color.white);
					king = new King(boardLocationsX[i][j], boardLocationsY[i][j], "black");
					g.fillRect(king.x, king.y, king.width, king.height);
				}
			}
		}
		
		//drawing grid ------------------------------------------------------
		
		g.setColor(Color.black);
		for(int i = 0; i <= screenWidth; i += screenWidth / 8) {
			g.drawLine(i, 0, i, screenHeight);
		}
		for(int i = 0; i <= screenHeight; i += screenHeight / 8) {
			g.drawLine(0, i, screenWidth, i);
		}
				
		//------------------------------------------------------------
		
}


	@Override
	public void actionPerformed(ActionEvent e) {
			
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int clickedX = e.getX() / 100;
		int clickedY = e.getY() / 100;
		
		System.out.print(clickedX + "\t");
		System.out.print(clickedY + "\n");
		
		selectedLocationX = clickedX * 100;
		selectedLocationY = clickedY * 100;
		
		
		
		if(!firstClick) {
			
			boolean foundX = false;
			boolean foundY = false;
			
			for(int i = 0; i <= 8 - 1; i++) {
				if(selectedLocationX == selectableLocationsX[i]) {
					foundX = true;
				}
				if(selectedLocationY == selectableLocationsY[i]) {
					foundY = true;
				}
			}
			
			if(!foundX || !foundY) {
				firstClick = true;
				clearSelectableLocations();
				repaint();
			}
			
		}
		
		
		
		switch(boardPieces[clickedX][clickedY]) {
			case 0:
				//System.out.println("case 0");
				break;
			case 1:
				//System.out.println("case 1");
				if(firstClick) {
					
					piece = 1;
					
					possibleMoves = 1; // vb hiljem 2
					
					selectableLocations[clickedX][clickedY + 1] = 1;
					selectableLocationsX[0] = selectedLocationX;
					selectableLocationsY[0] = selectedLocationY + 100;

					firstClick = false;
				} 							
				break;
			case 3:
				//System.out.println("case 2");
				if(firstClick) {
					
					piece = 3;
					
					possibleMoves = 4;
					
					if(clickedX >= 2) {
						selectableLocations[clickedX - 2][clickedY + 1] = 1;
						selectableLocationsX[0] = selectedLocationX - 200;
						selectableLocationsY[0] = selectedLocationY + 100;

						firstClick = false;
					}
					if(clickedX >= 1) {
						selectableLocations[clickedX - 1][clickedY + 2] = 1;
						selectableLocationsX[1] = selectedLocationX - 100;
						selectableLocationsY[1] = selectedLocationY + 200;

						firstClick = false;
					}
					if(clickedX <= 6) {
						selectableLocations[clickedX + 1][clickedY + 2] = 1;
						selectableLocationsX[2] = selectedLocationX + 100;
						selectableLocationsY[2] = selectedLocationY + 200;

						firstClick = false;
					}
					if(clickedX <= 5) {
						selectableLocations[clickedX + 2][clickedY + 1] = 1;
						selectableLocationsX[3] = selectedLocationX + 200;
						selectableLocationsY[3] = selectedLocationY + 100;	

						firstClick = false;
					}				
					
				}
				break;
			case 5:
				
				if(firstClick) {
					for(int i = 0; i < 8; i++) {
						selectableLocations[clickedX][clickedY + i] = 1;
						selectableLocationsX[i] = selectedLocationX;
						selectableLocationsY[i] = selectedLocationY + i * 100;
						
						firstClick = false;
					}
				}
				break;
		}
		
						
		for(int i = 0; i <= possibleMoves - 1; i++) {
			if(!firstClick && selectedLocationX == selectableLocationsX[i] && selectedLocationY == selectableLocationsY[i]){
				
				switch(piece) {
					case 1:
						switch(i) {
							case 0:
								boardPieces[clickedX][clickedY - 1] = 0;
								boardPieces[clickedX][clickedY] = 1;
								piece = 0;
								break;
							}
							break;
					case 3:										
						switch(i) {
							case 0:
								//System.out.println("knight move 1");
								boardPieces[clickedX + 2][clickedY - 1] = 0;
								boardPieces[clickedX][clickedY] = 3;
								piece = 0;
								break;
							case 1:
								//System.out.println("knight move 2");
								boardPieces[clickedX + 1][clickedY - 2] = 0;
								boardPieces[clickedX][clickedY] = 3;
								piece = 0;
								break;
							case 2:
								//System.out.println("knight move 3");
								boardPieces[clickedX - 1][clickedY - 2] = 0;
								boardPieces[clickedX][clickedY] = 3;
								piece = 0;
								break;
							case 3:
								//System.out.println("knight move 4");
								boardPieces[clickedX - 2][clickedY - 1] = 0;
								boardPieces[clickedX][clickedY] = 3;
								piece = 0;
								break;
							}
							break;
						
				}
											
				
				firstClick = true;
				clearSelectableLocations();
				repaint();
				
			}
			
			//System.out.println("i is " + i);
			//System.out.println("piece is " + piece);

		}
		
			
		repaint();
				
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void clearSelectableLocations() {
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            selectableLocations[i][j] = 0;
	        }
	    }
	}
	
	/*
	 
	public void initializePieces() {
		bPawn1 = new Pawn(boardLocationsX[0][1], boardLocationsY[0][1], "black");
		bPawn2 = new Pawn(boardLocationsX[1][1], boardLocationsY[1][1], "black");
		bPawn3 = new Pawn(boardLocationsX[2][1], boardLocationsY[2][1], "black");
		bPawn4 = new Pawn(boardLocationsX[3][1], boardLocationsY[3][1], "black");
		bPawn5 = new Pawn(boardLocationsX[4][1], boardLocationsY[4][1], "black");
		bPawn6 = new Pawn(boardLocationsX[5][1], boardLocationsY[5][1], "black");
		
		
		bPawn7 = new Pawn(boardLocationsX[6][1], boardLocationsY[6][1], "black");
		bPawn8 = new Pawn(boardLocationsX[7][1], boardLocationsY[7][1], "black");
	}
	
	*/

}