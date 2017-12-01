package file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileToLines {
	
	private List<String> lines;
	
	public FileToLines(String filePath) {
		lines = new ArrayList<String>();
		readFile( filePath );
	}
	
	public List<String> lines() {
		return this.lines;
	}

	private void readFile( String filePath ) {
		try {
			FileInputStream fis = new FileInputStream( filePath );
			InputStreamReader isr = new InputStreamReader( fis, "utf8" );
			BufferedReader br = new BufferedReader( isr );
			String linea;
			while (( linea = br.readLine() ) != null)
				lines.add(linea);
			fis.close();
		} catch (Exception e) {
			System.err.println( "Error while reading" + filePath + ": " + e );
		}
	}

}
