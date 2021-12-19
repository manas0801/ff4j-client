package com.example.ff4jclient.controller;

import org.ff4j.FF4j;
import org.ff4j.core.FlippingExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    FF4j ff4j;

    @GetMapping("/api/v1/hello")
    @Async
    public ResponseEntity<String>  hello(){

       ff4j.getFeatureStore().readAll().forEach((k,v)->System.out.println(v.toJson()));
  FlippingExecutionContext flippingExecutionContext= new FlippingExecutionContext();
  flippingExecutionContext.addValue("key","cde");
  String expression= "expressionFalse";
       if(ff4j.check("ExpressionBasedStrategy", flippingExecutionContext)){
           expression="expressionTrue";
       }

        return new ResponseEntity<>("Hello "+ expression, HttpStatus.OK);
    }

}
