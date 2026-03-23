package au.com.newad.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@Component
@RequiredArgsConstructor
public class JsonDataReader {

    private final ResourceLoader resourceLoader;
    private final JsonMapper jsonMapper;

    public <T> List<T> readJsonList(String filename, Class<T> clazz) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        try (InputStream is = resource.getInputStream()) {
            return jsonMapper.readerForListOf(clazz).readValue(is);
        }
    }
}
