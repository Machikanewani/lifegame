package lifegame;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class BoardView extends JPanel 
    implements BoardListener, MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5739833203786797482L;
    
	private int cols;
	private int rows;
	private int startx,starty;
	private int mouseX, mouseY;
	private int arrayJ, arrayI;
	private int preJ = 0, preI = 0; 
	private BoardModel BVmodel;	
	private JButton BVUndo;
	
	public BoardView(BoardModel m, JButton undo) {
		BVmodel = m;
		BVUndo = undo;
		cols = BVmodel.getCols();
		rows = BVmodel.getRows();
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void updated(BoardModel m) { 
		if(!BVmodel.isUndoable()) BVUndo.setEnabled(false);
		else BVUndo.setEnabled(true);
	    repaint();
	}
	public void mouseClicked(MouseEvent e) {  
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	    if((mouseX > startx) && (mouseX < startx + cols * 20) && 
	         (mouseY > starty) && (mouseY < starty + rows * 20)) {  	
	    	BVmodel.changeCellState(arrayJ, arrayI);	    	
	    	preI = arrayI;
	    	preJ = arrayJ;
	    }
	}
	public void mouseDragged(MouseEvent e) {  
		this.position(e);
		if((mouseX > startx) && (mouseX < startx + cols * 20) && 
		         (mouseY > starty) && (mouseY < starty + rows * 20)){  				
			if((arrayI != preI || arrayJ != preJ)) {
				BVmodel.changeCellState(arrayJ, arrayI);    	
		    	preI = arrayI;
		    	preJ = arrayJ;
			}    	
		}
	}
	public void mouseReleased(MouseEvent e) {	
	}
	public void mouseMoved(MouseEvent e) {	
		this.position(e);   
	}
	
	private void position(MouseEvent e) {
		mouseX = e.getX();
	    mouseY = e.getY();
	    arrayJ = ((mouseX - startx) - ((mouseX - startx) % 20)) / 20;
    	arrayI = ((mouseY - starty) - ((mouseY - starty) % 20)) / 20;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int w = this.getWidth();
		int h = this.getHeight();
		startx = (w / 2) - cols * 20 / 2;
		starty = (h / 2) - rows * 20 / 2;
		
		for(int i = 0; i <= rows; i++) {
			g.drawLine(startx, starty + i * 20, startx + cols * 20, starty + i * 20); //横
			g.drawLine(startx + i * 20, starty, startx + i * 20, starty + rows * 20); //縦
		}
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(BVmodel.isAlive(j, i) == true) {
					g.fillRect(startx + j * 20, starty + i * 20, 20, 20);
				}
			}
		}
	}
}
