package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Config {

	public Properties prop;
	public FileInputStream fis;

	public void Configurations() throws IOException {

		prop = new Properties();
		fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\inputData.properties");

		prop.load(fis);

	}

}
