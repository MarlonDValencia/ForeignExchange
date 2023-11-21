import axios from "axios";
import React, { useEffect, useState } from "react"
import './assets/index.css'

export default function App() {
  const [exchanges, setExchanges] = useState(0)
  const [abbreviations, setAbbreviations] = useState([])
  const [converted, setConverted] = useState("")
  const globalUrl = 'https://foreign-exchange-project.fly.dev/api/foreign-exchange'

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
    <div className="min-h-screen flex items-center justify-center">
      <div className="form grid">
      <div className="flex justify-center items-center">
        <h1 className="text-violet-500">
          Foreign Exchanges
        </h1>
      </div>
      <form className="flex justify-center items-center" onSubmit={(e) => {
        e.preventDefault();
        const firstExchange = document.getElementById('firstExchange').value;
        const amount = document.getElementById('amount').value;
        const secondExchange = document.getElementById('secondExchange').value;
        const coin = setConverted(secondExchange)
        getConversion(amount, firstExchange, secondExchange);
      }}>
        <select className="my-4 mx-2 px-4 py-3 rounded-full border-2 border-black-400" name="Primer divisa" id="firstExchange">
        {abbreviations.map((item) => (
        <option key={item.fullname} value={item.abbreviation}>
          {item.fullname}
        </option>
        ))}
        </select>
        <p>To</p>
        <select className="my-2 mx-2 px-4 py-3 rounded-full border-2 border-black-400" name="Primer divisa" id="secondExchange">
        {abbreviations.map((item) => (
        <option key={item.fullname} value={item.abbreviation}>
          {item.fullname}
        </option>
        ))}
        </select>
        <button className="my-2 mx-2 px-2.5 py-2.5 text-white bg-violet-500 hover:bg-violet-600 active:bg-violet-700 focus:outline-none focus:ring focus:ring-violet-300 rounded-full" onClick={getAbbreviations}>
          Convert
        </button>
      </form>
      <input className="my-2 mx-2 px-4 py-3 rounded-full border-2 border-black-400 " type="number" id="amount"/>
      <div className="my-2 mx-2 px-4 py-3 rounded-full border-2 border-black-400">
      {exchanges && converted &&<p>El valor total es: {exchanges.toFixed(2)} {converted}</p>}
      </div>
      </div>
      
      </div>
    </>
  )
}