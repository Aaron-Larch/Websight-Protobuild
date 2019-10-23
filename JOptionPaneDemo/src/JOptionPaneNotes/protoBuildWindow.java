package JOptionPaneNotes;

import javax.swing.*;

import java.awt.event.*;      
public class protoBuildWindow implements ActionListener{    
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	FileReadWrite substring = new FileReadWrite();
	notePadTools edit = new notePadTools();
	JFrame f = new JFrame();
	JMenuBar mb;    
	JMenu file;
	JMenuItem loadpage, Caclulator;
	JTextArea textArea;
	JButton btn;
	public protoBuildWindow(String printOut){
		file=new JMenu("File");
		loadpage=new JMenuItem("Edit Page");
		Caclulator=new JMenuItem("Caclulator");
		loadpage.addActionListener(this);
		Caclulator.addActionListener(this);
		textArea =new JTextArea(printOut,800,800);
		mb=new JMenuBar();    
		mb.setBounds(0,0,800,20);
		textArea.setBounds(0,20,800,800);
		textArea.setEditable(false);
		file.add(substring.FileChooser(textArea));
		file.add(loadpage); file.add(Caclulator);
		mb.add(file); mb.add(edit.EditMenu(printOut, textArea));
		f.add(mb);
		f.add(textArea);
		f.setSize(500,500);    
     	f.setLayout(null);    
     	f.setVisible(true);    
     	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//build a blank page
	public JLayeredPane fileLoadPane(JTextArea ta){   
		btn=new JButton("Click to Print");
		JLayeredPane pane = f.getLayeredPane();
		ta.setEditable(true);
		JScrollPane sta = new JScrollPane(ta);
		sta.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
		sta.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		
		btn.setBounds(185,420,115,30);
		sta.setBounds(0,20,800,800);
		
		btn.addActionListener(this);
	    pane.add(btn, 1);  
	    pane.add(sta, 2);
	    return pane;
	}
	
	public void actionPerformed(ActionEvent e) {    
		if(e.getSource()==loadpage){fileLoadPane(textArea);}
		if(e.getSource()==Caclulator){new MyCalculator("Hello World");}
		if(e.getSource()==btn){ 
			String printStatement=textArea.getText();
			System.out.println(printStatement);
			f.dispose();
		}
	}          
	public static void main(String[] args) {    
		new protoBuildWindow("Hello World");        
	}
} 
