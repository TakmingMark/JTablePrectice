


import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.security.PublicKey;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Hello {
	
	JFrame jFrame=new JFrame("test");
	
	JTable table;
	
	Object[][]tableData= {
			new Object[] {"as","23","girl"},
			new Object[] {"b","23","man"},
			new Object[] {"c","23","man"},
			new Object[] {"d","23","girl"},
			new Object[] {"e","23","girl"}
	};
	
	String[] columnTitle= {"first","age","sex"};
	LeaveTableModel leaveTableModel=new LeaveTableModel(columnTitle, tableData);
			
	public void init() {
		table=new JTable(leaveTableModel);
		table.setRowSelectionAllowed(true);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	
		int condition = JComponent.WHEN_FOCUSED;
		  InputMap inputMap = table.getInputMap(condition);
		  ActionMap actionMap = table.getActionMap();

		  // DELETE is a String constant that for me was defined as "Delete"
		  String DELETE="Delete";
		  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), DELETE);
		  actionMap.put(DELETE, new AbstractAction() {
		     public void actionPerformed(ActionEvent e) {
		    	 int seleteRow=table.getSelectedRow();
		    	 if(seleteRow!=-1)
		    		 removeRow();
		     }
		  });
		  
		  String INSERT="Insert";
		  inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), INSERT);
		  actionMap.put(INSERT, new AbstractAction() {
		     public void actionPerformed(ActionEvent e) {
		    	 int seleteRow=table.getSelectedRow();
		    	 if(seleteRow!=-1)
		    		 insertRow();
		     }
		  });
		  
		jFrame.add(new JScrollPane(table));
		jFrame.pack();
		jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}
	
	public void removeRow() {
		 leaveTableModel.removeRow(table.getSelectedRow()); 	
	}
	public void insertRow() {
		leaveTableModel.insertRow(table.getSelectedRow(), new Object[] {"test","23","girl"});
	}
  public static void main(String... args) {
	  new Hello().init();
  }
  
  
}

class LeaveTableModel extends DefaultTableModel{
	public LeaveTableModel(String[] columnNames,Object[][]cells) {
		super(cells,columnNames);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex,int columnIndex) {
		return true;
	}
}