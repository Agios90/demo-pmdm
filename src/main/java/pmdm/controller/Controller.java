package pmdm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pmdm.model.Item;
import pmdm.model.TransformedItem;
import pmdm.service.Service;

@RestController
@RequestMapping("/")
public class Controller {

    private Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping(path = "/test")
    public String transformAndPropagate(@RequestBody Item item) throws Exception {
        return service.propagate(item);
    }

    @PostMapping(path = "/print")
    public String print(@RequestBody TransformedItem transformedItem) {
        return service.print(transformedItem);
    }
}
