import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Login from '../components/Login';
import './LoginPage.css'; 

function LoginPage({ onLoginSuccess }) {
  const navigate = useNavigate();


  useEffect(() => {
    const token = localStorage.getItem('jwt');
    if (token) {
      navigate('/products');  
    }
  }, [navigate]);

  const handleLoginSuccess = (fecha, rol) => {
  onLoginSuccess(fecha, rol);
    navigate('/products'); 
  };

  return (
    <div className="container"> {}
      <h1>Login</h1>
      <Login onLoginSuccess={handleLoginSuccess} />
    </div>
  );
}

export default LoginPage;
