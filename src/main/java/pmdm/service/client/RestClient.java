package pmdm.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pmdm.model.TransformedItem;

import java.net.URI;
import java.util.Collections;

@Component
public class RestClient {

    @Value("${endpoint.local}")
    private String endpointLocal;

    @Value("${endpoint.remote}")
    private String endpointRemote;

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    public String sendMessage(TransformedItem item) throws Exception {
        try {
            URI uri = new URI(endpointLocal);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            HttpEntity<String> request = new HttpEntity<>(
                    mapper.writeValueAsString(item),headers);

            ResponseEntity<String> totalEntity
                    = restTemplate.postForEntity(uri, request, String.class);

            return totalEntity.getBody();

        } catch (Exception e) {
            throw new Exception("Could not send to target system", e);
        }
    }
}
