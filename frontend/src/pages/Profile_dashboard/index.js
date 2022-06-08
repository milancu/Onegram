// src/pages/Profile_dashboard/index.js

import React, {useEffect} from "react";
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
import {GET_TARGET_USER_POSTS} from "../../gql/query";

export const Profile_dashboard = () => {

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

    let currentUser = params.id === String(user.id);

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_USER_FOLLOWING,
            variables: {
                userId: params.id
            }
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        following = r.data.data.following;
        if (currentUser) {
            localStorage.setItem('following', JSON.stringify(following));
        } else {
            localStorage.setItem('targetFollowing', JSON.stringify(following));
        }

    })

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_USER_FOLLOWERS,
            variables: {
                userId: params.id
            }
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        followers = r.data.data.followers;
        if (currentUser) {
            localStorage.setItem('followers', JSON.stringify(followers));
        } else {
            localStorage.setItem('targetFollowers', JSON.stringify(followers));
        }

    })

    if (currentUser) {
        axios.post(Constants.POST_GRAPHQL_API,
            {
                query: Constants.GET_TARGET_USER_POSTS,
                variables: {
                    author: params.id
                }
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
        localStorage.setItem("acces", "true");
    } else {
        let targetUserData;
        let targetUserPosts;

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
            targetUserData = r.data.data.user;

            let requestingUser = JSON.parse(localStorage.getItem('userData')).id;
            if (targetUserData.isPublic) {
                localStorage.setItem("acces", "true");
            } else {
                const followControl = JSON.parse(localStorage.getItem('targetFollowers'));

                let help = false
                followControl.forEach(f => {
                    if (f.id === requestingUser) {
                        localStorage.setItem("acces", "true");
                        help = true;
                    }
                })

                if (!help) {
                    localStorage.setItem("acces", "")
                }
            }
            localStorage.setItem('targetUserData', JSON.stringify(targetUserData));

        })
        if (localStorage.getItem("acces")) {
            axios.post(Constants.POST_GRAPHQL_API,
                {
                    query: Constants.GET_TARGET_USER_POSTS,
                    variables: {
                        author: params.id
                    }
                }, {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                }).then(r => {
                targetUserPosts = r.data.data.userPosts;
                localStorage.setItem('targetUserPosts', JSON.stringify(targetUserPosts));
            })
            postList = JSON.parse(localStorage.getItem('targetUserPosts'));
        }
    }

    let acces = localStorage.getItem("acces")

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

            {acces ?
                <div className={"postImageContainer"}>
                    {postList.map(post => (
                        <img className={"profileDashboardPhoto"} src={post.imagePaths} alt={post.description}
                             onClick={() => openModal(post)}/>
                    ))}
                </div> :
                <Private_account/>
            }

            <Footer/>
        </div>
    )
}

export default Profile_dashboard;