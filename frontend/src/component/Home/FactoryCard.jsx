import { Card, Chip } from "@mui/material";
import PropTypes from "prop-types";

const FactoryCard = ({ city, activity, models }) => {
  const modelsString = models
    .map((model) => `${model.model} (${model.type})`)
    .join(", ");

  return (
    <Card className="m-5 w-[18rem]">
      <div className="cursor-pointer relative">
        <img
          className="w-full h-[10rem] rounded-md object-cover"
          src="https://img.freepik.com/free-vector/factory-concept-illustration_114360-5260.jpg?t=st=1735756528~exp=1735760128~hmac=9c75f82ef645ae7ba86c3836535f1c71672e469395f5a01553597d25b45bbe3c&w=1380"
          alt=""
        />
        <Chip
          size="small"
          className="absolute top-2 left-2"
          color="green"
        />
      </div>
      <div className="p-4 textPart lg:flex w-full justify-between">
        <div className="space-y-1">
          <p className="font-semibold text-gray-400 text-lg">{city}</p>
          <p className="text-gray-200 text-sm">{activity}</p>
          <p className="text-gray-200 text-sm">{modelsString}</p>
        </div>
      </div>
    </Card>
  );
};

FactoryCard.propTypes = {
  city: PropTypes.string.isRequired,
  activity: PropTypes.string.isRequired,
  models: PropTypes.arrayOf(
    PropTypes.shape({
      model: PropTypes.string.isRequired,
      type: PropTypes.string.isRequired,
    })
  ).isRequired,
};

export default FactoryCard;
