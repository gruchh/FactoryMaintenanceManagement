import { ShoppingCart } from "@mui/icons-material";
import PersonIcon from "@mui/icons-material/Person";
import YoutubeSearchedForIcon from "@mui/icons-material/YoutubeSearchedFor";
import { Avatar, Badge, Box, IconButton } from "@mui/material";
import { useNavigate } from "react-router-dom";

export const Navbar = () => {
  const isLogged = false;

  const navigate = useNavigate();
  const handleLogin = () => {
    navigate("/account/login");
  }

  return (
    <Box className="px-5 top-0 z-50 py-[.8rem] bg-[#543671] lg:px20 lg:py-3 flex justify-between">
      <div className="lg:mr-10 cursor-pointer flex items-center space-x-4">
        <li className="logo font-semibold text-gray-500 text-2xl list-none">
          Maintenance management
        </li>
      </div>
      <div className="flex items-center space-x-2 lg:space-x-10">
        <div className="">
          <IconButton>
            <YoutubeSearchedForIcon sx={{ fontSize: "1.5rem" }} />
          </IconButton>
        </div>
        <div className="">
          {isLogged ? (
            <Avatar sx={{ bgColor: "white", color: "pink.A400" }}>JM</Avatar>
          ) : (
              <IconButton onClick={() => handleLogin()}>
                <PersonIcon />
              </IconButton>
          )}
        </div>
        <div className="">
          <IconButton>
            <Badge color="primary" badgeContent="3">
              <ShoppingCart sx={{ fontSize: "1.5rem" }} />
            </Badge>
          </IconButton>
        </div>
      </div>
    </Box>
  );
};
