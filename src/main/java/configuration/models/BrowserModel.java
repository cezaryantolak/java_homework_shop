package configuration.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class BrowserModel {
    Map<String, Object> properties = new LinkedHashMap<>();

    @JsonAnySetter
    void setBrowserProperties(String key, Object value) {

        properties.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getBrowserProperties() {

        return properties;
    }
}