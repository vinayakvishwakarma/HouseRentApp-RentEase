// src/components/Properties/AddProperty.js
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addProperty } from '../../services/propertyService';
import './AddProperty.css';

const AddProperty = () => {
  const [property, setProperty] = useState({
    address: '',
    area: '',
    city: '',
    state: '',
    description: '',
    amenities: '',
    rent: 0,
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProperty({ ...property, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // Attempt to retrieve user data from session storage
    const data = sessionStorage.getItem('user');

    if (!data) {
      console.error('No user data found in session storage!');
      alert('Please log in to continue.');
      return;
    }
    let user;
    try {
      user = JSON.parse(data);
    } catch (error) {
      console.error('Error parsing user data from session storage:', error);
      alert('Failed to retrieve user information. Please log in again.');
      return;
    }

    // Extract landlordId from parsed data
    const landlordId = user?.data?.id;

    if (!landlordId) {
      console.error('No landlord ID found in parsed user data!');
      alert('Invalid landlord information. Please log in again.');
      return;
    }

    try {
      await addProperty(landlordId, property);
      navigate('/dashboard/LandlordDashboard');
    } catch (error) {
      console.error('Error adding property:', error);
      alert('Failed to add property. Please try again.');
    }
};



  return (
    <div className="container">
      <h2>Add Property</h2>
      <form onSubmit={handleSubmit}>
  <div className="form-group">
    <div className="form-item">
      <label>Address</label>
      <input
        type="text"
        name="address"
        className="form-control"
        value={property.address}
        onChange={handleChange}
        required
      />
    </div>
    <div className="form-item">
      <label>Area</label>
      <input
        type="text"
        name="area"
        className="form-control"
        value={property.area}
        onChange={handleChange}
        required
      />
    </div>
  </div>
  <div className="form-group">
    <div className="form-item">
      <label>City</label>
      <input
        type="text"
        name="city"
        className="form-control"
        value={property.city}
        onChange={handleChange}
        required
      />
    </div>
    <div className="form-item">
      <label>State</label>
      <input
        type="text"
        name="state"
        className="form-control"
        value={property.state}
        onChange={handleChange}
        required
      />
    </div>
  </div>
  <div className="form-group">
    <div className="form-item">
      <label>Description</label>
      <textarea
        name="description"
        className="form-control"
        value={property.description}
        onChange={handleChange}
      />
    </div>
    <div className="form-item">
      <label>Amenities</label>
      <input
        type="text"
        name="amenities"
        className="form-control"
        value={property.amenities}
        onChange={handleChange}
        required
      />
    </div>
  </div>
  <div className="form-group">
    <div className="form-item">
      <label>Rent</label>
      <input
        type="number"
        name="rent"
        className="form-control"
        value={property.rent}
        onChange={handleChange}
        required
      />
    </div>
  </div>
  <button type="submit" className="btn btn-primary">
    Add Property
  </button>
</form>

    </div>
  );
};

export default AddProperty;