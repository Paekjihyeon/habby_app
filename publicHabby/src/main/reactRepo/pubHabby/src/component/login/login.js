import axios from "axios";
import { useState } from "react";
// import GetAxios from "../../common/axiox"; // 이렇게 수정해야 함

const UserLogin = () => {
  
  /**로그인 정보 그릇 */
  const [loginInfo, setLoginInfo] = useState({
    userId : "",
    password : ""
  });

  const submit = async()=> {
    try {
        var formdata = new FormData();
        formdata.append("userId", loginInfo.userId);
        formdata.append("password", loginInfo.password);
        axios
          .post("http://localhost:8081/api/login", formdata)
          .then((response) => {
            console.log("response ::", response.data);
          })
          .catch((error) => console.log(error));
        
        // const response = await axios.post("http://localhost:8081/login", formdata);
        // return response;
      // const response = await GetAxios("/api/login");
      // console.log(response);
    } catch (error) {
      console.error(error);
    }
    
  }

  function ChangeLoginInfo(e){
    setLoginInfo({...loginInfo, [e.target.name] : e.target.value});
    
  }


  return (
    <div>
      <input type="text" name="userId" onChange={ChangeLoginInfo} placeholder="아이디" />
      <input type="password" name="password" onChange={ChangeLoginInfo} placeholder="비밀번호" />
      <input id="submit" onClick={submit} type="button" value="제출" />
    </div>
  );
}

export default UserLogin;