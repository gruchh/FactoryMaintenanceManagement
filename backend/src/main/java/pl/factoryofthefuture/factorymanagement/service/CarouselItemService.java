package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.CarouselItem;
import pl.factoryofthefuture.factorymanagement.repository.CarouselRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarouselItemService {

    private final CarouselRepository carouselRepository;

    public List<CarouselItem> getAllCarouselItems() {
        return carouselRepository.findByIsVisibleTrue();
    }
}
