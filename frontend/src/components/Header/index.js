// src/components/Header/index.js

import React from "react";
import "./Header.css";
import logo from '../../images/logo.png'
import {Link} from "react-router-dom";
import addIcon from "../../images/add.png";
import messagesIcon from "../../images/messages.png";
import settingsIcon from "../../images/settings.png";
import {useParams} from "react-router-dom";

export const Header = (props) => {
    const profileData = JSON.parse(localStorage.getItem('userData'));
    const nickname = profileData.username;
    const profilepicture = profileData.image;
    const user = JSON.parse(localStorage.getItem('userData'))

    const params = useParams();
    let pesrsonNavigate = props.profile && String(profileData.id) === params.id;

    return (
        <nav className={"profile-header header-line"}>
            <Link to={'/'}>
                <img className="logo" src={logo} alt="Logo"/>
            </Link>
            <Link to={'/search'}>
                <p className="title">Onegram</p>
            </Link>
            {pesrsonNavigate
                ? <div className={"other-options"}>
                    {/*// TODO add new post*/}
                    <Link to={'/add'}>
                        <img className={"options"} src={addIcon} alt={"add new picture icon"} />
                    </Link>
                    <Link to={'/messages'}>
                        <img className={"options"} src={messagesIcon} alt={"messages icon"} />
                    </Link>
                    <Link to={'/settings'}>
                        <img className={"options"} src={settingsIcon} alt={"settings icon"} />
                    </Link>
                </div> : <div className="Post-user-profilepicture profile-image" >
                    <Link to={'/profile/'+user.id}>
                        <img src={profilepicture} alt={nickname} />
                    </Link>
                </div>
            }
        </nav>
    );
}

export default Header;