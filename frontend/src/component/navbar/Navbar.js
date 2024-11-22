import { Avatar, Badge, IconButton } from "@mui/material";
import React from "react";
import YoutubeSearchedForIcon from "@mui/icons-material/YoutubeSearchedFor";
import { ShoppingCart } from "@mui/icons-material";

export const Navbar = () => {
  return (
    <div className="px-5 z-50 py-1 bg-[#543671] lg:px20 lg:py-3 flex justify-between">
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
          <Avatar sx={{ bgColor: "white", color: "pink.A400" }}>JM</Avatar>
        </div>
        <div className="">
          <IconButton>
            <Badge color="primary" badgeContent="3">
              <ShoppingCart sx={{ fontSize: "1.5rem" }} />
            </Badge>
          </IconButton>
        </div>
      </div>
    </div>
  );
};
