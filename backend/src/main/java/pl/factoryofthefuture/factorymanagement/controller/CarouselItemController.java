package pl.factoryofthefuture.factorymanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.factoryofthefuture.factorymanagement.entity.dto.CarouselItemDTO;
import pl.factoryofthefuture.factorymanagement.mapper.CarouselDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.CarouselRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carousel-item")
@RequiredArgsConstructor
public class CarouselItemController {

    private final CarouselRepository carouselRepository;
    private final CarouselDtoMapper carouselDtoMapper;

    @GetMapping("/visible")
    public ResponseEntity<List<CarouselItemDTO>> getVisibleCarouselItems() {
        List<CarouselItemDTO> carouselItemDtos = carouselRepository.findByIsVisibleTrue().stream()
                .map(carouselDtoMapper::mapCarouselItemToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(carouselItemDtos);
    }
}