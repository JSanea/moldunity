package web.app.moldunity.entity.postgres.ad;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.r2dbc.spi.Row;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import web.app.moldunity.enums.AdSubtype;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "ads")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Ad {
    @Id
    private Long id;
    private String username;
    @NotBlank private String offerType;
    @NotBlank private String title;
    @NotBlank private String categoryName;
    @NotBlank private String subcategoryName;
    private String country;
    private String location;
    private String description;
    @Min(value = 1, message = "Price must be greater than 0")
    @NotNull private Integer price;
    private Integer views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime republishedAt;
    @JsonIgnore
    private Long userId;
    private static final Ad EMPTY = new Ad();

    public Ad(){}

    public void setSubcategoryNameFromAdType(AdSubtype type) {
        this.subcategoryName = type.getSubcategoryName();
    }

    public void setDateTimeFields() {
        if(createdAt != null && updatedAt != null && republishedAt != null) return;
        var t = LocalDateTime.now();
        this.createdAt = t;
        this.updatedAt = t;
        this.republishedAt = t;
    }

    public static Ad mapRowToAd(Row row) {
        return Ad.builder()
                .id(row.get("ads_id", Long.class))
                .offerType(row.get("offer_type", String.class))
                .title(row.get("title", String.class))
                .description(row.get("description", String.class))
                .categoryName(row.get("category_name", String.class))
                .subcategoryName(row.get("subcategory_name", String.class))
                .price(row.get("price", Integer.class))
                .createdAt(row.get("ads_created_at", LocalDateTime.class))
                .updatedAt(row.get("updated_at", LocalDateTime.class))
                .republishedAt(row.get("republished_at", LocalDateTime.class))
                .username(row.get("username", String.class))
                .userId(row.get("user_id", Long.class))
                .build();
    }

    public Ad update(Ad ad){
        this.setOfferType(ad.getOfferType());
        this.setTitle(ad.getTitle());
        this.setCountry(ad.getCountry());
        this.setDescription(ad.getDescription());
        this.setPrice(ad.getPrice());
        this.setUpdatedAt(LocalDateTime.now());
        return this;
    }

    public static Ad empty(){
        return EMPTY;
    }
}















