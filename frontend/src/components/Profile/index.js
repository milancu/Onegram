// src/components/Profile/index.js

import React from "react";
import "./profile.css";


export const Profile = (props) => {

    const nickname = props.nickname;
    const profilepicture = props.profilepicture;
    const description = props.description;
    const webLinkUrl = props.webLinkUrl;
    const follows = props.follows;
    const followers = props.followers
    const postsNumber = props.postsNumber;


    return (
        <section className="profile-description">
            <div className="profile-photo">
                <div className={"profile-main"}>
                    <div className={"profile-user-profilepicture"}>
                        <img src={profilepicture} alt={nickname} className="profile-user-profilepicture"/>
                    </div>
                    <h3>{nickname}</h3>
                </div>
            </div>
            <div className="profile-info">
                <div className="Profile-bio"><p>{description}</p></div>
                <div className="Profile-web">
                    {/*TODO nefunguje*/}
                    <a href={'/profile'}>
                        <p>{webLinkUrl}</p>
                    </a>
                </div>

            </div>
            <div className="profile-stats">
                <div className={"statsContainer"}>
                    <p>Posts</p>
                    <p>{postsNumber}</p>
                </div>

                <div className={"statsContainer"}>
                    <p>Followers</p>
                    <p>{followers}</p>
                </div>
                <div className={"statsContainer"}>
                    <p>Follows</p>
                    <p>{follows}</p>
                </div>
            </div>
        </section>
    )
}

export default Profile