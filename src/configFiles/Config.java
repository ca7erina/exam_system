package configFiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private Properties cfgF = new Properties();
	
	public Config(String filename){
		try {
			cfgF.load(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getStringData(String key){
		return cfgF.getProperty(key);
	}
	
	public int getIntData(String key){
		return Integer.parseInt(cfgF.getProperty(key)) ;
	}
	public double getDoubleData(String key){
		return Double.parseDouble(cfgF.getProperty(key));
	}
	
}
