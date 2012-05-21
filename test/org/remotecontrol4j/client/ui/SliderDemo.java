package org.remotecontrol4j.client.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;
import java.lang.reflect.InvocationTargetException;

/**
* Created by IntelliJ IDEA. User: Administrator Date: 2009-7-5 Time: 16:24:34
* To change this template use File | Settings | File Templates. 进度条
*/
public class SliderDemo extends JFrame {
JProgressBar progressBar;// 进度条

JButton startButton;// 开始按钮

JButton stopButton;// 停止按钮

JTextArea output;// 显示区域

private int num = 50;// default number

Object lock = new Object();

boolean shouldStop;// should thread to be stopped:判断进度是否停止

Thread myThread;//线程

public static void main(String[] args) {
   SliderDemo frame = new SliderDemo();
   frame.pack();
   frame.setVisible(true);
}

public SliderDemo() {
   setTitle("Demo of Slider");
   Container content = getContentPane();

   // value is from 0 to 100,initial value is 50
   JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, num);//游标,范围为0到100
   slider.addChangeListener(new SliderListener());
   slider.setMajorTickSpacing(10);//设置主标尺的间隔
   slider.setMinorTickSpacing(2);//设置次标尺的间隔
   slider.setPaintTicks(true);//设置是否现实标度尺
   slider.setPaintLabels(true);//设置是否现实标签
   slider.setPaintTrack(true);//设置是否现实滑道

   ButtonListener buttonListener = new ButtonListener();//按钮事件
   startButton = new JButton("Start");
   startButton.addActionListener(buttonListener);
   stopButton = new JButton("Stop");
   stopButton.addActionListener(buttonListener);

   progressBar = new JProgressBar();
   progressBar.setValue(0);//设置进度条的当前值
   progressBar.setStringPainted(true);//设置是否现实进度的字符串,比如百分比啊

   // use textarea to show the counter
   output = new JTextArea(5, 20);
   output.setMargin(new Insets(5, 5, 5, 5));//设置组件的边框和内容之间的空白
   output.setEditable(false);
   JScrollPane scrollPane = new JScrollPane(output);

   JPanel panel = new JPanel();
   panel.add(startButton);
   panel.add(stopButton);
   panel.add(progressBar);

   content.add(slider, BorderLayout.NORTH);
   content.add(scrollPane, BorderLayout.CENTER);
   content.add(panel, BorderLayout.SOUTH);

   addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
     System.exit(0);
    }
   });
}

// listener to the start button
class ButtonListener implements ActionListener {
   public void actionPerformed(ActionEvent event) {
    // start the task
    if (event.getSource() == startButton) {
     startButton.setEnabled(false);//按钮设置不可用
     if (myThread == null) {
      myThread = new TaskThread(num);// create a task thread
      shouldStop = false;
      myThread.start();// start the task thread
     }
    } else {// end the task
     synchronized (lock) {
      shouldStop = true;
      lock.notify();// notify thread if stopped
     }
    }
   }
}

// listen to the slider
class SliderListener implements ChangeListener {
   public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider) e.getSource();
    if (!source.getValueIsAdjusting()) {
     num = (int) source.getValue();//获得当前游标所在的位子的标度
    }
   }
}

// task will be executed in a thread and monitored by a progress bar
class TaskThread extends Thread {
   private int max;//进度条的最大值

   public TaskThread(int max) {
    this.max = max;
   }

   public void run() {
    int min = 0;
    progressBar.setValue(min);
    progressBar.setMinimum(min);
    progressBar.setMaximum(max);

    Runnable runner = new Runnable() {
     public void run() {
      int value = progressBar.getValue();
      value++;// increase counter value
      progressBar.setValue(value);// change current value of
             // progress bar
      output.setText("Number:" + value);
     }
    };

    for (int i = min; i < max; i++) {
     try {// block until AWT events hava been processed
      SwingUtilities.invokeAndWait(runner);//组件和线程同步进行
     } catch (InvocationTargetException e) {
      break;
     } catch (InterruptedException e) {
      // Ignore Exception
     }

     synchronized (lock) {
      if (shouldStop)// check if Stop is pressed
       break;
      try {
       lock.wait(100);
      } catch (InterruptedException e) {
       // Ignore Exception
      }
     }
    }

    startButton.setEnabled(true);// enable button
    myThread = null;
   }
}
}

