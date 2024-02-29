package web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;

import java.util.List;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        A a = new A("str a", 105, List.of("a", "b", "c"));
        String s = om.writeValueAsString(a);
        System.out.println(s);
        String json = "{\"a\":\"str a\",\"b\":105,\"l\":[\"a\",\"b\",\"c\"]}";
        A a1 = om.readValue(json, A.class);
        Map<String, Object> map = om.readValue(json, new TypeReference<>() {});
        int i = 0;
    }

    record A(String a, Integer b, List<String> l) {
    }
}


