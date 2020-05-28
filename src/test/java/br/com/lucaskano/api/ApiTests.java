package br.com.lucaskano.api;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;

public class ApiTests {
    protected Injector injector = Guice.createInjector(
            new AbstractModule() {
                @Override
                protected void configure() {
                }
            }
    );

    @Before
    public void setup () {
        injector.injectMembers(this);
    }
}
