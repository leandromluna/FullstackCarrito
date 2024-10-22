import React, { useState, useEffect } from 'react';
import axios from 'axios';

function PurchaseHistory() {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    const fetchHistory = async () => {
      const token = localStorage.getItem('jwt');
      try {
        const response = await axios.get('http://localhost:8080/purchases', {
          headers: { Authorization: `Bearer ${token}` }
        });
        setHistory(response.data);
      } catch (error) {
        console.error('Error fetching purchase history', error);
      }
    };

    fetchHistory();
  }, []);

  return (
    <div>
      <h2>Purchase History</h2>
      <ul>
        {history.map((purchase) => (
          <li key={purchase.id}>Order ID: {purchase.id}, Total: ${purchase.total}</li>
        ))}
      </ul>
    </div>
  );
}

export default PurchaseHistory;
