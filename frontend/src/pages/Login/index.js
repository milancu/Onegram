import React from 'react';
import {Route, Routes} from "react-router-dom";
import {
    Profile_dashboard
} from "../../pages";
import './login.css';

export const Login = () => {

    const queryParams = new URLSearchParams(window.location.search);
    const token = (queryParams.get("Authorization"));
    if (token) {
        localStorage.setItem('token', token)
    }

    return (
        <div className={"center-input-page"}>
            {token ? <Routes>
                    <Route exact path="/profile/:id" element={<Profile_dashboard/>}/>
                </Routes>
                :
                <form className={"login-form"}>
                    <p>Do you want a sign up or log in?</p>
                    <p>Click button bellow.</p>
                    <br />
                    <button className={"login-button"}>
                        <a className={"no-link-style"} href={"http://localhost:1010/oauth2/authorization/google?redirect_uri=http://localhost:3000/oauth2/redirect"}><img
                            alt=""/>Log in</a>
                    </button>
                </form>

            }
        </div>

    )
}

export default Login;