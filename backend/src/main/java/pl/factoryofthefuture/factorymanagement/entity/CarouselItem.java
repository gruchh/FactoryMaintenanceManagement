package pl.factoryofthefuture.factorymanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "CAROUSEL_ITEMS")
@Table(name = "carousel_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarouselItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name="LINK", nullable = false)
    private String link;

    @Column(name = "IS_VISIBLE")
    private boolean isVisible;
}