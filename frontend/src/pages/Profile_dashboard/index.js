// src/pages/Profile_dashboard/index.js

import React, {Component} from 'react';
import '../../App.css';
import './profile_dashboard.css';
import Profile from '../../components/Profile';
import Footer from "../../components/Footer";
import Profile_header from "../../components/Profile_header";
import { useState, useEffect} from "react";
import * as Constants from '../../gql/query';
import axios from 'axios';
import {GET_MY_FOLLOWING} from "../../gql/query";
import PostModal from "../../components/PostModal";
import {useParams} from "react-router-dom";

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

    if(params.id === String(user.id)){
        console.log('updating')
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
            // followers = r.data.data.followers;
            // following = r.data.data.following;
            localStorage.setItem('userData', JSON.stringify(userData));
            // localStorage.setItem('followers', JSON.stringify(followers));
            // localStorage.setItem('following', JSON.stringify(following));
        })

        axios.post(Constants.USER_GRAPHQL_API,
            {
                query: Constants.GET_USER_FOLLOWING
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
                query: Constants.GET_USER_FOLLOWERS
            }, {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            }).then(r => {
            followers = r.data.data.followers;
            localStorage.setItem('followers', JSON.stringify(followers));
        })
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

            <Profile_header />


            <Profile />

            <div className={"postImageContainer"}>
                {JSON.parse(localStorage.getItem('userPosts')).map(post => (
                    <img className={"profileDashboardPhoto"} src={post.imagePaths} alt={post.description} onClick={() => openModal(post)}/>
                ))}
                {/*<img className={"profileDashboardPhoto"} src={testImage} alt={"randomPic"} />*/}
            </div>
            <Footer />
        </div>
    )
}

export default Profile_dashboard;