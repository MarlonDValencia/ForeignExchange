import axios from "axios";
import React, { useEffect, useState } from "react"
export default function App() {
  const [exchanges, setExchanges] = useState(0)
  const [abbreviations, setAbbreviations] = useState([])
  const globalUrl = 'http://localhost:8080/api/foreign-exchange'

useEffect(() => {
  const fetchData = async () => {
    try {
      const url = globalUrl + '/abbreviation';
      const response = await axios.get(url, {
        headers: {
          'Access-Control-Allow-Origin': 'http://localhost:5173'
        }
      });
      setAbbreviations(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  fetchData();
},[])

  const getConversion = async (exchange, exchange1, exchange2) => {
    try {
      const url = globalUrl+'/convert';
      const response = await axios.get(url, {
        headers: {
          'Access-Control-Allow-Origin': 'http://localhost:5173'
        }, params: {
          firstExchange: exchange1,
          secondExchange: exchange2,
          amount: exchange
        }
      });
      setExchanges(response.data)
    } catch (error) {
      console.log(error);
    }
  }

  const getAbbreviations = async() => {
    try {
      const url = globalUrl+'/abbreviation';
      const response = await axios.get(url, {
        headers: {
          'Access-Control-Allow-Origin': 'http://localhost:5173'
        }
      });
      setAbbreviations(response.data)
    }catch(error){
      console.log(error)
    }
  }

  return (
    <>
    <div>
      <div>
        <h1>
          Foreign Exchanges
        </h1>
      </div>
      <div>
      </div>
      <form onSubmit={(e) => {
        e.preventDefault();
        const firstExchange = document.getElementById('firstExchange').value;
        const amount = document.getElementById('amount').value;
        const secondExchange = document.getElementById('secondExchange').value;
        getConversion(amount, firstExchange, secondExchange);
      }}>
        <select name="Primer divisa" id="firstExchange">
        {abbreviations.map((item) => (
        <option key={item.fullname} value={item.abbreviation}>
          {item.fullname}
        </option>
        ))}
        </select>
        <input type="number" id="amount"/>
        <p>To</p>
        <select name="Primer divisa" id="secondExchange">
        {abbreviations.map((item) => (
        <option key={item.fullname} value={item.abbreviation}>
          {item.fullname}
        </option>
        ))}
        </select>
        <button onClick={getAbbreviations}>
          Convert
        </button>
      </form>
      {exchanges && <p>El valor total es: {exchanges} </p>}
      </div>
    </>
  )
}