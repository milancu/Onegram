// src.pages/index.js

import React, {Component, useState, useRef} from 'react';
import '../../App.css';
import {
    Post,
    Header
} from "../../pages";

export const Feed = (props) => {

    const nickname = "John D. Veloper";
    const profilepicture = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg";
    const caption = "Loving Educative!";
    const image = "https://cdn-images-1.medium.com/max/1200/1*dMSWcBZCuzyRDeMr4uE_og.png";


    return (
        <div className="App">

            <Header nickname = {nickname}
                    profilepicture = {profilepicture} />
            <br/>
            <br/>

            <section className="App-main">
                <Post nickname = {nickname}
                      profilepicture={profilepicture}
                      caption={caption}
                      image={image}
                />
            </section>
        </div>
    )
}

export default Feed;
