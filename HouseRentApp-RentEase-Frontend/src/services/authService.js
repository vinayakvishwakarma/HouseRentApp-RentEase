// src/services/authService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/users';

export const register = (userData) => {
    return axios.post(`${API_URL}/register`, userData);
};

export const login = (credentials) => {
    return axios.post(`${API_URL}/signIn`, credentials);
};

// Other auth-related methods...
