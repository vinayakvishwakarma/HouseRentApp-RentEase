// src/components/Dashboard/LandlordDashboard.js
import React, { useState, useEffect } from 'react';
import { getProperties, deleteProperty } from '../../services/propertyService';
import { getAllRentalApplicationByPropertyId, approveRentalApplication } from '../../services/applicationService';
import { useNavigate } from 'react-router-dom';
import './LandlordDashboard.css';

const LandlordDashboard = () => {
    const [properties, setProperties] = useState([]);
    const [rentalApplications, setRentalApplications] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        handleGetProperties();
    }, []);

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
        if (!data) {
            console.error('No landlord ID found in session storage!');
            alert('Please log in to continue.');
            return;
        }
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

    const handleGetRentalApplications = async (propertyId) => {
        try {
            const applications = await getAllRentalApplicationByPropertyId(propertyId);
            console.log('Fetched Applications:', applications);  // Check the data structure here
            setRentalApplications(prevState => ({ ...prevState, [propertyId]: applications }));
        } catch (error) {
            console.error('Error fetching rental applications:', error);
            alert('Failed to retrieve rental applications.');
        }
    };

    const handleStatusChange = (e, propertyId, applicationId) => {
        const newStatus = e.target.value;
        setRentalApplications(prevState => {
            const updatedApplications = { ...prevState };
            const propertyApplications = updatedApplications[propertyId];

            if (propertyApplications) {
                const applicationIndex = propertyApplications.findIndex(app => app.id === applicationId);
                if (applicationIndex > -1) {
                    updatedApplications[propertyId][applicationIndex].status = newStatus;
                }
            }
            return updatedApplications;
        });
    };

    const handleSubmitApplication = async (propertyId, applicationId, status) => {
        const data = sessionStorage.getItem('user');
        if (!data) {
            console.error('No landlord ID found in session storage!');
            alert('Please log in to continue.');
            return;
        }
        const user = JSON.parse(data);
        const landlordId = user?.data?.id;

        if (!landlordId) {
            console.error('Landlord ID is not available!');
            alert('Landlord ID is missing.');
            return;
        }

        try {
            await approveRentalApplication(landlordId, applicationId, status);
            alert('Application status updated successfully.');
        } catch (error) {
            console.error('Error updating application status:', error);
            alert('Failed to update application status.');
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
            <div>
                <button onClick={handleAddProperty} className="btn btn-primary mb-3">
                    Add Property
                </button>
                <button onClick={handleGetProperties} className="btn btn-secondary mb-3">
                    Get Properties
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
                                    <button 
                                        onClick={() => handleGetRentalApplications(property.id)} 
                                        className="btn btn-info">
                                        Get Rental Applications
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}

            {Object.keys(rentalApplications).map((propertyId) => (
                <div key={propertyId}>
                    <h3>Rental Applications for Property ID: {propertyId}</h3>
                    <table className="rental-application-table" border={1}>
                        <thead>
                            <tr>
                                <th>Tenant ID</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone Number</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {rentalApplications[propertyId]?.map((application) => (
                                <tr key={application.id}>
                                    <td>{application.tenant.id}</td>
                                    <td>{application.tenant.firstName}</td>
                                    <td>{application.tenant.email}</td>
                                    <td>{application.tenant.phone}</td>
                                    <td>
                                        <select
                                            value={application.status}
                                            onChange={(e) => handleStatusChange(e, propertyId, application.id)}
                                            className="dropdown"
                                        >
                                            <option value="PENDING">Pending</option>
                                            <option value="APPROVED">Approved</option>
                                            <option value="CANCELLED">Cancelled</option>
                                        </select>
                                    </td>
                                    <td>
                                        <button 
                                            onClick={() => handleSubmitApplication(propertyId, application.id, application.status)} 
                                            className="btn btn-submit">
                                            Submit
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            ))}
        </div>
    );
};

export default LandlordDashboard;


