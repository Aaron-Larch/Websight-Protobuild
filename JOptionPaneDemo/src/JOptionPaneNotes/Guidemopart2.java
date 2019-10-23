package JOptionPaneNotes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;  

public class Guidemopart2 implements ActionListener{    
	JFrame f;    
	JMenuBar mb;    
	JMenu file,help;    
	JMenuItem i1,i2,i3,i4,i5;    
	JTextArea ta, tf;
	Guidemopart2(String Message){    
	f=new JFrame();
	tf=new JTextArea(Message);
    i1=new JMenuItem("Notepad");  
    i2=new JMenuItem("Text Field");  
    i3=new JMenuItem("Item 3");  
    i4=new JMenuItem("Item 4");  
    i5=new JMenuItem("Item 5");
	i1.addActionListener(this);
	i2.addActionListener(this);
	mb=new JMenuBar();    
	file=new JMenu("File");        
	help=new JMenu("Help");
	file.add(i1); file.add(i2); file.add(i3);  
	mb.add(file);mb.add(help);      
	f.add(mb);
	f.add(TabbedPane());
	f.setJMenuBar(mb);  
	f.setLayout(null);    
	f.setSize(400,400);    
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	public JTabbedPane TabbedPane(){   
	    JTextArea ta=new JTextArea(200,200);  
	    JPanel p1=new JPanel();  
	    p1.add(ta);  
	    JPanel p2=new JPanel();  
	    JPanel p3=new JPanel();  
	    JTabbedPane tp=new JTabbedPane();  
	    tp.setBounds(50,50,200,200);  
	    tp.add("main",p1);  
	    tp.add("visit",p2);  
	    tp.add("help",p3);    
	    return tp;
	}
	
	public void actionPerformed(ActionEvent e) {    
		//https://stackoverflow.com/questions/218155/how-do-i-change-jpanel-inside-a-jframe-on-the-fly
		if(e.getSource()==i1){
			ta=new JTextArea();    
			ta.setBounds(5,5,360,320);
			f.getContentPane().removeAll();
			f.getContentPane().add(ta);
		}
	
		if(e.getSource()==i2){
			tf.setEditable(false);
			tf.setBounds(5,5,360,320); 
			f.getContentPane().removeAll();
			f.getContentPane().add(tf);
		}
	}     
public static void main(String[] args) {  
	String input="import javax.swing.JOptionPane;\r\n" + 
			"import javax.swing.JScrollPane;\r\n" + 
			"import javax.swing.JTextArea;\r\n" + 
			"/*from www  . ja va 2  s  . co m*/\r\n" + 
			"public class Main {\r\n" + 
			"  public static void main(String[] args) {\r\n" + 
			"    String text = \"one two three four five six seven eight nine ten \";\r\n" + 
			"    JTextArea textArea = new JTextArea(text);\r\n" + 
			"    textArea.setColumns(30);\r\n" + 
			"    textArea.setLineWrap(true);\r\n" + 
			"    textArea.setWrapStyleWord(true);\r\n" + 
			"    textArea.append(text);\r\n" + 
			"    textArea.append(text);\r\n" + 
			"    textArea.append(text);\r\n" + 
			"    textArea.append(text);\r\n" + 
			"    textArea.append(text);\r\n" + 
			"    textArea.setSize(textArea.getPreferredSize().width, 1);\r\n" + 
			"    JOptionPane.showMessageDialog(null, new JScrollPane( textArea), \"Not Truncated!\",\r\n" + 
			"        JOptionPane.WARNING_MESSAGE);\r\n" + 
			"  }\r\n" + 
			"} ";
	new Guidemopart2(input); 

}
}  
