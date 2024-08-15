
import './Login.css';
import { FaUser } from "react-icons/fa";
import { RiLockPasswordFill } from "react-icons/ri";

// src/components/Login.js
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/users/signIn', formData);
      alert('Login successful');
      // Handle successful login (e.g., redirect or store user info)
    } catch (error) {
      console.error('Error during login:', error);
      alert('Login failed');
    }
  };

  return (
    <div className="login-container">
      
      <form onSubmit={handleSubmit}>
        <h2>Login</h2>
        <div className="input-box">
            <input
            type="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
            required
            />
            <FaUser className='icon' />
        </div>
        
        <div className="input-box">
            <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
            />
            <RiLockPasswordFill className='icon'  />
        </div> 

        <div className="remember-forget">
            <label> <input type="checkbox"/>Remember me</label>
            <a href="#">Forget password</a>
        </div>
        <button type="submit">Login</button>

        <div className="register-link">
            <p>
                Don't have an account? <Link to="/signup">Register here</Link>
            </p>
        </div>
      
      </form>
    </div>
  );
};

export default Login;
