package com.offcn.reptile.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-03 14:41
 **/
@Configuration
public class SolrConfig {

    @Bean
    public SolrTemplate solrTemplate(SolrClient solrClient){

        return new SolrTemplate(solrClient);
    }
}
