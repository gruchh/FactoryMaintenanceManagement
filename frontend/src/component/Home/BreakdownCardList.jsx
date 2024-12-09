import { breakdownExamples } from "./BreakdownExamples";
import BreakdownCard from "./BreakdownCard";

const BreakdownCardList = () => {
  return (
    <>
    <h1 className="text-2xl font-semibold text-gray-400 pb-5">Ostatnie awarie</h1>
    <div className="flex flex-wrap items-center justify-around">
      {breakdownExamples.map((breakdown, index) => (
        <BreakdownCard
          key={index}
          title={breakdown.title}
          description={breakdown.description}
        />
      ))}
    </div>
    </>
  );
};

export default BreakdownCardList;
