import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';
import Products from '../components/Products';
import Cart from '../components/Cart';
import { CartContext } from '../components/CartContext'; 
import './HomePage.css'; 
import Navbar from '../components/Navbar'; 
import axios from 'axios';
import dayjs from 'dayjs'; 

function HomePage({ fechaEspecial, rolUsuario, onUpdateFecha }) {  
  const { clearCart } = useContext(CartContext); 
  const [isCartOpen, setIsCartOpen] = useState(false);
  const [selectedDate, setSelectedDate] = useState('');  
  const [creationDate, setCreationDate] = useState(new Date().toISOString()); 

  const navigate = useNavigate();

  const handleLogout = () => {
    clearCart();
    localStorage.removeItem('jwt'); 
    navigate('/login');
  };

  const toggleCart = () => {
    setIsCartOpen(!isCartOpen);
  };

  const handleDateChange = async () => {
    try {
      const token = localStorage.getItem('jwt');
      const formattedDate = dayjs(selectedDate).format('DD/MM/YYYY');

      const specialDateResponse = await axios.post('http://localhost:8080/specialdates/validate', {
        date: formattedDate
      }, {
        headers: { Authorization: `Bearer ${token}` }
      });

      const fechaEspecialMsg = specialDateResponse.data === "It's a special date." ? "SPECIAL_DATE" : "COMMON";
      
      const userResponse = await axios.get('http://localhost:8080/user', {
        headers: { Authorization: `Bearer ${token}` }
      });

      onUpdateFecha(fechaEspecialMsg, userResponse.data.rol);
      
      alert(`Fecha seleccionada: ${formattedDate}, Tipo: ${fechaEspecialMsg}, Rol: ${userResponse.data.rol}`);

      if (selectedDate) {
        setCreationDate(dayjs(selectedDate).toISOString());
      } else {
        setCreationDate(new Date().toISOString()); 
      }
    } catch (err) {
      alert('Error al validar la fecha. Inténtalo de nuevo.');
    }
  };

  return (
    <div>
      <div className="date-picker-container">
        <input 
          type="date" 
          value={selectedDate} 
          onChange={(e) => setSelectedDate(e.target.value)} 
        />
        <button onClick={handleDateChange}>Cambiar Fecha</button>
      </div>

      <button className="logout-button" onClick={handleLogout}>
        Logout
      </button>

      <div className="header">
        <h1 className="title">Bienvenido al sitio E-Commerce</h1>
      </div>

      <Navbar /> 

      <Products />
      
      <button className="toggle-cart-button" onClick={toggleCart}>
        <FontAwesomeIcon icon={faShoppingCart} className="cart-icon" />
      </button>

      {isCartOpen && <Cart fechaEspecial={fechaEspecial} rolUsuario={rolUsuario} creationDate={creationDate} />}
    </div>
  );
}

export default HomePage;
