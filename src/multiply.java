

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class multiply {
	public static String res=null;
	public static double result=0;
	public static double income;
	public static double interest;
	PreparedStatement statement2 ;
	
	
	public multiply()
	{
		
	}
	
	public multiply(double income,double interest)
	{
		this.income=income;
		this.interest=interest;
	}
	
    protected void initUI() {
        final DefaultTableModel model = new DefaultTableModel() {

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                case 2:
                    return Integer.class;
                case 3:
                	return Integer.class;
                case 4:
                case 5:
                    return Double.class;
               case 7:
            	   return Double.class;
              
                 
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public Object getValueAt(int row, int column) {
               if (column == 5) {
                    Integer i = (Integer) getValueAt(row, 3);
                    Double d = (Double) getValueAt(row, 4);
                    if (i != null && d != null) {
                        return i * d;
                    } else {
                        return 0.0;
                    }
                }
               
                if(column==2){
            	   int oprice=0;
            	   String s=(String) getValueAt(row,1);
            	   
           		try
        		{
        			Class.forName("com.mysql.jdbc.Driver");
        			System.out.print("Driver Loaded");
        			
        			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
        			System.out.println("Database Connected");
        			Statement statement=connection.createStatement();
        			String str="select originalPrice from Item where Item.itemCode ='"+ s+"' ";
        			
        			ResultSet rs=statement.executeQuery(str);
        			
        			while (rs.next())
        				oprice=Integer.parseInt(rs.getString(1));
        			
        		}
        		catch(Exception ex)
        		{
        			ex.printStackTrace();
        		}
        		
        		
        		return oprice;
        		
               }
            	   
            	   
                
                
                if(column==3){
             	   int sprice=0;
             	   String s=(String) getValueAt(row,1);
             	   
            		try
         		{
         			Class.forName("com.mysql.jdbc.Driver");
         			System.out.print("Driver Loaded");
         			
         			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
         			System.out.println("Database Connected");
         			Statement statement=connection.createStatement();
         			
         			String str="select soldPrice from Item where Item.itemCode ='"+ s+"' ";
         			
         			ResultSet rs=statement.executeQuery(str);
         			
         			while (rs.next())
         				sprice=Integer.parseInt(rs.getString(1));
         			
         		}
         		catch(Exception ex)
         		{
         			ex.printStackTrace();
         		}
         		
         		
         		return sprice;
         		
                }
             	   
             	   
                if(column==7){
              	   double serprice=0;
              	   String s=(String) getValueAt(row,6);
              	   
             		try
          		{
          			Class.forName("com.mysql.jdbc.Driver");
          			System.out.print("Driver Loaded");
          			
          			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
          			System.out.println("Database Connected");
          			Statement statement=connection.createStatement();
          			String str="select serviceCost from Service where Service.serviceType ='"+ s+"' ";
          			
          			ResultSet rs=statement.executeQuery(str);
          			
          			while (rs.next())
          				serprice=Double.parseDouble(rs.getString(1));
          			
          		}
          		catch(Exception ex)
          		{
          			ex.printStackTrace();
          		}
          		
          		
          		return serprice;
          		
                 }
              	   
                 return super.getValueAt(row, column);
            }
            
            public boolean isCellEditable() {
               return true;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
            
            	
                super.setValueAt(aValue, row, column);
                fireTableCellUpdated(row, 5);
                
            }

            
            @Override
            public String getColumnName(int column) {
                switch (column) {
                case 0:
                    return "Car No.";
                case 1:
                    return "Item";
                case 2:
                    return "Original Price";
                case 3:
                    return "Unit Price";
                case 4:
                    return "Quantity";
                case 5:
                    return "Item Cost";
                case 6:
                    return "Service Type";
                case 7:
                    return "Service Cost";
                                }
                return super.getColumnName(column);
            }

            @Override
            public int getColumnCount() {
                return 8;
            }
            
           
        };
        final JTable table = new JTable(model);
      
        table.setFillsViewportHeight(true);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (table.rowAtPoint(e.getPoint()) < 0) {
                        model.addRow(new Vector());
                    }
                }
            }
        });
        model.addRow(new Vector());
        
        
        final JFrame frame = new JFrame(multiply.class.getSimpleName());
        
        frame.setTitle("Invoice");
    	
		JPanel p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p3=new JPanel(new FlowLayout(FlowLayout.LEFT));	
		JPanel p4=new JPanel(new GridLayout(1,2,5,0));
		JPanel p5=new JPanel(new GridLayout(2,1,5,0));
		JPanel pint=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p6=new JPanel(new GridLayout(2,1,5,0));
		JPanel ptint=new JPanel(new GridLayout(1,2,5,0));
		
		
		
		 String[] day={
					"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"
			};
	final JComboBox c1=new JComboBox(day);
	final JLabel days=new JLabel("Day");
	JPanel pp1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	pp1.add(days);
	pp1.add(c1);


	String[] month={"1","2","3","4","5","6","7","8","9","10","11","12"};
			final JComboBox c2=new JComboBox(month);
	final JLabel mm=new JLabel("Month");
	JPanel pp2=new JPanel(new FlowLayout(FlowLayout.CENTER));
	pp2.add(mm);
	pp2.add(c2);


	String[] year={"2010","2011","2012","2013","2014","2015"};
			
			final JComboBox c3=new JComboBox(year);
			
	final JLabel yy=new JLabel("Year");
	JPanel pp3=new JPanel(new FlowLayout(FlowLayout.LEFT));
	pp3.add(yy);
	pp3.add(c3);

	JPanel pp4=new JPanel(new GridLayout(1,3,5,5));
	pp4.add(pp1);
	pp4.add(pp2);
	pp4.add(pp3);

final JTextField it=new JTextField(15);
		JButton iamt=new JButton("Item Cost");
		p1.add(iamt);
		p1.add(it);
		
		iamt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double sum=0;
				for(int i=0;i<table.getRowCount();i++){
					sum+=(Double)table.getValueAt(i,5);
				}
				String s=""+sum;
				it.setText(s);
			
		            }
		        });
		
		
		
		
		final JTextField ttl=new JTextField(15);
		JButton tamt=new JButton("Total Amount");
		p2.add(tamt);
		p2.add(ttl);
		
		
		
		final JTextField ser=new JTextField(15);
		JButton samt=new JButton("Service Cost");
		
		samt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double sum=0;
				for(int i=0;i<table.getRowCount();i++){
					
					Object scost=table.getValueAt(i,7);
					if(scost!=null){
						sum+= (Double)table.getValueAt(i,7);
					}
					else sum=0.0;
				}
				
				String s=""+sum;
				ser.setText(s);
			
		            }
		        });
		
		tamt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double sum=Double.parseDouble(it.getText())+Double.parseDouble(ser.getText());
				String s=""+sum;
				ttl.setText(s);
			
				income=sum;
				System.out.print(income);
		            }
		        });
		
		
		
		p3.add(samt);
		p3.add(ser);     
				
		
		final JTextField tti=new JTextField(15);
		JButton tint=new JButton("Total Interest");
		pint.add(tint);
		pint.add(tti);
		
		tint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int unitinterest=0;
				double qty=0;
				double inter=0;
				double interest1=0;
				double tinterest=0;
				for(int i=0;i<table.getRowCount();i++){
					if(table.getValueAt(i, 3)!=null && table.getValueAt(i, 2)!=null && table.getValueAt(i,4)!=null)
					{
						unitinterest=(Integer)((Integer)table.getValueAt(i,3)-(Integer)table.getValueAt(i,2));
						qty=(Double)table.getValueAt(i,4);
						inter=unitinterest*qty;
						interest1+=inter;
					}
					else interest1=0.0;
				}
				tinterest=interest1+Double.parseDouble(ser.getText());
				String i=""+tinterest;
				tti.setText(i);
			
				interest=tinterest;
	System.out.print(interest);
				
		            }
		        });
		
		
		
		ptint.add(p3);
		ptint.add(pint);
		
		
		p4.add(p1);
		p4.add(p2);
		p5.add(p4);
		p5.add(ptint);
	
		
		Color c=new Color(220,220,220);
		Border b=new LineBorder(new Color(205,201,201),3);
		
		JToolBar tool=new JToolBar("Tools");
		tool.setLayout(new GridLayout(1,2,5,5));
		tool.setFloatable(false);
		JButton save=new JButton("Save Amount");
		
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			
				insertData();
				
				updateItem();
				
				res=(ttl.getText());
				result=Double.parseDouble(res);
				 System.out.print(result);
				model.setRowCount(0);
				ser.setText(null);
				ttl.setText(null);
				it.setText(null);
				tti.setText(null);
				
				
			}
			private void updateItem() {
				// TODO Auto-generated method stub
				for(int i=0;i<table.getRowCount();i++){
					String icode=null;
					double qtt=0;
					if(table.getValueAt(i,1)!=null && table.getValueAt(i,4)!=null)
					
					{
						icode=(String) table.getValueAt(i,1);
						qtt=(Double) table.getValueAt(i,4);
						
						try
						{
							Class.forName("com.mysql.jdbc.Driver");
							System.out.print("Driver Loaded");
							
							java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
							System.out.println("Database Connected");
							Statement s=(Statement) connection.createStatement();
							
					
							
							String str="select soldItem, remainItem from Item where Item.itemCode ='"+ icode +"' ";
							
							ResultSet rs=s.executeQuery(str);
							
							double titem = 0;
							double sitem=0;
							double ritem=0;
							while(rs.next())
							{
								//titem=Double.parseDouble(rs.getString(1));
								sitem=Double.parseDouble(rs.getString(1));
								ritem=Double.parseDouble(rs.getString(2));
								
							}
							
							//titem=titem-qtt;
							sitem=sitem+qtt;
							ritem=ritem-qtt;
							try
							{
								Class.forName("com.mysql.jdbc.Driver");
								System.out.print("Driver Loaded");
								
								java.sql.Connection connection1=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
								System.out.println("Database Connected");
								
								
								
									String s2 = "UPDATE Item SET soldItem='"+ (int) sitem +"' ,remainItem='"+(int)ritem+"' WHERE itemCode='"+ icode+"'";
					            	
									java.sql.PreparedStatement statement2;
									
									 statement2 = connection1.prepareStatement(s2);
									
									 
							           statement2.executeUpdate();
							       
							}
							catch(Exception ex)
							{
								ex.printStackTrace();
							}
							
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}
				}
			}
			private void insertData()
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("Driver Loaded");
			
			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
			System.out.println("Database Connected");
			
			
			
				String s2 = "INSERT INTO DailyI (dday, dmonth, dyear, dincome, dinterest) VALUES (?,?,?,?,?)";
            	
       

            statement2 = connection.prepareStatement(s2);
            statement2.setInt(1, (Integer.parseInt( (String) c1.getSelectedItem())));
            statement2.setInt(2, (Integer.parseInt( (String) c2.getSelectedItem())));
            statement2.setInt(3, (Integer.parseInt( (String) c3.getSelectedItem())));
            statement2.setDouble(4, (Double.parseDouble( (String) ttl.getText())));
            statement2.setDouble(5, (Double.parseDouble( (String) tti.getText())));
            
            statement2.executeUpdate();
       
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	});

		
		
        save.setBackground(c);
		save.setBorder(b);
		tool.add(save);
		JButton menu=new JButton("Menu");
		menu.setBackground(c);
		menu.setBorder(b);
		tool.add(menu);
		
		menu.setMnemonic('M');
		save.setMnemonic('S');
		
		
		p6.add(p5);
		JPanel p7=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		p7.add(tool);
		p6.add(p7);
		frame. add(p6,BorderLayout.SOUTH);
		 menu.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					main f1=new main();
					f1.setVisible(true);
					f1.pack();
					frame.setVisible(false);
					f1.setLocationRelativeTo(null);
					f1.setSize(600,500);
				}
			});
		 
		

			JToolBar t=new JToolBar("Date");
			t.setFloatable(false);
			
			t.add(pp4);
			
			JPanel p8=new JPanel(new FlowLayout(FlowLayout.LEFT));
			 p8.add(t);
			 frame.add(p8,BorderLayout.NORTH);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollpane = new JScrollPane(table);
        frame.add(scrollpane, BorderLayout.CENTER);
        frame.setSize(700,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
            UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new multiply().initUI();
            }
        });
    }

    
    
    
}