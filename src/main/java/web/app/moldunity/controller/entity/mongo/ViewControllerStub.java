package web.app.moldunity.controller.entity.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.app.moldunity.entity.mongo.FurnitureView;
import web.app.moldunity.service.async.entity.AsyncMongoTemplateService;
import web.app.moldunity.util.CompletableFutureUtil;

@RestController
public class ViewControllerStub {
    private final AsyncMongoTemplateService asyncMongoTemplateService;

    @Autowired
    public ViewControllerStub(AsyncMongoTemplateService asyncMongoTemplateService) {
        this.asyncMongoTemplateService = asyncMongoTemplateService;
    }

    @PostMapping(value = "/furniture-view")
    public ResponseEntity<FurnitureView> addFurnitureView(@RequestBody FurnitureView furnitureView){
        if(furnitureView.getViewId() == null || furnitureView.getArticleId() == null)
            return new ResponseEntity<>(new FurnitureView("None", 0L), HttpStatus.NOT_ACCEPTABLE);
        return CompletableFutureUtil.exceptionWrapper(asyncMongoTemplateService.asyncAdd(furnitureView));
    }
}
