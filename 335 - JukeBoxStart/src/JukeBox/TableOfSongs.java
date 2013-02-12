/*=============================================================================
 |   Assignment:  Program #2 

 |      Authors:  Carlton Ochoa (cochoa@email.arizona.edu)
 |				  Haziel Zuniga (zuniga7@email.arizona.edu)
 |
 |		 Grader: Rohit
 |       Course:  335
 |   Instructor:  R. Mercer
 |     Due Date:  Tuesday February 12, 2013 at 3:00
 |
 |  Description:  This class is what represents a collection of student objects.
 |                
 *===========================================================================*/
package JukeBox;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableOfSongs implements TableModel {

	private SongCollection list = new SongCollection();
	private ArrayList<Song> copy = new ArrayList<Song>();

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	public TableOfSongs() {
		copy = list.getCollectionList();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		if (columnIndex == 0 || columnIndex == 2)
			return String.class;
		else if (columnIndex == 1 || columnIndex == 3)
			return Integer.class;

		else {
			return String.class;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {

		if (columnIndex == 0)
			return "Song Name";
		else if (columnIndex == 1)
			return "Song Length";
		else if (columnIndex == 2)
			return "Artist";
		else
			return "Number of Plays";

	}

	@Override
	public int getRowCount() {
		return copy.size();

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Song temp = copy.get(rowIndex);
		if (columnIndex == 0)
			return temp.getName();
		else if (columnIndex == 1)
			return temp.getLength();
		else if (columnIndex == 2)
			return temp.getArtist();
		else
			return temp.getNumPlays();

	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValueAt(Object obj, int rowIndex, int columnIndex) {
		
		copy.get(rowIndex);
		copy.set(columnIndex, (Song) obj);
	}

}
