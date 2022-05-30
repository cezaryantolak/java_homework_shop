package configuration.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnvironmentsModel {
    Map<String, EnvironmentModel> environments = new LinkedHashMap<>();

    @JsonAnySetter
    void setEnvironments(String key, EnvironmentModel value) {

        environments.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, EnvironmentModel> getEnvironments() {

        return environments;
    }
}
