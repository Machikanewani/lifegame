package lifegame;
import java.util.ArrayList;

public class BoardModel {
	private int cols;
	private int rows;
	private boolean[][] cells;
	private ArrayList<BoardListener> listeners;
	private ArrayList<ArrayList<ArrayList<Boolean>>> list;
	private ArrayList<ArrayList<Boolean>> list_rows;
	private ArrayList<Boolean> list_cols;	
	public BoardModel(int c, int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
		list = new ArrayList<ArrayList<ArrayList<Boolean>>>();
	}
	
	public int getCols() {
		return cols;
	}
	
	public int getRows() {
		return rows;
	}

	public void changeCellState(int x, int y) {
		boolean[][]copy = new boolean[rows][cols];

        for(int i = 0; i < rows; i++) {
        	for(int j = 0; j < cols; j++) {
        		copy[i][j] = cells[i][j];
        	}
        }
		this.his(copy);
		
		if(cells[y][x] == true)
			cells[y][x] = false;
		else cells[y][x] = true;
	    
	    fireUpdate();
	}
	
	public void addListener(BoardListener listener) {
		listeners.add(listener);
	}
	
	private void fireUpdate() {
		for(BoardListener listener: listeners) {
			listener.updated(this);
		}
	}
	
	public void next() {
		boolean[][] nextGen = new boolean[rows][cols];
		
		this.his(cells);
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
                int cnt = 0;
                int k,l;
                
				if(i - 1 >= 0) k = i - 1;
				else k = i;
				
				for(; k <= i + 1 && k < rows; k++) {
					if(j - 1 >= 0) l = j - 1;
					else l = j;
					
					for(; l <= j + 1 && l < cols; l++) {
						if(k != i || l != j) {
							if(cells[k][l] == true) cnt++;
						}
					}
				}
				
				if(cells[i][j] == true) {
					if(cnt < 2 || cnt > 3) nextGen[i][j] = false;
					else nextGen[i][j] = true;
				}
				else {
					if(cnt == 3) nextGen[i][j] = true;
					else nextGen[i][j] = false;
				}
			}		
		}		
		for(int i = 0; i < rows; i ++) {
			for(int j = 0; j < cols; j ++) {
				cells[i][j] = nextGen[i][j];
			}
		}
		fireUpdate();		
	}
	
	private void his(boolean[][] CELL) {
		list_rows = new ArrayList<ArrayList<Boolean>>();
		
		for(int i = 0; i < rows; i ++) {
			list_cols = new ArrayList<Boolean>();
			for(int j = 0; j < cols; j++) {
				list_cols.add(Boolean.valueOf(CELL[i][j]));
			}
			list_rows.add(list_cols);
		}
		if(list.size() >= 32 ) {
			list.remove(0);
		}
		list.add(list_rows);
	}
	
	public void undo() {
		int sz = list.size();
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				cells[i][j] = list.get(sz - 1).get(i).get(j);
			}
		}
		list.remove(sz - 1);
		fireUpdate();	
	}
	
	public boolean isUndoable() {
		int sz = list.size();
		
		if(sz == 0) return false;
		else return true;	
	}
	
	public boolean isAlive(int x, int y) {
		if(cells[y][x] == true) return true;
		else return false;
	}
}
