package lifegame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class undoButtonPushed implements ActionListener{
	
	private BoardModel undoM;
	
	public undoButtonPushed(BoardModel m) {
		undoM = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(undoM.isUndoable()) undoM.undo();
	}
	
}
