package web.app.moldunity.entity.postgres.real_estate;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import web.app.moldunity.entity.postgres.ad.Subcategory;

@Getter
@Setter
@Table(value = "apartments")
public class Apartment implements Subcategory{
    @Id
    private Long id;
    private Long AdId;
}
