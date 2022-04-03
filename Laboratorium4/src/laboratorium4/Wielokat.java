package laboratorium4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.Random;

public class Wielokat extends JFrame{
	//inicjalizacja suwaka
	//MARCIN MARCIN
	//dodaje komentarz
	static final int SLIDER_MIN = 3;
    static final int SLIDER_MAX = 33;
    static final int SLIDER_INIT = 3;
	//elementy
	private JPanel topPanel, downPanel, leftPanel, rightPanel, centerPanel;
	private JButton button1, button2, button3;
	private JSlider slider;
	private JRadioButton radio1, radio2;
	private JLabel label1, label2, label3, label4;
	private JTextField field2, field3;
	//menu
	private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem1, menuItem2, menuItem3, menuItem4;

    private String selectedPanel = "regular";
    private Color lineColor = Color.black;
    private int lineWidth = 1;
		
    private Polygon polygon;
    
    private int x[];
    private int y[];
    
    private static final int radius = 100;
    
    private static int sliderValue = SLIDER_INIT;
      
    
    public Wielokat(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900,600);
        
        //MENU
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
        
        menuItem4 = new JMenuItem("Author");
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		JFrame frame1 = new JFrame();
            		JLabel label2 = new JLabel("AUTHOR: Marcin P¹œko");
            		label2.setBackground(Color.black); // nie dzia³a (?)
            		label2.setForeground(Color.blue);
            		frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            		frame1.setSize(300,200);
            		frame1.add(label2, BorderLayout.CENTER);
            		frame1.setVisible(true);
            }
        });
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menuBar.add(menu);
        
        menuBar.add(menuItem4);
        this.setJMenuBar(menuBar);
         
        //GÓRNY
        topPanel = new JPanel();
        slider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
        slider.setMajorTickSpacing(10); //podzia³ki
        slider.setMinorTickSpacing(2); //ma³e kreski
        slider.setPaintTicks(true);//kreski
        slider.setPaintLabels(true); //liczby
        slider.addChangeListener(new SliderChangeListener());

        button1 = new JButton("draw");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	button1.setBackground(Color.red);
            }
        });
        
        label1 = new JLabel("No. of vertices");
        
        topPanel.add(label1);
        topPanel.add(slider);
        topPanel.add(button1);
        this.add(topPanel, BorderLayout.PAGE_START);
        
                
        //LEWY
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3,1));
        label2 = new JLabel("POLYGON");

        radio1 = new JRadioButton("Regular");
        radio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPanel = "regular";
                drawRegularShape(sliderValue);
            }
        });
        radio1.setSelected(true); //domyœlny

        radio2 = new JRadioButton("Random");
        radio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPanel = "random";
                drawRandomShape(sliderValue);
            }
        });

        leftPanel.add(label2);
        leftPanel.add(radio1);
        leftPanel.add(radio2);
        this.add(leftPanel, BorderLayout.LINE_START);
                

        //PRAWY
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2,1));
        label3 = new JLabel("X pos");
        label4 = new JLabel("Y pos");
        field2 = new JTextField("l k¹tów");
        field3 = new JTextField();
        rightPanel.add(label3);
        rightPanel.add(label4);
        rightPanel.add(field2);
        rightPanel.add(field3);
        this.add(rightPanel, BorderLayout.LINE_END);

        
        //ŒROdKOWY
        drawRegularShape(3);
        centerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                BasicStroke stroke = new BasicStroke(lineWidth); //do zwiêkszania gruboœci
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(370, 230); //ustawienie na œrodku
                g2d.setStroke(stroke);
                g2d.setColor(lineColor);
                g2d.drawPolygon(polygon);
            }
        };
        centerPanel.setBackground(Color.white);
        this.add(centerPanel, BorderLayout.CENTER);
        
        //DOLNY        
        downPanel = new JPanel();
        downPanel.setLayout(new FlowLayout());
        button2 = new JButton("BG color");
        button3 = new JButton("LN color");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser color = new JColorChooser();
                JDialog dialog = JColorChooser.createDialog(Wielokat.this, "Kolor t³a", true, color, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        centerPanel.setBackground(color.getColor());
                    }}, null);
                dialog.setVisible(true);
            }
        });
        
        button3.addActionListener(new ActionListener() {
        	 @Override
             public void actionPerformed(ActionEvent e) {
                 JColorChooser color = new JColorChooser();
                 JDialog dialog = JColorChooser.createDialog(Wielokat.this, "Kolor linii", true, color, new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {
                         lineColor = color.getColor();
                         getContentPane().repaint();
                     }
                 }, null);
                 dialog.setVisible(true);
             }
         });
        
        downPanel.add(button2);
        downPanel.add(button3);
        this.add(downPanel, BorderLayout.PAGE_END);
        
    }
    
    private void drawRandomShape(int angle) {
        Random rand = new Random();
        x = new int[angle];
        y = new int[angle];

        for (int i = 0; i < angle; i++) 
        {
            x[i] = rand.nextInt(100);
        }
        for (int i = 0; i < angle; i++) 
        {
            y[i] = rand.nextInt(100) ;
        }

        polygon = new Polygon(x, y, angle);
        this.getContentPane().repaint();
    }
        
    private void drawRegularShape(int angle) {
        x = new int[angle];
        y = new int[angle];

        for (int i = 0; i < angle; i++) {
            x[i] = (int) (radius * Math.cos((Math.PI / 2 + 2 * Math.PI * i) / angle));
        }
        for (int i = 0; i < angle; i++) {
            y[i] = (int) (radius * Math.sin((Math.PI / 2 + 2 * Math.PI * i) / angle));
        }

        polygon = new Polygon(x, y, angle);
        this.getContentPane().repaint();
    }
    
	public static void main(String[] args) {
		Wielokat frame = new Wielokat();
        frame.setVisible(true);
	}
		
	public class SliderChangeListener implements ChangeListener {
		   @Override
	        public void stateChanged(ChangeEvent arg0) {
	            JSlider source = (JSlider) arg0.getSource();
	            
	            if (selectedPanel == "regular") {
	                drawRegularShape(source.getValue());
	            } 
	            else if (selectedPanel == "random") {
	                drawRandomShape(source.getValue());
	            }
	            String value = String.format("%d", slider.getValue());
	 		    field3.setText(value);
	        }		   
	    }
}