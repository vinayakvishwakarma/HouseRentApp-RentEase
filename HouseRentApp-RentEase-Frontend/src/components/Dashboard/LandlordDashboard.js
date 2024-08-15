import React, { useState } from 'react';
import { getProperties, deleteProperty } from '../../services/propertyService';
import { useNavigate } from 'react-router-dom';
import './LandlordDashboard.css';

const LandlordDashboard = () => {
    const [properties, setProperties] = useState([]);
    const navigate = useNavigate();

    const handleAddProperty = () => {
        navigate('/Properties/AddProperty');
    };

    const handleGetProperties = async () => {
        const data = sessionStorage.getItem('user');
        if (!data) {
            console.error('No landlord ID found in session storage!');
            alert('Please log in to continue.');
            return;
        }
        const user = JSON.parse(data);
        const landlordId = user?.data?.id;

        try {
            const response = await getProperties(landlordId);
            setProperties(response.data);
        } catch (error) {
            console.error('Error fetching properties:', error);
            alert('Failed to retrieve properties.');
        }
    };

    const handleDeleteProperty = async (propertyId) => {
        const data = sessionStorage.getItem('user');
        const user = JSON.parse(data);
        const landlordId = user?.data?.id;

        try {
            await deleteProperty(landlordId, propertyId);
            alert('Property deleted successfully.');
            setProperties(properties.filter(property => property.id !== propertyId));
        } catch (error) {
            console.error('Error deleting property:', error);
            alert('Failed to delete property.');
        }
    };

    const handleLogout = () => {
        sessionStorage.removeItem('user');
        alert('You have been logged out.');
        navigate('/login');
    };

    return (
        <div className="landlord-container">
            <div className="logout-container">
                <button onClick={handleLogout} className="btn btn-logout">
                    Logout
                </button>
            </div>
            <h2>Landlord Dashboard</h2>
            <div className="logout-container">
            <button onClick={handleAddProperty} className="btn btn-primary mb-3">
                Add Property
            </button>
            <button onClick={handleGetProperties} className="btn btn-secondary mb-3">
                Get Property
            </button>
            </div>
            

            {properties.length > 0 && (
                <table className="property-table" border={2}>
                    <thead>
                        <tr>
                            <th>Address</th>
                            <th>Area</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Description</th>
                            <th>Amenities</th>
                            <th>Rent</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {properties.map((property) => (
                            <tr key={property.id}>
                                <td>{property.address}</td>
                                <td>{property.area}</td>
                                <td>{property.city}</td>
                                <td>{property.state}</td>
                                <td>{property.description}</td>
                                <td>{property.amenities}</td>
                                <td>{property.rent}</td>
                                <td>
                                    <button 
                                        onClick={() => handleDeleteProperty(property.id)} 
                                        className="btn btn-danger">
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default LandlordDashboard;
