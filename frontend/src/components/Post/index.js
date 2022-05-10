// src/components/Post/index.js

import React, {Component, useEffect, useRef} from "react";
import "./Post.css";
import logo from "../../images/logo.png";

export const Post = (props) => {

    const nickname = props.nickname;
    const profilepicture = props.profilepicture;
    const caption = props.caption;
    const image = props.image;

    return (
        <article className="Post">
            <div className="Post-user">

                <div className="Post-user-profilepicture">
                    <img src={profilepicture} alt={nickname}/>
                </div>
                <div className="Post-user-nickname">
                    <span>{nickname}</span>
                </div>
            </div>

            <div className="Post-image">
                <div className="Post-image-bg">
                    <img alt={caption} src={image}/>
                </div>
            </div>

            <div className="Post-caption">
                <strong>{nickname}:&#160;</strong>{caption}
            </div>
        </article>
    );
}

export default Post;