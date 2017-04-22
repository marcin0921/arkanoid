package pl.edu.mk;

import java.util.List;

public class Bonus {
	private int x, y, type;

	public Bonus(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public boolean fall(List<Ball> ballList) {
		this.y=this.y+4;
		boolean removeMe=false;
		//fall out of screen
		if(this.y>600)
			removeMe=true;
		//check if gathered
		if((this.x>=Paletka.getX()&&this.x<=Paletka.getX()+Paletka.getWidth()&&this.y>=Paletka.getY()-Paletka.getHeight()&&this.y<=Paletka.getY())||(this.x+15>=Paletka.getX()&&this.x+15<=Paletka.getX()+Paletka.getWidth()&&this.y>=Paletka.getY()-Paletka.getHeight()&&this.y<=Paletka.getY())) {
			if(this.type==1) {
				for(int ballNr=0; ballNr<ballList.size(); ballNr++) {
					if(ballList.get(ballNr).getHorizontalSpd()<0)
						ballList.get(ballNr).setHorizontalSpd(-8);
					else if(ballList.get(ballNr).getHorizontalSpd()>=0)
						ballList.get(ballNr).setHorizontalSpd(8);
					if(ballList.get(ballNr).getVerticalSpd()<0)
						ballList.get(ballNr).setVerticalSpd(-8);
					else if(ballList.get(ballNr).getVerticalSpd()>=0)
						ballList.get(ballNr).setVerticalSpd(8);
				}
			
			}
			else if(this.type==2) {
				for(int ballNr=0; ballNr<ballList.size(); ballNr++) {
					if(ballList.get(ballNr).getHorizontalSpd()<0)
						ballList.get(ballNr).setHorizontalSpd(-3);
					else if(ballList.get(ballNr).getHorizontalSpd()>=0)
						ballList.get(ballNr).setHorizontalSpd(3);
					if(ballList.get(ballNr).getVerticalSpd()<0)
						ballList.get(ballNr).setVerticalSpd(-3);
					else if(ballList.get(ballNr).getVerticalSpd()>=0)
						ballList.get(ballNr).setVerticalSpd(3);
				}
			
			}
			else if(this.type==3) {
				Paletka.setLife(Paletka.getLife()+1);
			}
			else if(this.type==4) {
				Paletka.setLife(Paletka.getLife()-1);
			}
			else if(this.type==5) {
				Paletka.setBonus(1);
			}
			else if(this.type==6) {
				ballList.add(new Ball(Paletka.getX()+Paletka.getWidth()/2, Paletka.getY()-12, 0, 4, -4));
			}
			else if(this.type==7) {
				if(Paletka.getWidth()==60) {
					Paletka.setWidth(90);
				}
				else if(Paletka.getWidth()==40) {
					Paletka.setWidth(60);
				}
				
			}
			else if(this.type==8) {
				if(Paletka.getWidth()==60) {
					Paletka.setWidth(40);
				}
				else if(Paletka.getWidth()==90) {
					Paletka.setWidth(60);
				}
			}
			else if(this.type==9) {
				Paletka.setBonus(2);
				Paletka.setCounter(600);
			}
			Paletka.setScore(Paletka.getScore()+300);
			removeMe=true;
		}
		return removeMe;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
