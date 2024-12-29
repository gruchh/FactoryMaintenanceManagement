import { ProfileOrderCard } from "./ProfileOrderCard";

const ProfileOrders = () => {
  return (
    <div className="flex items-center flex-col">
      <h1 className="text-xl text-center py-7 font-semibold">My orders</h1>
      <div className="space-y-5 w-full  lg:w-1/2">
        {[1, 1, 1].map((order, index) => 
          <ProfileOrderCard key={index} />
        )}
      </div>
    </div>
  );
};

export default ProfileOrders;
