import java.awt.*;
import java.util.*;
import java.awt.geom.Ellipse2D;

public class Race {
	private int x, y;
	Mover racer;
	
	public Race(){
		x = 40;
		y = 20;
		racer = new Mover();
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

	public void setPosition(int xPos, int yPos){
		x = xPos;
		y = yPos;
	}
	
	public Shape getRacer(){
		return new Ellipse2D.Double(x, y, 25, 25);
	}	
	
	public void move(int first, int last) {
		racer.play(first, last, x);
		x = racer.position;
	}
}

