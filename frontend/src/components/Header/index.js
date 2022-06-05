// src/components/Header/index.js

import React from "react";
import "./Header.css";
import logo from '../../images/logo.png'
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import * as Constants from "../../gql/query";

const profileData = JSON.parse(localStorage.getItem('userData'));
// let userData;
// if(!profileData){
//     axios.post(Constants.USER_GRAPHQL_API,
//         {
//             query: Constants.GET_USER_DATA
//         }, {
//             headers: {
//                 "Authorization": "Bearer " + localStorage.getItem('token')
//             }
//         }).then(r => {
//         userData = r.data.data.my;
//         localStorage.setItem('userData', JSON.stringify(userData));
//     })
// }

export const Header = () => {

    const nickname = profileData.username;
    const profilepicture = profileData.image;

    return (
            <nav>
                <div className="header-line">
                    <Link to={'/'}>
                        <img className="logo" src={logo} alt="Logo"/>
                    </Link>
                    <Link to={'/search'}>
                        <a className="title">Onegram</a>
                    </Link>
                    <div className="Post-user-profilepicture profile-image" >
                        <Link to={'/profile'}>
                            <img src={profilepicture} alt={nickname} />
                        </Link>
                    </div>
                </div>
            </nav>
        );
}

export default Header;