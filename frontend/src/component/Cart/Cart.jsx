import { Divider } from "@mui/material";
import CartItem from "./CartItem";
import AddressCard from "./AddressCard";

const items = [1, 1, 1];
const Cart = () => {
  const createOrderUsingSelectedAddress = () => {};
  return (
    <div>
      <main className="flex justify-between">
        <section className="lg:w-[30%] flex justify-start px-5 pb-10 lg:pb-0 flex-col">
          <h1 className="text-center font-semibold text-2xl py-10 w-full">
            Delivery address
          </h1>
          <div className="flex gap-5 flex-wrap justify-center">
            {[1, 1, 1].map((card, index) => (
              <AddressCard key={index}
                handleSelectAddress={createOrderUsingSelectedAddress}
                selectedCard={card}
                showButton={true}
              />
            ))}
          </div>
        </section>
        <Divider orientation="vertical" flexItem />
        <section className="lg:w-[70%] space-y-6 lg:min-h-screen pt-10 lg:pl-20 lg:pr-20">
          {items.map((item, index) => (
            <CartItem key={index} />
          ))}
          <Divider />
          <div className="billDetails px-5 text-sm">
            <p className="font-extralight py-5">Bill Details</p>
            <div className="space-y-3">
              <div className="flex justify-between text-gray-400">
                <p>Item total</p>
                <p>$500</p>
              </div>
              <div className="flex justify-between text-gray-400">
                <p>Deliver Fee</p>
                <p>$500</p>
              </div>
              <Divider />
            </div>
            <div className="flex justify-between text-gray-400">
              <p>Total pay</p>
              <p>$999</p>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default Cart;
