import React, { useState } from "react";
import MyLocationIcon from "@mui/icons-material/MyLocation";
import DateRangeIcon from "@mui/icons-material/DateRange";
import {
  Divider,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";

const breakdownTypes = [
  { key: 1, value: "all", label: "Wszystkie" },
  { key: 2, value: "automatical", label: "Automatyczne" },
  { key: 3, value: "mechanical", label: "Mechaniczne" },
];
const departments = [
  { key: 1, value: "all", label: "Wszystkie" },
  { key: 2, value: "D1", label: "Dział 1" },
  { key: 3, value: "D2", label: "Dział 2" },
  { key: 4, value: "D3", label: "Dział 3" },
];

const BreakdownList = () => {
  const [breakdownType, setBreakdownType] = useState("all");
  const onBreakdownType = (e) => {
    console.log(e.target.value);
  };

  const [departmentType, setDepartmentType] = useState("all");
  const onDepartmentTypeChange = (e) => {
    console.log(e.target.value);
  };

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
      <section className="pt-3 lg:pt-5 lg:flex relative">
        <div className="space-y-10 lg:w-[20%] filter">
          <div className="box space-y-5 lg:sticky top-28 p-5 shadow-sm">
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Typ awarii
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup>
                  {breakdownTypes.map((breakdown) => (
                    <FormControlLabel
                      key={breakdown.key}
                      value={breakdown.value}
                      control={<Radio />}
                      label={breakdown.label}
                    />
                  ))}
                </RadioGroup>
              </FormControl>
            </div>
            <Divider/>
            <div>
              <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
                Dział
              </Typography>
              <FormControl className="py-10 space-y-5" component={"fieldset"}>
                <RadioGroup>
                  {departments.map((department) => (
                    <FormControlLabel
                      key={department.key}
                      value={department.value}
                      control={<Radio />}
                      label={department.label}
                    />
                  ))}
                </RadioGroup>
              </FormControl>
            </div>
          </div>
        </div>
        <div className="space-y-5 lg:w-[80%] lg:pl-10">aa</div>
      </section>
    </div>
  );
};

export default BreakdownList;