import React, {useEffect, useState} from "react";
import cn from "classnames";
import {ReactComponent as Hand} from "./hand.svg";
import "./LikeButton.scss";
import axios from "axios";
import * as Constants from "../../gql/query";

const LikeButton = ({likes, postId}) => {

    const [liked, setLiked] = useState(false);
    const [clicked, setClicked] = useState(false);
    const [postLikes, setPostLikes] = useState([])

    function handleClick() {
        if (!liked) {
            likePost()
        } else {
            unlikePost()
        }
        setLiked(!liked);
    }

    function likePost() {
        const CREATE_LIKE = `
        mutation CREATE_LIKE{
            createLike(input:{
                postId:"` + postId + `",
                likeableId:"` + postId + `"
            }){
            authorId
            }
        }
        `;
        axios.post(Constants.POST_GRAPHQL_API, {
            query: CREATE_LIKE
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    function unlikePost() {
        const DELETE_LIKE = `
        mutation DELETE_LIKE{
            deleteLike(input:{
                postId:"` + postId + `",
                likeableId:"` + postId + `"
            })
        }
        `;
        axios.post(Constants.POST_GRAPHQL_API, {
            query: DELETE_LIKE
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    useEffect(() => {
        let tmp = []
        likes.map(like => (
            tmp.push(like.authorId)
        ))
        setPostLikes(tmp)
        setLiked(postLikes.includes(parseInt(localStorage.getItem('userId'))))
    }, [])

    return (
        <div className={"like-button-container"}>
            {postLikes.length}
            <button
                onClick={() => {
                    handleClick();
                    setClicked(true);
                }}
                onAnimationEnd={() => setClicked(false)}
                className={cn("like-button-wrapper", {
                    liked,
                    clicked,
                })}
            >
                <div className="like-button">
                    <Hand/>
                </div>
            </button>
        </div>
    );
};

export default LikeButton;