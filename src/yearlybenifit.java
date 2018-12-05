import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class yearlybenifit extends JFrame{
	public yearlybenifit(){
		Font Lfont=new Font("Serif",Font.BOLD+Font.ITALIC,20);
		Font sfont=new Font("Serif",Font.BOLD+Font.ITALIC,18);
		Border Blue=new LineBorder(new Color(139,137,137),2);
		Border Black=new LineBorder(new Color(139,137,137),1);
		JLabel j=new JLabel("Your Desired Date");
	
		String[] year={"2010","2011","2012","2013","2014","2015"};
		final JComboBox c3=new JComboBox(year);
		
		//declaration finish
		setLayout(new GridLayout(4,1));
		JPanel p1=new JPanel(new GridLayout(2,1));
		JPanel p1_1=new JPanel(new GridLayout(1,2,5,5));
		
		JPanel pfed=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JLabel ed=new JLabel("Enter Year:");
		
		pfed.add(ed);
		ed.setFont(sfont);
		JPanel pfcb=new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		pfcb.add(c3);
		p1_1.add(pfed);
		p1_1.add(pfcb);
		
		JPanel pf=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton submit=new JButton("Submit");
		
		pf.add(submit);
		
		p1.add(p1_1);
		p1.add(pf);


		p1.setBorder(Blue);
		
		//p1 finish
		JPanel p2=new JPanel(new FlowLayout());
		p2.add(j);
		
		final JTextField date=new JTextField(10);
		date.setEditable(false);
		p2.add(date);
		
		//p2 finish
		JPanel p3=new JPanel(new GridLayout(2,1));
		JPanel p3_branch1=new JPanel(new FlowLayout());
		JButton income=new JButton("Calculate Yearly Income");
		
		
		final JTextField setincome=new JTextField(10);
		
		
		
		income.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			
				getIncome();
			}
			
			
			private void getIncome()
	{
		
				
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("Driver Loaded");
			
			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
			System.out.println("Database Connected");
			Statement s=connection.createStatement();
			int $year=Integer.parseInt((String) c3.getSelectedItem());
			String str="select yincome from YearlyI where YearlyI.yyear='"+$year+"' " ;
			
			ResultSet rs=s.executeQuery(str);
			
			
			 double[]mii=new double[100];
			
			 double totalmii=0;
			double totalmint=0;
		
			
			int count=0;
			int i=0;
			while(rs.next())
			{
				mii[i]=Double.parseDouble(rs.getString(1));
					totalmii+=mii[i];
					i++;
					
           }
			
			setincome.setText(String.valueOf(totalmii));
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	}); 
		
		p3_branch1.add(income);
		p3_branch1.add(setincome);
		p3.add(p3_branch1);
		JPanel p3_branch2=new JPanel(new FlowLayout());
		JButton interest=new JButton("Calculate Yearly Interest");
		final JTextField setinterest=new JTextField(10);
		interest.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getInterest();
				
			}
			
			
			private void getInterest()
	{
		
				
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("Driver Loaded");
			
			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
			System.out.println("Database Connected");
			Statement s=connection.createStatement();
			
			int $year=Integer.parseInt((String) c3.getSelectedItem());
			
			String str="select yinterest from YearlyI where YearlyI.yyear='"+$year+"' " ;
			ResultSet rs=s.executeQuery(str);
			
			
			 double[]mii=new double[100];
			
			
			double totalmint=0;
			
			int count=0;
			
			int i=0;
			while(rs.next())
			{
				mii[i]=Double.parseDouble(rs.getString(1));
					totalmint+=mii[i];
					i++;
					
           }
			
			setinterest.setText(String.valueOf(totalmint));
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	}); 
		
		p3_branch2.add(interest);
		p3_branch2.add(setinterest);
		p3.add(p3_branch2);
		p3.setBackground(new Color(255,255,224));
		
		//finish p3
		JPanel p5=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton menu=new JButton("menu");
		
		
		p5.add(menu);
		
		//finish p5
		
		menu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				main f1=new main();
				f1.setVisible(true);
				f1.pack();
				setVisible(false);
				f1.setLocationRelativeTo(null);
				f1.setSize(600,500);
			}
		});
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s3=(String) c3.getSelectedItem();
				date.setText(s3);
				
			}
		});
		
	//finish action
		add(p1);
		add(p2,BorderLayout.SOUTH);
		add(p3);
		add(p5);
		
	}
	public static void main(String[] args){
		yearlybenifit frame=new yearlybenifit();
		frame.setTitle("Yearly Benifit");
		frame.setSize(600,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
