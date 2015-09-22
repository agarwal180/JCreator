import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.regex.*;
public class JCreator extends WindowAdapter implements ActionListener
{
	
	String fileItems[]={"New","Open","Save","Save As","Exit"};
	String editItems[]={"Cut","Copy","Paste","Find","Find & Replace"};
	String buildItems[]={"Compile","Run"};
	String tabItems[]={"Current Tab","All Tabs"};
	JFrame f,f1,f2;
	JPanel p1,p2,p3,p4,p5;
	JTextField tf1,tf2,tf3;
	JButton b1,b2,b3,b4,b5,b6;
	JTextArea t1,t2,t3;
	JLabel l1,l2,l3;
	JScrollPane sp,sp1,sp2;
	JMenuBar mb;
	JMenu file,edit,build,tabs; 
	JSplitPane jsp; 
	JTabbedPane jtp;
	JFileChooser fc;
	String fname="",str,temp,temp1,str1,temp2,temp3;
	File file1,file2,file3,file4;
	int x=0,y;
	Vector v1,v2;
public JCreator()
	{
		f=new JFrame("JCreator");
		f.setSize(600,600);
		mb=new JMenuBar();
		file=new JMenu("File");
		edit=new JMenu("Edit");
		build=new JMenu("Build");
		tabs=new JMenu("Close Tabs");
		
		for(int i=0;i<fileItems.length;i++)
		{
			JMenuItem item=new JMenuItem(fileItems[i]);
			item.addActionListener(this);
			file.add(item);
		}
		for(int i=0;i<editItems.length;i++)
		{
			JMenuItem item=new JMenuItem(editItems[i]);
			item.addActionListener(this);
			edit.add(item);
		}
		for(int i=0;i<buildItems.length;i++)
		{
			JMenuItem item=new JMenuItem(buildItems[i]);
			item.addActionListener(this);
			build.add(item);
		}
		for(int i=0;i<tabItems.length;i++)
		{
			JMenuItem item=new JMenuItem(tabItems[i]);
			item.addActionListener(this);
			tabs.add(item);
		}
		edit.insertSeparator(3);
		file.insertSeparator(4);
		mb.add(file);
		mb.add(edit);
		mb.add(build);
		mb.add(tabs);
		f.setJMenuBar(mb);
		v1=new Vector();
		v2=new Vector();
		t1=new JTextArea();
		t2=new JTextArea();
		sp=new JScrollPane(t1);
		sp2=new JScrollPane(t2);
		jtp=new JTabbedPane();
		sp.setPreferredSize(new Dimension(300,300));
		JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jtp,sp2);
		f.add(jsp);
		f.setVisible(true);
		f.addWindowListener(this);
		
		
}
public void actionPerformed(ActionEvent e1)
{
	String str=e1.getActionCommand();
	Object source;
	source=e1.getSource();
	if(str.equals("New"))
	{
		System.out.print("new was clicked");
		newfile();
	}
	if(str.equals("Open"))
	{
		System.out.print("open was clicked");
		openfile();
	}
	if(str.equals("Save"))
	{
		System.out.print("Save was clicked");
		savefile();
	}
	if(str.equals("Save As"))
	{
		System.out.print("Save as was clicked");
		saveasfile();
	}
	if(str.equals("Exit"))
	{
		System.out.print("exit was clicked");
		exit();
	}
	if(str.equals("Cut"))
	{
		System.out.print("cut was clicked");
		cutfile();
	}
	if(str.equals("Copy"))
	{
		System.out.print("copy was clicked");
		copyfile();
	}
	if(str.equals("Paste"))
	{
		System.out.print("paste was clicked");
		pastefile();
	}
	if(str.equals("Compile"))
	{
		System.out.print("compile was clicked");
		compileprogram();
	}
	if(str.equals("Find"))
	{
		System.out.print("find was clicked");
		find();
	}
	if(str.equals("Find & Replace"))
	{
		System.out.print("find & replace was clicked");
		findreplace();
	}
	if(str.equals("Run"))
	{
		System.out.print("run was clicked");
		runprogram();
	}
	if(str.equals("Current Tab"))
	{
		System.out.print("close cureent tab");
		closecurrenttab();
	}
	if(str.equals("All Tabs"))
	{
		System.out.print("all was clicked");
		closealltabs();
	}
	if(str.equals("Find Next"))
	{
		if(source==b1)
		{
			System.out.println("Find next was clicked");
			findnextword();
		}
	}
	if(str.equals("Cancel"))
	{
		if(source==b2)
		{
			System.out.print("Cancel was clicked");
			findcancel();
		}
		if(source==b6)
		{
			System.out.print("Cancel was clicked");
			findreplacecancel();

		}
	}
	if(str.equals("find"))
	{
		if(source==b3)
		{
			System.out.println("find in find replace was clicked");
			findword();
		}
	}
	if(str.equals("Replace"))
	{
		if(source==b4)
		{
			System.out.println("replace was clicked");
			replace();
		}
	}
	if(str.equals("Replace All"))
	{
		if(source==b5)
		{
			System.out.println("replace all was clicked");
			replaceall();
		}
	}
}

// to close main window
 public void windowClosing(WindowEvent e1)
    {
    	if(!(jtp.getSelectedIndex()==-1))
	{
		System.out.println(v1.size());
		while(v1.size()!=0)
		{
			str1=(String)v2.get(jtp.getSelectedIndex());
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				JTextArea t3=(JTextArea)viewport.getView();
				System.out.println("hello");
	if(t3.getText().equals(str1))
	{
		v1.remove(jtp.getSelectedIndex());
		v2.remove(jtp.getSelectedIndex());
		jtp.removeTabAt(jtp.getSelectedIndex());
						 	
	}
	else{
	int result;
	result=JOptionPane.showConfirmDialog(f,"Do u want to save file ?","Respond",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
	if(result==JOptionPane.YES_OPTION)
					{
						savefile();
						v1.remove(jtp.getSelectedIndex());
						v2.remove(jtp.getSelectedIndex());
						jtp.removeTabAt(jtp.getSelectedIndex());
												//closealltabs();

					}
					else if(result==JOptionPane.NO_OPTION)
					{ 
						v1.remove(jtp.getSelectedIndex());
						v2.remove(jtp.getSelectedIndex());
						jtp.removeTabAt(jtp.getSelectedIndex());
					}
					else if(result==JOptionPane.CANCEL_OPTION)
					{
						return;
					}
					}
		}
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
  }
    
// on clicking new button in menu
public void newfile()
{
				
				fc = new JFileChooser();
				int rslt= fc.showSaveDialog(f);
				if(rslt == fc.APPROVE_OPTION)
				{
					file4=fc.getSelectedFile();
					fname=file4.getAbsolutePath();
					file3=new File(fname);
					temp=file3.getParent();
					temp1=file3.getName();
				
				try{
					System.out.println("File name"+fname);
					File f3=new File(temp,temp1);
					if(!(f3.exists()))
				{
					FileWriter fw=new FileWriter(fname);
					fw.write("");
					fw.close();
				}
				else
				{
					int result;
					result=JOptionPane.showConfirmDialog(f,"a file with "+temp1+" already exist.do u want to replace it","Respond",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
					if(result==JOptionPane.YES_OPTION)
					{
						try{
						FileWriter fw=new FileWriter(fname);
						fw.write(" ");
						fw.close();}
						catch(IOException e)
						{
						}
					}
					else if(result==JOptionPane.NO_OPTION)
					{
						return;
					}
					else if(result==JOptionPane.CANCEL_OPTION)
					{
						return;
					}
				}}
				catch(IOException e){
				} ;
				t1=new JTextArea();
			sp=new JScrollPane(t1);
			jtp.addTab(temp1,sp);
			//}
			y=jtp.getTabCount();
			jtp.setSelectedIndex(y-1);
			v1.add(fname);
			v2.add(t1.getText());
			str=(String)v1.get(jtp.getSelectedIndex());
				str1=(String)v2.get(jtp.getSelectedIndex());
				}	
		
}
// to open a file
public void openfile()
{             
				
	            fc=new JFileChooser();
				int rent=fc.showOpenDialog(f);
				if(rent==JFileChooser.APPROVE_OPTION)
				{
					file4=fc.getSelectedFile();
					fname=file4.getAbsolutePath();
					file3=new File(fname);
					temp=file3.getParent();
					temp1=file3.getName();

				
				try
				{
					int t=v1.indexOf(fname);
					if(t==-1)
					{
					File fobject=new File(temp,temp1);	
					if(!(fobject.exists()))
					{
						JOptionPane.showMessageDialog(f,"file not found","ffff",JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						t1=new JTextArea();
						sp=new JScrollPane(t1);
						jtp.addTab(temp1,sp);
						//}
						FileInputStream fis=new FileInputStream(fobject);
                        StringBuffer sb=new StringBuffer();
					int ch;
					int flag=0;
					while((ch=fis.read())!=-1)
					{
						if(t1.getText()!=null&&flag==0)
						{	
							flag=1;
							sb.append((char)ch+"");
							System.out.print("flag=0");
						}
						else
						{
							
							         sb.append((char)ch+"");

						}
					}
					 t1.setText(sb.toString());
					}
					}
					else
				{
					JOptionPane.showMessageDialog(f,"file already exist","ffff",JOptionPane.PLAIN_MESSAGE);
				}}
				catch(Exception e)
				{
					
					System.out.println("File not found");
				}
				y=jtp.getTabCount();
				jtp.setSelectedIndex(y-1);
				v1.add(fname);
				System.out.println((String)v1.get(v1.size()-1));
				v2.add(t1.getText());
				str=(String)v1.get(jtp.getSelectedIndex());
				str1=(String)v2.get(jtp.getSelectedIndex());
				}
				}

		
// to close all tabs
public void closealltabs()
{
	if(!(jtp.getSelectedIndex()==-1))
	{
		System.out.println(v1.size());
		while(v1.size()!=0)
		{
			str1=(String)v2.get(jtp.getSelectedIndex());
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				JTextArea t3=(JTextArea)viewport.getView();
				System.out.println("hello");
	if(t3.getText().equals(str1))
	{
		v1.remove(jtp.getSelectedIndex());
		v2.remove(jtp.getSelectedIndex());
		jtp.removeTabAt(jtp.getSelectedIndex());
						 	
	}
	else{
	int result;
	result=JOptionPane.showConfirmDialog(f,"Do u want to save file ?","Respond",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
	if(result==JOptionPane.YES_OPTION)
					{
						savefile();
						v1.remove(jtp.getSelectedIndex());
						v2.remove(jtp.getSelectedIndex());
						jtp.removeTabAt(jtp.getSelectedIndex());
												//closealltabs();

					}
					else if(result==JOptionPane.NO_OPTION)
					{ 
						v1.remove(jtp.getSelectedIndex());
						v2.remove(jtp.getSelectedIndex());
						jtp.removeTabAt(jtp.getSelectedIndex());

					}
					else if(result==JOptionPane.CANCEL_OPTION)
					{
						return;
					}
					}
		}}}
// to current tab
public void closecurrenttab()
{
			if(!(jtp.getSelectedIndex()==-1))
			{	int index=jtp.getSelectedIndex();
				str1=(String)v2.get(jtp.getSelectedIndex());
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				JTextArea t3=(JTextArea)viewport.getView();
					if(t3.getText().equals(str1))
					{
						jtp.removeTabAt(index);
						v1.remove(index);
						v2.remove(index);
					}
					else{
						int result;
						result=JOptionPane.showConfirmDialog(f,"Do u want to save file ?","Respond",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
						if(result==JOptionPane.YES_OPTION)
						{
						savefile();
						jtp.removeTabAt(index);
						v1.remove(index);
						v2.remove(index);
						}
						else if(result==JOptionPane.NO_OPTION)
						{ 
						jtp.removeTabAt(index);
						v1.remove(index);
						v2.remove(index);
						}
						else if(result==JOptionPane.CANCEL_OPTION)
					{
						return;
					}
						}
			}
		
}

// to exit
public void exit()
{
	if(!(jtp.getSelectedIndex()==-1))
	{
		System.out.println(v1.size());
		while(v1.size()!=0)
		{
			str1=(String)v2.get(jtp.getSelectedIndex());
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				JTextArea t3=(JTextArea)viewport.getView();
				System.out.println("hello");
	if(t3.getText().equals(str1))
	{
		v1.remove(jtp.getSelectedIndex());
		v2.remove(jtp.getSelectedIndex());
		jtp.removeTabAt(jtp.getSelectedIndex());
						 	
	}
	else{
	int result;
	result=JOptionPane.showConfirmDialog(f,"Do u want to save file ?","Respond",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
	if(result==JOptionPane.YES_OPTION)
					{
						savefile();
						v1.remove(jtp.getSelectedIndex());
						v2.remove(jtp.getSelectedIndex());
						jtp.removeTabAt(jtp.getSelectedIndex());
												//closealltabs();

					}
					else if(result==JOptionPane.NO_OPTION)
					{ 
						v1.remove(jtp.getSelectedIndex());
						v2.remove(jtp.getSelectedIndex());
						jtp.removeTabAt(jtp.getSelectedIndex());

					}
					else if(result==JOptionPane.CANCEL_OPTION)
					{
						return;
					}
					}
		}
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}
		else
		{
			System.exit(1);
		}
}
    	
// to save as a file
public void savefile()
{
				if(!(jtp.getSelectedIndex()==-1))
				{
				str=(String)v1.get(jtp.getSelectedIndex());
				
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				JTextArea t3=(JTextArea)viewport.getView();
				try{
				FileWriter fw=new FileWriter(str);
				fw.write(t3.getText());
				fw.close();}
				catch(IOException e)
				{
				}
			System.out.println("save block entered");
	
				}}
// to save as a file
public void saveasfile()
{
	if(!(jtp.getSelectedIndex()==-1))
	{fc=new JFileChooser();
			int rent1=fc.showSaveDialog(f);
			if(rent1==JFileChooser.APPROVE_OPTION)
			{		
				file4=fc.getSelectedFile();
					fname=file4.getAbsolutePath();
					file3=new File(fname);
					temp=file3.getParent();
					temp1=file3.getName();
					str=(String)v1.get(jtp.getSelectedIndex());
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				JTextArea t3=(JTextArea)viewport.getView();
					
			try
			{				
				FileWriter fw=new FileWriter(fname);
				fw.write(t3.getText());
				fw.close();
				}
			catch(Exception e)
			{
				System.out.println("file not exist create new");
			}
			JScrollPane sp3=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
			jtp.removeTabAt(jtp.getSelectedIndex());
			jtp.addTab(temp1,sp3);
			
}
	}
}
// to cut a text
public void cutfile()
{if(!(jtp.getSelectedIndex()==-1))
{
	t1.cut();
}
}
// to copy a text
public void copyfile()
{
	if(!(jtp.getSelectedIndex()==-1))
	{
	t1.copy();
	}
}
//to paste a text
public void pastefile()
{
	if(!(jtp.getSelectedIndex()==-1))
	{
	t1.paste();}
}

//compil1e program
	void compileprogram()
	{
		if(!(jtp.getSelectedIndex()==-1))
		{
			savefile();
			String err="";
			
			str=(String)v1.get(jtp.getSelectedIndex());
			System.out.println("File   "+str);
			
	      try 
	      	{
	      Process pro = Runtime.getRuntime().exec("javac \""+str+"\"");
	    BufferedInputStream bis=new BufferedInputStream(pro.getErrorStream());
	    int ch;
	    while((ch=bis.read())!=-1)
	    {
	    	err=err+(char)ch;
	    }
	    if(err.equals(""))
	    {
	    	t2.setText("Program Compiled Succesfully");
	    	JOptionPane.showMessageDialog(f,"Program Compiled Succesfully","Sucess",JOptionPane.PLAIN_MESSAGE);
	    }
	    else
	    {
	    t2.setText(err);}
	    bis.close();
		}
		 catch (Exception e) 
	    {
	      e.printStackTrace();
	    
	    }
		}
	}
	// to run a program
	void runprogram()
	{
		if(!(jtp.getSelectedIndex()==-1))
		{
	    try{
	    compileprogram();
	    str=(String)v1.get(jtp.getSelectedIndex());
	    System.out.println("file name"+str);
	    File f2=new File(str);
	    System.out.println("Name"+f2.getName());
	    temp1=f2.getName();
	    x=temp1.length()-5;
	    temp1=temp1.substring(0,x);
	    System.out.println(temp1);
	    	System.out.println("parent"+f2.getParent());
	    	temp=f2.getParent();
	    Process pro1=Runtime.getRuntime().exec("cmd /k start cmd /k java "+temp1,null,new File(temp));
	   
	    } catch (Exception e) 
	    {
	      e.printStackTrace();
	    }}
 }	
  	
	

  // on clicking find button
public void find()
  {
  	if(!(jtp.getSelectedIndex()==-1))
  	{
	f1=new JFrame("Find");
			f1.setSize(300,300);
			p1=new JPanel();
			p2=new JPanel();
			l1=new JLabel("Find What");
			tf1=new JTextField(15);
			p1.add(l1);
			p1.add(tf1);
			b1=new JButton("Find Next");
			b2=new JButton("Cancel");
			p2.add(b1);
			p2.add(b2);
			f1.add(p1);
			f1.add(p2,"South");
			b1.addActionListener(this);
			b2.addActionListener(this);
			f1.setVisible(true);
            f1.addWindowListener(new WindowAdapter()
                               { public void windowClosing(WindowEvent e1)
                                {
                             	 f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                }});
  	}}
public void findnextword()
{
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				t3=(JTextArea)viewport.getView();
				temp2=t3.getText();
				String line2=temp2;
				Pattern p2=Pattern.compile("\r\n");
				Matcher m2=p2.matcher(line2);
				String result2=null;
				if(m2.find())
				{
					result2=m2.replaceAll("\n");
					t3.setText(result2);
					System.out.println("got");
				}	
                temp=t3.getText();
				String line=temp;
				Pattern p=Pattern.compile(tf1.getText());
				Matcher m=p.matcher(line);
				int a=t3.getCaretPosition();
				if(m.find(t3.getCaretPosition()))
				{
					t3.select(m.start(),m.end());
										
				}
				else
				{
					JOptionPane.showMessageDialog(f,"Can't find word","find",JOptionPane.PLAIN_MESSAGE);
 
				}
				System.out.println("Find Next");
						
			}
public void findcancel()
{
	System.out.println("close");
	f1.setVisible(false);
	f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

public void findreplace()
		{
			if(!(jtp.getSelectedIndex()==-1))
			{
			f2=new JFrame("Find & Replace");
			f2.setSize(400,400);
			p3=new JPanel();
			l2=new JLabel("Find What");
			tf2=new JTextField(15);
			l3=new JLabel("Replace With");
			tf3=new JTextField(15);
			p3.add(l2);
			p3.add(tf2);
			f2.add(p3,"North");
			p4=new JPanel();
			p4.add(l3);
			p4.add(tf3);
			f2.add(p4);
			p5=new JPanel();
			b3=new JButton("find");
			b4=new JButton("Replace");
			b5=new JButton("Replace All");
			b6=new JButton("Cancel");
			b3.addActionListener(this);
			b4.addActionListener(this);
			b5.addActionListener(this);
			b6.addActionListener(this);
			p5.add(b3);
			p5.add(b4);
			p5.add(b5);
			p5.add(b6);
			f2.add(p5,"South");
			f2.setVisible(true);
            f2.addWindowListener(new WindowAdapter()
                                             { public void windowClosing(WindowEvent e1)
                                           {
                                             f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                              
                                              }});	
			}}
			
public void findword()
{
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();
				t3=(JTextArea)viewport.getView();
				temp2=t3.getText();
				String line2=temp2;
				Pattern p2=Pattern.compile("\r\n");
				Matcher m2=p2.matcher(line2);
				String result2=null;
				if(m2.find())
				{
					result2=m2.replaceAll("\n");
					t3.setText(result2);
					System.out.println("got");
				}	
                temp=t3.getText();
				String line=temp;
				Pattern p=Pattern.compile(tf2.getText());
				Matcher m=p.matcher(line);
				if(m.find(t3.getCaretPosition()))
				{
					t3.select(m.start(),m.end());
					f.toFront();
					
				}
				else
				{
					JOptionPane.showMessageDialog(f,"Can't find word","find",JOptionPane.PLAIN_MESSAGE);
 
				}
				System.out.println("find ");
						
			}

public void replace()
{
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();	
				JTextArea t3=(JTextArea)viewport.getView();
				temp2=t3.getText();
				String line2=temp2;
				Pattern p2=Pattern.compile("\r\n");
				Matcher m2=p2.matcher(line2);
				String result2=null;
				if(m2.find())
				{
					result2=m2.replaceAll("\n");
					t3.setText(result2);
				}
				temp=t3.getText();
				String line=temp;
				Pattern p=Pattern.compile(tf2.getText());
                temp3=tf3.getText();
				Matcher m=p.matcher(line);
				String result1=null;
				if(m.find(t3.getCaretPosition()))
				{
					t3.replaceRange(temp3,m.start(),m.end());
				}
       
                }
				
public void replaceall()
{
				sp1=new JScrollPane();
				sp1=(JScrollPane)jtp.getComponentAt(jtp.getSelectedIndex());
				JViewport viewport=sp1.getViewport();	
				JTextArea t3=(JTextArea)viewport.getView();
				temp=t3.getText();
				String line=temp;
				Pattern p=Pattern.compile(tf2.getText());
				Matcher m=p.matcher(line);
				String result1=null;
				if(m.find())
				{
					result1=m.replaceAll(tf3.getText());
					t3.setText(result1);
				}
}
public void findreplacecancel()
{
	System.out.println("close");
	f2.setVisible(false);
	f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}


	public static void main(String args[]) throws Exception
	{
		JCreator jc =new JCreator();
	}
}