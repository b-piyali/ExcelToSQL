import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;

public class ConfigReader {
    public ConfigObject parseJSON() throws IOException {
        Gson gson = new Gson();
        ConfigObject configObject = new ConfigObject();
        try (Reader reader = new FileReader(getFileFromResources().getAbsolutePath())) {
            configObject = gson.fromJson(reader, ConfigObject.class);
        }
        return configObject;
    }

    private File getFileFromResources() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("config.json");
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    /*public static void main(String[] args) throws IOException {
        ConfigReader configReader = new ConfigReader();
        System.out.println(configReader.getFileFromResources().getAbsolutePath());
    }*/
}
