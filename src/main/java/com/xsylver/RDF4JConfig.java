package com.xsylver;

import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;


@Configuration
public class RDF4JConfig {

    @Bean
    public Model bobModel() throws IOException {
        InputStream inputStream = new ClassPathResource("database.trig").getInputStream();
        return Rio.parse(inputStream, "http://samuel-gui.at", RDFFormat.TRIG);
    }

}
