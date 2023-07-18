package config;

public class Config {
    public final static String WEB_BROWSER_NAME = System.getProperty("browser_name");
    public final static boolean BROWSER_HEADLESS = Boolean.parseBoolean(System.getProperty("browser_headless"));
    public static final String TOKEN = System.getProperty("kanboard_token");
    public static final String URL_API = System.getProperty("url_project_api");
    public static final String URL = System.getProperty("url_project");
}