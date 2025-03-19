package web.app.moldunity.entity.electronics.monitor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import web.app.moldunity.entity.article.Article;
import web.app.moldunity.entity.electronics.ElectronicsArticle;

@Data
@Entity
@Table(name = "monitor")
@EqualsAndHashCode(callSuper = true)
public class Monitor extends ElectronicsArticle<MonitorImage, MonitorView> {
    @NotEmpty
    private String resolution;

    @NotEmpty
    private String refreshRate;
}

















