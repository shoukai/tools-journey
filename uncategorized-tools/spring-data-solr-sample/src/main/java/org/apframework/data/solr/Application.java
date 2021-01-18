package org.apframework.data.solr;

import com.google.common.collect.Lists;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Iterator;

@EnableAutoConfiguration
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SolrClient client;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws IOException, SolrServerException {
        Product phone = new Product();
        phone.setId("P0002");
        phone.setName("Phone");
        productRepository.save(phone);

        System.out.println(productRepository.findOne("P0001"));
        System.out.println(productRepository.findById("P0001"));

        SolrDocumentList list = client.getById("product", Lists.newArrayList("P0001", "P0002"));
        Iterator<SolrDocument> iterator = list.iterator();
        while (iterator.hasNext()) {
            SolrDocument entry = iterator.next();
            System.out.println(entry.entrySet());
        }
    }

}
