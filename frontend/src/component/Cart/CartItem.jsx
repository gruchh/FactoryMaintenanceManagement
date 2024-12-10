import { AddCircleOutline, RemoveCircleOutline } from "@mui/icons-material";
import { IconButton } from "@mui/material";

const CartItem = () => {
  return (
    <div className="px-5">
      <div className="lg:flex items-center lg:space-x-5">
        <div>
          <img
            className="w-[5rem] h-[5rem] object-cover"
            src="https://cdn.pixabay.com/photo/2022/12/14/11/02/gas-struts-7655178_960_720.jpg"
            alt=""
          />
        </div>
        <div className="flex items-center justify-between lg:w-[70%]">
          <div className="space-y-1 lg:space-y-3 w-full">
            <p>Siłownik</p>
            <div className="flex justify-between items-center">
              <div className="flex items-center space-x-1">
                <IconButton>
                  <RemoveCircleOutline />
                </IconButton>
                <div className="flex items-center w-5 h-5 text-xs">2</div>
                <IconButton>
                  <AddCircleOutline />
                </IconButton>
              </div>
            </div>
          </div>
          <p>200zł</p>
        </div>
      </div>
    </div>
  );
};

export default CartItem;
