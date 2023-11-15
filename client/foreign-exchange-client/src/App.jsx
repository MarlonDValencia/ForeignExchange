import axios from "axios";
import React, { useState } from "react"
export default function App() {
  const [exchanges, setExchanges] = useState(0)
  const getConversion = async (exchange, exchange1, exchange2) => {
    try {
      const url = 'http://localhost:8080/api/foreign-exchange/convert';
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

  return (
    <>
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
          <option value="EUR">EUR</option>
          <option value="COP">COP</option>
        </select>
        <input type="number" id="amount"/>
        <p>To</p>
        <select name="Segunda divisa" id="secondExchange">
          <option value="EUR">EUR</option>
          <option value="COP">COP</option>
        </select>
        <button>
          Convert
        </button>
      </form>
      {exchanges && <p>El valor total es: {exchanges} </p>}
    </>
  )
}