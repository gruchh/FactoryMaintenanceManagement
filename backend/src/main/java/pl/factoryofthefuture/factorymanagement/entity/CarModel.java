package pl.factoryofthefuture.factorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "CAR_MODEL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="MODEL_NAME", nullable = false)
    private String modelName;

    @Column(name="MODEL_TYPE")
    private String modelType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "factory_id")
    private Factory factory;
}