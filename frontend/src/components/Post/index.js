// src/components/Post/index.js

import React, {Component, useEffect, useRef} from "react";
import "./Post.css";
import logo from "../../images/logo.png";
import LikeButton from "../LikeButton";
import Comments from "../Comments";

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

            <div className="Post-caption-container" >
                <div>
                    <strong>{nickname}:&#160;</strong>{caption}
                </div>
                <div className="Post-reactions">
                    <LikeButton/>
                </div>
            </div>

            <div className="Post-comments">
                <Comments comments = {[
                    {
                        "id": 1,
                        "parentId": null,
                        "content": "hi",
                        "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",
                        "username": "John D. Veloper",
                        "likeAmount": 5
                    },
                    {
                        "id": 2,
                        "parentId": 1,
                        "content": "reply",
                        "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",
                        "username": "John D. Veloper",
                        "likeAmount": 3
                    }
                ]}
                />
            </div>
        </article>
    );
}

export default Post;