/* Authors: Floris de Bruin(5772583), Gijs van Horn(10070370) en Jasper van Eck(6228194.)
 * The FileManager class allows us to read from a .txt file, so that we may use the text hidden within.
 * It's also possible to write to a .txt file, so that we may later use the NGrams created.
 */

import java.io.*;
import java.util.regex.*;
import java.util.*;

public class FileManager {
	BufferedReader readerIn;
	String fileIn;
	FileWriter writerOut;
	String fileOut;
	int sizeIn;
	
	//Default constructor, only creates a file reader.
	public FileManager(String fileIn) {
		this.fileIn = fileIn;
				
		try {
			readerIn = new BufferedReader(new FileReader(fileIn),2);
		} catch (Throwable e) {
			System.err.println("There was an error opening the file...");
			System.exit(1);
		}	
	}

	//Opens a file reader, and a file writer.
	public FileManager(String fileIn, String fileOut) {
		this.fileIn = fileIn;

		this.fileOut = fileOut;
		try {
			readerIn = new BufferedReader(new FileReader(this.fileIn),2);
			writerOut = new FileWriter(this.fileOut);
		} catch (Throwable e) {
			System.err.println("There was an error opening the file...");
			System.exit(1);
		}	
	}

	//Reads the whole file, by appending every read line. Also replaces newlines with a single space.
	public String readWholeFile() {
		String completeFileAsString = "";
		String currentLine = "";

		while(currentLine != null) {
			try {
				currentLine = readerIn.readLine();
				completeFileAsString += currentLine + " ";
			} catch (IOException e) {
				System.err.println("There was an error reading from file " + fileIn);
				return "";
			}
		}
		completeFileAsString = completeFileAsString.substring(0, completeFileAsString.length() - 1);
		completeFileAsString = completeFileAsString.replace("   "," ").replace("  "," ");
		return completeFileAsString;
	}

	//Reads the next line from the file.
	public String readNextLine() {
		try {
			return readerIn.readLine();
		} catch (IOException e) {
			System.err.println("There was an error reading from file " + fileIn);
			return "";
		}
	}

	//Closes the file reader and writer.	
	public void terminate() throws IOException {
		readerIn.close();
		writerOut.close();
	}
}
