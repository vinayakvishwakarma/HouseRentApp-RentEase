// src/components/Properties/PropertyDetail.js
import React from 'react';

function PropertyDetail({ property }) {
    return (
        <div>
           <h3>{property.address}</h3>
                        <p>{property.area}</p>
                        <p>{property.city}</p>
                        <p>{property.state}</p>
                        <p>{property.description}</p>
                        <p>{property.amenities}</p>
                        <p>{property.rent}</p>
            
            <button>Apply for Rent</button>
        </div>
    );
}

export default PropertyDetail;
