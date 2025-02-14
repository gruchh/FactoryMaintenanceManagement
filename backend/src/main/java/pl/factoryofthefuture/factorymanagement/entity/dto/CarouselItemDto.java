package pl.factoryofthefuture.factorymanagement.entity.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarouselItemDto {
    private Long id;
    private String imageUrl;
    private String title;
    private String link;
    private boolean isVisible;
}
