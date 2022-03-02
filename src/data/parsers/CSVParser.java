package data.parsers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CSVParser implements IDataParser {

	public final String filePath;
	
	public CSVParser(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public List<String[]> parseData() throws IOException {
		List<String[]> spaceData = new LinkedList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        spaceData.add(values);
		    }
		}
		
		return spaceData;
	}

}
