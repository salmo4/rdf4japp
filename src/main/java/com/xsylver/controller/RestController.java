package com.xsylver.controller;

import org.eclipse.rdf4j.model.*;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.StringWriter;

/**
 * Created by samuelgui on 6/8/17.
 */
@Controller
@RequestMapping("/test")
public class RestController {

    private Model bobModel;

    @Autowired
    public RestController(Model bobModel) {
        this.bobModel = bobModel;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/1")
    public Object testing() {
        bobModel.forEach(System.out::println);
        return new Object();
    }

    @GetMapping()
    public ResponseEntity<String> myTest() {

//        StringBuilder sb = new StringBuilder("<html><body>");
//        bobModel.forEach(item -> sb.append(item.getObject()).append("<br/>"));
//        sb.append("<body/><html>");
        StringWriter sr = new StringWriter();
        RDFWriter writer = Rio.createWriter(RDFFormat.JSONLD, sr);
        Rio.write(bobModel, writer);


        return new ResponseEntity<String>(sr.toString(), HttpStatus.OK);
    }
    @GetMapping("/{make}")
    public ResponseEntity<String> filterBmw(@PathVariable String make) {
        ValueFactory vf = SimpleValueFactory.getInstance();

        IRI makeIri = vf.createIRI("http://samuel-gui.at/" + make);
        Model filteredByMake = bobModel.filter(makeIri, null, null);

        StringWriter sr = new StringWriter();
        RDFWriter writer = Rio.createWriter(RDFFormat.JSONLD, sr);
        Rio.write(filteredByMake, writer);

        return new ResponseEntity<String>(sr.toString(), HttpStatus.OK);
    }
}
