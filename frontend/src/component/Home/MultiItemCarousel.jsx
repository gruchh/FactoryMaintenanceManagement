import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import { carouselData } from "./carouselData";
import CarouselItem from "./CarouselItem";

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
  return (
    <div>
      <Slider {...settings}>
        {carouselData.map((data, index) => (
          <CarouselItem 
          key={index}
          image={data.image}
          title={data.title} />
        ))}
      </Slider>
    </div>
  );
};

export default MultiItemCarousel;