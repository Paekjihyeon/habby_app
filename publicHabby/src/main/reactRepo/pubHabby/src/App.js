import './App.css';
import React, { useEffect } from "react";
import axios from "axios";

function App() {

  useEffect(() => {
  axios
    .get("/api/hello")
    .then((response) => {console.log(response)})
    .catch((error) => console.log("???" + error));
  }, []);

  return <div>백엔드에서 가져온 데이터입니다 </div>;
}

export default App;
