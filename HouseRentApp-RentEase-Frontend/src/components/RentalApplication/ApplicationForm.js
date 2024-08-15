// src/components/RentalApplication/ApplicationForm.js
import React, { useState } from 'react';
import { sendApplication } from '../../services/applicationService';

function ApplicationForm({ propertyId }) {
    const [formData, setFormData] = useState({
        propertyId: propertyId,
        message: ''
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        sendApplication(formData)
            .then(res => {
                console.log(res.data);
                // Redirect to tenant dashboard or show confirmation
            })
            .catch(err => console.error(err));
    };

    return (
        <form onSubmit={handleSubmit}>
            <textarea name="message" placeholder="Message" onChange={handleChange} required />
            <button type="submit">Submit Application</button>
        </form>
    );
}

export default ApplicationForm;
