import WarningAmberOutlinedIcon from "@mui/icons-material/WarningAmberOutlined";
import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";
import WorkOutlineOutlinedIcon from "@mui/icons-material/WorkOutlineOutlined";
import { Divider, Drawer, useMediaQuery } from "@mui/material";
import { useNavigate } from "react-router-dom";

const menu = [
  { title: "Profile", icon: <WorkOutlineOutlinedIcon /> },
  { title: "Work orders", icon: <WorkOutlineOutlinedIcon /> },
  { title: "Breakdowns", icon: <WarningAmberOutlinedIcon /> },
  { title: "Orders", icon: <BookmarkBorderOutlinedIcon /> },
  { title: "Workers", icon: <BookmarkBorderOutlinedIcon /> },
];

export const ProfileNavigation = ({ open, handleClose }) => {
  const isSmallScreen = useMediaQuery("(max-width:900px)");
  // const navigate = useNavigate();
  // const handleNavigate = (navigateDestiny) => {
  //   navigate(`/profile/${navigateDestiny.title.toLowerCase()}`);
  // };
  return (
    <div>
      <Drawer
        variant={isSmallScreen ? "temporary" : "permanent"}
        onClose={handleClose}
        open={isSmallScreen ? open : true}
        anchor="left"
      >
        <div className="w-[50vw] lg:w-[20vw] h-[90vh] flex flex-col justify-center text-xl gap-8 pt-16">
          {menu.map((menu, key) => (
            <>
              <div
                className="px-5 flex items-center space-x-5 cursor-pointer"
                key={key}
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
