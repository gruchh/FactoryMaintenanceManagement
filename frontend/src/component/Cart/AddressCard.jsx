import React from "react";
import FactoryIcon from "@mui/icons-material/Factory";
import { Button, Card } from "@mui/material";

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
            onClick={() => handleSelectAddress()}
          >
            Wybierz!
          </Button>
        )}
      </div>
    </Card>
  );
};

export default AddressCard;
