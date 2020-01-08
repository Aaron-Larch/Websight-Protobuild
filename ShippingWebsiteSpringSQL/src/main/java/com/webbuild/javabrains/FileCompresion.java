/**
 * 
 */
package com.webbuild.javabrains;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * @author gce
 *
 */
public class FileCompresion {
	//Compress a string into a byte array
	public static byte[] compress(final String str) throws IOException {
	    if ((str == null) || (str.length() == 0)) {return null;}  //check to see if input is populated
	    
	    //Create a stream to hold the output
	    ByteArrayOutputStream obj = new ByteArrayOutputStream();
	    GZIPOutputStream gzip = new GZIPOutputStream(obj);
	    gzip.write(str.getBytes("UTF-8"));// Tell Java to use your special stream
	    
	    //Close down stream and release resources used
	    gzip.flush();
	    gzip.close();
	    return obj.toByteArray(); //return byte array
	  }

	//Convert a Byte array into a string
	public static String decompress(final byte[] compressed) throws IOException {
		final StringBuilder outStr = new StringBuilder(); //create a stream variable
		if ((compressed == null) || (compressed.length == 0)) {return "";} //check to see if input is populated
	    
			//check for recognized forms of compression
			if (isCompressed(compressed)) {
				 //Create a stream to hold the output
				final GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(compressed));
				final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gis, "UTF-8"));

				String line;
				//Populate stream variable
				while ((line = bufferedReader.readLine()) != null) {outStr.append(line);}
			} else {outStr.append(compressed);}//else print stored array
	    //convert output back into string
		return outStr.toString();
	}

	  //to prevent code trying to decompress a uncompressed("not a zip format") byte[]. to prevent "Not in GZIP format" exception message
	  public static boolean isCompressed(final byte[] compressed) {
	    return (compressed[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (compressed[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
	  }
}
