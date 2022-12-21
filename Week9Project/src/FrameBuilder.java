import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;

public interface FrameBuilder {
    JPanel buildButtonPanel();
    JPanel buildDisplayPanel();
    JFrame buildAppFrame(String title);
}

class Calculator implements FrameBuilder, ActionListener{
	String[] labels = {"C", "/", "x", "-", "+", "=", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	JTextArea output;
	IOp operation;
	ArrayList<Double> operands = new ArrayList<Double>();

	@Override
	public JPanel buildButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(0, 80, 350, 450);
		
		for(int i = 0; i < labels.length; i++) {
			buttons.add(new JButton(labels[i]));
			buttons.get(i).setPreferredSize(new Dimension(110,80));
			buttons.get(i).addActionListener(this);
			buttons.get(i).setFont(new Font("Times New Roman", Font.BOLD, 30));
			buttonPanel.add(buttons.get(i));
		}
		
		return buttonPanel;
	}

	@Override
	public JPanel buildDisplayPanel() {
		JPanel displayPanel = new JPanel();
		displayPanel.setBounds(5, 0, 340, 80);
		
		output = new JTextArea();
		output.setEditable(false);
		output.setPreferredSize(new Dimension(350, 80));
		output.setFont(new Font("Times New Roman", Font.BOLD, 30));
		output.setAlignmentX(JTextField.CENTER);
		output.setText("  ");
		
		displayPanel.add(output);
		return displayPanel;
	}

	@Override
	public JFrame buildAppFrame(String title) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(400, 600);
		frame.setVisible(true);
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = ((JButton) e.getSource()).getText();
		int index = -1;
		
		for(int i = 0; i < labels.length; i++) {
			if(labels[i].equalsIgnoreCase(text)) {
				index = i;
				break;
			}
		}
		
		if((index > 0) && (index < 5) && (operands.size() == 1)){//Operation
			operation = OpFactory(text);
			output.append(text);
		}
		else if(index == 0) {
			operation = null;
			output.setText("  ");
			operands.clear();
		}
		else if((index == 5) && (operands.size() == 2) && operation != null) {
			double total = operation.compute(operands.get(0), operands.get(1));
			String totalOutput = String.valueOf(total);
			output.setText("  " + totalOutput);
		}
		else if((index > 5) && (((operands.size() == 1) && (operation != null)) || (operands.size() == 0))){
			double num = NumFactory(text);
			if(operands.size() == 1) {
				operands.add(num);
			}
			else {
				operands.add(num);
			}
			output.append(text);
		}
		
	}
	
	static IOp OpFactory(String op) {
		if(op.equals("+")) {
			return new Plus();
		}
		else if(op.equals("-")) {
			return new Minus();
		}
		else if(op.equals("x")) {
			return new Multiply();
		}
		else if(op.equals("/")) {
			return new Divide();
		}
		return null;
	}
	
	static double NumFactory(String num) {
		return Double.parseDouble(num);
	}
	
}
