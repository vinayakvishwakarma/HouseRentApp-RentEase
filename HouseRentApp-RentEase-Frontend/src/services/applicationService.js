// src/services/applicationService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/rentalApplication'; 

// Function to create a new rental application
export const createApplication = async ({ tenantId, propertyId ,rentalApplication}) => {
    try {
        return axios.post(`${API_URL}/${tenantId}/${propertyId}`, rentalApplication);
        
    } catch (error) {
        console.error('Error creating rental application:', error);
        throw error;
    }
};

// Function to get all rental applications by property ID
export const getAllRentalApplicationByPropertyId = async (propertyId) => {
    try {
        const response = await axios.get(`${API_URL}/${propertyId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching rental applications:', error);
        throw error;
    }
};

// Function to approve or cancel a rental application
export const approveRentalApplication = async (landlordId, rentalId, status) => {
    try {
        const response = await axios.put(`${API_URL}/${landlordId}/${rentalId}/${status}`, {
            landlordId,rentalId,
            status,
        });
        return response.data;
    } catch (error) {
        console.error('Error updating rental application status:', error);
        throw error;
    }
};

// Function to get all rental applications by tenant ID
export const getAllRentalApplicationByTenantId = async (tenantId) => {
    try {
        const response = await axios.get(`${API_URL}/prop/${tenantId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching rental applications by tenant ID:', error);
        throw error;
    }
};

const applicationService = {
    createApplication,
    getAllRentalApplicationByPropertyId,
    approveRentalApplication,
    getAllRentalApplicationByTenantId, // Include getAllProperties in the export
};

export default applicationService;