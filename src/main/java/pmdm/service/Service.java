package pmdm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import pmdm.model.Item;
import pmdm.model.TransformedItem;
import pmdm.service.client.RestClient;
import pmdm.transformator.ItemTransformator;

@org.springframework.stereotype.Service
public class Service {

    private Logger log = LoggerFactory.getLogger(getClass());

    private RestClient restClient;

    public Service(RestClient restClient) {
        this.restClient = restClient;
    }

    public String propagate(Item item) throws Exception {
        log.info("Source system: input is " + item.getValue()
                + ", transforming and sending to target system...");
        TransformedItem transformedItem = ItemTransformator.transform(item);
        return restClient.sendMessage(transformedItem);
    }

    public String print(TransformedItem transformedItem) {
        log.info("Target system: input is " + transformedItem.getTransformedValue()
        + ", returning \"OK\"");
        return "OK";
    }
}
