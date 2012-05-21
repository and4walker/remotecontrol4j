package org.remotecontrol4j.client.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 文件菜单
 * @author and4walker
 *
 */
public class Menu
{

	/**
	 * 设置下拉菜单
	 * @param menuBar
	 */
	public static void setMenuBar(Frame frame){
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		
	// 创建File菜单和相应的菜单项
			JMenu mnuFile = new JMenu("文件");
			JMenuItem mnuFileNew = new JMenuItem("新建");
			JMenuItem mnuFileOpen = new JMenuItem("打开…");
			JMenuItem mnuFileSave = new JMenuItem("保存");
			JMenuItem mnuFilePrint = new JMenuItem("打印");
			JMenuItem mnuFileQuit = new JMenuItem("退出");
			// 为菜单项添加图标
//			mnuFileNew.setIcon(new ImageIcon("new.gif"));
//			mnuFileOpen.setIcon(new ImageIcon("open.gif"));
//			mnuFileSave.setIcon(new ImageIcon("save.gif"));
//			mnuFilePrint.setIcon(new ImageIcon("print.gif"));
			// 把菜单项加入到File菜单中
			mnuFile.add(mnuFileNew);
			mnuFile.add(mnuFileSave);
			mnuFile.addSeparator();// 添加分割条
			mnuFile.add(mnuFilePrint);
			mnuFile.add(mnuFileQuit);

			// 创建Edit菜单和相关菜单项并加入到Edit菜单中
			JMenu mnuEdit = new JMenu("功能");
			mnuEdit.add(new JMenuItem("剪切"));
			mnuEdit.add(new JMenuItem("复制"));
			mnuEdit.add(new JMenuItem("粘贴"));

			// 创建Help菜单和相关菜单项并加入到Help菜单中
			JMenu mnuHelp = new JMenu("帮助");
			mnuHelp.add(new JMenuItem("关于帮助"));
			mnuHelp.add(new JMenuItem("帮助主题"));


			menuBar.add(mnuFile);
			menuBar.add(mnuEdit);
			menuBar.add(mnuHelp);		
	}
	
	
}
