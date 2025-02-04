import PropTypes from "prop-types";

const CarouselItem = ({ image, title, link }) => {
  return (
    <div className="flex flex-col justify-center items-center">
      <a href={link} className="inline-block">
        <img
          className="w-[10rem] h-[10rem] lg:w-[14rem] lg:h-[14rem] rounded-full object-cover object-center"
          src={image}
          alt=""
        />
      </a>
      <span className="py-5 font-semibold text-xl text-gray-600">{title}</span>
    </div>
  );
};

CarouselItem.propTypes = {
  image: PropTypes.string.isRequired,
  title: PropTypes.string.isRequired,
  link: PropTypes.string.isRequired,
};

export default CarouselItem;
