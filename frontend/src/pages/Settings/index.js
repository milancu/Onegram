// src/components/Settings/index.js

import React, {Component} from 'react';
import './settings.css';
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import {Follow_requests, Profile_settings_form
} from "../../pages";

export const Settings = () => {
    return (
        <div>
            <Header nickname="John D. Veloper"
                    profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>
            <Follow_requests />
            <h2>Profile settings</h2>
            <Profile_settings_form />
            <Footer />
        </div>
    )
}

export default Settings;