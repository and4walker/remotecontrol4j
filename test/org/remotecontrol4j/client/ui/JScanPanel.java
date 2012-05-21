package org.remotecontrol4j.client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

public class JScanPanel extends JPanel implements ActionListener, PropertyChangeListener{
  
  private JProgressBar progressBar;   
  private JButton startButton;
  private JButton stopButton;
  
  
  public JScanPanel(){
    startButton = new JButton("开始");   
    startButton.setActionCommand("start");   
    startButton.addActionListener(this);
    
    stopButton = new JButton("停止");   
    stopButton.setActionCommand("stop");   
    stopButton.addActionListener(this);
    
    progressBar = new JProgressBar(0, 100);   
    progressBar.setValue(0); 
    
    this.add(startButton);
    this.add(stopButton);
  }
  
  
  public static void main(String[] args) { 
    JFrame frame = new JFrame();     
    frame.setTitle("Demo");   
    frame.getContentPane().setLayout(new BorderLayout());  
    
    JScanPanel scan = new JScanPanel();

    frame.getContentPane().add(scan, BorderLayout.CENTER);   
    frame.setSize(800, 600);   
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();   
    frame.setLocation((d.width - frame.getSize().width) / 2,   
                      (d.height - frame.getSize().height) / 2);   
    frame.setVisible(true);   
  }


  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    // TODO Auto-generated method stub
    
  }


  @Override
  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
    
  }
}
