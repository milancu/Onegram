import React from "react";
import { GoogleLogin } from 'react-google-login';
import {Link, useNavigate} from "react-router-dom";
import {gapi} from "gapi-script";
import {Feed} from "../../pages";

const clientId = "1092533079010-rt6qic7u16ljrdm69tok4n0751apai8o.apps.googleusercontent.com";
const clientSecret = "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";

function LoginButton(){
    const nav = useNavigate();

    const onSuccess = (res) => {
        // console.log("LOGIN SUCCESS! Current user: ", res.profileObj);
        let accessToken = gapi.auth.getToken().access_token;
        //Todo
        accessToken = "admin";
        window.localStorage.setItem('authToken', accessToken);
        nav("/Feed")
    }

    const onFailure = (res) => {
        console.error("LOGIN FAILED~ red: ", res);
    }

    return(
        <div id={"signInButton"}>
            <GoogleLogin
                clientId={clientId}
                clientSecret={clientSecret}
                buttonText={"Login"}
                onSuccess={onSuccess}
                onFailure={onFailure}
                cookiePolicy={'single_host_origin'}
                isSignedIn={true} />
        </div>
    )
}

export default LoginButton;