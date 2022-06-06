// src/components/Settings/index.js

import React from 'react';
import './settings.css';
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import {Follow_requests, Profile_settings_form
} from "../../pages";

export const Settings = () => {

    return (
        <div>
            <Header />
            <Follow_requests />
            <h2>Profile settings</h2>
            <Profile_settings_form />
            <Footer />
        </div>
    )
}

export default Settings;