import {
  Divider,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import React, { useState } from "react";
import BreakdownShortSummary from "./BreakdownShortSummary";

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

const BreakdownsList = () => {
  const breakdownListItem = [1, 1, 1];
  const [breakdownType, setBreakdownType] = useState("all");
  const onBreakdownType = (e) => {
    console.log(e.target.value);
    setBreakdownType(e.target.value);
  };
  const [departmentType, setDepartmentType] = useState("all");
  const onDepartmentChange = (e) => {
    console.log(e);
    setDepartmentType(e.target.value);
  };

  return (
    <section className="pt-3 lg:pt-5 lg:flex relative">
      <div className="space-y-10 lg:w-[20%] filter">
        <div className="box space-y-5 lg:sticky top-28 p-5 shadow-sm">
          <div>
            <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
              Typ awarii
            </Typography>
            <FormControl className="py-10 space-y-5" component={"fieldset"}>
              <RadioGroup
                onChange={onBreakdownType}
                name="breakdown_type"
                value={breakdownType}
              >
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
          <Divider />
          <div>
            <Typography variant="h5" sx={{ paddingBottom: "1rem" }}>
              Dział
            </Typography>
            <FormControl className="py-10 space-y-5" component={"fieldset"}>
              <RadioGroup
                onChange={onDepartmentChange}
                name="breakdown_type"
                value={departmentType}
              >
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
      <div className="space-y-5 lg:w-[80%] lg:pl-10">
        {breakdownListItem.map(() => (
          <BreakdownShortSummary />
        ))}
      </div>
    </section>
  );
};

export default BreakdownsList;
