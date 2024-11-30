import { PrecisionManufacturing } from "@mui/icons-material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import TimerIcon from "@mui/icons-material/Timer";
import { Button, Checkbox, FormControlLabel, FormGroup } from "@mui/material";
import Accordion from "@mui/material/Accordion";
import AccordionDetails from "@mui/material/AccordionDetails";
import AccordionSummary from "@mui/material/AccordionSummary";
import React from "react";

const BreakdownShortSummary = () => {
  const checkList = [
    {
      category: "Ogólne sprawdzenie",
      duties: ["Stan ogólny", "Elektryka", "Mechanika", "Wycieki"],
    },
    {
      category: "Bezpieczeństwo i BHP",
      duties: ["Zatrzymanie awaryjne", "", "", "Użycie gaśnicy"],
    },
    {
      category: "Części",
      duties: ["Wymiana", "Konieczność kontroli", "", ""],
    },
    {
      category: "Dodatkowe",
      duties: [
        "Kalibracja",
        "Działania w przyszłości",
        "Prewencja",
        "Testy końcowe",
      ],
    },
  ];

  return (
    <div>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <div className="lg:flex justify-between">
            <div className="lg:flex lg:gap-5">
              <img
                className="w-[7rem] h-[7rem] object-cover"
                src="https://cdn.pixabay.com/photo/2013/02/10/09/02/lift-arm-79970_1280.jpg"
                alt=""
              />
            </div>
            <div className="space-y-15 lg:space-y-5 lg:max-w-2xl space-y-2 ml-2 lg:ml-5">
              <div>
                <p className="font-semibold font-xl">Awaria 1</p>
              </div>
              <div className="lg:flex items-center space-x-1 lg:space-x-3">
                <TimerIcon />
                <p className="font-light">Czas trwania</p>
              </div>
              <div className="lg:flex items-center space-x-1 lg:space-x-3">
                <PrecisionManufacturing />
                <p className="font-light">Dział</p>
              </div>
              <div></div>
            </div>
          </div>
        </AccordionSummary>
        <AccordionDetails>
          <div className="mb-5">
            <h2 className="font-semibold text-2xl">Opis</h2>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit.
              Suspendisse malesuada lacus ex, sit amet blandit leo lobortis
              eget.
            </p>
          </div>
          <form>
            <div className="flex gap-5 flex-wrap">
              {checkList.map((checkItem) => (
                <div key={checkItem.category}>
                  <p className="font-bold text-xl">{checkItem.category}</p>
                    <FormGroup>
                      {checkItem.duties.map((duty, index) => (
                        <FormControlLabel
                          key={index}
                          control={<Checkbox />}
                          label={duty}
                        />
                      ))}
                    </FormGroup>
                </div>
              ))}
            </div>
          </form>
          <div>
            <Button>Więcej szczegółów</Button>
          </div>
        </AccordionDetails>
      </Accordion>
    </div>
  );
};

export default BreakdownShortSummary;
