import WarningAmberOutlinedIcon from "@mui/icons-material/WarningAmberOutlined";
import BookmarkBorderOutlinedIcon from "@mui/icons-material/BookmarkBorderOutlined";
import WorkOutlineOutlinedIcon from '@mui/icons-material/WorkOutlineOutlined';
import { Drawer, useMediaQuery } from "@mui/material";

const menu = [
  { title: "Work orders", icon: <WorkOutlineOutlinedIcon /> },
  { title: "Breakdowns", icon: <WarningAmberOutlinedIcon /> },
  { title: "Orders", icon: <BookmarkBorderOutlinedIcon /> },
];

const ProfileNavigation = ({open, handleClose}) => {
    const isSmallScreen = useMediaQuery("(max-width:1080)");
  return <div>
    <Drawer variant={isSmallScreen?"temporary":"permanent"} onClose={handleClose} open={open} anchor='left' sx={{zIndex:1}}>

    </Drawer>
  </div>;
};

export default ProfileNavigation;
