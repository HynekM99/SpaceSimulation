package data.parsers;

import java.io.IOException;
import java.util.List;

public interface IDataParser {
	List<String[]> parseData() throws IOException;
}
