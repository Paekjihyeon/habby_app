import axios from "axios";


//TOBE :: 에러를 다룰 수 있는 API 생성 


const DOMAIN = "localhost:8080";

async function GetAxios(url){
  try {
    console.log(url);
    const response = await axios.get(DOMAIN + url);
    return response;
  } catch (error) {
    return error;
  }
};

async function PostAxios(url, data){
  try {
    const response = await axios.post(DOMAIN +  url, data);
    return response;
  } catch (error) {
    console.log(error);
  }
};


export default {GetAxios, PostAxios};