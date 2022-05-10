// src/components/Profile/index.js

import React from "react";
import "./profile.css";

export const Profile = (props) => {

    const nickname = props.nickname;
    const profilepicture = props.profilepicture;
    const description = props.description;

    console.log("cauky mnauky")

    return (
        <section className="profile-description">
            <div className="Post-user-profilepicture">
                <img src={profilepicture} alt={nickname} className="Post-user-profilepicture"/>

            </div>
            {/*    TODO */}
        </section>
    )

}

export default Profile