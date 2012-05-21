package org.remotecontrol4j.client.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * 结果表单
 * 
 * @author and4walker
 *
 */
public class Table
{

	/**
	 * 设置返回结果
	 */
	public static void setResultTable(Frame frame){
		
		
		final Object rowData[][] = {    
        { "1", "one",  "I" },   
        { "2", "two",  "II" },  
        { "2", "two",  "II" }, 
        { "2", "two",  "II" }, 
        { "2", "two",  "II" }, 
        { "2", "two",  "II" }, 
        { "2", "two",  "II" }, 
        { "2", "two",  "II" }, 
        { "3", "three", "III" }};   
    final String columnNames[] = { "#", "English", "Roman" };   
		final JTable table = new JTable(rowData, columnNames);
		table.addColumn(new TableColumn());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		DefaultTableModel tablemode = new DefaultTableModel(rowData, columnNames){
				public boolean isCellEditable(int row,int column){
					return false;
				}
		};
		table.setModel(tablemode);
		
		JTabbedPane jtab = new JTabbedPane();
		jtab.setBounds(103, 103, 103, 103);
		jtab.addTab("asdfasd", table);
		frame.getContentPane().add(jtab);

		JScrollPane scrollpane = new JScrollPane(table);  
		scrollpane.setBounds(0,80,790,450);
		frame.getContentPane().add(scrollpane,BorderLayout.CENTER);
		
		
		
		final JPopupMenu popup = new JPopupMenu(); // 右键弹出菜单
		JMenuItem jmi1 = new JMenuItem("清空聊天记录");
		JMenuItem jmi2 = new JMenuItem("保存聊天记录");
		JMenuItem jmi3 = new JMenuItem("刷新");
		popup.add(jmi1);
		popup.add(jmi2);
		popup.add(jmi3);
		scrollpane.add(popup);
		
		table.addMouseListener(new MouseAdapter(){       
      public void mousePressed(MouseEvent event){       
      	if(table.isRowSelected(table.getSelectedRow()) && event.isPopupTrigger()){
           popup.show(event.getComponent(), event.getX(),event.getY());   
      	}
      }   
      public void mouseReleased(MouseEvent event){       
      	if(table.isRowSelected(table.getSelectedRow()) && event.isPopupTrigger()){
           	popup.show(event.getComponent(), event.getX(), event.getY()); 
      	}
      }   
    }); 
		
		
		
		
	}
	
}
