import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class DailyBenifit extends JFrame{
	public DailyBenifit(){
		Font Lfont=new Font("Serif",Font.BOLD+Font.ITALIC,20);
		Font sfont=new Font("Serif",Font.BOLD+Font.ITALIC,18);
		Border Blue=new LineBorder(new Color(139,137,137),2);
		Border Black=new LineBorder(new Color(139,137,137),1);
		
	
		
		
		String[] day={
				"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"
		};
		final JComboBox c1=new JComboBox(day);
		
		String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
		final JComboBox c2=new JComboBox(month);
		
		String[] year={"2010","2011","2012","2013","2014","2015"};
		
		final JComboBox c3=new JComboBox(year);
		
		
		setLayout(new GridLayout(5,1));
		JPanel p1=new JPanel(new FlowLayout());
		JLabel ed=new JLabel("Enter Date:");
		ed.setFont(Lfont);
		
		p1.add(ed);
		JPanel p1_branch1=new JPanel(new GridLayout(2,1));
		
		JPanel p1_branch1_1=new JPanel(new GridLayout(1,3));
		
		JPanel p1_branch1_1_1=new JPanel(new FlowLayout());
		
		JLabel days=new JLabel("Day");
		
		p1_branch1_1_1.add(days);
		p1_branch1_1_1.add(c1);
		JPanel p1_branch1_1_2=new JPanel(new FlowLayout());
		
		
		JLabel mm=new JLabel("Month");
		
		p1_branch1_1_2.add(mm);
		p1_branch1_1_2.add(c2);
		JPanel p1_branch1_1_3=new JPanel(new FlowLayout());
		
		
		JLabel yy=new JLabel("Year");
		
		p1_branch1_1_3.add(yy);
		p1_branch1_1_3.add(c3);
		p1_branch1_1.add(p1_branch1_1_1);
		p1_branch1_1.add(p1_branch1_1_2);
		p1_branch1_1.add(p1_branch1_1_3);
		JPanel p1_branch1_2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		
		JButton submit=new JButton("Submit");

		p1_branch1_2.add(submit);
		p1_branch1.add(p1_branch1_1);
		p1_branch1.add(p1_branch1_2);
		p1_branch1.setBorder(Black);
		p1.add(p1_branch1);
		p1.setBorder(Blue);
		
		JPanel p2=new JPanel(new FlowLayout());
	
		JLabel dd=new JLabel("Your Desired Date:");
	
		p2.add(dd);
		final JTextField date=new JTextField(10);
		date.setEditable(false);
		p2.add(date);
		
		JPanel p3=new JPanel(new GridLayout(2,1));
	
		
		JPanel p3_branch1=new JPanel(new FlowLayout());
	
		
		JButton income=new JButton("Calculate Daily Income");
	
		
		
		java.sql.Connection connection;
		java.sql.Statement statement2;
		
		final JTextField setincome=new JTextField(10);
		
		income.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
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
			
			
			int $day=Integer.parseInt((String) c1.getSelectedItem());
			int $month=Integer.parseInt((String) c2.getSelectedItem());
			int $year=Integer.parseInt((String) c3.getSelectedItem());
			
			String str="select dincome from DailyI where DailyI.dday ='"+ $day +"' "+"and DailyI.dmonth='"+$month+"'"+"and DailyI.dyear='"+$year+"' " ;
		
			ResultSet rs=s.executeQuery(str);
			
			
			 double[]dii=new double[100];
			
			 double totaldii=0;
			double totaldint=0;
			
			int count=0;
			
			int i=0;
			while(rs.next())
			{
				
				
					dii[i]=Double.parseDouble(rs.getString(1));
					totaldii+=dii[i];
					i++;
					
           }
			
			setincome.setText(String.valueOf(totaldii));
			
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
		JButton interest=new JButton("Calculate Daily Interest");
		
		final JTextField setinterest=new JTextField(10);
		interest.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
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
			
			
			int $day=Integer.parseInt((String) c1.getSelectedItem());
			int $month=Integer.parseInt((String) c2.getSelectedItem());
			int $year=Integer.parseInt((String) c3.getSelectedItem());
			
			String str="select dinterest from DailyI where DailyI.dday ='"+ $day +"' "+"and DailyI.dmonth='"+$month+"'"+"and DailyI.dyear='"+$year+"' " ;
			
			ResultSet rs=s.executeQuery(str);
			 double[]dii=new double[100];
			double totaldint=0;int count=0;
			int i=0;
			while(rs.next())
			{dii[i]=Double.parseDouble(rs.getString(1));
					totaldint+=dii[i];
					i++;
					
           }
			
			setinterest.setText(String.valueOf(totaldint));
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
		final JPanel p4=new JPanel(new GridLayout(2,1));
	JLabel confirm=new JLabel("Do you want to save the interest and income to monthly interest and income?");
	confirm.setFont(sfont);
		p4.add(confirm);
		JPanel p4_branch1=new JPanel(new FlowLayout());
		JButton yes=new JButton("Yes");
	yes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.print("Driver Loaded");
					
					java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
					System.out.println("Database Connected");
					
					
					
						String s2 = "INSERT INTO MonthlyI (mmonth, myear, mincome, minterest) VALUES (?,?,?,?)";
		            	
						java.sql.PreparedStatement statement2;
						
						 statement2 = connection.prepareStatement(s2);
				            ( statement2).setInt(1, (Integer.parseInt( (String) c2.getSelectedItem())));
				            ( statement2).setInt(2, (Integer.parseInt( (String) c3.getSelectedItem())));
				           
				            ( statement2).setDouble(3, (Double.parseDouble( (String) setincome.getText())));
				            ( statement2).setDouble(4, (Double.parseDouble( (String) setinterest.getText())));
				            
				            statement2.executeUpdate();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			
				JLabel sure=new JLabel("Amount have saved successfully");
				JOptionPane.showMessageDialog(p4, sure, "Amount have saved successfully", JOptionPane.INFORMATION_MESSAGE,null);
		
				
			}

		
		});	
		
		
		
		JButton no=new JButton("No");
		no.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JLabel sure=new JLabel("Amount haven't saved");
				JOptionPane.showMessageDialog(p4, sure, "Do Not Save Amount", JOptionPane.INFORMATION_MESSAGE,null);
		
			}
		});	
		
		
	p4_branch1.add(yes);
		p4_branch1.add(no);
		p4.add(p4_branch1);
		JPanel p5=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton menu=new JButton("menu");
		
		p5.add(menu);
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
				String s1=(String) c1.getSelectedItem();
				String s2=(String) c2.getSelectedItem();
				String s3=(String) c3.getSelectedItem();
				date.setText(s1+" ."+s2+" ."+s3);
				
			}
		});
		
		add(p1);
		add(p2,BorderLayout.SOUTH);
		add(p3);
		add(p4);
		add(p5);
		
	}
	public static void main(String[] args){
		DailyBenifit frame=new DailyBenifit();
		frame.setTitle("Daily Benifit");
		frame.setSize(600,500);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
