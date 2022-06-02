// src/components/Profile/index.js

import React from "react";
import "./profile.css";
import ModalFollowing from "../../pages/Profile_dashboard/ModalFollowing.js";

import {useState, useMemo, useEffect} from "react";
import ModalFollowers from "../../pages/Profile_dashboard/ModalFollowers";

export const Profile = () => {
    const profileData = JSON.parse(localStorage.getItem('userData'));
    const followersData = JSON.parse(localStorage.getItem('followers'));
    const followingData = JSON.parse(localStorage.getItem('following'));
    const profilePosts = JSON.parse(localStorage.getItem('userPosts'));


    const nickname = profileData.username;
    const profilepicture = profileData.image;
    const description = profileData.bio;
    const webLinkUrl = profileData.link;
    const follows = followingData.length;
    const followers = followersData.length;
    const postsNumber = profilePosts.length;

    const[openFollowersModal, setOpenFollowingModal] = useState(false);
    const[openFollowsModal, setOpenFollowsModal] = useState(false);

    return (
        <section className="profile-description">
            <div className={"modal-windows"}>
                {openFollowsModal && <ModalFollowing close={setOpenFollowsModal}/>}
                {openFollowersModal && <ModalFollowers close={setOpenFollowingModal}/>}
            </div>
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

                <div className={"statsContainer"} >
                    <a id={"followers-link"}>Followers</a>
                    <p onClick={() => {setOpenFollowingModal(true)}}>{followers}</p>
                </div>

                <div className={"statsContainer"} >
                    <a id={"follows-link"}>Follows</a>
                    <p onClick={() => {setOpenFollowsModal(true)}}>{follows}</p>
                </div>
            </div>
        </section>
    )
}

export default Profile