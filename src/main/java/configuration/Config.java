package configuration;

import configuration.models.BrowserModel;
import configuration.models.EnvironmentModel;
import configuration.models.EnvironmentsModel;

public class Config {
    public String activeEnvironmentName;
    public EnvironmentsModel environments;
    public BrowserModel browser;

    public EnvironmentsModel getEnvironments() {
        return environments;
    }

    public BrowserModel getBrowser() {

        return browser;
    }

    public EnvironmentModel getActiveEnvironment() {

        return environments.getEnvironments().get(activeEnvironmentName);
    }
}
