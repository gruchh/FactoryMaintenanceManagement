import WarningAmberOutlinedIcon from "@mui/icons-material/WarningAmberOutlined";
import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";
import WorkOutlineOutlinedIcon from "@mui/icons-material/WorkOutlineOutlined";
import { Divider, Drawer, useMediaQuery } from "@mui/material";
import { useLocation, useNavigate } from "react-router-dom";

const menu = [
  { title: "Profile", icon: <WorkOutlineOutlinedIcon />, destiny: "me" },
  {
    title: "Work orders",
    icon: <WorkOutlineOutlinedIcon />,
    destiny: "work_orders",
  },
  {
    title: "Breakdowns",
    icon: <WarningAmberOutlinedIcon />,
    destiny: "brekdowns",
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
    console.log(navigateDestiny);
    console.warn(actualLocation);
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
          {menu.map((menu, key) => (
            <>
              <div
                className={`px-5 flex items-center space-x-5 py-4 cursor-pointer ${
                  actualLocation.pathname === `${componentPrefix}${menu.destiny}`
                    ? "bg-white text-black"
                    : ""
                }`}
                key={key}
                onClick={() => handleNavigate(menu)}
              >
                {menu.icon}
                <span>{menu.title}</span>
              </div>
              {key !== menu.length - 1 && <Divider />}
            </>
          ))}
        </div>
      </Drawer>
    </div>
  );
};

export default ProfileNavigation;
