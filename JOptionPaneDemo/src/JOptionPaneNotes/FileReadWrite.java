package JOptionPaneNotes;

import javax.swing.*;

import java.awt.event.*;    
import java.io.*;    
public class FileReadWrite extends JFrame implements ActionListener{    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar mb;    
	JMenu submenu;
	JMenuItem open, save; 
	JTextArea display;
	
	public JMenu FileChooser(JTextArea ta){ 
		open=new JMenuItem("Open File");
		save=new JMenuItem("Save File");
		display=ta;
		open.addActionListener(this); 
		save.addActionListener(this);
		submenu=new JMenu("Sub Menu");    
		submenu.add(open); submenu.add(save);
		return submenu;
	}
	public void actionPerformed(ActionEvent e) {    
		if(e.getSource()==open){    
			JFileChooser fcRead=new JFileChooser();    
			int i=fcRead.showOpenDialog(this);    
			if(i==JFileChooser.APPROVE_OPTION){    
				File f=fcRead.getSelectedFile();    
				String filepath=f.getPath();    
				try{  
					BufferedReader br=new BufferedReader(new FileReader(filepath));    
					String s1="",s2="";                         
					while((s1=br.readLine())!=null){    
						s2+=s1+"\n";    
					}    
					display.setText(s2);    
					br.close();    
				}catch (Exception ex) {ex.printStackTrace();  }                 
			}    
		}
		if(e.getSource()==save){    
	        String filename = JOptionPane.showInputDialog("Name this file");
	        JFileChooser savefile = new JFileChooser();
	        savefile.setSelectedFile(new File(filename));
	        savefile.showSaveDialog(savefile);
	        BufferedWriter writer;
	        int sf = savefile.showSaveDialog(null);
	        if(sf == JFileChooser.APPROVE_OPTION){
	            try {
	            	writer = new BufferedWriter(new FileWriter(savefile.getSelectedFile()+".txt"));
	            	display.write(writer);
	                writer.close();
	                JOptionPane.showMessageDialog(null, "File has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE);
	                // true for rewrite, false for override

	            } catch (IOException s) {
	                s.printStackTrace();
	            }
	        }else if(sf == JFileChooser.CANCEL_OPTION){
	            JOptionPane.showMessageDialog(null, "File save has been canceled");
	        }
		}
	}          
} 
