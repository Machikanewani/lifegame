package lifegame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class nextButtonPushed implements ActionListener{
	
	private BoardModel nextM;
	
	public nextButtonPushed(BoardModel m) {
		nextM = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		nextM.next();
	}
	
}
