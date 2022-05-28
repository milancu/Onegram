// src.App.js

import React from 'react';
import './App.css';
import {Profile_dashboard,
    Feed, Registration, Search,
    Settings, Messages,
    Login, Message_detail,} from "./pages";
import {Route, Routes} from "react-router-dom";

// import {UserRoutes} from "./routes/UserRoutes.js";

export const App = () => {

    const user = localStorage.getItem("token");

    if(user){
        const queryParams = new URLSearchParams(window.location.search);
        const authorization = queryParams.get("Authorization");
    } else {
        
    }



    return (
        <div>
            <Routes>
                <Route exact path="/profile" element={<Profile_dashboard/>}/>
                <Route exact path="/settings" element={<Settings/>}/>
                <Route exact path="/registration" element={<Registration/>}/>
                <Route exact path="/login" element={<Login/>}/>
                <Route exact path="/search" element={<Search/>}/>
                <Route exact path="/messages" element={<Messages/>}/>
                <Route exact path="/message-detail" element={<Message_detail/>}/>
                {/*TODO detail podle id*/}
                <Route exact path="/" element={<Feed/>}/>
            </Routes>
        </div>
    )
}

export default App;
