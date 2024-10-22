import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css'; 

const Navbar = () => {
  return (
    <nav className="navbar">
      <Link to="/products" className="nav-link">PRODUCTOS</Link>
      <Link to="/vip-history" className="nav-link">HISTORIAL DE CLIENTES VIP</Link>
      <Link to="/cart-history" className="nav-link">MIS COMPRAS</Link>
    </nav>
  );
};

export default Navbar;
