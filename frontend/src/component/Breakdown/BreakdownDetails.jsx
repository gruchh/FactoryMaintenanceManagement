import React from "react";
import MyLocationIcon from "@mui/icons-material/MyLocation";
import DateRangeIcon from "@mui/icons-material/DateRange";
import { Divider } from "@mui/material";
import BreakdownsList from "./BreakdownsList";

const BreakdownDetails = () => {
  return (
    <div className="px-5 lg:px-20">
      <section className="flex justify-end">
        <h3 className="text-gray-500 py-2 mt-3">/Breakdown/i</h3>
      </section>
      <div></div>
      <div className="pt-3 pb-5">
        <h1 className="text-4xl font-semibold flex mb-3 lg:mb-5">Awaria 1</h1>
        <p className="text-gray-500 flex items-center gap-3 mb-3 lg:mb-5">
          <MyLocationIcon />
          <span>Location 1</span>
        </p>
        <p className="text-gray-500 flex items-center gap-3 ">
          <DateRangeIcon />
          <span className="text-orange-200">Date 1</span>
        </p>
      </div>
      <Divider />
      <BreakdownsList />
    </div>
  );
};

export default BreakdownDetails;
