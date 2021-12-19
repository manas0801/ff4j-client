package com.example.ff4jclient.config;

import org.ff4j.FF4j;
import org.ff4j.cache.FF4jCacheProxy;
import org.ff4j.cache.InMemoryCacheManager;
import org.ff4j.web.jersey2.store.FeatureStoreHttp;
import org.ff4j.web.jersey2.store.PropertyStoreHttp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Ff4jConfig {

    @Bean
    public FF4j ff4j(){
        String ff4jApiEndPoint = "http://localhost:8080/api/ff4j";
        FeatureStoreHttp fStore = new FeatureStoreHttp(ff4jApiEndPoint);
        PropertyStoreHttp pStore = new PropertyStoreHttp(ff4jApiEndPoint);
        /*
         * Maybe we don't want to do an http call each time we test a feature
         * as there is latency. Local in memory cache with TTL 10min can help.
         */
       FF4jCacheProxy cc = new FF4jCacheProxy(fStore, pStore, new InMemoryCacheManager(600));

        FF4j ff4j = new FF4j();
        ff4j.setFeatureStore(cc);
        ff4j.setPropertiesStore(cc);
        ff4j.audit(false); //(auditing at server level)
        ff4j.autoCreate(true);
return ff4j;
    }

}
