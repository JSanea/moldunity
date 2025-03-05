package web.app.moldunity.entity.electronics.monitor;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import web.app.moldunity.entity.Advert;

@Entity
@Getter
@Setter
@Table(name = "monitors")
public class MonitorAdvert extends Advert {
}
