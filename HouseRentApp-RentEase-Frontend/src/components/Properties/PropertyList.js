// src/components/Properties/PropertyList.js
import React, { useState, useEffect } from 'react';
import { getProperties } from '../../services/propertyService';



function PropertyList() {
    const [properties, setProperties] = useState([]);

    useEffect(() => {
        getProperties()
            .then(res => setProperties(res.data))
            .catch(err => console.error(err));
    }, []);

    return (
        <div>
            <h2>Property List</h2>
            <div>
                {properties.map(property => (
                    <div key={property.id}>
                        <h3>{property.address}</h3>
                        <p>{property.area}</p>
                        <p>{property.city}</p>
                        <p>{property.state}</p>
                        <p>{property.description}</p>
                        <p>{property.amenities}</p>
                        <p>{property.rent}</p>
                        <button>View Details</button>
                    </div>
                ))}
            </div>
            
        </div>
    );
}

export default PropertyList;
