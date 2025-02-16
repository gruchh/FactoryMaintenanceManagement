package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.CarouselItem;
import pl.factoryofthefuture.factorymanagement.entity.dto.CarouselItemDto;
import pl.factoryofthefuture.factorymanagement.mapper.CarouselDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.CarouselRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarouselItemService {

    private final CarouselRepository carouselRepository;
    private final CarouselDtoMapper carouselDtoMapper;

    public List<CarouselItemDto> getAllCarouselItems() {
        List<CarouselItem> visibleCaorouselItems = carouselRepository.findByIsVisibleTrue();
        return carouselDtoMapper.mapCarouselItemsToDtos(visibleCaorouselItems);
    }
}
