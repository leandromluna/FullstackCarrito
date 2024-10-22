import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import HomePage from './pages/HomePage';
import PurchaseHistory from './components/PurchaseHistory';
import { CartProvider } from './components/CartContext';
import VipHistory from './components/VipHistory';
import CartHistory from './components/CartHistory';
import Cart from './components/Cart';

function App() {
  const [fechaEspecial, setFechaEspecial] = useState(null);
  const [rolUsuario, setRolUsuario] = useState(null);

  const handleLoginSuccess = (fecha, rol) => {
    setFechaEspecial(fecha);
    setRolUsuario(rol);
  };

  const handleUpdateFecha = (fecha, rol) => {
    setFechaEspecial(fecha);
    setRolUsuario(rol);
  };

  return (
    <CartProvider>
      <Router>
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route 
            path="/login" 
            element={<LoginPage onLoginSuccess={handleLoginSuccess} />} 
          />
          <Route path="/vip-history" element={<VipHistory />} />
          <Route path="/cart-history" element={<CartHistory />} />
          <Route 
            path="/products" 
            element={<HomePage fechaEspecial={fechaEspecial} rolUsuario={rolUsuario} onUpdateFecha={handleUpdateFecha} />} 
          />
          <Route path="/history" element={<PurchaseHistory />} />
          <Route 
            path="/cart" 
            element={<Cart fechaEspecial={fechaEspecial} rolUsuario={rolUsuario} />} 
          />
        </Routes>
      </Router>
    </CartProvider>
  );
}

export default App;
