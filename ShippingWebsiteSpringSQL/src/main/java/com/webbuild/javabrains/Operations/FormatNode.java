package com.webbuild.javabrains.Operations;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class FormatNode {
	
	public static Multipart LetterFormat(String body) throws MessagingException {
		Multipart multipart = new MimeMultipart();
		BodyPart messageBodyPart = new MimeBodyPart();
		
		// Third part is image attachment
		String filename = "C:/Users/gce/Desktop/GCE-Logo.jpg";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		//Trick is to add the content-id header here
		messageBodyPart.setHeader("Content-ID", "<image>");
		multipart.addBodyPart(messageBodyPart);

		//third part for displaying image in the email body
		messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(NewsLetter(body), "text/html");
    
		multipart.addBodyPart(messageBodyPart);
		return multipart;
	}
	private static String NewsLetter (String body) {
		String style1="<body style='background-color: blue'>"+
				"<h1 style='vertical-align:middle; border: double; text-align: center;'>"+
				"<img src='cid:image' style='float:left'> Offical GCE Update </h1>"+
				"<hr style='height:2px;border-width:0;background-color:white'>"+
				"<textarea id='w3mission' rows='4' cols='50'"+
				"style='display: block; margin-left: auto; margin-right: auto'>"+
				body+"</textarea>"+
				"</body>";
		return style1;
	}

}
