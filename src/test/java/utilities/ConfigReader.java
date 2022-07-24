package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static FileInputStream input;
    private static Properties properties;

    static{
        String path="/Users/alinapopova/IdeaProjects/MindtekNGTesting/src/test/resources/configurations/Configuration.properties";
        try {
            input=new FileInputStream(path);
            properties=new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("Path for properties file is invalid");
        } catch (IOException e) {
            System.out.println("Failed to load the properties file");
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                System.out.println("Exeptionoccured while closing input object");
            }

        }
    }
    public static String getProperty(String key){//configur.properties->key->url
        return properties.getProperty(key);//configur.properties->value->url
    }
}
