package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.factoryofthefuture.factorymanagement.entity.CarModel;
import pl.factoryofthefuture.factorymanagement.exception.NotFoundException;
import pl.factoryofthefuture.factorymanagement.repository.CarModelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;

    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }

    public CarModel getCarModel(long id) {
        return carModelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public CarModel saveCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public CarModel updateCarModel(CarModel carModel) {
        CarModel updatedCarModel = carModelRepository.findById(carModel.getId())
                .orElseThrow(() -> new NotFoundException(carModel.getId()));
        updatedCarModel.setModelName(carModel.getModelName());
        updatedCarModel.setModelType(carModel.getModelType());
        updatedCarModel.setFactory(carModel.getFactory());
        return carModelRepository.save(updatedCarModel);
    }

    @Transactional
    public void deleteById(long id) {
        if (!carModelRepository.existsById(id)) {
            throw new NotFoundException(id);
        }
        carModelRepository.deleteById(id);
    }
}