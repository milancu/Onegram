// src/components/Post/index.js

import React, {Component} from "react";
import "./Post.css";

export const Post = (props) => {
    const nickname = props.nickname;
    const profilepicture = props.profilepicture;
    const image = props.image;
    const caption = props.caption;

    return (
        <article className="Post" ref="Post">
            <header>
                <div className="Post-user">
                    <div className="Post-user-profilepicture">
                        <img src={profilepicture} alt={nickname}/>
                    </div>
                    <div className="Post-user-nickname">
                        <span>{nickname}</span>
                    </div>
                </div>
            </header>

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