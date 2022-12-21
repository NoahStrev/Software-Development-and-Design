import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class FrameDriver{
	Calculator calc;
		
	public static void main(String[] args) {
		FrameDriver start = new FrameDriver();
	}
	
	FrameDriver(){
		calc = new Calculator();
		JFrame frame = calc.buildAppFrame("Calculator");
		JPanel buttons = calc.buildButtonPanel();
		JPanel display = calc.buildDisplayPanel();
		frame.add(display);
		frame.add(buttons);
	}
}
