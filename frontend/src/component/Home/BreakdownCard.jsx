import { Card, Chip } from "@mui/material";
import PropTypes from "prop-types";

const BreakdownCard = ({ title, description }) => {
  return (
    <Card className="m-5 w-[18rem]">
      <div className="cursor-pointer relative">
        <img
          className="w-full h-[10rem] rounded-md object-cover"
          src="https://cdn.pixabay.com/photo/2015/10/12/17/54/breakdown-984812_1280.jpg"
          alt=""
        />
        <Chip
          size="small"
          className="absolute top-2 left-2"
          color={"green"}
          label={true ? "closed" : "opened"}
        />
      </div>
      <div className="p-4 textPart lg:flex w-full justify-between">
        <div className="space-y-1">
          <p className="font-semibold text-gray-400 text-lg">{title}</p>
          <p className="text-gray-200 text-sm ">{description}</p>
        </div>
      </div>
    </Card>
  );
};

BreakdownCard.propTypes = {
  title: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
};

export default BreakdownCard;
