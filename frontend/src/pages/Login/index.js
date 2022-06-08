import React from 'react';
import {Route, Routes} from "react-router-dom";
import {
    Profile_dashboard
} from "../../pages";

export const Login = () => {

    const queryParams = new URLSearchParams(window.location.search);
    const token = (queryParams.get("Authorization"));
    if(token){
        localStorage.setItem('token', token)
    }

    return (
        <div>
            { token ? <Routes>
                <Route exact path="/profile/:id" element={<Profile_dashboard/>}/>
            </Routes>
                : <a href={"http://localhost:1010/oauth2/authorization/google?redirect_uri=http://localhost:3000/oauth2/redirect"}><img
                alt=""/>Google</a>}
        </div>

    )
}

export default Login;