package com.example.currencyConvert;

import com.example.currencyConvert.core.Template;
import com.example.currencyConvert.health.TemplateHealthCheck;
import com.example.currencyConvert.resources.CurrencyResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CurrencyConversionApplication extends Application<CurrencyConversionConfiguration> {
    public static void main(String[] args) throws Exception {
        new CurrencyConversionApplication().run(args);
    }

    @Override
    public String getName() {
        return "Currency-Coverter";
    }

    @Override
    public void initialize(Bootstrap<CurrencyConversionConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)
                )
        );
    }

    @Override
    public void run(CurrencyConversionConfiguration configuration, Environment environment) {
        final Template template = configuration.buildTemplate();

        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(new CurrencyResource());
    }
}
