// src/components/Profile/index.js

import React from "react";
import "./profile.css";
import {useNavigate} from "react-router-dom";

import {useState, useMemo, useEffect} from "react";
import axios from "axios";
import * as Constants from "../../gql/query";
import FollowersModal from "../FollowersModal";

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

    const [modalState, setModalState] = useState(null);
    const [openFollowingModal, setOpenFollowingModal] = useState(false);
    const [openFollowersModal, setOpenFollowersModal] = useState(false);

    return (
        <section className="profile-description">
            <div className={"modal-windows"}>
                {openFollowersModal && <FollowersModal
                    closeFollowersModal={setOpenFollowersModal}
                    modalState={modalState}
                />}
                {openFollowingModal && <FollowersModal
                    closeFollowersModal={setOpenFollowingModal}
                    modalState={modalState}
                />}
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
                        <p id={"webLinkUrl"}>{webLinkUrl}</p>
                    </a>
                </div>

            </div>
            <div className="profile-stats">
                <div className={"statsContainer"}>
                    <p>Posts</p>
                    <p>{postsNumber}</p>
                </div>

                <div className={"statsContainer follows"} onClick={() => {
                    setOpenFollowingModal(true);
                    setModalState("followers")
                }}>
                    <a id={"followers-link"}>Followers</a>
                    <p>{followers}</p>
                </div>

                <div className={"statsContainer follows"} onClick={() => {
                    setOpenFollowersModal(true);
                    setModalState("following")
                }}>
                    <a id={"follows-link"}>Follows</a>
                    <p>{follows}</p>
                </div>
            </div>
        </section>
    )
}

export default Profile