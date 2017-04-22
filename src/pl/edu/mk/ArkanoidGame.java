package pl.edu.mk;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


import java.awt.Image;

import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.util.ArrayList;
import java.util.List;


import javax.swing.*;
import javax.swing.event.MouseInputListener;



public class ArkanoidGame extends JPanel implements Runnable, MouseInputListener{
	

	private static final long serialVersionUID = 6116314143729726149L;
	//Lists
	List<Ball> ballList;
	List<Brick> brickList;
	List<Bonus> bonusList;
	List<Shot> shotList;
	
	//Images
	Image background, ball, life, spdUp, spdDown, skull, force, addball, paletka, brick1, brick2, brick2_2, brick2_3, brick3, wider, shorter, paletkaSmall, paletkaBig, shot, crosshair;

	public ArkanoidGame() {
		
		Thread animator = new Thread(this);
		animator.start();
		
		
				
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.ballList = new ArrayList<Ball>();
		this.brickList = new ArrayList<Brick>();
		this.bonusList = new ArrayList<Bonus>();
		this.shotList = new ArrayList<Shot>();
		
		try{
			background=new ImageIcon(this.getClass().getResource("background.jpg")).getImage();
			ball=new ImageIcon(this.getClass().getResource("ball.gif")).getImage();
			paletka=new ImageIcon(this.getClass().getResource("paletka.png")).getImage();
			brick1=new ImageIcon(this.getClass().getResource("brick1.jpg")).getImage();
			brick2=new ImageIcon(this.getClass().getResource("brick2.jpg")).getImage();
			brick2_2=new ImageIcon(this.getClass().getResource("brick2_2.jpg")).getImage();
			brick2_3=new ImageIcon(this.getClass().getResource("brick2_3.jpg")).getImage();
			brick3=new ImageIcon(this.getClass().getResource("brick3.jpg")).getImage();
			force=new ImageIcon(this.getClass().getResource("force.png")).getImage();	
		
			spdUp=new ImageIcon(this.getClass().getResource("spdup.png")).getImage();
			spdDown=new ImageIcon(this.getClass().getResource("spddown.png")).getImage();
			life=new ImageIcon(this.getClass().getResource("life.png")).getImage();
			skull=new ImageIcon(this.getClass().getResource("skull.png")).getImage();
			addball=new ImageIcon(this.getClass().getResource("addball.png")).getImage();
			paletkaSmall=new ImageIcon(this.getClass().getResource("paletkasmall.png")).getImage();
			paletkaBig=new ImageIcon(this.getClass().getResource("paletkabig.png")).getImage();
			wider=new ImageIcon(this.getClass().getResource("wider.png")).getImage();
			shorter=new ImageIcon(this.getClass().getResource("shorter.png")).getImage();
			shot=new ImageIcon(this.getClass().getResource("shot.png")).getImage();
			crosshair=new ImageIcon(this.getClass().getResource("crosshair.png")).getImage();
			}catch(Exception e){System.out.println("Nie mozna wczytac obrazkow");}

					    
		    try {
		    
			ball = Transparency.makeColorTransparent(ball, Color.blue);
		    paletka = Transparency.makeColorTransparent(paletka, Color.blue);
		   	paletkaSmall = Transparency.makeColorTransparent(paletkaSmall, Color.blue);
		    paletkaBig = Transparency.makeColorTransparent(paletkaBig, Color.blue);
		    //bonuses:
		    force = Transparency.makeColorTransparent(force, Color.blue);
		    spdUp = Transparency.makeColorTransparent(spdUp, Color.blue);
		    spdDown = Transparency.makeColorTransparent(spdDown, Color.blue);
		    life = Transparency.makeColorTransparent(life, Color.blue);
		    skull = Transparency.makeColorTransparent(skull, Color.blue);
		    addball = Transparency.makeColorTransparent(addball, Color.blue);
		    wider = Transparency.makeColorTransparent(wider, Color.blue);
		    shorter = Transparency.makeColorTransparent(shorter, Color.blue);
		    shot = Transparency.makeColorTransparent(shot, Color.blue);
		    crosshair = Transparency.makeColorTransparent(crosshair, Color.blue);
		       
		      } 
		    catch(Exception e) {System.out.println("Transparency fail");}
		    
		    /* HIDE CURSOR!
		    setCursor(getToolkit().createCustomCursor(
		    new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
		    new Point(0, 0), "null"));
		    */
		    
			
		    
	}
	

	public void paintComponent(Graphics g){
		   Graphics2D g2=(Graphics2D)g;
		   
		   
		   //clear the screen
		   g2.setColor(Color.white);
		   g2.fillRect(0, 0, 800, 600);
		   g2.drawImage(background, 0, 0, null);
		   
		   
		   //DISPLAY PALETTE
		   if(Paletka.getWidth()==60) {
			   g2.drawImage(paletka, Paletka.getX(), Paletka.getY(), null);
		   }
		   else if(Paletka.getWidth()==90) {
			   g2.drawImage(paletkaBig, Paletka.getX(), Paletka.getY(), null);
		   }
		   else if(Paletka.getWidth()==40) {
			   g2.drawImage(paletkaSmall, Paletka.getX(), Paletka.getY(), null);
		   }
		   
		   
		   //DISPLAY BALLS FROM LIST
		   g2.setColor(Color.red);
		   		   
		   for(int ballNr=0; ballNr<ballList.size(); ballNr++) {
			   //g2.fillOval(ballList.get(ballNr).getX(), ballList.get(ballNr).getY(), 9, 9);
			   g2.drawImage(ball, ballList.get(ballNr).getX(), ballList.get(ballNr).getY(), null);
		   }
		   
		   //DISPLAY BRICKS FROM LIST
		   
		   for(int brickNr=0; brickNr<brickList.size(); brickNr++) {
			   if(brickList.get(brickNr).getType()==1) {
				   g2.drawImage(brick1, brickList.get(brickNr).getX(), brickList.get(brickNr).getY(), null);
			   }
			   else if(brickList.get(brickNr).getType()==2) {
				   if(brickList.get(brickNr).getHp()==3)
					   g2.drawImage(brick2, brickList.get(brickNr).getX(), brickList.get(brickNr).getY(), null);
				   else if(brickList.get(brickNr).getHp()==2)
					   g2.drawImage(brick2_2, brickList.get(brickNr).getX(), brickList.get(brickNr).getY(), null);
				   else if(brickList.get(brickNr).getHp()==1)
					   g2.drawImage(brick2_3, brickList.get(brickNr).getX(), brickList.get(brickNr).getY(), null);
			   }
			   else if(brickList.get(brickNr).getType()==3) {
				   g2.drawImage(brick3, brickList.get(brickNr).getX(), brickList.get(brickNr).getY(), null);
			   }
			   
		   }
		   
		   //DISPLAY BONUSES
		   g2.setColor(Color.yellow);
		   for(int bonusNr=0; bonusNr<bonusList.size(); bonusNr++) {
			   if(bonusList.get(bonusNr).getType()==1)
				   g2.drawImage(spdUp, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==2)
				   g2.drawImage(spdDown, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==3)
				   g2.drawImage(life, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==4)
				   g2.drawImage(skull, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==5)
				   g2.drawImage(force, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==6)
				   g2.drawImage(addball, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==7)
				   g2.drawImage(wider, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==8)
				   g2.drawImage(shorter, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
			   else if(bonusList.get(bonusNr).getType()==9)
				   g2.drawImage(crosshair, bonusList.get(bonusNr).getX(), bonusList.get(bonusNr).getY(), null);
		   }
		   
		   //DISPLAY SHOTS FIRED
		   for(int shotNr=0; shotNr<shotList.size(); shotNr++) {
			   g2.drawImage(shot, shotList.get(shotNr).getX(), shotList.get(shotNr).getY(), null);
		   }
		   
		   //DISPLAY FORCE FIELD
		   if(Paletka.getBonus()==1) {
		   g2.setColor(Color.green);
		   g2.fillRect(0, 592, 800, 5);
		   }
		   
		   //DISPLAY LIFE
		   g2.setColor(Color.white);
		   g2.drawString("LIFE: "+Paletka.getLife(), 750, 30);
		   
		   //DISPLAY MAIN BONUS
		   if(Paletka.getBonus()==2) {
			   g2.setColor(Color.white);
			   g2.drawString("STRZELANIE: "+Paletka.getCounter(), 250, 30);
		   }
		   
		   //DISPLAY LEVEL
		   g2.setColor(Color.white);
		   g2.drawString("LEVEL: "+Paletka.getLevel(), 10, 30);
		   
		   //DISPLAY SCORE
		   g2.setColor(Color.white);
		   g2.drawString("SCORE: "+Paletka.getScore(), 360, 30);
		   
		   //DISPLAY GAMEOVER
		   if(Paletka.getLife()<=0) {
			   g2.setColor(Color.black);
			   g2.fillRect(0, 0, 800, 600);
			   g2.setColor(Color.white);
			   g2.drawString("GAME OVER", 380, 250);
		   }
		   //DISPLAY WIN
		   if(brickList.size()<=0) {
			   g2.setColor(Color.black);
			   g2.fillRect(0, 0, 800, 600);
			   g2.setColor(Color.white);
			   g2.drawString("Congratulations! You have won!", 330, 250);
		   }
		   
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(Paletka.getBonus()==2) {
			if(Paletka.getCounter2()==0) {
				shotList.add(new Shot(Paletka.getX(), Paletka.getY()));
				Paletka.setCounter2(1);
			}
			else if(Paletka.getCounter2()==1) {
				shotList.add(new Shot(Paletka.getX()+Paletka.getWidth(), Paletka.getY()));
				Paletka.setCounter2(0);
			}
				
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		new Paletka();
		boolean win=false, removeMe;
		ballList.add(new Ball(400, 500, 0, 4, -4));

			
		//MAKE LEVEL 1 
		/*
		brickList.add(new Brick(45, 60, 1));
		brickList.add(new Brick(90, 60, 1));
		brickList.add(new Brick(45, 75, 1));
		brickList.add(new Brick(90, 75, 1));
		brickList.add(new Brick(45, 90, 1));
		brickList.add(new Brick(90, 90, 1));
		
		brickList.add(new Brick(660, 60, 1));
		brickList.add(new Brick(705, 60, 1));
		brickList.add(new Brick(660, 75, 1));
		brickList.add(new Brick(705, 75, 1));
		brickList.add(new Brick(660, 90, 1));
		brickList.add(new Brick(705, 90, 1));
		
		brickList.add(new Brick(385, 60, 1));
		brickList.add(new Brick(355, 75, 1));
		brickList.add(new Brick(415, 75, 1));
		
		brickList.add(new Brick(295, 90, 1));
		brickList.add(new Brick(340, 90, 1));
		brickList.add(new Brick(385, 90, 1));
		brickList.add(new Brick(430, 90, 1));
		brickList.add(new Brick(475, 90, 1));
		
		brickList.add(new Brick(250, 105, 1));
		brickList.add(new Brick(295, 105, 1));
		brickList.add(new Brick(340, 105, 2));
		brickList.add(new Brick(385, 105, 2));
		brickList.add(new Brick(430, 105, 2));
		brickList.add(new Brick(475, 105, 1));
		brickList.add(new Brick(520, 105, 1));
		
		brickList.add(new Brick(205, 120, 1));
		brickList.add(new Brick(250, 120, 1));
		brickList.add(new Brick(295, 120, 1));
		brickList.add(new Brick(340, 120, 2));
		brickList.add(new Brick(385, 120, 3));
		brickList.add(new Brick(430, 120, 2));
		brickList.add(new Brick(475, 120, 1));
		brickList.add(new Brick(520, 120, 1));
		brickList.add(new Brick(565, 120, 1));
		
		brickList.add(new Brick(250, 135, 1));
		brickList.add(new Brick(295, 135, 1));
		brickList.add(new Brick(340, 135, 2));
		brickList.add(new Brick(385, 135, 2));
		brickList.add(new Brick(430, 135, 2));
		brickList.add(new Brick(475, 135, 1));
		brickList.add(new Brick(520, 135, 1));
		
		brickList.add(new Brick(295, 150, 1));
		brickList.add(new Brick(340, 150, 1));
		brickList.add(new Brick(385, 150, 1));
		brickList.add(new Brick(430, 150, 1));
		brickList.add(new Brick(475, 150, 1));
		
		brickList.add(new Brick(355, 165, 1));
		brickList.add(new Brick(415, 165, 1));
		brickList.add(new Brick(385, 180, 1));
		*/
		
		
		
		//MAKE LEVEL 3
		/*
		int bricks=0, rows=0;
		for(rows=0; rows<2; rows++) {
			for(bricks=0; bricks<16; bricks++) {
				brickList.add(new Brick(40+bricks*45, rows*15, 3));
			}
		}
		for(rows=0; rows<1; rows++) {
			for(bricks=0; bricks<16; bricks++) {
				brickList.add(new Brick(40+bricks*45, 30+rows*15, 2));
			}
		}
		for(rows=0; rows<1; rows++) {
			for(bricks=0; bricks<7; bricks++) {
				brickList.add(new Brick(40+bricks*45, 45+rows*15, 2));
			}
		}
		brickList.add(new Brick(40+7*45, 45, 1));
		for(rows=0; rows<1; rows++) {
			for(bricks=8; bricks<16; bricks++) {
				brickList.add(new Brick(40+bricks*45, 45+rows*15, 2));
			}
		}
		*/
		///////////////////////////////////////////
		
			
		readLevel("level1");
		
		while(true)	{
			//slow down
			try{
				Thread.sleep(20);
			}catch(Exception c){
			}
			
			//move all balls
			for(int ballNr=0; ballNr<ballList.size(); ballNr++)
			ballList.get(ballNr).move(brickList, ballList, bonusList, shotList);
			
			//move bonuses
			for(int bonusNr=0; bonusNr<bonusList.size(); bonusNr++) {
				removeMe = bonusList.get(bonusNr).fall(ballList);
				if(removeMe) {
					bonusList.remove(bonusNr);
					removeMe=false;
				}
					
			}
			
			//move shots
			for(int shotNr=0; shotNr<shotList.size(); shotNr++) {
				removeMe = shotList.get(shotNr).move(brickList);
				if(removeMe) {
					shotList.remove(shotNr);
					removeMe=false;
				}
			}
			
			//handle uncommon bonuses
			if(Paletka.getBonus()==2) {
				Paletka.setCounter(Paletka.getCounter()-1);
				if(Paletka.getCounter()<=0)
					Paletka.setBonus(0);
			}
			
			//draw all
			repaint();
			
			
			//END GAME CONDITIONS
			if(Paletka.getLife()<=0)
				break;
			
			win = true;
			for(int brickNr=0; brickNr<brickList.size(); brickNr++) {
				if(brickList.get(brickNr).getType()!=3)
					win = false;
			}
			if(win) {
				brickList.clear();
				bonusList.clear();
				shotList.clear();
				ballList.clear();
				if(Paletka.getLevel()!=2) {
					Paletka.setLevel(Paletka.getLevel()+1);
				}
				else
					break;
				if(Paletka.getLevel()==2) {
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(80+brickNr*45, 60, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(35+brickNr*45, 75, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(80+brickNr*45, 90, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(35+brickNr*45, 105, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(80+brickNr*45, 120, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(35+brickNr*45, 135, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(80+brickNr*45, 150, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr=brickNr+2) {
						brickList.add(new Brick(35+brickNr*45, 165, 1));
					}
					for(int brickNr=0; brickNr<16; brickNr++) {
						brickList.add(new Brick(35+brickNr*45, 180, 2));
					}
					
					brickList.add(new Brick(35, 260, 3));
					brickList.add(new Brick(80, 260, 3));
					brickList.add(new Brick(720, 260, 3));
					brickList.add(new Brick(675, 260, 3));
					
					ballList.add(new Ball(400, 400, 0, 5, 5));
				}
				win=false;
			}
				
		}
		repaint();
	}

	

	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		Paletka.setX(arg0.getX());
		
		
	}
	
	private void readLevel(String name) {
		///READ LEVEL FROM FILE
		try {
			FileInputStream fis = new FileInputStream(name+".dat");
			ObjectInputStream stream = new ObjectInputStream(fis);
			int ile = stream.readInt();
			for(int brickNr=0; brickNr<ile; brickNr++) {
				brickList.add((Brick)stream.readObject());
			}
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void writeLevel(String name) {
		///SAVE LEVEL TO FILE (Objects from the current brick list)
		
		try {
			FileOutputStream fos = new FileOutputStream(name+".dat");
			ObjectOutputStream stream = new ObjectOutputStream(fos);
			stream.writeInt(brickList.size());
			for(int brickNr=0; brickNr<brickList.size(); brickNr++) {
				stream.writeObject(brickList.get(brickNr));
			}
			stream.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}