package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesOperation {

	private Properties prop;

	public PropertiesOperation(String filepath) {
		File file = new File(filepath);
		try {
			FileInputStream inputStream = new FileInputStream(file);
			prop = new Properties();
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getValue(String key) {
		return prop.getProperty(key);
	}
}
