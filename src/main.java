import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class main extends JFrame{
	public main (){
		Font sfont=new Font("Serif",Font.BOLD+Font.ITALIC,15);
		
		
		add(new imagePanel());
		JButton b1=new JButton("Invoice");
		JButton b2=new JButton("Instock Item");
		JButton b4=new JButton("Daily Income & Interest");
		JButton b5=new JButton("Monthly Income & Interest");
		JButton b6=new JButton("Yearly Income & Interest");
		
		
		b1.setMnemonic('v');
		b2.setMnemonic('i');
		b4.setMnemonic('d');
		b5.setMnemonic('m');
		b6.setMnemonic('y');
		
		b1.setFont(sfont);
		b2.setFont(sfont);
		b6.setFont(sfont);
		b4.setFont(sfont);
		b5.setFont(sfont);
		
		
		b1.setForeground(new Color(0,0,0));
		b2.setForeground(new Color(0,0,0));
		b4.setForeground(new Color(0,0,0));
		b5.setForeground(new Color(0,0,0));
		b6.setForeground(new Color(0,0,0));
		
		b1.setBackground(new Color(176,224,230));
		b2.setBackground(new Color(176,224,230));
		b4.setBackground(new Color(176,224,230));
		b5.setBackground(new Color(176,224,230));
		b6.setBackground(new Color(176,224,230));
		
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				SwingUtilities.invokeLater(new Runnable() {

		            @Override
		            public void run() {
		            	try{
		                new multiply().initUI();
		            	}
		            	catch(Exception ex){
		            		ex.printStackTrace();
		            	}
		            }
		        });
				
				setVisible(false);
				
			}
		});
		
		b4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				DailyBenifit f1=new DailyBenifit();
				f1.setVisible(true);
				f1.pack();
				setVisible(false);
				f1.setLocationRelativeTo(null);
				f1.setSize(600,500);
			}
		});
		
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				monthlybenifit f2=new monthlybenifit();
				f2.setVisible(true);
				f2.pack();
				setVisible(false);
				f2.setLocationRelativeTo(null);
				f2.setSize(600,500);
			}
		});
		b6.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				yearlybenifit f1=new yearlybenifit();
				f1.setVisible(true);
				f1.pack();
				setVisible(false);
				f1.setLocationRelativeTo(null);
				f1.setSize(600,500);
			}
		});
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					sample s=new sample();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setVisible(false);
			}
		});
		JToolBar tb=new JToolBar("Tools");
		tb.setFloatable(false);
		tb.add(b1);
		tb.add(b2);
		tb.add(b4);
		tb.add(b5);
		tb.add(b6);
		add(tb,BorderLayout.NORTH);
		
	
	}
	class imagePanel extends JPanel{
		ImageIcon im=new ImageIcon("D:/Thirdyrprj/img7.jpg");
		Image ima=im.getImage();
		protected void paintComponent(Graphics g){
			super.paintComponents(g);
			if(ima!=null){
				g.drawImage(ima,0,0,getWidth(),getHeight(),this);
			}
		}
	}

public static void main(String[] args){
	main frame=new main();
	frame.setTitle("Main");
	frame.setSize(700,500);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	
}
}
