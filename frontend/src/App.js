// src.App.js

import React from 'react';
import './App.css';
import {Profile_dashboard,
    Feed, Search,
    Settings, Messages,
    Message_detail,} from "./pages";
import {Route, Routes} from "react-router-dom";
import LogoutButton from "./components/Buttons/LogoutButton";
import LoginButton from "./components/Buttons/LoginButton";

import { useEffect } from "react";
import { gapi } from "gapi-script";

import {useQuery} from "@apollo/client";
import {GET_USERS} from "./gql/Query";
import Header from "./components/Header";

// import {UserRoutes} from "./routes/UserRoutes.js";

const userClientId = "1092533079010-rt6qic7u16ljrdm69tok4n0751apai8o.apps.googleusercontent.com";

export const App = () => {
    const { loading, error, data } = useQuery(GET_USERS);
    console.log(data);


    useEffect(() => {
        function start(){
            gapi.client.init({
                clientId: userClientId,
                scope: ""
            })
        };
        gapi.load('client:auth2', start);
    });

    return (
        <div>
            <LoginButton />
            {/*<LogoutButton />*/}
            <Routes>
                <Route exact path="/profile" element={<Profile_dashboard/>}/>
                <Route exact path="/settings" element={<Settings/>}/>
                <Route exact path="/search" element={<Search/>}/>
                <Route exact path="/messages" element={<Messages/>}/>
                <Route exact path="/message-detail" element={<Message_detail/>}/>
                {/*TODO detail podle id*/}
                <Route exact path="/Feed" element={<Feed/>}/>
            </Routes>
        </div>
    )
}

export default App;
