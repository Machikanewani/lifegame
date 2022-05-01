package lifegame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newButtonPushed implements ActionListener{
	private Main newMa;
	
	public newButtonPushed(Main ma) {
		newMa = ma;
	}
	
	public void actionPerformed(ActionEvent e) {
		newMa.run();
	}
}
