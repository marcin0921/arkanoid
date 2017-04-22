package pl.edu.mk;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Brick implements Serializable{
	private int x, y, hp, type;

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

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Brick(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		if(type==1)
			this.hp = 1;
		else if(type==2)
			this.hp = 3;
		else if(type==3)
			this.hp = 99999;
		this.type = type;
	}

}
