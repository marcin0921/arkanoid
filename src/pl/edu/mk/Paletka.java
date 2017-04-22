package pl.edu.mk;

public class Paletka {

	private static int x, y, width, height, counter, bonus, life, score, counter2, level;
	
	
	public static int getX() {
		return x;
	}


	public static void setX(int xx) {
		x = xx;
	}


	public static int getY() {
		return y;
	}


	public static void setY(int yy) {
		y = yy;
	}


	public static int getWidth() {
		return width;
	}


	public static void setWidth(int wwidth) {
		width = wwidth;
	}


	public static int getHeight() {
		return height;
	}


	public static void setHeight(int hheight) {
		height = hheight;
	}


	public static int getCounter() {
		return counter;
	}


	public static void setCounter(int ccounter) {
		counter = ccounter;
	}


	public static int getBonus() {
		return bonus;
	}


	public static void setBonus(int bbonus) {
		bonus = bbonus;
	}


	public Paletka() {
		Paletka.setLife(3);
		Paletka.x=350;
		Paletka.y=550;
		Paletka.width=60;
		Paletka.height=10;
		Paletka.counter=0;
		Paletka.bonus=0;
		Paletka.score=0;
		Paletka.counter2=0;
		Paletka.setLevel(1);
	}


	public static void setLife(int life) {
		Paletka.life = life;
	}


	public static int getLife() {
		return life;
	}


	public static void setScore(int score) {
		Paletka.score = score;
	}


	public static int getScore() {
		return score;
	}


	public static void setCounter2(int counter2) {
		Paletka.counter2 = counter2;
	}


	public static int getCounter2() {
		return counter2;
	}


	public static void setLevel(int level) {
		Paletka.level = level;
	}


	public static int getLevel() {
		return level;
	}
}

