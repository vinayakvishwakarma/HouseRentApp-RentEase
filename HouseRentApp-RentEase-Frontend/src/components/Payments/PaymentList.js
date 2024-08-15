// src/components/Payments/PaymentList.js
import React, { useState, useEffect } from 'react';
import { getPayments } from '../../services/paymentService';

function PaymentList() {
    const [payments, setPayments] = useState([]);

    useEffect(() => {
        getPayments()
            .then(res => setPayments(res.data))
            .catch(err => console.error(err));
    }, []);

    return (
        <div>
            <h2>Payments</h2>
            <div>
                {payments.map(payment => (
                    <div key={payment.id}>
                        <p>Application ID: {payment.applicationId}</p>
                        <p>Amount: {payment.amount}</p>
                        <p>Status: {payment.status}</p>
                        <button>Mark as Received</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default PaymentList;
