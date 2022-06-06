// src/components/Post/index.js

import React from "react";
import "./Post.css";
import LikeButton from "../LikeButton";
import Comments from "../Comments";
import {Link} from "react-router-dom";

export const Post = (props) => {

    const authorUsername = props.authorUsername;
    const authorImage = props.authorImage;
    const description = props.description;
    const imagePaths = props.imagePaths;
    const authorId = props.authorId;
    const comments = props.comments;

    // console.log(authorUsername);
    // console.log(authorImage);
    // console.log(JSON.parse(localStorage.getItem('followingPosts')));

    return (
        <article className="Post">
            <Link to={'/profile/'+authorId}>
                <div className="Post-user">

                    <div className="Post-user-profilepicture">
                        <img src={authorImage} alt={authorUsername}/>
                    </div>
                    <div className="Post-user-nickname">
                        <span>{authorUsername}</span>
                    </div>

                </div>
            </Link>

            <div className="Post-image">
                <div className="Post-image-bg">
                    <img alt={description} src={imagePaths}/>
                </div>
            </div>

            <div className="Post-caption-container">
                <div>
                    <strong>{authorUsername}:&#160;</strong>{description}
                </div>
                <div className="Post-reactions">
                    <LikeButton/>
                </div>
            </div>

            <div className="Post-comments">
                {/*<Comments comments={[*/}
                {/*    {*/}
                {/*        "id": 1,*/}
                {/*        "parentId": null,*/}
                {/*        "content": "hi",*/}
                {/*        "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",*/}
                {/*        "username": "John D. Veloper",*/}
                {/*        "likeAmount": 5*/}
                {/*    },*/}
                {/*    {*/}
                {/*        "id": 2,*/}
                {/*        "parentId": 1,*/}
                {/*        "content": "reply",*/}
                {/*        "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",*/}
                {/*        "username": "John D. Veloper",*/}
                {/*        "likeAmount": 3*/}
                {/*    }*/}
                {/*]}*/}
                {/*/>*/}
                {<Comments comments={comments}/>}
            </div>
        </article>
    );
}

export default Post;