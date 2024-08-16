// src/components/Dashbord/Tenentdashboard.js
import React, { useState, useEffect } from 'react';
import { getAllProperties } from '../../services/propertyService';
import { createApplication } from '../../services/applicationService';
import { useNavigate } from 'react-router-dom';
import './TenantDashboard.css';

const TenantDashboard = () => {
    const [properties, setProperties] = useState([]);
    const [filteredProperties, setFilteredProperties] = useState([]);
    const [states, setStates] = useState([]);
    const [cities, setCities] = useState([]);
    const [areas, setAreas] = useState([]);
    const [selectedState, setSelectedState] = useState('');
    const [selectedCity, setSelectedCity] = useState('');
    const [selectedArea, setSelectedArea] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchProperties();
    }, []);

    const fetchProperties = async () => {
        try {
            const response = await getAllProperties();
            setProperties(response.data);
            setFilteredProperties(response.data);

            const uniqueStates = [...new Set(response.data.map(property => property.state))];
            setStates(uniqueStates);
        } catch (error) {
            console.error('Error fetching properties:', error);
            alert('Failed to retrieve properties.');
        }
    };

    const handleStateChange = (e) => {
        const state = e.target.value;
        setSelectedState(state);

        const filteredByState = properties.filter(property => property.state === state);
        setFilteredProperties(filteredByState);

        const uniqueCities = [...new Set(filteredByState.map(property => property.city))];
        setCities(uniqueCities);
        setSelectedCity('');
        setAreas([]);
        setSelectedArea('');
    };

    const handleCityChange = (e) => {
        const city = e.target.value;
        setSelectedCity(city);

        const filteredByCity = properties.filter(
            property => property.state === selectedState && property.city === city
        );
        setFilteredProperties(filteredByCity);

        const uniqueAreas = [...new Set(filteredByCity.map(property => property.area))];
        setAreas(uniqueAreas);
        setSelectedArea('');
    };

    const handleAreaChange = (e) => {
        const area = e.target.value;
        setSelectedArea(area);

        const filteredByArea = properties.filter(
            property =>
                property.state === selectedState &&
                property.city === selectedCity &&
                property.area === area
        );
        setFilteredProperties(filteredByArea);
    };

    const handleBookProperty = async (propertyId) => {
        const data = sessionStorage.getItem('user');
        if (!data) {
            console.error('No tenant ID found in session storage!');
            alert('Please log in to continue.');
            return;
        }
        const user = JSON.parse(data);
        const tenantId = user?.data?.id;
        

        // Define the rentalApplication object with necessary fields
        const rentalApplication = {
            // Add any necessary fields for your rental application
            // For example:
            propertyId: propertyId,
            tenantId: tenantId,
            status: 'PENDING' // Or whatever default status you need
            // Add other fields if required
        };

        

        try {
            await createApplication({ tenantId, propertyId, rentalApplication });
            alert('Rental application created successfully.');
        } catch (error) {
            console.error('Error creating rental application:', error);
            alert('Failed to create rental application.');
        }
    };

    const handleLogout = () => {
        sessionStorage.removeItem('user');
        alert('You have been logged out.');
        navigate('/login');
    };

    return (
        <div className="tenant-container">
            <div className="logout-container">
                <button onClick={handleLogout} className="btn btn-logout">
                    Logout
                </button>
            </div>
            <h2>Tenant Dashboard</h2>

            <div className="filters">
                <select value={selectedState} onChange={handleStateChange} className="dropdown">
                    <option value="">Select State</option>
                    {states.map((state) => (
                        <option key={state} value={state}>
                            {state}
                        </option>
                    ))}
                </select>

                <select value={selectedCity} onChange={handleCityChange} className="dropdown" disabled={!selectedState}>
                    <option value="">Select City</option>
                    {cities.map((city) => (
                        <option key={city} value={city}>
                            {city}
                        </option>
                    ))}
                </select>

                <select value={selectedArea} onChange={handleAreaChange} className="dropdown" disabled={!selectedCity}>
                    <option value="">Select Area</option>
                    {areas.map((area) => (
                        <option key={area} value={area}>
                            {area}
                        </option>
                    ))}
                </select>
            </div>

            {filteredProperties.length > 0 && (
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
                        {filteredProperties.map((property) => (
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
                                        onClick={() => handleBookProperty(property.id)} 
                                        className="btn btn-primary">
                                        Book Property
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

export default TenantDashboard;
