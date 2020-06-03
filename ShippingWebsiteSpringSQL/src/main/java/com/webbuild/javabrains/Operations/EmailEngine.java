package com.webbuild.javabrains.Operations;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailEngine {
	
	// The javamail session object.
	protected Session session;
	// The sender's email address
	protected String from;
	// The subject of the message.
	protected String subject;
	// The recipient ("To:"), as Strings.
	protected List<String> toList = new ArrayList<>();
	// The CC list, as Strings.
	protected List<String> ccList = new ArrayList<String>();
	// The BCC list, as Strings.
	protected List<String> bccList = new ArrayList<String>();
	// The text of the message.
	protected String body;
	// The SMTP relay host
	protected String mailHost;
	// The Data log setting
	protected boolean verbose;
	//Set up alias
	protected boolean useAlias;
	protected static boolean newsletter;
		
	// SETTER/GETTER FOR General Variables 
	public String getFrom() {return from;}// Get from 
	public void setFrom(String fm) {from = fm;}	// Set from
		
	public String getSubject() {return subject;} // Get subject
	public void setSubject(String subj) {subject = subj;}// Set subject
		
	public String getBody() {return body;} // Get message
	public void setBody(String text) {body = text;}// Set message
		
	public boolean isVerbose() {return verbose;} // Get verbose
	public void setVerbose(boolean v) {verbose = v;} // Set verbose
		
	public boolean isUseAlias() {return useAlias;} // Get verbose
	public void setUseAlias(boolean a) {useAlias = a;} // Set verbose
		
	public void setServer(String s) {mailHost = s;}//there is no get server because the send operaton is in class
		
	// SETTERS/GETTERS FOR TO: LIST
	public List<String> getToList() {return toList;} //Get tolist, as an array of Strings	
	public void setToList(ArrayList<String> to) {toList = to;}// Set to list to an ArrayList of Strings
		
	// Set to as a string like "tom, mary, robin@host". Loses any previously set values.
	public void setToList(String s) {toList = Arrays.asList(s.split(",\\s+"));}
	public void addTo(String to) {toList.add(to);}	// Add one "to" recipient
		
		
		// SETTERS/GETTERS FOR CC: LIST
		public List<String> getCcList() {return ccList;} // Get cc list, as an array of Strings
		public void setCcList(ArrayList<String> cc) {ccList = cc;} //Set cc list to an ArrayList of Strings
		
		// Set cc as a string like "tom, mary, robin@host". Loses any previously set values.
		public void setCcList(String s) {ccList = Arrays.asList(s.split(",\\s+"));}
		public void addCc(String cc) {ccList.add(cc);}// Add one "cc" recipient
		
		// SETTERS/GETTERS FOR BCC: LIST
		public List<String> getBccList() {return bccList;}// Get Bcc list, as an array of Strings
		public void setBccList(List<String> bcc) {bccList = bcc;}// Set bcc list to an ArrayList of Strings
		
		// Set bcc as a string like "tom, mary, robin@host". Loses any previously set values.
		public void setBccList(String s) {bccList = Arrays.asList(s.split(",\\s+"));}
		public void addBcc(String bcc) {bccList.add(bcc);} // Add one "bcc" recipient
		
		/** Check if all required fields have been set before sending.
		 * Normally called before doSend; called by doSend for verification.*/
		public boolean isComplete() {
			if (from == null    || from.length()==0) {
				System.err.println("doSend: no FROM");
				return false;
				}
			if (subject == null || subject.length()==0) {
				System.err.println("doSend: no SUBJECT");
				return false;
				}
			if (toList.size()==0) {
				System.err.println("doSend: no recipients");
				return false;
				}
			if (body == null || body.length()==0) {
				System.err.println("doSend: no body");
				return false;
				}
			if (mailHost == null || mailHost.length()==0) {
				System.err.println("doSend: no server host");
				return false;
				}
			return true; 
			}
		
		// Send the message.
		public synchronized void doSend(List<String> Attachment) throws MessagingException {
			
			if (!isComplete())throw new IllegalArgumentException("doSend called before message was complete");
			session.setDebug(true);// Set Debug log
			
			//Properties object used to pass props into the MAIL API
			Properties properties = new Properties();
			
			// Your LAN must define the local SMTP server as "mailhost"
			properties.put("mail.smtp.host", mailHost); //SMTP Host must match the from email address not to.
			properties.put("mail.smtp.port", "587"); //TLS Port
			properties.put("mail.smtp.auth", "true"); //enable authentication
			properties.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
			
			//Username and password verification
			Authenticator authenticator = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
		        	return new PasswordAuthentication(from, "Spring2020");
		         }
		     };
		     
			// Create the Session object
			if (session == null) {
				System.out.println("TLSEmail Start");
				session = Session.getDefaultInstance(properties, authenticator);
				if (isVerbose())session.setDebug(true);// Set Debug log
			}
			
			
			// create a message
			final MimeMessage mesg = new MimeMessage(session);
			mesg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			mesg.addHeader("format", "flowed");
			mesg.addHeader("Content-Transfer-Encoding", "8bit");
			
			InternetAddress[] addresses;
			
			// TO Address list
			addresses = new InternetAddress[toList.size()];
			for (int i=0; i<addresses.length; i++)
				addresses[i] = new InternetAddress((String)toList.get(i));
			mesg.setRecipients(Message.RecipientType.TO, addresses);
			
			// From Address
			if(isUseAlias()) {
				try {
					//Enable aliasing to hide email address.
					mesg.setFrom(new InternetAddress(from, "no_reply@gce.com"));
					mesg.setReplyTo(InternetAddress.parse("no_reply@gce.org", false)); //set false to hide real name
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				//set a true/false state to alternate between alias and real sender.
				mesg.setFrom(new InternetAddress(from));
			}
			
			
			// CC Address list
			addresses = new InternetAddress[ccList.size()];
			for (int i=0; i<addresses.length; i++)
				addresses[i] = new InternetAddress((String)ccList.get(i));
			mesg.setRecipients(Message.RecipientType.CC, addresses);
			
			// BCC Address list
			addresses = new InternetAddress[bccList.size()];
			for (int i=0; i<addresses.length; i++)
				addresses[i] = new InternetAddress((String)bccList.get(i));
			mesg.setRecipients(Message.RecipientType.BCC, addresses);
			
			// The Subject
			mesg.setSubject(subject, "UTF-8");
			mesg.setSentDate(new Date());
			
			 // Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			 // Create a multipart message for attachment
	        Multipart multipart = new MimeMultipart();

	        // Second part is attachment
	        for(String i: Attachment) {
	        	messageBodyPart = new MimeBodyPart();
	        	DataSource source = new FileDataSource(i);
	        	messageBodyPart.setDataHandler(new DataHandler(source));
	        	messageBodyPart.setFileName(i);
	        	multipart.addBodyPart(messageBodyPart);
	        }
	        
	        if(newsletter==true) {
	        	multipart=FormatNode.LetterFormat(body);
	        }else {
	        	 // Fill the message
	            messageBodyPart.setText(body);
	            
	        	// Set text message part
	        	multipart.addBodyPart(messageBodyPart);
	        }
	           
	 
	        // Send the complete message parts
	        mesg.setContent(multipart);
			Transport.send(mesg);  
		}
		
		public static void send(
				String mailhost, String recipient, String sender, ArrayList<String> cc,
				Boolean alias, String subject, String message, List<String> inputPath)
						throws MessagingException {
			EmailEngine m = new EmailEngine();
			m.setCcList(cc);
			m.setVerbose(true);
			m.setUseAlias(alias);
			m.setServer(mailhost);
			m.addTo(recipient);
			m.setFrom(sender);
			m.setSubject(subject);
			m.setBody(message);
			m.doSend(inputPath);
			newsletter=true;
			}

	}