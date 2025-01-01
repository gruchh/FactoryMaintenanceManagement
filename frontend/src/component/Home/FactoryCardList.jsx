import { factoriesExamples } from "./FactoriesExamples"
import FactoryCard from "./FactoryCard"

const FactoryCardList = () => {
  return (
        <>
        <h1 className="text-2xl font-semibold text-gray-400 pb-5">Nasze fabryki</h1>
        <div className="flex flex-wrap items-center justify-around">
          {factoriesExamples.map((factory, index) => (
            <FactoryCard
              key={index}
              city={factory.city}
              activity={factory.scopeOfActivity}
              models={factory.models}
            />
          ))}
        </div>
        </>
  )
}

export default FactoryCardList