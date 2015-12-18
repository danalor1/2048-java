package Windows;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class RecordList extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Record> recordTable;
	private final int maxSize=10;
	private final String[] columns = { "Player Name", "Score" };

	public RecordList(List<Record> recordTable) {
		this.recordTable = recordTable;
	}
	public RecordList() {
		this.recordTable =new ArrayList<Record>();
	}

	public List<Record> getRecordTable() {
		return recordTable;
	}

	public void setRecordTable(List<Record> recordTable) {
		this.recordTable = recordTable;
	}
	public void setRecordTable() {
		this.recordTable = new ArrayList<Record>();
	}
	
	public void addRecord(Record record) {
		recordTable.add(record);
		sort();
		
		if (recordTable.size() > maxSize) {
			recordTable.removeAll(recordTable.subList(maxSize, recordTable.size()));
		}
	}
	public void sort() {
		Collections.sort(recordTable);
	}
	@Override
	public int getRowCount() {
		return recordTable.size();
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex==0)
			return recordTable.get(rowIndex).getName();
		if(columnIndex==1)
			return recordTable.get(rowIndex).getScore();
		return null;
	}
	public String getColumnName(int column) {
		return columns[column];
	}

}
