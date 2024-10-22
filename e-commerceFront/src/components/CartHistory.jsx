import React, { useEffect, useState, useContext } from 'react';
import { CartContext } from './CartContext';
import { useNavigate } from 'react-router-dom';
import './CartHistory.css'; 
import Navbar from '../components/Navbar'; 


const CartHistory = () => {
  const [cartHistory, setCartHistory] = useState([]);
  const [openDetail, setOpenDetail] = useState(null);
  const { clearCart } = useContext(CartContext);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCartHistory = async () => {
      clearCart(); 
      const token = localStorage.getItem('jwt');
      try {
        const response = await fetch('http://localhost:8080/cart/history', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });
        
        if (!response.ok) {
          throw new Error('Error fetching cart history');
        }

        const data = await response.json();
        setCartHistory(data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchCartHistory();
  }, []); 

  const toggleDetail = (id) => {
    setOpenDetail(openDetail === id ? null : id);
  };

  const handleBackToHome = () => {
    navigate('/products');
  };

  return (
    <div className="cart-history-container">
        <Navbar />
      <h1>Historial de Compras</h1>
      <table className="cart-history-table">
        <thead>
          <tr>
            <th>Fecha</th>
            <th>Carrito</th>
            <th>Total</th>
            <th>Detalles</th>
          </tr>
        </thead>
        <tbody>
          {cartHistory.map(cart => (
            <React.Fragment key={cart.id}>
              <tr onClick={() => toggleDetail(cart.id)}>
                <td>{new Date(cart.creationDate).toLocaleDateString()}</td>
                <td>{cart.cartType}</td>
                <td>${cart.total.toFixed(2)}</td>
                <td>
                  <button className="detail-button">Ver Detalles</button>
                </td>
              </tr>
              {openDetail === cart.id && (
                <tr className="cart-details">
                  <td colSpan="4">
                    <ul>
                      {cart.cartDetails.map(detail => (
                        <li key={detail.id}>
                          <strong>Nombre del producto:</strong> {detail.product.name}<br />
                          <strong>Cantidad:</strong> {detail.amount}<br />
                          <strong>Precio unitario:</strong> ${detail.product.price.toFixed(2)}<br />
                          <strong>Precio total:</strong> ${detail.totalPrice.toFixed(2)}
                        </li>
                      ))}
                    </ul>
                  </td>
                </tr>
              )}
            </React.Fragment>
          ))}
        </tbody>
      </table>
      <button className="back-button" onClick={handleBackToHome}>
        Regresar al Home
      </button>
    </div>
  );
};

export default CartHistory;
