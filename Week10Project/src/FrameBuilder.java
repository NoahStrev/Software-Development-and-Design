import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.util.*;

public interface FrameBuilder {
    JPanel buildTopPanel();
    JPanel buildBottomPanel();
    JFrame buildAppFrame(String title);
}

class Composite implements FrameBuilder, ActionListener{
	String[] labels = {"Create", "Erase"};
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	JTextArea output;
	ArrayList<IShape> shapes = new ArrayList<IShape>();
	JFrame frame;
	JPanel buttonPanel, displayPanel;

	@Override
	public JPanel buildTopPanel() {
		displayPanel = new JPanel();
		displayPanel.setBounds(10, 100, 550, 380);
		return displayPanel;
	}

	@Override
	public JPanel buildBottomPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setBounds(10, 480, 550, 80);
		JButton eraseB = new JButton("Erase");
		eraseB.setPreferredSize(new Dimension(70,26));
		eraseB.addActionListener(this);
		
		IBtn createB = new ShallowBtn(new DeepBtn());
		buttonPanel.add(eraseB);
		buttonPanel.add(createB.create("Button created by an approxy.."));
		return buttonPanel;
	}

	@Override
	public JFrame buildAppFrame(String title) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(600, 600);
		frame.setVisible(true);
		return frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = ((JButton) e.getSource()).getText();
		if(text.equalsIgnoreCase("erase")){
			shapes.clear();
		}
		repaint();
	}	
	
	public void repaint(){
		buttonPanel.repaint();
	}
}

interface IBtn {
    JButton create(String str);
}

class DeepBtn implements IBtn{
	ArrayList<IShape> shapes = new ArrayList<IShape>();
	@Override
	public JButton create(String str) {
		JButton btn = new JButton(str);
		btn.addMouseListener(new MouseAdapter() {
			String text;
			@Override
			public void mousePressed(MouseEvent e) {
				JButton btn = (JButton) e.getSource();
				text = btn.getText();
				btn.setText("Shapes Created");
				if(text.equalsIgnoreCase("create")) {
					shapes.add(new SimpleCircle());
					shapes.add(new SimpleRectangle());
					shapes.add(new SimpleLine());
					shapes.add(new DoubleCircle());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				JButton btn = (JButton) e.getSource();
				btn.setText(text);
			}
		});
		return btn;
	}
}

class ShallowBtn implements IBtn{
	IBtn proxyBtn;
	public ShallowBtn(IBtn real) {
		proxyBtn = real;
	}

	@Override
	public JButton create(String str) {
		return proxyBtn.create("Create");
	}
}
