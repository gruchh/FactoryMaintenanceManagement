package pl.factoryofthefuture.factorymanagement.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import pl.factoryofthefuture.factorymanagement.entity.CarouselItem;
import pl.factoryofthefuture.factorymanagement.entity.dto.CarouselItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@AllArgsConstructor
public class CarouselDtoMapper {

    public CarouselItemDto mapCarouselItemToDto(CarouselItem carouselItem) {
        return CarouselItemDto.builder()
                .id(carouselItem.getId())
                .imageUrl(carouselItem.getImageUrl())
                .title(carouselItem.getTitle())
                .link(carouselItem.getLink())
                .isVisible(carouselItem.isVisible())
                .build();
    }

    public List<CarouselItemDto> mapCarouselItemsToDtos(List<CarouselItem> carouselItems) {
        return carouselItems.stream()
                .map(this::mapCarouselItemToDto)
                .collect(Collectors.toList());
    }
}