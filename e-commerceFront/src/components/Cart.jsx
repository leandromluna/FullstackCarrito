import React, { useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { CartContext } from './CartContext';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';
import './Cart.css';
import axios from 'axios';

function Cart({ fechaEspecial, rolUsuario, creationDate }) {
  const { cart, removeFromCart, updateQuantity, clearCart } = useContext(CartContext);
  const navigate = useNavigate();

  const cartType = rolUsuario === 'VIP' 
    ? 'VIP' 
    : (fechaEspecial === 'SPECIAL_DATE' ? 'SPECIAL_DATE' : 'COMMON');
    console.log("rolUsuario en HomePage:", rolUsuario);  

  useEffect(() => {}, [fechaEspecial, rolUsuario]);

  const calculateTotal = (cart) => {
    return cart.reduce((total, item) => total + item.price * item.quantity, 0).toFixed(2);
  };

  const calculateTotalItems = (cart) => {
    return cart.reduce((total, item) => total + item.quantity, 0);
  };

  const getBonificationMessage = () => {
    const totalItems = calculateTotalItems(cart);

    if (totalItems >= 10 && rolUsuario === 'VIP') {
      return '*Se bonificará 1 item y $500 del total';
    } else if (totalItems >= 10 && fechaEspecial === 'SPECIAL_DATE') {
      return '*Se bonificará $300 del total';
    } else if (totalItems >= 10 && cartType === 'COMMON') {
      return '*Se bonificará $100 del total';
    } else if (totalItems === 4) {
      return '*Se bonificará un 25% del total';
    }
    return '';
  };

  const handleBuy = async () => {
    const token = localStorage.getItem('jwt');
    const total = calculateTotal(cart);

    const finalCreationDate = creationDate ? creationDate : new Date().toISOString(); 

    const cartDetails = cart.map(item => ({
      product: { id: item.id },
      amount: item.quantity,
      totalPrice: (item.price * item.quantity).toFixed(2)
    }));

    try {
      const response = await axios.post('http://localhost:8080/cart/purchase', {
        cartType,
        total,
        creationDate: finalCreationDate, 
        cartDetails
      }, {
        headers: { Authorization: `Bearer ${token}` }
      });

      alert(`${response.data}`);

      clearCart();

      navigate('/products', { state: { purchased: true } });
      
    } catch (error) {
      console.error('Error during purchase:', error);
      alert('There was an issue processing your purchase. Please try again.');
    }
  };

  const bonificationMessage = getBonificationMessage();

  return (
    <div className="cart">
      <h2>Carrito</h2>
      {cart.length === 0 ? (
        <p>Your cart is empty</p>
      ) : (
        cart.map(item => (
          <div key={item.id} className="cart-item">
            <img src={item.imageUrl} alt={item.nameProduct} className="cart-item-image" />
            <div className="cart-item-info">
              <h3>{item.nameProduct}</h3>
              <p>Price: ${(item.price * item.quantity).toFixed(2)}</p>
              <p>Amount: {item.quantity}</p>
              <div className="cart-item-actions">
                <button 
                  onClick={() => updateQuantity(item.id, item.quantity - 1)} 
                  disabled={item.quantity <= 1}
                >
                  -
                </button>
                <button 
                  onClick={() => updateQuantity(item.id, item.quantity + 1)} 
                  disabled={item.quantity >= item.stock}
                >
                  +
                </button>
                <button onClick={() => removeFromCart(item.id)}>Remove</button>
              </div>
            </div>
          </div>
        ))
      )}
      {cart.length > 0 && (
        <>
          <div className="cart-total">Total: ${calculateTotal(cart)}</div>
          <p>TIPO: {cartType}</p> 
          
          {bonificationMessage && (
            <p className="bonification-message">{bonificationMessage}</p>
          )}

          <div className="cart-actions">
            <button className="checkout-button" onClick={handleBuy}>Buy</button>
            <button className="clear-cart-button" onClick={clearCart}>
              <FontAwesomeIcon icon={faTrash} className="trash-icon" />
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default Cart;
