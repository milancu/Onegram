// src/components/Post/index.js

import React, {Component} from "react";
import "./Post.css";
import LikeButton from "../LikeButton";

class Post extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        const nickname = this.props.nickname;
        const profilePicture = this.props.profilepicture;
        const image = this.props.image;
        const caption = this.props.caption;

        return <article className="Post" ref="Post">

            <header>
                <div className="Post-user">
                    <div className="Post-user-profilepicture">
                        <img src={profilePicture} alt={nickname} />
                    </div>
                    <div className="Post-user-nickname">
                        <span>{nickname}</span>
                    </div>
                </div>
            </header>

            <div className="Post-image">
                <div className="Post-image-bg">
                    <img alt={caption} src={image} />
                </div>
            </div>

            <div className="Post-caption">
                <strong>{nickname}</strong>{caption}
            </div>
            <div className="Post-reactions">
                <LikeButton />
            </div>

        </article>;
    }
}

export default Post;