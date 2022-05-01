package lifegame;

import javax.swing.*;
import java.awt.*;

public class Main implements Runnable{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
	}
	
	public void run() {
		BoardModel model = new BoardModel(12,12);
		JButton nextButton = new JButton("Next");
		JButton undoButton = new JButton("Undo");
		JButton newButton = new JButton("New Game");
		
		nextButton.addActionListener(new nextButtonPushed(model));
		undoButton.addActionListener(new undoButtonPushed(model));
		newButton.addActionListener(new newButtonPushed(this));
	
		undoButton.setEnabled(false);
		
        JFrame frame = new JFrame("Lifegame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel base = new JPanel();
        frame.setContentPane(base);
        base.setPreferredSize(new Dimension(512,384)); //希望サイズの指定
        frame.setMinimumSize(new Dimension(350,350)); //最小サイズの指定
        
        base.setLayout(new BorderLayout());
        BoardView view = new BoardView(model,undoButton);
        base.add(view,BorderLayout.CENTER);
        model.addListener(view);
        
        JPanel nextButtonPanel = new JPanel();
        JPanel undoButtonPanel = new JPanel();
        JPanel newPanel = new JPanel();
                
        base.add(nextButtonPanel, BorderLayout.SOUTH);
        nextButtonPanel.add(undoButtonPanel, BorderLayout.EAST);
        undoButtonPanel.add(newPanel, BorderLayout.WEST);
        
        nextButtonPanel.add(nextButton);
        nextButtonPanel.setLayout(new FlowLayout());
        undoButtonPanel.add(undoButton);
        undoButtonPanel.setLayout(new FlowLayout());
        newPanel.add(newButton);
        newPanel.setLayout(new FlowLayout());

        frame.pack(); //ウィンドウに乗せたものの配置を確定する
        frame.setVisible(true); //ウィンドウを表示する        
	}

}
