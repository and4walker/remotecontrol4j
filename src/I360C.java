import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
/**
 * 
 */
public class I360C
{
 
    /**
     * 
     */
    public I360C()
    {
    }
 
    public static void main(String[] args)
    {
        new MainPanel().setVisible(true);
    }
}
 
class MainPanel
    extends JFrame
    implements MouseListener, MouseMotionListener
{
 
    private static final long serialVersionUID = -8303249428878144366L;
 
    private Container con = null;
 
    private Toolkit tkit = null;
 
    private JLabel menuLabel, minLabel, closeLabel, mssfhLabel, mcpsjLabel,
            mbdcsLabel, kssmLabel, qpsmLabel, zdsmLabel;
 
    private JPanel bdcsPanel, ssfhPanel, cpsjPanel, headPanel, mainPanel;
 
    private int operType = 0;
 
    private Point offset = new Point();
 
    public MainPanel()
    {
        super();
        initSelf();
        combinElems();
    }
 
    public void initSelf()
    {
        this.setUndecorated(true);
        con = this.getContentPane();
        con.setLayout(new BorderLayout());
        this.setSize(642, 482);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("360`杀毒高手");
        tkit = new Toolkit();
        this.setIconImage(tkit.getImage("ico.gif"));
    }
 
    public void combinElems()
    {
        headPanel = new JPanel(new BorderLayout());
        new Dragger(this, headPanel);
 
        SkinPanel h1Panel = new SkinPanel("h1_bg_l.gif", 642, 17);
        h1Panel.setPreferredSize(new Dimension(642, 17));
        h1Panel.setLayout(new FlowLayout(2, 0, 0));
        menuLabel = new JLabel(tkit.getImgageIcon("h1_menu_01.gif"));
        menuLabel.setToolTipText("显示菜单");
        menuLabel.addMouseListener(this);
        minLabel = new JLabel(tkit.getImgageIcon("h1_min_01.gif"));
        minLabel.setToolTipText("最小化系统");
        minLabel.addMouseListener(this);
        closeLabel = new JLabel(tkit.getImgageIcon("h1_close_01.gif"));
        closeLabel.setToolTipText("关闭系统");
        closeLabel.addMouseListener(this);
        h1Panel.add(menuLabel);
        h1Panel.add(minLabel);
        h1Panel.add(closeLabel);
        h1Panel.add(new JLabel(tkit.getImgageIcon("h1_bg_r.gif")));
 
        SkinPanel h2Panel = new SkinPanel("h2_01.gif", 642, 85);
        h2Panel.setPreferredSize(new Dimension(642, 85));
        h2Panel.setLayout(new FlowLayout(2, 0, 0));
        h2Panel.add(new JLabel(tkit.getImgageIcon("h2_02.gif")));
 
        headPanel.add(BorderLayout.NORTH, h1Panel);
        headPanel.add(BorderLayout.CENTER, h2Panel);
 
        JPanel bPanel = new JPanel(new BorderLayout());
        JPanel btPanel = new JPanel(new BorderLayout());
        JLabel bltLabel = new JLabel(tkit.getImgageIcon("blt.gif"));
        JLabel brtLabel = new JLabel(tkit.getImgageIcon("brt.gif"));
        JPanel btmpPanel = new SkinPanel("m_fgf.gif", 400, 37);
        btmpPanel.setPreferredSize(new Dimension(400, 37));
        btmpPanel.setLayout(new FlowLayout(0, 0, 0));
        mbdcsLabel = new JLabel(tkit.getImgageIcon("m_bdcs_00.gif"));
        mbdcsLabel.addMouseListener(this);
        mbdcsLabel.setToolTipText("病毒查杀");
        mssfhLabel = new JLabel(tkit.getImgageIcon("m_ssfh_01.gif"));
        mssfhLabel.addMouseListener(this);
        mssfhLabel.setToolTipText("实时防护");
        mcpsjLabel = new JLabel(tkit.getImgageIcon("m_cpsj_01.gif"));
        mcpsjLabel.addMouseListener(this);
        mcpsjLabel.setToolTipText("产品升级");
        btmpPanel.add(mbdcsLabel);
        btmpPanel.add(new JLabel(tkit.getImgageIcon("m_fgf.gif")));
        btmpPanel.add(mssfhLabel);
        btmpPanel.add(new JLabel(tkit.getImgageIcon("m_fgf.gif")));
        btmpPanel.add(mcpsjLabel);
        btmpPanel.add(new JLabel(tkit.getImgageIcon("m_fgf.gif")));
 
        btPanel.add(BorderLayout.WEST, bltLabel);
        btPanel.add(BorderLayout.CENTER, btmpPanel);
        btPanel.add(BorderLayout.EAST, brtLabel);
 
        btPanel.setPreferredSize(new Dimension(622, 38));
        SkinPanel blPanel = new SkinPanel("bl.gif", 0, 0);
        blPanel.setPreferredSize(new Dimension(10, 368));
        SkinPanel brPanel = new SkinPanel("br.gif", 0, 0);
        brPanel.setPreferredSize(new Dimension(9, 368));
        SkinPanel bmPanel = new SkinPanel("bm.gif", 0, 0);
        bmPanel.setPreferredSize(new Dimension(642, 10));
 
        mainPanel = new JPanel(new CardLayout());
        mainPanel.setBackground(Color.white);
        mainPanel.setPreferredSize(new Dimension(622, 331));
        bdcsPanel = new JPanel(new FlowLayout(1, 30, 50));
        bdcsPanel.setBackground(Color.white);
        kssmLabel = new JLabel(tkit.getImgageIcon("b_kssm_01.gif"));
        kssmLabel.addMouseListener(this);
        qpsmLabel = new JLabel(tkit.getImgageIcon("b_qpsm_01.gif"));
        qpsmLabel.addMouseListener(this);
        zdsmLabel = new JLabel(tkit.getImgageIcon("b_zdsm_01.gif"));
        zdsmLabel.addMouseListener(this);
        bdcsPanel.add(kssmLabel);
        bdcsPanel.add(qpsmLabel);
        bdcsPanel.add(zdsmLabel);
 
        ssfhPanel = new JPanel();
        ssfhPanel.setBackground(Color.white);
 
        cpsjPanel = new JPanel();
        cpsjPanel.setBackground(Color.white);
 
        mainPanel.add("bdcs", bdcsPanel);
        mainPanel.add("cpsj", cpsjPanel);
        mainPanel.add("ssfh", ssfhPanel);
 
        bPanel.add(BorderLayout.NORTH, btPanel);
        bPanel.add(BorderLayout.WEST, blPanel);
        bPanel.add(BorderLayout.SOUTH, bmPanel);
        bPanel.add(BorderLayout.EAST, brPanel);
        bPanel.add(BorderLayout.CENTER, mainPanel);
 
        con.add(BorderLayout.NORTH, headPanel);
        con.add(BorderLayout.CENTER, bPanel);
    }
 
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (closeLabel == e.getSource())
        {
            System.exit(0);
        } else if (minLabel == e.getSource())
        {
            this.setState(JFrame.ICONIFIED);
        } else if (0 != operType && mbdcsLabel == e.getSource())
        {
            mbdcsLabel.setIcon(tkit.getImgageIcon("m_bdcs_00.gif"));
            mssfhLabel.setIcon(tkit.getImgageIcon("m_ssfh_01.gif"));
            mcpsjLabel.setIcon(tkit.getImgageIcon("m_cpsj_01.gif"));
            operType = 0;
            ((CardLayout) mainPanel.getLayout()).show(mainPanel, "bdcs");
        } else if (1 != operType && mssfhLabel == e.getSource())
        {
            mssfhLabel.setIcon(tkit.getImgageIcon("m_ssfh_00.gif"));
            mbdcsLabel.setIcon(tkit.getImgageIcon("m_bdcs_01.gif"));
            mcpsjLabel.setIcon(tkit.getImgageIcon("m_cpsj_01.gif"));
            operType = 1;
            ((CardLayout) mainPanel.getLayout()).show(mainPanel, "ssfh");
        } else if (2 != operType && mcpsjLabel == e.getSource())
        {
            mcpsjLabel.setIcon(tkit.getImgageIcon("m_cpsj_00.gif"));
            mbdcsLabel.setIcon(tkit.getImgageIcon("m_bdcs_01.gif"));
            mssfhLabel.setIcon(tkit.getImgageIcon("m_ssfh_01.gif"));
            operType = 2;
            ((CardLayout) mainPanel.getLayout()).show(mainPanel, "cpsj");
        }
        setCursor(Cursor.DEFAULT_CURSOR);
    }
 
    @Override
    public void mouseEntered(MouseEvent e)
    {
        if (menuLabel == e.getSource())
        {
            menuLabel.setIcon(tkit.getImgageIcon("h1_menu_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (minLabel == e.getSource())
        {
            minLabel.setIcon(tkit.getImgageIcon("h1_min_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (closeLabel == e.getSource())
        {
            closeLabel.setIcon(tkit.getImgageIcon("h1_close_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (0 != operType && mbdcsLabel == e.getSource())
        {
            mbdcsLabel.setIcon(tkit.getImgageIcon("m_bdcs_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (1 != operType && mssfhLabel == e.getSource())
        {
            mssfhLabel.setIcon(tkit.getImgageIcon("m_ssfh_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (2 != operType && mcpsjLabel == e.getSource())
        {
            mcpsjLabel.setIcon(tkit.getImgageIcon("m_cpsj_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (kssmLabel == e.getSource())
        {
            kssmLabel.setIcon(tkit.getImgageIcon("b_kssm_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (qpsmLabel == e.getSource())
        {
            qpsmLabel.setIcon(tkit.getImgageIcon("b_qpsm_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        } else if (zdsmLabel == e.getSource())
        {
            zdsmLabel.setIcon(tkit.getImgageIcon("b_zdsm_02.gif"));
            setCursor(Cursor.HAND_CURSOR);
        }
    }
 
    @Override
    public void mouseExited(MouseEvent e)
    {
        if (menuLabel == e.getSource())
        {
            menuLabel.setIcon(tkit.getImgageIcon("h1_menu_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (minLabel == e.getSource())
        {
            minLabel.setIcon(tkit.getImgageIcon("h1_min_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (closeLabel == e.getSource())
        {
            closeLabel.setIcon(tkit.getImgageIcon("h1_close_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (0 != operType && mbdcsLabel == e.getSource())
        {
            mbdcsLabel.setIcon(tkit.getImgageIcon("m_bdcs_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (1 != operType && mssfhLabel == e.getSource())
        {
            mssfhLabel.setIcon(tkit.getImgageIcon("m_ssfh_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (2 != operType && mcpsjLabel == e.getSource())
        {
            mcpsjLabel.setIcon(tkit.getImgageIcon("m_cpsj_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (kssmLabel == e.getSource())
        {
            kssmLabel.setIcon(tkit.getImgageIcon("b_kssm_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (qpsmLabel == e.getSource())
        {
            qpsmLabel.setIcon(tkit.getImgageIcon("b_qpsm_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        } else if (zdsmLabel == e.getSource())
        {
            zdsmLabel.setIcon(tkit.getImgageIcon("b_zdsm_01.gif"));
            setCursor(Cursor.DEFAULT_CURSOR);
        }
    }
 
    @Override
    public void mousePressed(MouseEvent e)
    {
    }
 
    @Override
    public void mouseReleased(MouseEvent e)
    {
    }
 
    @Override
    public void mouseDragged(MouseEvent e)
    {
    }
 
    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
}
 
class SkinPanel
    extends JPanel
{
    private static final long serialVersionUID = -1142694233560441425L;
 
    private static String RES_PATH = "/res/";
 
    private Image skinImg;
 
    private int width, height;
 
    public SkinPanel(String resName, int width, int height)
    {
        this.width = width;
        this.height = height;
        try
        {
            skinImg = ImageIO.read(this.getClass().getResource(
                RES_PATH + resName));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
 
    /**
     * 复写渲染方法
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (0 != width)
        {
            g.drawImage(skinImg, 0, 0, width, height, null);
        } else
        {
            g.drawImage(skinImg, 0, 0, null);
        }
 
    }
}
 
class Toolkit
{
    private static String RES_PATH = "/res/";
 
    public Image getImage(String resName)
    {
        Image img = null;
        try
        {
            img = ImageIO.read(this.getClass().getResource(RES_PATH + resName));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return img;
    }
 
    public ImageIcon getImgageIcon(String resName)
    {
        return new ImageIcon(getImage(resName));
    }
}
 
class Dragger
{
    private Window fWindow;
 
    private Component fComponent;
 
    private int dx;
 
    private int dy;
 
    /**
     * 让指定的Component通过鼠标拖动来移动Window
     */
    public Dragger(Window window, Component component)
    {
 
        fWindow = window;
        fComponent = component;
 
        fComponent.addMouseListener(createMouseListener());
        fComponent.addMouseMotionListener(createMouseMotionListener());
    }
 
    private MouseListener createMouseListener()
    {
        return new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                Point clickPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(clickPoint, fComponent);
 
                dx = clickPoint.x - fWindow.getX();
                dy = clickPoint.y - fWindow.getY();
                fWindow.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
 
            public void mouseReleased(MouseEvent e)
            {
                fWindow.setCursor(Cursor.getDefaultCursor());
            }
        };
    }
 
    private MouseMotionAdapter createMouseMotionListener()
    {
        return new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                Point dragPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(dragPoint, fComponent);
 
                fWindow.setLocation(dragPoint.x - dx, dragPoint.y - dy);
            }
        };
    }
}