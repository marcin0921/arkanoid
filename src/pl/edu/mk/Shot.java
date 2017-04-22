package pl.edu.mk;

import java.util.List;

public class Shot {

	private int x, y;

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
	
	public boolean move (List<Brick> brickList){
		this.y=this.y-4;
		boolean destroy=false;
		for(int brickNr=0; brickNr<brickList.size(); brickNr++) {
			if(this.y-17<=brickList.get(brickNr).getY()&&this.y-17>=brickList.get(brickNr).getY()-15&&this.x>=brickList.get(brickNr).getX()&&this.x<=brickList.get(brickNr).getX()+45) {
				destroy=true;
				if(brickList.get(brickNr).getType()!=3&&brickList.get(brickNr).getHp()==1)
					brickList.remove(brickNr);
				else
					brickList.get(brickNr).setHp(brickList.get(brickNr).getHp()-1);
			}
		}
		if(destroy)
			return destroy;
		this.y=this.y-4;
		for(int brickNr=0; brickNr<brickList.size(); brickNr++) {
			if(this.y-17<=brickList.get(brickNr).getY()&&this.y-17>=brickList.get(brickNr).getY()-15&&this.x>=brickList.get(brickNr).getX()&&this.x<=brickList.get(brickNr).getX()+45) {
				destroy=true;
				if(brickList.get(brickNr).getType()!=3&&brickList.get(brickNr).getHp()==1)
					brickList.remove(brickNr);
				else
					brickList.get(brickNr).setHp(brickList.get(brickNr).getHp()-1);
			}
		}
		return destroy;
	}

	public Shot(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
