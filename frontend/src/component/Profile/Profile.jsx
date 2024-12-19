import { useState } from "react";
import ProfileNavigation from "./ProfileNavigation";
import {Outlet} from "react-router-dom";

const Profile = () => {
  const [openSideBar, setOpenSideBar] = useState(false);

  return (
    <div className="lg:flex justify-between">
      <div className="sticky h-[80vh] lg:w-[20%]">
        <ProfileNavigation />
      </div>
      <div className="lg:w-[80%]">
        <Outlet />
      </div>
    </div>
  );
};

export default Profile;
