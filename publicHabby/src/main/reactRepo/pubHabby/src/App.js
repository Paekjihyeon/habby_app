import './App.css';
import React, { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [str, setStr] = useState("");

  useEffect(() => {
  axios
    .get("http://localhost:8081/api/hello")
    .then((response) => {
      setStr(response.data);
    })
    .catch((error) => console.log(error));
  }, []);

  return <div>백엔드에서 가져온 데이터입니다 : {str}</div>;
}

export default App;
