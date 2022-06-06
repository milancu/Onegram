// src/pages/Profile_dashboard/index.js

import React from 'react';
import '../../App.css';
import './profile_dashboard.css';
import Profile from '../../components/Profile';
import Footer from "../../components/Footer";
import {useState} from "react";
import * as Constants from '../../gql/query';
import axios from 'axios';
import PostModal from "../../components/PostModal";
import Private_account from "../../components/Private_account";
import {useParams} from "react-router-dom";
import Header from "../../components/Header";

export const Profile_dashboard = (props) => {

    const params = useParams();
    const user = JSON.parse(localStorage.getItem('userData'))

    const [openPostModal, setOpenPostModal] = useState(false);
    const [activePost, setActivePost] = useState(null);

    const openModal = (post) => {
        setActivePost(post);
        setOpenPostModal(true);
    };

    const closeModal = () => {
        setActivePost(null);
        setOpenPostModal(false);
    }

    let userData;
    let userPosts;
    let followers;
    let following;
    let postList;

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_USER_FOLLOWING,
            variables: {
                userId: params.id //TODO pokud by byl rpoblem s formatem
            }
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        following = r.data.data.following;
        localStorage.setItem('following', JSON.stringify(following));
    })

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_USER_FOLLOWERS,
            variables: {
                userId: params.id //TODO pokud by byl rpoblem s formatem
            }
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        followers = r.data.data.followers;
        localStorage.setItem('followers', JSON.stringify(followers));
    })

    let currentUser = params.id === String(user.id);
    if(currentUser){
        axios.post(Constants.POST_GRAPHQL_API,
            {
                query: Constants.GET_USER_POSTS
            }, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            }).then(r => {
            userPosts = r.data.data.userPosts;
            localStorage.setItem('userPosts', JSON.stringify(userPosts));
        })

        axios.post(Constants.USER_GRAPHQL_API,
            {
                query: Constants.GET_USER_DATA
            }, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            }).then(r => {
            userData = r.data.data.my;
            localStorage.setItem('userData', JSON.stringify(userData));
        })
        postList = JSON.parse(localStorage.getItem('userPosts'));
    } else {
        let targetUserData;
        let targetUserPosts;
        
        axios.post(Constants.POST_GRAPHQL_API,
            {
                query: Constants.GET_TARGET_USER_POSTS,
                variables: {
                    author: params.id //TODO pokud by byl rpoblem s formatem
                }
            }, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            }).then(r => {
            targetUserPosts = r.data.data.userPosts;
            localStorage.setItem('targetUserPosts', JSON.stringify(targetUserPosts));
        })
        axios.post(Constants.USER_GRAPHQL_API,
            {
                query: Constants.GET_TARGET_DATA,
                variables: {
                    userId: params.id
                }
            }, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            }).then(r => {
                // console.log(r.data);
            targetUserData = r.data.data.user;
            localStorage.setItem('targetUserData', JSON.stringify(targetUserData));
        })
        postList = JSON.parse(localStorage.getItem('targetUserPosts'));
    }




    return (
        <div className="App">

            <div className={"postModalWindow"}>
                {openPostModal && <PostModal
                    closePostModal={closeModal}
                    post={activePost}
                    user={localStorage.getItem('userData')}
                />}
            </div>

            <Header profile={true}/>

            <Profile currentUser={currentUser}/>

            <Private_account />

            <div className={"postImageContainer"}>
                {postList.map(post => (
                    <img className={"profileDashboardPhoto"} src={post.imagePaths} alt={post.description} onClick={() => openModal(post)}/>
                ))}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
            </div>
            <Footer />
        </div>
    )
}

export default Profile_dashboard;