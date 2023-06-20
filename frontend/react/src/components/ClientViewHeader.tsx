import {FaBed, FaCheck, FaRegCalendarAlt} from "react-icons/fa";
import {RxDotsHorizontal} from "react-icons/rx";

export default function ClientViewHeader() {

    return (
        <>
            <div className="bg-blue-200 flex justify-center items-center h-20">
                <h2 className="text-2xl font-semibold">BOKA SÄNG</h2>
            </div>
            <div className="flex items-center justify-evenly pt-8">
        <span className="text-3xl text-green-500">
          <FaRegCalendarAlt/>
        </span>
                <span className="text-3xl text-gray-500">
          <RxDotsHorizontal/>
        </span>
                <span className="text-3xl text-yellow-400">
          <FaBed/>
        </span>
                <span className="text-3xl text-gray-500">
          <RxDotsHorizontal/>
        </span>
                <span className="text-3xl">
          <FaCheck/>
        </span>
            </div>
        </>
    )
}