import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Register from './components/Auth/Register';
import Login from './components/Auth/Login';
import TenantDashboard from './components/Dashboard/TenantDashboard';
import LandlordDashboard from './components/Dashboard/LandlordDashboard';
import AdminDashboard from './components/Dashboard/AdminDashboard';
import PropertyList from './components/Properties/PropertyList';
import PropertyDetail from './components/Properties/PropertyDetail';
import ApplicationForm from './components/RentalApplication/ApplicationForm';
import AddProperty from './components/Properties/AddProperty';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard/tenantDashboard" element={<TenantDashboard />} />
        <Route path="/dashboard/landlordDashboard" element={<LandlordDashboard />} />
        <Route path="/dashboard/adminDashboard" element={<AdminDashboard />} />
        <Route path="/Properties/AddProperty" element={<AddProperty />} />     
        <Route path="/properties" element={<PropertyList />} />
        <Route path="/property/:id" element={<PropertyDetail />} />
        <Route path="/apply/:propertyId" element={<ApplicationForm />} />
        {/* <Route path="/pay/:applicationId" element={<PaymentForm />} /> */}
        {/* Add a catch-all route */}
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </Router>
  );
}

export default App;
