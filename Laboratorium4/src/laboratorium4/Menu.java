package laboratorium4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class Menu extends JFrame{

	static JMenuBar menuBar;
	static JMenu menu;
	static JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;
	static int lineWidth = 1;
    
    Menu()
    {
	menuBar = new JMenuBar();
    menu = new JMenu("Line width");
    menuItem1 = new JMenuItem("1 pxl");
    menuItem1.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lineWidth = 1;
            getContentPane().repaint();
        }
    });
    menuItem2 = new JMenuItem("5 pxl");
    menuItem2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lineWidth = 5;
            getContentPane().repaint();
        }
    });
    menuItem3 = new JMenuItem("10 pxl");
    menuItem3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lineWidth = 10;
            getContentPane().repaint();
        }
    });
    
    //menu1 = new JMenu("Author");
    menuItem4 = new JMenuItem("Author");
    menuItem4.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        		JFrame frame1 = new JFrame();
        		JLabel label3 = new JLabel("AUTOR: Marcin P¹œko");
        		frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        		frame1.setSize(300,200);
        		frame1.add(label3, BorderLayout.CENTER);
        		frame1.setVisible(true);
        }
    });

    menu.add(menuItem1);
    menu.add(menuItem2);
    menu.add(menuItem3);
    menuBar.add(menu);
    
    menuBar.add(menuItem4);
    
    this.setJMenuBar(menuBar);
    }
}
