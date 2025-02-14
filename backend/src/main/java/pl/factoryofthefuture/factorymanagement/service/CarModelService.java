package pl.factoryofthefuture.factorymanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.factoryofthefuture.factorymanagement.entity.CarModel;
import pl.factoryofthefuture.factorymanagement.repository.CarModelRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CarModelService {

    private final CarModelRepository carModelRepository;

    public List<CarModel> getCarModels() {
        return carModelRepository.findAll();
    }

    public CarModel getCarModel(long id) {
        return carModelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No such car model with id: " + id));
    }

    public CarModel saveCarModel(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public CarModel updateCarModel(CarModel carModel) {
        CarModel updatedCarModel = carModelRepository.findById(carModel.getId())
                .orElseThrow(() -> new NoSuchElementException("CarModel not found with id: " + carModel.getId()));
        updatedCarModel.setModelName(carModel.getModelName());
        updatedCarModel.setModelType(carModel.getModelType());
        updatedCarModel.setFactory(carModel.getFactory());
        return carModelRepository.save(updatedCarModel);
    }

    public void deleteById(long id) {
        if (!carModelRepository.existsById(id)) {
            throw new NoSuchElementException("Car model not found with id: " + id);
        }
        carModelRepository.deleteById(id);
    }
}