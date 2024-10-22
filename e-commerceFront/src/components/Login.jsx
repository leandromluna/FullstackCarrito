import React, { useState } from 'react';
import axios from 'axios';
import dayjs from 'dayjs';  

function Login({ onLoginSuccess }) {
  const [usuario, setUsuario] = useState('');
  const [clave, setClave] = useState('');
  const [error, setError] = useState('');
  const [fechaEspecial, setFechaEspecial] = useState(null); 
  const [rolUsuario, setRolUsuario] = useState(null);      

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/login', { 
        usuario, 
        clave 
      });
  
      const token = response.data.jwtToken;
      localStorage.setItem('jwt', token);
  
      const fechaHoy = dayjs().format('DD/MM/YYYY');
      const specialDateResponse = await axios.post('http://localhost:8080/specialdates/validate', {
        date: fechaHoy
      }, {
        headers: { Authorization: `Bearer ${token}` }
      });
  
      const fechaEspecialMsg = specialDateResponse.data === "It's a special date." ? "SPECIAL_DATE" : "COMMON";
  
      const userResponse = await axios.get('http://localhost:8080/user', {
        headers: { Authorization: `Bearer ${token}` }
      });
  
      onLoginSuccess(fechaEspecialMsg, userResponse.data.rol);  
    } catch (err) {
      setError('Login failed. Please try again.');
    }
  };
  
  

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input 
          type="text" 
          placeholder="Usuario" 
          value={usuario} 
          onChange={(e) => setUsuario(e.target.value)} 
          required 
        />
        <input 
          type="password" 
          placeholder="Clave" 
          value={clave} 
          onChange={(e) => setClave(e.target.value)} 
          required 
        />
        <button type="submit">Login</button>
        {error && <p className="error">{error}</p>}

        {/* Muestra las variables guardadas después del login */}
        {fechaEspecial && <p>Fecha Especial: {fechaEspecial}</p>}
        {rolUsuario && <p>Rol Usuario: {rolUsuario}</p>}
      </form>
    </div>
  );
}

export default Login;
