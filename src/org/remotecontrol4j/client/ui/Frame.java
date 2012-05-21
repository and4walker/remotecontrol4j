package org.remotecontrol4j.client.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class Frame extends JFrame
{
	
	public Frame(String title) {
		super(title);
	
		this.getContentPane().setLayout(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
		
		Menu.setMenuBar(this);
		GraphicBar.setGraphicBar(this);
		Table.setResultTable(this);
		StatusColumn.set(this);
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
		}
	}
	
	/**
	 * 设置长、宽、居中
	 */
	public Dimension getPreferredSize() {
		int windowWidth = 800;               
    int windowHeight = 600;                 
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		Dimension screenSize = kit.getScreenSize(); 
    int screenWidth = screenSize.width;               
    int screenHeight = screenSize.height;            
    this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		return new Dimension(windowWidth,windowHeight);
	}
	
	public static void main(String... args){
		Frame rcFrame = new Frame("Remote Control 4J");
		rcFrame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		
		rcFrame.pack();
		rcFrame.setVisible(true);
	}
}
