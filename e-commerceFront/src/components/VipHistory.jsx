import React, { useEffect, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { CartContext } from './CartContext';
import './VipHistory.css';
import Navbar from '../components/Navbar'; 


const VipHistory = () => {
  const [vipHistory, setVipHistory] = useState([]);
  const navigate = useNavigate();
  const { clearCart } = useContext(CartContext);

  useEffect(() => {
    clearCart();

    const fetchVipHistory = async () => {
      const token = localStorage.getItem('jwt');
      try {
        const response = await fetch('http://localhost:8080/vip/history', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

        if (!response.ok) {
          throw new Error('Error fetching VIP history');
        }

        const data = await response.json();
        setVipHistory(data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchVipHistory();
  }, []); 

  return (
    <div className="vip-history-container">
      <Navbar />
      <h1>Historial de Clientes Vips</h1>
      <table className="vip-history-table">
        <thead>
          <tr>
            <th>Email</th>
            <th>VIP Status</th>
            <th>Start VIP</th>
            <th>End VIP</th>
          </tr>
        </thead>
        <tbody>
          {vipHistory.map(vip => (
            <tr key={vip.id}>
              <td>{vip.user.email}</td>
              <td>VIP</td>
              <td>{new Date(vip.startVip).toLocaleDateString()}</td>
              <td>{vip.endVip ? new Date(vip.endVip).toLocaleDateString() : '-'}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button className="back-button" onClick={() => navigate('/products')}>Regresar al Home</button>
    </div>
  );
};

export default VipHistory;
