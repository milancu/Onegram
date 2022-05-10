// src/components/Post/index.js

import React, {Component} from "react";
import "./Post.css";
import LikeButton from "../LikeButton";
import Comments from "../Comments";

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
                        <img src={profilePicture} alt={nickname}/>
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
            <div className="Post-reactions">
                <LikeButton/>
            </div>
            <div className="Post-comments">
                <Comments comments = {[
                    {
                        "id": "1",
                        "parentId": null,
                        "content": "hi",
                        "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",
                        "username": "John D. Veloper",
                        "likeAmount": 5
                    },
                    {
                        "id": "2",
                        "parentId": 1,
                        "content": "hi",
                        "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",
                        "username": "John D. Veloper",
                        "likeAmount": 3
                    }
                ]}/>
            </div>
        </article>;
    }
}

export default Post;