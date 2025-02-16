package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.CarModel;
import pl.factoryofthefuture.factorymanagement.entity.Factory;
import pl.factoryofthefuture.factorymanagement.entity.dto.CarModelDto;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.mapper.CarModelDtoMapper;
import pl.factoryofthefuture.factorymanagement.repository.CarModelRepository;
import pl.factoryofthefuture.factorymanagement.repository.FactoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;
    private final FactoryRepository factoryRepository;
    private final CarModelDtoMapper carModelDtoMapper;

    public List<CarModelDto> getAllCarModelsDtos() {
        List<CarModel> allCarModels = carModelRepository.findAll();
        return carModelDtoMapper.mapCarModelsToDtos(allCarModels);
    }

    public CarModelDto getCarModelDtoById(long id) {
        CarModel carModel = carModelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
        return carModelDtoMapper.mapCarModelToDto(carModel);
    }

    public CarModelDto saveCarModel(CarModelDto carModelDto) {
        CarModel carModel = carModelDtoMapper.mapCarModelDtoToEntity(carModelDto);
        CarModel savedCarModel = carModelRepository.save(carModel);
        return carModelDtoMapper.mapCarModelToDto(savedCarModel);
    }

    @Transactional
    public CarModelDto updateCarModel(CarModelDto carModelDto) {
        CarModel updatedCarModel = carModelRepository.findById(carModelDto.getId())
                .orElseThrow(() -> new NotFoundException(carModelDto.getId()));
        updatedCarModel.setModelName(carModelDto.getModelName());
        updatedCarModel.setModelType(carModelDto.getModelType());
        if (carModelDto.getId() != null) {
            Factory factory = factoryRepository.findById(carModelDto.getId())
                    .orElseThrow(() -> new NotFoundException(carModelDto.getId()));
            updatedCarModel.setFactory(factory);
        } else {
            updatedCarModel.setFactory(null);
        }
        CarModel savedCarModel = carModelRepository.save(updatedCarModel);
        return carModelDtoMapper.mapCarModelToDto(savedCarModel);
    }

    @Transactional
    public void deleteById(long id) {
        if (!carModelRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        carModelRepository.deleteById(id);
    }
}