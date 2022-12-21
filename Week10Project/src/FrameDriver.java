import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class FrameDriver{
	Composite comp;
	JFrame frame;
	JPanel buttons, display;
		
	public static void main(String[] args) {
		FrameDriver start = new FrameDriver();
	}
	
	FrameDriver(){
		comp = new Composite();
		frame = comp.buildAppFrame("Shapes");
		display = comp.buildTopPanel();
		buttons = comp.buildBottomPanel();
		frame.add(buttons);
		frame.add(display);
	}
}
