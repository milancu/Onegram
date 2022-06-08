// src.App.js

import React from 'react';
import './App.css';
import {
    Profile_dashboard,
    Feed, Search, Login,
    Settings, Messages,
    Message_detail, Add_post
} from "./pages";
import {Route, Routes} from "react-router-dom";


export const App = () => {

    return (
        <div>
            {!localStorage.getItem('token') ?
                <Login></Login> :
                <Routes>
                    <Route exact path="/oauth2/redirect/" element={<Search/>}/>
                    <Route exact path="/profile/:id" element={<Profile_dashboard/>}/>
                    <Route exact path="/settings" element={<Settings/>}/>
                    <Route exact path="/search" element={<Search/>}/>
                    <Route exact path="/messages" element={<Messages/>}/>
                    <Route exact path="/message-detail" element={<Message_detail/>}/>
                    <Route exact path="/" element={<Feed/>}/>
                    <Route exact path="/add" element={<Add_post/>}/>
                </Routes> }

        </div>
    )
}

export default App;
