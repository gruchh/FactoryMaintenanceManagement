import WarningAmberOutlinedIcon from "@mui/icons-material/WarningAmberOutlined";
import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";
import WorkOutlineOutlinedIcon from "@mui/icons-material/WorkOutlineOutlined";
import { Divider, Drawer, useMediaQuery } from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";
import React from "react";

const menu = [
  { title: "Profile", icon: <WorkOutlineOutlinedIcon />, destiny: "" },
  {
    title: "Work orders",
    icon: <WorkOutlineOutlinedIcon />,
    destiny: "workorders",
  },
  {
    title: "Breakdowns",
    icon: <WarningAmberOutlinedIcon />,
    destiny: "breakdowns",
  },
  { title: "Orders", icon: <BookmarkBorderOutlinedIcon />, destiny: "orders" },
  {
    title: "Workers",
    icon: <BookmarkBorderOutlinedIcon />,
    destiny: "workers",
  },
];

export const ProfileNavigation = ({ open, handleClose }) => {
  const componentPrefix = "/profile/";
  const isSmallScreen = useMediaQuery("(max-width:900px)");
  const navigate = useNavigate();
  const handleNavigate = (navigateDestiny) => {
    navigate(componentPrefix + navigateDestiny.destiny.toLowerCase());
  };
  const actualLocation = useLocation();
  return (
    <div>
      <Drawer
        variant={isSmallScreen ? "temporary" : "permanent"}
        onClose={handleClose}
        open={isSmallScreen ? open : true}
        anchor="left"
      >
        <div className="w-[50vw] lg:w-[20vw] h-[90vh] flex flex-col justify-center text-xl gap-1 pt-16">
          {menu.map((menuItem, key) => (
            <React.Fragment key={key}>
              <div
                className={`px-5 flex items-center space-x-5 py-4 cursor-pointer ${
                  actualLocation.pathname ===
                  `${componentPrefix}${menuItem.destiny}`
                    ? "bg-white text-black"
                    : ""
                }`}
                onClick={() => handleNavigate(menuItem)}
              >
                {menuItem.icon} <span>{menuItem.title}</span>
              </div>
              {key !== menu.length - 1 && <Divider />}
            </React.Fragment>
          ))}
        </div>
      </Drawer>
    </div>
  );
};

export default ProfileNavigation;