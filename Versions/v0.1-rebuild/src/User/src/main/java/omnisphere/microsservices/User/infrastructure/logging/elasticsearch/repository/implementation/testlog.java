package omnisphere.microsservices.User.infrastructure.logging.elasticsearch.repository.implementation;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class testlog {
    private void to() {
        var client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")  // Substitua pelo endereço correto do seu servidor Elasticsearch
                )
        );
    }
}
