// src/components/RentalApplication/ApplicationList.js
import React, { useState, useEffect } from 'react';
import { getApplications } from '../../services/applicationService';

function ApplicationList() {
    const [applications, setApplications] = useState([]);

    useEffect(() => {
        getApplications()
            .then(res => setApplications(res.data))
            .catch(err => console.error(err));
    }, []);

    return (
        <div>
            <h2>Rental Applications</h2>
            <div>
                {applications.map(application => (
                    <div key={application.id}>
                        <p>Property: {application.propertyId}</p>
                        <p>Status: {application.status}</p>
                        <button>Approve</button>
                        <button>Reject</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ApplicationList;
