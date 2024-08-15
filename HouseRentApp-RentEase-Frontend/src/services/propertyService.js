// src/services/propertyService.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/property';

// Fetch properties for a specific landlord
export const getProperties = (landlordId) => {
    return axios.get(`${API_URL}/prop/${landlordId}`);
};

// Fetch all properties
export const getAllProperties = () => {
    return axios.get(`${API_URL}`);
};

// Add a new property
export const addProperty = (id, property) => {
    return axios.post(`${API_URL}/${id}`, property);
};

// Delete a specific property by landlordId and propertyId
export const deleteProperty = (landlordId, propertyId) => {
    return axios.delete(`${API_URL}/${landlordId}/${propertyId}`);
};

const PropertyService = {
    addProperty,
    deleteProperty,
    getProperties,
    getAllProperties, // Include getAllProperties in the export
};

export default PropertyService;


// Other property-related methods...
