package web.app.moldunity.entity.electronics.monitor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import web.app.moldunity.entity.article.ArticleComment;
import web.app.moldunity.entity.electronics.ElectronicsArticle;
import web.app.moldunity.entity.electronics.ElectronicsComment;

@Data
@Entity
@Table(name = "monitor_comments")
public class MonitorComment extends ElectronicsComment<Monitor, MonitorCommentReply> {
}
