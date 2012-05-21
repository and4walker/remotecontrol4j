package org.remotecontrol4j.client.ui;

import java.io.*;   

import java.awt.*;
  
import javax.swing.*;   

public class JLayout extends JTabbedPane implements Serializable{   

    public JLayout(){   
      JFrame frame = new JFrame();     
      frame.setTitle("Demo");   
      frame.getContentPane().setLayout(new BorderLayout());   
      this.add(new JPanel(), "常用");   
      this.add(new JPanel(), "远程唤醒");   
      this.add(new JPanel(), "Popup");  


      frame.getContentPane().add(this, BorderLayout.CENTER);   
      frame.setSize(800, 600);   
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();   
      frame.setLocation((d.width - frame.getSize().width) / 2,   
                        (d.height - frame.getSize().height) / 2);   
      frame.setVisible(true);   
    }     
    

    /**  
     * 测试  
     * @param args String[]  
     */  
    public static void main(String[] args){   
        try {   
          UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");   
        } catch (Exception e) {   
        }   
        final JLayout layout = new JLayout();
    }   
}  
