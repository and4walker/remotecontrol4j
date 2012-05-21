package org.remotecontrol4j.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * 状态栏
 *
 * @author and4walker
 *
 */
public class StatusColumn
{
	
	public static void set(Frame frame){
		StatusBar status = new StatusBar();
		frame.getContentPane().add(status, BorderLayout.SOUTH);
		int minimum = 0;
    int maximum = 100;
    
    JProgressBar progress = new JProgressBar(minimum, maximum);
    progress.setBounds(0, 534, 800, 20);
		frame.getContentPane().add(progress);
		
//		JLabel statusInfo = new JLabel("查询结果111条");
//		statusInfo.setHorizontalAlignment(SwingConstants.LEFT);
//		statusInfo.setBounds(0, 534, 100, 20);
//		frame.getContentPane().add(statusInfo);
	}
	
}


class StatusBar extends JPanel {

  public StatusBar() {
  	this.setLayout(null);
    this.setBounds(0,530,800,5);
    
   // JPanel rightPanel = new JPanel(new BorderLayout());
    //rightPanel.add(new JLabel(new AngledLinesWindowsCornerIcon()), BorderLayout.SOUTH);
    //rightPanel.setOpaque(false);

    //add(rightPanel, BorderLayout.EAST);
    //setBackground(SystemColor.control);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int y = 0;
    g.setColor(new Color(156, 154, 140));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(196, 194, 183));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(218, 215, 201));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(233, 231, 217));
    g.drawLine(0, y, getWidth(), y);

    y = getHeight() - 3;
    g.setColor(new Color(233, 232, 218));
    g.drawLine(0, y, getWidth(), y);
    y++;
    g.setColor(new Color(233, 231, 216));
    g.drawLine(0, y, getWidth(), y);
    y = getHeight() - 1;
    g.setColor(new Color(221, 221, 220));
    g.drawLine(0, y, getWidth(), y);

  }

}