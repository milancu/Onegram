// src.App.js

import React from 'react';
import './App.css';
import {Profile_dashboard, Feed} from "./pages";
import {Route, Routes} from "react-router-dom";

import {UserRoutes} from "./routes/UserRoutes.js";

export const App = () => {
    return (
        <div>
            <Routes>
                <Route exact path="/profile" element={<Profile_dashboard/>}/>
                <Route exact path="/" element={<Feed/>}/>
            </Routes>
        </div>
    )
}

export default App;
