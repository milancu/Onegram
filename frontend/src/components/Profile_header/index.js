// src/components/Profile-header/index.js

import React from "react";
import "./profile-header.css";
import logo from '../../images/logo.png'
import messagesIcon from '../../images/messages.png'
import settingsIcon from '../../images/settings.png'
import {Link, useNavigate} from "react-router-dom";

export const Profile_header = (props) => {

    const nickname = props.nickname;
    const profilepicture = props.profilepicture;

    return (
        <nav className={"profile-header header-line"}>
                <Link to={'/'}>
                    <img className="logo" src={logo} alt="Logo"/>
                </Link>
                <Link to={'/search'}>
                    <a className="title">Onegram</a>
                </Link>
                <div className={"other-options"}>
                    <Link to={'/messages'}>
                        <img className={"options"} src={messagesIcon} alt={"messages icon"} />
                    </Link>
                    <Link to={'/settings'}>
                        <img className={"options"} src={settingsIcon} alt={"settings icon"} />
                    </Link>
                </div>
        </nav>
    );
}

export default Profile_header;