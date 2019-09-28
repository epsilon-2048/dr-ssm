package epsilon.ssm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static <T> String obj2String(T obj) throws JsonProcessingException {
        return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
    }

    public static <T> T string2Obj(String str,Class<T> clazz) throws IOException {
        return clazz.equals(String.class)? (T) str :objectMapper.readValue(str,clazz);
    }
}
