// src/components/Post/index.js

import React, {Component, useState} from "react";
import "./Post.css";

export const Post = (props) => {

    const nickname = useState(props.nickname);
    const profilepicture = useState(props.profilepicture);
    const caption = useState(props.caption);
    const image = useState(props.image);

    return (
        <article className="Post" ref="Post">
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
                <strong>{nickname}</strong>{caption}
            </div>

        </article>
    )
}

export default Post;