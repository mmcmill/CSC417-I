import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logger {
	private static final String LOG_FILE_NAME = "log.txt";
	private static StringBuilder sb = new StringBuilder();
	/**
	 * Append parameterized String to log file
	 * 
	 * @param entry The String to append to the log file
	 */
	public static void log(String entry) {

		sb.append("[");
		sb.append(new Timestamp(System.currentTimeMillis()));
		sb.append("] ");
		sb.append(entry);
		sb.append("\n");
		try {
			File file = new File(LOG_FILE_NAME);
			if (file.exists()) {
				file.delete();
			}
			FileWriter writer = new FileWriter(LOG_FILE_NAME, true);
			writer.write(sb.toString());
			writer.close();
		} catch (IOException ioe) {
			System.err.println("Unable to write to log file.");
		}
	}
	
	public void printLogToConsole () {
		System.out.print(sb.toString());
	}
}
