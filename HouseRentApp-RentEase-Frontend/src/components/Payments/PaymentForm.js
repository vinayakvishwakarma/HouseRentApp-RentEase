// src/components/Payments/PaymentForm.js
import React, { useState } from 'react';
import { makePayment } from '../../services/paymentService';

function PaymentForm({ applicationId }) {
    const [formData, setFormData] = useState({
        applicationId: applicationId,
        amount: ''
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        makePayment(formData)
            .then(res => {
                console.log(res.data);
                // Redirect to tenant dashboard or show confirmation
            })
            .catch(err => console.error(err));
    };

    return (
        <form onSubmit={handleSubmit}>
            <input name="amount" placeholder="Amount" onChange={handleChange} required />
            <button type="submit">Make Payment</button>
        </form>
    );
}

export default PaymentForm;
