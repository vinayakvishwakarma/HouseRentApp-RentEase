import './Login.css';
// src/components/Auth/Login.js
import React, { useState } from 'react';
import { FaUser } from "react-icons/fa";
import { RiLockPasswordFill } from "react-icons/ri";
import axios from 'axios';


import { useNavigate,Link } from 'react-router-dom'; 

function Login() {
    const [formData, setFormData] = useState({
        email: '',
        password: ''
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            const data = await axios.post('http://localhost:8080/users/signIn', formData)
            console.log("Login successful", data);
            alert('Login successful');
            
                sessionStorage.setItem('user', JSON.stringify(data));
                const role = data.data.role;
                if (role === 'TENANT') {
                    navigate('/dashboard/TenantDashboard');
                } else if (role === 'LANDLORD') {
                    navigate('/dashboard/LandlordDashboard');
                } else if (role === 'ADMIN') {
                    navigate('/dashboard/AdminDashboard');
                }
               
           }
            
            catch (error) {
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
                    Don't have an account? <Link to="/register">Register here</Link>
                </p>
            </div>
          
          </form>
        </div>
      );
}

export default Login;

