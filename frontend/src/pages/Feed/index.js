// src.pages/index.js

import React, {Component, useState, useRef} from 'react';
import '../../App.css';
import {
    Post,
    Header
} from "../../pages";

export const Feed = (props) => {

    const nickname = useRef(props.nickname);
    const profilepicture = useRef(props.profilepicture);
    const caption = useRef(props.caption);
    const image = useRef(props.image);

    return (
        <div className="App">

            <Header nickname = {nickname}
                    profilepicture = {profilepicture} />
            <br/>
            <br/>

            <section className="App-main">
                <Post nickname ={nickname}
                      profilepicture={profilepicture}
                      caption={caption}
                      image={image}/>
            </section>
        </div>
    )
}

export default Feed;
