/**
 * 
 */
package com.webbuild.javabrains;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class ConsoleOutputCapturer {
    private ByteArrayOutputStream baos;
    private PrintStream previous;
    private boolean capturing;

    //Start collecting Information
    public void start() {
        if (capturing) {
            return;
        }

        capturing = true;
        previous = System.out;      
        
        baos = new ByteArrayOutputStream(); //Create a stream to hold the output

        OutputStream outputStreamCombiner = 
                new OutputStreamCombiner(Arrays.asList(previous, baos)); 
        PrintStream custom = new PrintStream(outputStreamCombiner);	// IMPORTANT: Save the old System.out!

        System.setOut(custom); // Tell Java to use your special stream
    }

    //Stop collecting information
    public String stop() {
        if (!capturing) {
            return "";
        }

        
        System.setOut(previous); //return output back to system.out console

        String capturedValue = baos.toString();             
        //Release all stored values for better data management 
        try {
			baos.close();
			baos = null;
		    previous = null;
		    capturing = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return capturedValue; // Show what happened
    }

    //library of stored functions
    private static class OutputStreamCombiner extends OutputStream {
        private List<OutputStream> outputStreams;

        // Print some output: goes to your special stream
        public OutputStreamCombiner(List<OutputStream> outputStreams) {
            this.outputStreams = outputStreams;
        }

        public void write(int b) throws IOException {
            for (OutputStream os : outputStreams) {
                os.write(b);
            }
        }

	 	// Put things back
        public void flush() throws IOException {
            for (OutputStream os : outputStreams) {
                os.flush();
            }
        }

        //Release Resources  
        public void close() throws IOException {
            for (OutputStream os : outputStreams) {
                os.close();
            }
        }
    }	
}