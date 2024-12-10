import FactoryIcon from "@mui/icons-material/Factory";
import { Button, Card } from "@mui/material";
import PropTypes from "prop-types";

const AddressCard = ({ handleSelectAddress, selectedCard, showButton }) => {
  return (
    <Card className="flex gap-5 w-70 p-5">
      <FactoryIcon />
      <div className="space-y-3 text-gray-500">
        <h1 className="font-semibold text-lg text-white">Katowice</h1>
        <p>40-611 Katowice, ul. Fabryczna 1</p>
        {showButton && (
          <Button
            variant="outlined"
            fullWidth
            onClick={() => handleSelectAddress(selectedCard)}
          >
            Wybierz!
          </Button>
        )}
      </div>
    </Card>
  );
};

AddressCard.propTypes = {
  handleSelectAddress: PropTypes.func.isRequired,
  selectedCard: PropTypes.number.isRequired,
  showButton: PropTypes.bool.isRequired,
};

export default AddressCard;
