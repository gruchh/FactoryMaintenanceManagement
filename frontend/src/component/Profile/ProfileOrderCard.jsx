import { Button, Card } from "@mui/material";

export const ProfileOrderCard = () => {
  return (
    <Card className="flex justify-between items-center p-5">
      <div className="flex items-center space-x-5">
        <img
          className="h-16 w-16"
          src="https://img.freepik.com/darmowe-zdjecie/ulozenie-metalowych-elementow-pod-wysokim-katem_23-2148932630.jpg?t=st=1735468068~exp=1735471668~hmac=3651fe01a5f09d414a55ff11d41f05ead6aa043020bdad83fa6967a094311752&w=1380"
          alt="Order picture"
        />
        <div>
          <p>Order</p>
          <p>120 zł</p>
        </div>
      </div>
      <div>
        <Button disabled className="cursor-not-allowed">completed</Button>
      </div>
    </Card>
  );
};
