// src.pages/index.js

import React, {Component, useState, useRef} from 'react';
import '../../App.css';
import {
    Post,
    Header,
    Footer
} from "../../pages";
import axios from "axios";
import * as Constants from "../../gql/query";

export const Feed = () => {
    // TODO
    const nickname = "John D. Veloper";
    const profilepicture = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg";
    // const caption = "Loving Educative!";
    // const image = "https://cdn-images-1.medium.com/max/1200/1*dMSWcBZCuzyRDeMr4uE_og.png";

    let userData;
    let followingPosts;

    axios.post(Constants.POST_GRAPHQL_API,
        {
            query: Constants.GET_FOLLOWING_POSTS
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        followingPosts = r.data.data.followingsPosts;
        console.log(followingPosts)
        localStorage.setItem('followingPosts', JSON.stringify(followingPosts));
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


    return (
        <div className="App">

            <Header />
            <br/>
            <br/>

            <section className="App-main">
                {JSON.parse(localStorage.getItem('followingPosts')).map(post => (
                    // <img className={"profileDashboardPhoto"} src={post.imagePaths} alt={post.description} />
                    <Post nickname = {nickname}
                      profilepicture={profilepicture}
                      caption={post.description}
                      image={post.imagePaths}
                />

                ))}

                {/*<Post nickname = {nickname}*/}
                {/*      profilepicture={profilepicture}*/}
                {/*      caption={caption}*/}
                {/*      image={image}*/}
                {/*/>*/}
                {/*<Post nickname = {nickname}*/}
                {/*      profilepicture={profilepicture}*/}
                {/*      caption={caption}*/}
                {/*      image={image}*/}
                {/*/>*/}
                {/*<Post nickname = {nickname}*/}
                {/*      profilepicture={profilepicture}*/}
                {/*      caption={caption}*/}
                {/*      image={image}*/}
                {/*/>*/}
                {/*<Post nickname = {nickname}*/}
                {/*      profilepicture={profilepicture}*/}
                {/*      caption={caption}*/}
                {/*      image={image}*/}
                {/*/>*/}
                {/*<Post nickname = {nickname}*/}
                {/*      profilepicture={profilepicture}*/}
                {/*      caption={caption}*/}
                {/*      image={image}*/}
                {/*/>*/}
            </section>
            <Footer />
        </div>
    )
}

export default Feed;
