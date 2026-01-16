package photomanager;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class provides support to the Photo and PhotoManager classes.
 * Do not modify this class.
 * 
 * @author UMCP CS Department
 *
 */
public class Utilities {
	/**
	 * Returns a long value corresponding to the number of seconds since 1970 for
	 * the specified string. The string format is "MM/dd/yyyy-HH:mm". For example,
	 * "10/18/2020-17:10"
	 * 
	 * @param date string representing date
	 * @return long value representing seconds since 1970
	 */
	public static long getDate(String date) {
		long answer = -1;

		try {
			answer = (new SimpleDateFormat("MM/dd/yyyy-HH:mm")).parse(date).getTime();
		} catch (ParseException e) {
			System.err.print(e.getMessage());
		}

		return answer;
	}

	/**
	 * Returns string representing the date associated with the number of seconds
	 * since 1970 specified in the parameter
	 * 
	 * @param date
	 * @return string with date
	 */
	public static String getDate(long date) {
		return new Date(date).toString();
	}

	/**
	 * Writes a string to a file
	 * 
	 * @param filename filename to write to
	 * @param data data(string) to write to file
	 * @return true if write completed; false otherwise.
	 */
	public static boolean writeStringToFile(String filename, String data) {
		try {
			FileWriter output = new FileWriter(filename);
			output.write(data);
			output.close();
		} catch (IOException exception) {
			System.err.println("ERROR: Writing to file " + filename + " failed.");
			return false;
		}
		return true;
	}

	/**
	 * Generate an HTML5 Page with the body specified in the parameter.
	 * @param filename name of file to create.
	 * @param body HMTL body.
	 */
	public static void generateHTMLPageWithBody(String filename, String body) {
		String page = "<!doctype html>\n";

		page += "<html lang=\"en\">\n";
		page += "<head>\n";
		page += "<title>PhotoManager Page</title>\n";
		page += "<meta charset=\"utf-8\" />\n";
		page += "</head>\n";
		page += "<body>\n";
		page += body + "\n";
		page += "</body>\n";
		page += "</html>\n";

		writeStringToFile(filename, page);
		System.out.println(filename + " has been created (Refresh Eclipse project to see the file).");
	}

	/**
	 * Driver illustrating functionality of Utility methods.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String date1 = "01/01/1970-17:10", date2 = "10/19/2020-17:10";

		System.out.println("Integer for " + date1 + " is: " + getDate(date1));
		System.out.println("Integer for " + date2 + " is: " + getDate(date2));

		/* Creating HTML to display image present in umcpImages */
		String htmlPage = "SamplePage.html", body = "<h1>Sample Page</h1>";
		body += "<img src=\"umcp/college1.jpg\" width=\"364\" height=\"273\">";

		generateHTMLPageWithBody(htmlPage, body);
		System.out.println("Sample page in file " + htmlPage);
	}
}