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
    const id = props.postId;
    const likes = props.likes

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
                    <LikeButton likes={likes} postId={id}/>
                </div>
            </div>

            <div className="Post-comments">
                {<Comments comments={comments} postId={id} postAuthorId={authorId}/>}
            </div>
        </article>
    );
}

export default Post;