import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RaindropSimulation {
	public static void main(String[] args) {		
		RainSimuFrame fr= new RainSimuFrame("Raindrop Frame");
		fr.setVisible(true);;
	}
}

abstract class AnimationFrame extends JFrame{
	protected Thread animator;
	protected JPanel pnl;
	
	public AnimationFrame(String title){
		super(title);
		this.setSize(600, 600);
		this.setLocation(300, 100);
		pnl = getAnimationPanel();
		this.add(pnl);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				animator.interrupt();
				e.getWindow().dispose();
				System.exit(0);
			}
		});
		init();
		animator = new Thread(getTask());
		animator.start();
	}
	
	public void repaint(){
		super.repaint();
		pnl.repaint();
	}	
	
	abstract void init();
	abstract Runnable getTask();
	abstract JPanel getAnimationPanel();
}

class RainSimuFrame extends AnimationFrame{
	private Raindrop[] drops;
	
	public RainSimuFrame(String title){
		super(title);
	}

	@Override
	public void init() {
		drops = new Raindrop[10];
		for(int i = 0; i < drops.length; i++){
			drops[i] = new Raindrop();
			drops[i].setPosition((int) (Math.random() * this.getWidth()), (int) (Math.random() * this.getHeight()));
		}		
	}	

	@Override
	Runnable getTask() {
		return new RainTask();
	}
	
	@Override
	JPanel getAnimationPanel() {
		class RainPanel extends JPanel{		
			public RainPanel(){
				this.setBackground(Color.white);
			}			
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				for(int i = 0; i < drops.length; i++){
					g2.draw(drops[i].getDrop());
				}
			}
		}//end RainPanel
		return new RainPanel();
	}
	
	class RainTask implements Runnable{
		@Override
		public void run() {
			while( !Thread.currentThread().isInterrupted() ){
				for(int i = 0; i < drops.length; i++){
					Raindrop drop = drops[i];
					drop.ripple();
					if(!drop.isVisible()){
						drop.setPosition((int) (Math.random() * pnl.getWidth()), (int) (Math.random() * pnl.getHeight()));
					}
				}
				try{
					Thread.sleep(30);
				}catch(InterruptedException e){
					System.out.println(e.getStackTrace().toString());
				}
				pnl.repaint();
			}			
		}	
	}
}
