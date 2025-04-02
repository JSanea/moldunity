package web.app.moldunity.controller.entity.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.mongo.FurnitureView;
import web.app.moldunity.service.entity.mongo.MongoTemplateService;

@RestController
public class ViewControllerStub {
    private final MongoTemplateService mongoTemplateService;

    @Autowired
    public ViewControllerStub(MongoTemplateService furnitureViewService) {
        this.mongoTemplateService = furnitureViewService;
    }

    @PostMapping(value = "/furniture-view")
    public FurnitureView add(@RequestBody FurnitureView furnitureView){
        return mongoTemplateService.add(furnitureView);
    }
}
