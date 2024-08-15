// src/services/paymentService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/payments';

export const makePayment = (paymentData) => {
    return axios.post(`${API_URL}/{rentalAppId}`, paymentData);
};

export const getPayments = () => {
    return axios.get(`${API_URL}/{landlordId}`);
};

// Other payment-related methods...
