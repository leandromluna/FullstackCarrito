import React, { createContext, useState } from 'react';

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  const addToCart = (product) => {
    const existingProduct = cart.find(item => item.id === product.id);
    if (existingProduct) {
      if (existingProduct.quantity < product.stock) {
        setCart(cart.map(item =>
          item.id === product.id
            ? { ...existingProduct, quantity: existingProduct.quantity + 1 }
            : item
        ));
      }
    } else {
      setCart([...cart, { ...product, quantity: 1 }]);
    }
  };

  const removeFromCart = (id) => {
    setCart(cart.filter(item => item.id !== id));
  };

  const updateQuantity = (id, quantity) => {
    const product = cart.find(item => item.id === id);
    if (quantity <= product.stock && quantity >= 1) {
      setCart(cart.map(item =>
        item.id === id
          ? { ...item, quantity }
          : item
      ));
    } else if (quantity < 1) {
      removeFromCart(id);
    }
  };

  const clearCart = () => {
    setCart([]); 
    };

  return (
    <CartContext.Provider value={{ cart, addToCart, removeFromCart, updateQuantity, clearCart}}>
      {children}
    </CartContext.Provider>
  );
};
