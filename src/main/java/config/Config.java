package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static Properties properties = new Properties();
    private final static String configPath = "src/test/resources/config.properties";
    public final static String WEB_BROWSER_NAME = System.getProperty("browser_name", getProperty("web.browser.name"));
    public final static boolean BROWSER_HEADLESS = Boolean.parseBoolean(System.getProperty("browser_headless", getProperty("browser.headless")));
    public static final String TOKEN = System.getProperty("kanboard_token", getProperty("kanboard.token"));
    public static final String URL = System.getProperty("url_project", getProperty("url.project"));
    public static final String URL_API = System.getProperty("url_project_api", getProperty("url.project.api"));

    public static String getProperty(String name){
        try{
            InputStream inputStream = Files.newInputStream(Paths.get(configPath));
            properties.load(inputStream);
        }
        catch (IOException ioException){
            throw new RuntimeException(String.format("File not exist in path %s %s", configPath, ioException));
        }

        return properties.getProperty(name);
    }
}