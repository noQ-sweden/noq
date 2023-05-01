import { useState, useEffect } from "react";
import noqLogo from "/noQ.png";
import axios from "axios";

function App() {
  const [message, setMessage] = useState("hello world");

  useEffect(() => {
    axios.get("")
      .then((response) => setMessage(response.data))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="h-screen flex flex-col justify-center items-center bg-gray-800 text-white">
      <div>
        <img src={noqLogo} className="h-72 w-72 mb-5" alt="noQ-Logo" />
      </div>
      <h1 className="text-4xl font-bold underline">{message}</h1>
    </div>
  );
}

export default App;
