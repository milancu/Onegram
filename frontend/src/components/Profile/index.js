// src/components/Profile/index.js

import React from "react";
import "./profile.css";
import {useParams} from "react-router-dom";
import {useState} from "react";
import axios from "axios";
import * as Constants from "../../gql/query";
import FollowersModal from "../FollowersModal";

let profilePosts;
let profileData;

export const Profile = (props) => {

    if (props.currentUser) {
        profileData = JSON.parse(localStorage.getItem('userData'));
        profilePosts = JSON.parse(localStorage.getItem('userPosts'));
    } else {
        profileData = JSON.parse(localStorage.getItem('targetUserData'));
        profilePosts = JSON.parse(localStorage.getItem('targetUserPosts'));
    }

    function unfollowUser() {
        const UNFOLLOW_USER = `
        mutation UNFOLLOW_USER{
            unFollowUser(input:{
                userId:` + params.id + `
            })
        }
        `;
        axios.post(Constants.USER_GRAPHQL_API, {
                query: UNFOLLOW_USER
            },
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    function followUser() {
        const FOLLOW_USER = `
        mutation FOLLOW_USER{
            followUser(input:{
                userId:` + params.id + `
            })
        }
        `;
        axios.post(Constants.USER_GRAPHQL_API, {
                query: FOLLOW_USER
            },
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    const params = useParams();
    const user = JSON.parse(localStorage.getItem('userData'))
    let showButton = !(String(user.id) === params.id);
    const followsButtonControl = JSON.parse(localStorage.getItem('following'));
    let followButton = false;
    for (let i = 0; i < followsButtonControl.length; i++) {
        if (String(followsButtonControl[i].id) === params.id) {
            followButton = true;
            break;
        }
    }

    let currentUser = params.id === String(user.id);
    // let [followersData, setfollowersData] = useState([])
    let followersData;
    let followingData;
    let modalFollowerState;
    let modalFollowingState;



    if (currentUser) {
        followersData = JSON.parse(localStorage.getItem('followers'));
        followingData = JSON.parse(localStorage.getItem('following'));
        modalFollowerState = "followers";
        modalFollowingState = "following";
    } else {
        followersData = JSON.parse(localStorage.getItem('targetFollowers'));
        followingData = JSON.parse(localStorage.getItem('targetFollowing'));
        modalFollowerState = "targetFollowers";
        modalFollowingState = "targetFollowing";
    }

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
        <section>
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
            <div className="profile-description">
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
                        <a href={webLinkUrl} id={"webLinkUrl"}>{webLinkUrl}</a>
                    </div>

                    {showButton ? followButton ?
                            <div className={"center-button"}>
                                <button id={'submit'} className={"accept-button"} type={"button"} onClick={unfollowUser}>
                                    Unfollow
                                </button>
                            </div> :
                            <div className={"center-button"}>
                                <button id={'submit'} className={"accept-button"} type={"button"} onClick={followUser}>
                                    Follow
                                </button>
                            </div>
                        : ""
                    }

                </div>
                <div className="profile-stats">
                    <div className={"statsContainer"}>
                        <p>Posts</p>
                        <p>{postsNumber}</p>
                    </div>

                    <div className={"statsContainer"} onClick={() => {
                        setOpenFollowingModal(true);
                        setModalState(modalFollowerState)
                    }}>
                        <p id={"followers-link"}>Followers</p>
                        <p>{followers}</p>
                    </div>

                    <div className={"statsContainer"} onClick={() => {
                        setOpenFollowersModal(true);
                        setModalState(modalFollowingState)
                    }}>
                        <p id={"follows-link"}>Follows</p>
                        <p>{follows}</p>
                    </div>
                </div>

            </div>
        </section>
    )
}

export default Profile