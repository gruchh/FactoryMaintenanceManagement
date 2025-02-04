import { useEffect } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick-theme.css";
import "slick-carousel/slick/slick.css";
import { fetchCarouselItems } from "../State/Carousel/carouselActions";
import CarouselItem from "./CarouselItem";
import { useDispatch, useSelector } from "react-redux";

const MultiItemCarousel = () => {
  const settings = {
    dots: false,
    infinite: true,
    speed: 2000,
    slidesToShow: 5,
    slidesToScroll: 1,
    autoplay: true,

    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
        },
      },
      {
        breakpoint: 768,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 3,
        },
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
        },
      },
    ],
  };

  const dispatch = useDispatch();
  const carouselItems = useSelector((state) => state.carousel);
  const carouselData = carouselItems.carouselItems;

  useEffect(() => {
    dispatch(fetchCarouselItems());
  }, []);

  return (
    <div>
      <Slider {...settings}>
        {carouselData.map((data, index) => (
          <CarouselItem
            key={index}
            image={data.imageUrl}
            title={data.title}
            link={data.link}
          />
        ))}
      </Slider>
    </div>
  );
};

export default MultiItemCarousel;
