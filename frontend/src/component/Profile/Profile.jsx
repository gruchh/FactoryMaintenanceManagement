import { useState } from "react";
import ProfileNavigation from "./ProfileNavigation";
import { Route, Routes } from "react-router-dom";
import UserProfile from "./UserProfile";
import WorkOrders from "./WorkOrders";
import Breakdowns from "./Breakdowns";
import Orders from "./Orders";
import Workers from "./Workers";

const Profile = () => {
  const [openSideBar, setOpenSideBar] = useState("false");
  
  return (
    <div className="lg:flex justify-between">
      <div className="sticky h-[80vh] lg:w-[20%]">
        <ProfileNavigation />
      </div>
      <div className="lg:w-[80%]">
        <Routes>
          <Route path="/" element={<UserProfile />}></Route>
          <Route path="/workorders" element={<WorkOrders />}></Route>
          <Route path="/breakdowns" element={<Breakdowns />}></Route>
          <Route path="/orders" element={<Orders />}></Route>
          <Route path="/workers" element={<Workers />}></Route>
        </Routes>
      </div>
    </div>
  );
};

export default Profile;
