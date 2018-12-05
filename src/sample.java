import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
public class sample {
	public sample()throws SQLException,ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.print("Driver Loaded");
		
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
		System.out.println("Database Conected");
		Statement s=connection.createStatement();
		ResultSet rs=s.executeQuery("select * from Item");
		
		final JTextField jtf=new JTextField();
		
		
					String[][] st=new String[200][6];	
					int j=0;
				int count=0;
				while(rs.next()){
					
					for (int i=1;i<7;i++){
					st[j][i-1]=(rs.getString(i));
					
							
				}	
					j++;
					count++;
					
				
								
				
			}
				String[][] customer=new String [count][6];
				
				for(int r=0;r<count;r++)
				{
					for(int c=0;c<6;c++)
					{
						customer[r][c]=st[r][c];
					}
					
				}
				
					String[] col={"Description","Original Price","Sold Price","Total item","sold item","remain item"};
					
					
					DefaultTableModel table=new DefaultTableModel(customer,col){
						public boolean isCellEditable(int row,int column){
							
							return true;
						}		
					};
					final Color al=new Color(245,245,245);
					
					JTable tb=new JTable(table){
						public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
							Component stamp=super.prepareRenderer(renderer, row, column);
							if(row%2==0)
								stamp.setBackground(al);
							else
								stamp.setBackground(this.getBackground());
							return stamp;
						}
						
					};
					final TableRowSorter<TableModel> rows=new TableRowSorter
					(tb.getModel());
					tb.setRowSorter(rows);
					
				
					JScrollPane jp=new JScrollPane(tb);
						final JFrame f=new JFrame("Instock");
						
						f.setLayout(new BorderLayout());
						
						f.add(jp,BorderLayout.CENTER);
						
						
						jtf.getDocument().addDocumentListener(new DocumentListener(){

							@Override
							public void changedUpdate(DocumentEvent e) {
								throw new UnsupportedOperationException("Not Supported yet.");
								
								// TODO Auto-generated method stub
								
							}

							@Override
							public void insertUpdate(DocumentEvent e) {
								String text=jtf.getText();
								if(text.trim().length()==0){
									rows.setRowFilter(null);
								}
								else{
									rows.setRowFilter(RowFilter.regexFilter("(?i)"+text));
								}
								// TODO Auto-generated method stub
								
							}

							@Override
							public void removeUpdate(DocumentEvent e) {
								String text=jtf.getText();
								if(text.trim().length()==0){
									rows.setRowFilter(null);
								}
								else{
									rows.setRowFilter(RowFilter.regexFilter("(?i)"+text));
								}
								// TODO Auto-generated method stub
								
							}
							
							
						});
						
						
						
						
						f.setVisible(true);
						
						
						f.setSize(700,500);
						
						f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						f.setLocationRelativeTo(null);
						
						JToolBar tool=new JToolBar("Tools");
						tool.setFloatable(false);
						JButton menu=new JButton("Menu");
						tool.add(menu);
						
						JPanel pp=new JPanel(new BorderLayout());
						JPanel b=new JPanel(new FlowLayout(FlowLayout.RIGHT));
						JPanel p=new JPanel(new BorderLayout());
						JButton New=new JButton("NEW ITEM");
						JButton Delete=new JButton("DELETE ITEM");
						JButton UpdateI=new JButton("UPDATE TOTAL ITEM ");
						JButton UpdateP=new JButton("UPDATE PRICE");
						b.add(New);
						b.add(Delete);
						b.add(UpdateI);
						b.add(UpdateP);
						
						
						New.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								
								JLabel itemcode=new JLabel("ITEM CODE");
								JLabel originalprice=new JLabel("ORIGINAL PRICE");
								JLabel soldprice=new JLabel("SOLD PRICE");
								JLabel totalitem=new JLabel("TOTAL ITEM");
								
								final JTextField ic=new JTextField(10);
								final JTextField op=new JTextField(10);
								final JTextField sp=new JTextField(10);
								final JTextField ti=new JTextField(10);
								
								
								JButton update=new JButton("ADD ITEM");
								
								
								update.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										
										String icode=ic.getText();
										int oprice=Integer.parseInt(op.getText());
										int sprice=Integer.parseInt(sp.getText());
										int titem=Integer.parseInt(ti.getText());
										 int si=0;
										 int ri=Integer.parseInt(ti.getText());
										
										try
										{
											Class.forName("com.mysql.jdbc.Driver");
											System.out.print("Driver Loaded");
											
											java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
											System.out.println("Database Connected");
											
											
											
												String s2 = "INSERT INTO Item (itemCode,originalPrice,soldPrice,noOfItem,soldItem,remainItem) VALUES (?,?,?,?,?,?)";
								            	
												java.sql.PreparedStatement statement2;
												
												 statement2 = connection.prepareStatement(s2);
										            ( statement2).setString(1,icode);
										            ( statement2).setInt(2,oprice);
										            ( statement2).setInt(3,sprice);
										            ( statement2).setInt(4,titem);
										           ( statement2).setInt(5,si);
										           ( statement2).setInt(6,ri);
										            
										            statement2.executeUpdate();
										       
										}
										catch(Exception ex)
										{
											ex.printStackTrace();
										}
										
										JLabel text=new JLabel("New Item is Added Successfully");
										JOptionPane.showOptionDialog(null, new Object[]{text}, "Add New", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
											
									}
								});
						JOptionPane.showOptionDialog(null, new Object[]{itemcode,ic,originalprice,op,soldprice,sp,totalitem,ti,update}, "Add New Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
								
								
							}
							
						});
						
						
						
						
						Delete.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								
								JLabel itemcode=new JLabel("ITEM CODE");
								
								
								final JTextField ic=new JTextField(10);
								
								
								JButton delete=new JButton("DELETE");
								
								delete.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent arg0) {
										// TODO Auto-generated method stub
										
										String icode = ic.getText();
										

										try
										{
											Class.forName("com.mysql.jdbc.Driver");
											System.out.print("Driver Loaded");
											
											java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
											System.out.println("Database Connected");
											Statement s=(Statement) connection.createStatement();
											
									
											
											String str="delete from Item where Item.itemCode ='"+ icode +"' ";
											
											java.sql.PreparedStatement statement2;
											
											 statement2 = connection.prepareStatement(str);
											
											 
									           statement2.executeUpdate();
											}
											catch(Exception ex)
											{
												ex.printStackTrace();
											}
											

											JLabel text=new JLabel("Item is Deleted Successfully");
											JOptionPane.showOptionDialog(null, new Object[]{text}, "Delete Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
												
									}
									
								});
								
							JOptionPane.showOptionDialog(null, new Object[]{itemcode,ic,delete}, "Add New Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
								
								
							}
							
						});
						
						
						
						UpdateP.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								
								JLabel itemcode=new JLabel("ITEM CODE");
								JLabel originalprice=new JLabel("ORIGINAL PRICE");
								JLabel soldprice=new JLabel("SOLD PRICE");
								final JTextField ic=new JTextField(10);
								final JTextField op=new JTextField(10);
								final JTextField sp=new JTextField(10);
								JButton update=new JButton("UPDATE PRICE");
								update.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										
										String icode=ic.getText();
										int oprice=Integer.parseInt(op.getText());
										int sprice=Integer.parseInt(sp.getText());
										try
										{
											Class.forName("com.mysql.jdbc.Driver");
											System.out.print("Driver Loaded");
											
											java.sql.Connection connection1=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
											System.out.println("Database Connected");
											
											String s2 = "UPDATE Item SET originalPrice='"+ oprice +"' ,soldPrice='"+sprice+"' WHERE itemCode='"+ icode+"'";
								            	
												java.sql.PreparedStatement statement2;
												
												 statement2 = connection1.prepareStatement(s2);
												
												 
										           statement2.executeUpdate();
												
											}
											catch(Exception ex)
											{
												ex.printStackTrace();
											}
											

											JLabel text=new JLabel("Item's Price is Updated Successfully");
											JOptionPane.showOptionDialog(null, new Object[]{text}, "Update Price", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
												
											
										}
							});
								
								
								
								JOptionPane.showOptionDialog(null, new Object[]{itemcode,ic,originalprice,op,soldprice,sp,update}, "UPDATE PRICE", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
								
								
							}
							
						});
						
						
						
						UpdateI.addActionListener(new ActionListener(){

							@Override
							public void actionPerformed(ActionEvent arg0) {
								// TODO Auto-generated method stub
								
								JLabel itemcode=new JLabel("ITEM CODE");
								
								JLabel totalitem=new JLabel("TOTAL ITEM");
								
								final JTextField ic=new JTextField(10);
								
								final JTextField ti=new JTextField(10);
								
								
								JButton update=new JButton("UPDATE ITEM");
								update.addActionListener(new ActionListener(){

									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub
										
										String icode=ic.getText();
									
										int titem=Integer.parseInt(ti.getText());
										
							
										
										try
										{
											Class.forName("com.mysql.jdbc.Driver");
											System.out.print("Driver Loaded");
											
											java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
											System.out.println("Database Connected");
											Statement s=(Statement) connection.createStatement();
											
									
											
											String str="select noOfItem, soldItem, remainItem from Item where Item.itemCode ='"+ icode +"' ";
											
											ResultSet rs=s.executeQuery(str);



											int ri=0;
				                            int ni=0;
				                            int si=0;
											while(rs.next())
											{
												ni=Integer.parseInt(rs.getString(1));
												si=Integer.parseInt(rs.getString(2));
				                                ri=Integer.parseInt(rs.getString(3)); 

				                                System.out.print(ri);
				                                System.out.print(si);
												
											}
										 ni=ri+titem;
				                         ri=ni-si;
					                                try
														{
															Class.forName("com.mysql.jdbc.Driver");
															System.out.print("Driver Loaded");
															
															java.sql.Connection connection1=DriverManager.getConnection("jdbc:mysql://localhost/project","root","root");
															System.out.println("Database Connected");
															
															
															
																String s2 = "UPDATE Item SET noOfItem='"+ni+"' ,remainItem='"+ ri+"' WHERE itemCode='"+ icode+"'";
												            	
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
										
										JLabel text=new JLabel("Item Number is Updated Successfully");
										JOptionPane.showOptionDialog(null, new Object[]{text}, "Update Item Number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
											
										
										}
							});
								
								
								
								JOptionPane.showOptionDialog(null, new Object[]{itemcode,ic,totalitem,ti,update}, "UPDATE ITEM", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,null,"default");
								
								
							}
							
						});
						
						p.add(new JLabel("Search Item:"),BorderLayout.WEST);
						p.add(b,BorderLayout.EAST);
						p.add(jtf,BorderLayout.CENTER);
						
						pp.add(tool,BorderLayout.EAST);
						  f.add(pp,BorderLayout.SOUTH);
						  f.add(p,BorderLayout.NORTH);
						menu.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								main f1=new main();
								f1.setVisible(true);
								f1.pack();
								f.setVisible(false);
								f1.setLocationRelativeTo(null);
								f1.setSize(600,500);
							}
						});
						menu.setMnemonic('M');
					
		
			connection.close();
		
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		sample s=new sample();
	}

}
