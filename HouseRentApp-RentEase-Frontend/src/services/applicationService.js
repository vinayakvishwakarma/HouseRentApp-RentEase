// src/services/applicationService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/rentalApplication';

export const sendApplication = (applicationData) => {
    return axios.post(API_URL, applicationData);
};

export const getApplications = () => {
    return axios.get(API_URL);
};

// Other application-related methods...
