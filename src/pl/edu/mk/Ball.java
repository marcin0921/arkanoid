package pl.edu.mk;


import java.util.List;
import java.util.Random;



public class Ball {
	private int x, y, bonus, horizontalSpd, verticalSpd;
	

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

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}


	public Ball(int x, int y, int bonus, int horizontalSpd, int verticalSpd) {
		super();
		this.x = x;
		this.y = y;
		this.bonus = bonus;
		this.horizontalSpd = horizontalSpd;
		this.verticalSpd = verticalSpd;
		
		   
			
		
	}
	
	public void shitBricks(List<Brick> brickList, List<Bonus> bonusList) {
		for(int brickNr=0; brickNr<brickList.size(); brickNr++) {
			boolean killTheBastard=false;
			
			//punkt srodek dol
			if(this.x+4>=brickList.get(brickNr).getX()&&this.x+4<=brickList.get(brickNr).getX()+45&&this.y>=brickList.get(brickNr).getY()-10&&this.y<=brickList.get(brickNr).getY()) {
				this.verticalSpd=-this.verticalSpd;
				this.y=this.y-2;
				killTheBastard=true;
			}
			//punkt srodek gora
			if(this.x+4>=brickList.get(brickNr).getX()&&this.x+4<=brickList.get(brickNr).getX()+45&&this.y-8>=brickList.get(brickNr).getY()-10&&this.y-8<=brickList.get(brickNr).getY()) {
				this.verticalSpd=-this.verticalSpd;
				this.y=this.y+2;
				killTheBastard=true;
			}
			//punkt lewo srodek
			if(this.x>=brickList.get(brickNr).getX()&&this.x<=brickList.get(brickNr).getX()+45&&this.y-4>=brickList.get(brickNr).getY()-10&&this.y-4<=brickList.get(brickNr).getY()) {
				this.horizontalSpd=-this.horizontalSpd;
				this.x=this.x+2;
				killTheBastard=true;
			}
			//punkt prawo srodek
			if(this.x+8>=brickList.get(brickNr).getX()&&this.x+8<=brickList.get(brickNr).getX()+45&&this.y-4>=brickList.get(brickNr).getY()-10&&this.y-4<=brickList.get(brickNr).getY()) {
				this.horizontalSpd=-this.horizontalSpd;
				this.x=this.x-2;
				killTheBastard=true;
			}
			//punkt lewo gora SKOS
			if(this.x+2>=brickList.get(brickNr).getX()&&this.x+2<=brickList.get(brickNr).getX()+45&&this.y-6>=brickList.get(brickNr).getY()-10&&this.y-6<=brickList.get(brickNr).getY()) {
				this.verticalSpd=-this.verticalSpd;
				//this.horizontalSpd=-this.horizontalSpd;
				this.y=this.y+2;
				this.x=this.x+2;
				killTheBastard=true;
			}
			//punkt prawo gora SKOS
			if(this.x+6>=brickList.get(brickNr).getX()&&this.x+6<=brickList.get(brickNr).getX()+45&&this.y-6>=brickList.get(brickNr).getY()-10&&this.y-6<=brickList.get(brickNr).getY()) {
				this.verticalSpd=-this.verticalSpd;
				//this.horizontalSpd=-this.horizontalSpd;
				this.y=this.y+2;
				this.x=this.x-2;
				killTheBastard=true;
			}
			//punkt lewo dol SKOS
			if(this.x+2>=brickList.get(brickNr).getX()&&this.x+2<=brickList.get(brickNr).getX()+45&&this.y-2>=brickList.get(brickNr).getY()-10&&this.y-2<=brickList.get(brickNr).getY()) {
				this.verticalSpd=-this.verticalSpd;
				//this.horizontalSpd=-this.horizontalSpd;
				this.y=this.y-2;
				this.x=this.x+2;
				killTheBastard=true;
			}
			//punkt prawo dol SKOS
			if(this.x+6>=brickList.get(brickNr).getX()&&this.x+6<=brickList.get(brickNr).getX()+45&&this.y-2>=brickList.get(brickNr).getY()-15&&this.y-2<=brickList.get(brickNr).getY()) {
				this.verticalSpd=-this.verticalSpd;
				//this.horizontalSpd=-this.horizontalSpd;
				this.y=this.y-2;
				this.x=this.x-2;
				killTheBastard=true;
			}
			if(killTheBastard) {
				brickList.get(brickNr).setHp(brickList.get(brickNr).getHp()-1);
				if(brickList.get(brickNr).getHp()<=0) {
					
					if(brickList.get(brickNr).getType()==1)
						Paletka.setScore(Paletka.getScore()+100);
					else if(brickList.get(brickNr).getType()==2)
						Paletka.setScore(Paletka.getScore()+500);
					
					//Random if bonus drops
					Random generator = new Random();
					int randomNr = generator.nextInt(100);
					if(randomNr<4)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 1));
					else if(randomNr>=4&&randomNr<8)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 2));
					else if(randomNr>=8&&randomNr<12)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 3));
					else if(randomNr>=12&&randomNr<16)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 4));
					else if(randomNr>=16&&randomNr<20)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 5));
					else if(randomNr>=20&&randomNr<24)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 6));
					else if(randomNr>=24&&randomNr<28)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 7));
					else if(randomNr>=28&&randomNr<32)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 8));
					else if(randomNr>=32&&randomNr<36)
						bonusList.add(new Bonus(brickList.get(brickNr).getX()+22, brickList.get(brickNr).getY(), 9));
					
					//and finally remove the hit brick
					brickList.remove(brickNr); //WAZNE: MUSI BYC NA KONCU
					
					
					
				}
					
			}
		}
		
	}


	
	public void move(List<Brick> brickList, List<Ball> ballList, List<Bonus> bonusList, List<Shot> shotList) {
		int i=0;
		
		//check collisions and move
		for(i=0; i<-this.horizontalSpd; i++) {		//move LEFT
			
			//check bricks
			shitBricks(brickList, bonusList);
			
			//check Paletki
			if(this.x==Paletka.getX()+Paletka.getWidth()&&this.y>=Paletka.getY()-Paletka.getHeight()&&this.y<=Paletka.getY()) {
				this.horizontalSpd=-this.horizontalSpd;

			}
			
			if(this.x==Paletka.getX()&&this.y>=Paletka.getY()-Paletka.getHeight()&&this.y<=Paletka.getY()) {
				this.horizontalSpd=this.horizontalSpd-2;
				
			}
				
			if(this.x>0)
				this.x = x-1;
			else {
				this.horizontalSpd=-this.horizontalSpd;
				this.x = x+1;
			}
		}
		for(i=0; i<this.horizontalSpd; i++) {	//move RIGHT
			
			//check bricks
			shitBricks(brickList, bonusList);
			
			if(this.x==Paletka.getX()&&this.y>=Paletka.getY()-Paletka.getHeight()&&this.y<=Paletka.getY()) {
				this.horizontalSpd=-this.horizontalSpd;
				
			}
			
			if(this.x==Paletka.getX()+Paletka.getWidth()&&this.y>=Paletka.getY()-Paletka.getHeight()&&this.y<=Paletka.getY()) {
				this.horizontalSpd=this.horizontalSpd+2;
				
			}
			
			if(this.x<790)
				this.x = x+1;
			else {
				this.horizontalSpd=-this.horizontalSpd;
				this.x = x-1;
			}
			
		}
		for(i=0; i<-this.verticalSpd; i++) {		//move UP
			
			//check bricks
			shitBricks(brickList, bonusList);
			
			if(this.y>0)
				this.y = y-1;
			else {
				this.verticalSpd=-this.verticalSpd;
				this.y = y+1;
			}
		}
		for(i=0; i<this.verticalSpd; i++) {		//move DOWN
			
			//check bricks
			shitBricks(brickList, bonusList);
			
			//check Paletki
			if(x+4>=Paletka.getX()&&x+4<=Paletka.getX()+Paletka.getWidth()&&y>=Paletka.getY()-Paletka.getHeight()&&y<Paletka.getY()+1) {
				int marker = Paletka.getWidth()/5;
				if(x+4>=Paletka.getX()&&x+4<Paletka.getX()+marker) {
					this.verticalSpd=-this.verticalSpd;
					if(this.verticalSpd < -4)
						this.verticalSpd++;
					this.y = y-2;
					if(this.horizontalSpd>0) {
						this.horizontalSpd=-this.horizontalSpd;
						this.horizontalSpd=this.horizontalSpd+3;
					}
						
					this.horizontalSpd=this.horizontalSpd-2;
					
				}
				else if(x+4>=Paletka.getX()+marker&&x+4<Paletka.getX()+2*marker) {
					this.verticalSpd=-this.verticalSpd;
					this.y = y-2;
					this.horizontalSpd=this.horizontalSpd-1;
					if(this.horizontalSpd>0)
						this.horizontalSpd=-this.horizontalSpd;
				}
				else if(x+4>=Paletka.getX()+2*marker&&x+4<Paletka.getX()+3*marker) {
					this.verticalSpd=-this.verticalSpd;
					this.y = y-2;
					if(this.verticalSpd>-8)
						this.verticalSpd=this.verticalSpd-1;
					if(this.horizontalSpd<0)
						this.horizontalSpd++;
					if(this.horizontalSpd>0)
						this.horizontalSpd--;
						
				}
				else if(x+4>=Paletka.getX()+3*marker&&x+4<=Paletka.getX()+4*marker) {
					this.verticalSpd=-this.verticalSpd;
					this.y = y-2;
					this.horizontalSpd=this.horizontalSpd+1;
					if(this.horizontalSpd<0)
						this.horizontalSpd=-this.horizontalSpd;
				}
				else if(x+4>=Paletka.getX()+4*marker&&x+4<Paletka.getX()+5*marker) {
					this.verticalSpd=-this.verticalSpd;
					if(this.verticalSpd < -4)
						this.verticalSpd++;
					this.y = y-2;
					if(this.horizontalSpd<0) {
						this.horizontalSpd=-this.horizontalSpd;
						this.horizontalSpd=this.horizontalSpd-3;
					}
					this.horizontalSpd=this.horizontalSpd+2;
					
				}
				
				break;
			}
			
			//DEATH  
			if(this.y<600)
				this.y = y+1;
			else {
				if(Paletka.getBonus()==1) {
					this.verticalSpd=-this.verticalSpd;
					this.y = y-1;
					Paletka.setBonus(0);
				}
				else {
					ballList.remove(this);
					if(ballList.size()<=0) {
						try{
							Paletka.setLife(Paletka.getLife()-1);
							Paletka.setBonus(0);
							shotList.clear();
							if(Paletka.getWidth()!=60)
								Paletka.setWidth(60);
							Thread.sleep(1000);
						}catch(Exception c){
						}
						bonusList.clear();
						ballList.add(new Ball(20, 200, 0, 4, -4));
					}
				
				}
								
				
			}
		}
	}

	public void setHorizontalSpd(int horizontalSpd) {
		this.horizontalSpd = horizontalSpd;
	}

	public int getHorizontalSpd() {
		return horizontalSpd;
	}

	public void setVerticalSpd(int verticalSpd) {
		this.verticalSpd = verticalSpd;
	}

	public int getVerticalSpd() {
		return verticalSpd;
	}
	

}
