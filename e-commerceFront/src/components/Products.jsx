import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import { CartContext } from './CartContext'; 
import { useLocation } from 'react-router-dom'; 

function Products() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const { addToCart } = useContext(CartContext);
  const location = useLocation(); 

  useEffect(() => {
    const fetchProducts = async () => {
      const token = localStorage.getItem('jwt');
      try {
        const response = await axios.get('http://localhost:8080/product/all', {
          headers: { Authorization: `Bearer ${token}` }
        });
        setProducts(response.data);
      } catch (error) {
        console.error('Error fetching products', error);
      }
      setLoading(false);
    };

    fetchProducts();
  }, []);

  useEffect(() => {
    if (location.state?.purchased) {
      const fetchProducts = async () => {
        const token = localStorage.getItem('jwt');
        try {
          const response = await axios.get('http://localhost:8080/product/all', {
            headers: { Authorization: `Bearer ${token}` }
          });
          setProducts(response.data);
        } catch (error) {
          console.error('Error fetching products', error);
        }
      };
      fetchProducts();
    }
  }, [location.state]);

  const handleAddToCart = (product) => {
    addToCart(product);
  };

  if (loading) return <div>Loading...</div>;

  return (
    <div className="products-container">
      {products.map((product) => (
        <div className="product-card" key={product.id}>
          <img className="product-image" src={product.imageUrl} alt={product.nameProduct} />
          <h2 className="product-name">{product.nameProduct}</h2>
          <p className="product-price">${product.price}</p>
          <p className="product-stock">Stock: {product.stock > 0 ? product.stock : 'Sin Stock'}</p>
          <button 
            onClick={() => handleAddToCart(product)} 
            disabled={product.stock === 0} 
          >
            {product.stock === 0 ? 'Sin Stock' : 'Add to Cart'}
          </button>
        </div>
      ))}
    </div>
  );
}

export default Products;
