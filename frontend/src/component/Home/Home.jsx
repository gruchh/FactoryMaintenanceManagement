import React from "react";
import "./Home.css";
import MultiItemCarousel from "./MultiItemCarousel";
import { breakdownExamples } from "../Breakdown/BreakdownExamples";
import BreakdownCard from "../Breakdown/BreakdownCard";

const Home = () => {
  return (
    <div>
      <section className="banner z-50 relative flex flex-col justify-center items-center">
        <div className="w-[50vw] z-10 text-center ">
          <p className="text-2xl lg:text-6xl font-bold z-10 py-5">
            Maintenance service
          </p>
          <p className="z-10 text-gray-400 text-lg lg:text-2xl">
            Future starts today, not tomorrow
          </p>
        </div>
        <div className="cover absolute top-0 left-0 right-0"></div>
        <div className="fadout"></div>
      </section>
      <section className="p-10 lg:py-10 lg:px-20 w-[100vw] lg:w-[80vw] mx-auto items-center ">
        <MultiItemCarousel />
      </section>
      <section className="px-5 lg:px-20">
        <div>
          {breakdownExamples.map((breakdown) => (
            <BreakdownCard
              title={breakdown.title}
              description={breakdown.description}
            />
          ))}
        </div>
      </section>
    </div>
  );
};

export default Home;