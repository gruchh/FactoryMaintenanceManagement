package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarouselItemDTO {
    private Long id;
    private String imageUrl;
    private String title;
    private String link;
    private boolean isVisible;
}
