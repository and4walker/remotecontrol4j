package org.remotecontrol4j.client.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

/**
 * 图形按钮
 * 
 * @author and4walker
 *
 */
public class GraphicBar
{

	/**
	 * 设置图形菜单
	 * 
	 * @param menuBar
	 */
	public static void setGraphicBar(Frame frame){
		
    
    //工具栏
		JToolBar toolbar = new ToolBar(0,3,200,25).getToolBar();	
    toolbar.addSeparator();
		//网卡
		toolbar.add(new JLabel("网卡:"), new ImageIcon("new.gif"));
		JComboBox nicComboBox = new JComboBox(new String[]{"1"});
		toolbar.add(nicComboBox);
	  //局域网扫描
		JToolBar toolbar2 = new ToolBar(250,3,75,25).getToolBar();	   
    toolbar2.addSeparator();    
		toolbar2.add(new Button("局域网扫描","局域网扫描").getButton());   
		
	  //开机
		JToolBar toolbar3 = new ToolBar(325,3,30,25).getToolBar();			 
    toolbar3.add(new Button("开机","开机").getButton(), new ImageIcon("new.gif"));

    //关机
    JToolBar toolbar4 = new ToolBar(355,3,300,25).getToolBar();	
    toolbar4.add(new Button("关机","关机").getButton());
    
    frame.getContentPane().add(toolbar);
    frame.getContentPane().add(toolbar2);
    frame.getContentPane().add(toolbar3);
    frame.getContentPane().add(toolbar4);
	}
	
	
}

class ToolBar{

	private int x;
	private int y;
	private int width;
	private int height;

  //(int x, int y, int width, int height)
	ToolBar(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public JToolBar getToolBar(){
		JToolBar toolbar = new JToolBar();
		toolbar.setBounds(x, y, width, height);
		toolbar.setFloatable(false);
		return toolbar;
	}
}


class Button{
	
	private String name;
	private String tip;
	
	Button(String name,String tip){
		this.name = name;
		this.tip = tip;
	}
	
	public JButton getButton(){
		final JButton jbutton = new JButton(name);
		jbutton.setIcon(new ImageIcon(RCUtil.IMAGE_PATH+"123.jpg"));
		jbutton.setFocusable(false);
		jbutton.setToolTipText(tip);
		jbutton.setBorder(BorderFactory.createEmptyBorder());
		jbutton.setOpaque(true);
		jbutton.setContentAreaFilled(false);  
		jbutton.setRolloverEnabled(true);   

		jbutton.addMouseListener(new MouseAdapter(){ 
		  int  count = 0;
			
      public void mouseEntered(MouseEvent e){ 
      		if(count % 2 == 0){
      			jbutton.setBorder(BorderFactory.createRaisedBevelBorder());
      		}
      } 
      public void mouseExited(MouseEvent e){ 
      	if(count % 2 == 0){
      		jbutton.setBorder(BorderFactory.createEmptyBorder());
      	}
      	
      } 
      public void mouseClicked(MouseEvent e){
      	count ++ ;
      	if(count % 2 == 0){
      		jbutton.setBorder(BorderFactory.createEmptyBorder());
      	}else{
      		jbutton.setBorder(BorderFactory.createLoweredBevelBorder());
      	}
      }

      
    }); 
		
		return jbutton;
	}
}