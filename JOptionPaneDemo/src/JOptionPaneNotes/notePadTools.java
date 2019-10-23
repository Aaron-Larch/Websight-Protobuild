/**
 * 
 */
package JOptionPaneNotes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

/**
 * @author gce
 *
 */
public class notePadTools implements ActionListener{     
	JMenu edit; 
	JMenuItem cut,copy,paste,selectAll,clear,restore;
	JTextArea display;
	String Saved;
	JButton btn;
	JFrame f= new JFrame();
	
	public JMenu EditMenu(String SavedItem, JTextArea ta){    
		display=ta;
		Saved=SavedItem;
		cut=new JMenuItem("Cut");    
		copy=new JMenuItem("Copy");    
		paste=new JMenuItem("Paste");    
		selectAll=new JMenuItem("Select All");
		clear=new JMenuItem("clear");
		restore=new JMenuItem("Restore Default");
		cut.addActionListener(this);   
		copy.addActionListener(this);    
		paste.addActionListener(this);    
		selectAll.addActionListener(this);
		clear.addActionListener(this);
		restore.addActionListener(this);
		edit=new JMenu("Edit");    
		edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
		edit.add(clear); edit.add(restore);
		return edit;
	}
	
	public void actionPerformed(ActionEvent e) {    
		if(e.getSource()==cut){display.cut();}   
		if(e.getSource()==paste){display.paste();}  
		if(e.getSource()==copy){display.copy();}    
		if(e.getSource()==selectAll){display.selectAll();}
		if(e.getSource()==clear){display.setText(null);}
		if(e.getSource()==restore){
			display.setText(null);
			display.setText(Saved);
			}
	}
}
