import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import javax.swing.*;

public interface IShape {
	FrameDriver start = new FrameDriver();
	void draw(Graphics g);
	
	Color getColor();
}

class SimpleCircle implements IShape {
	
	SimpleCircle(){
		draw(start.display.getGraphics());
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		Random rand = new Random();
		Shape oval = new Ellipse2D.Double(rand.nextInt(500), rand.nextInt(400), (int)(Math.random() * 20 + 20),(int)(Math.random() * 20 + 20));
		g2.draw(oval);
	}

	@Override
	public Color getColor() {
		Color c = new Color(((int)(Math.random() * 256 + 1)), ((int)(Math.random() * 5 + 1)), ((int)(Math.random() * 5 + 1)));
		return c;
	}
}

class SimpleRectangle implements IShape{
	
	SimpleRectangle(){
		draw(start.display.getGraphics());
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		Random rand = new Random();
		Shape rect = new Rectangle2D.Double(rand.nextInt(500), rand.nextInt(400), (int)(Math.random() * 20 + 20),(int)(Math.random() * 20 + 20));
		g2.draw(rect);
	}

	@Override
	public Color getColor() {
		Color c = new Color(((int)(Math.random() * 256 + 1)), ((int)(Math.random() * 5 + 1)), ((int)(Math.random() * 5 + 1)));
		return c;
	}
}

class SimpleLine implements IShape{
	
	SimpleLine(){
		draw(start.display.getGraphics());
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Random rand = new Random();
		g2.setColor(getColor());
		g2.drawLine(rand.nextInt(500), rand.nextInt(400), rand.nextInt(500),rand.nextInt(400));
	}

	@Override
	public Color getColor() {
		Color c = new Color(((int)(Math.random() * 255)), ((int)(Math.random() * 255)), ((int)(Math.random() * 255)));
		return c;
	}
}

class DoubleCircle implements IShape{
	
	DoubleCircle(){
		draw(start.display.getGraphics());
	}
	
	@Override
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		Random rand = new Random();
		int x = rand.nextInt(500);
		int y = rand.nextInt(400);
		int size = (int)(Math.random() * 20 + 20);
		Shape oval = new Ellipse2D.Double(x, y, size,size);
		g2.draw(oval);
		oval = new Ellipse2D.Double(x + 10, y, size,size);
		g2.draw(oval);
	}

	@Override
	public Color getColor() {
		Color c = new Color(((int)(Math.random() * 256 + 1)), ((int)(Math.random() * 5 + 1)), ((int)(Math.random() * 5 + 1)));
		return c;
	}
}
