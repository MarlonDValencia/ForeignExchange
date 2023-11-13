import axios from "axios";
import React from "react"
export default function App() {
  const getForeignExchange = async () => {
    try{
      const url = 'http://localhost:8080/api/foreign-exchange/findAll';
      const response = await axios.get(url,{headers: {
          'Access-Control-Allow-Origin': 'http://localhost:5173'
        }});
      console.log(response)
    } catch(error){
      console.log(error);
    }
  }

  return (
    <>
      <div> 
        <b5>
          Foreign Exchanges
        </b5>
      </div>
      <div>
        <button onClick={getForeignExchange}>
          Get All Exchanges
        </button>
      </div>
    </>
  )
}