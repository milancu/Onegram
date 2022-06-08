import {useEffect, useState} from "react";
import Comment from "./Comment";
import CommentForm from "./CommentForm";
import axios from "axios";
import * as Constants from "../../gql/query";
import React from 'react';

const Comments = (props) => {
    const [postComments, setPostComments] = useState([])
    const [activeComment, setActiveComment] = useState(null)

    function createComment(text, mainCommentId) {
        console.log(mainCommentId)
        const CREATE_COMMENT = `
        mutation CREATE_COMMENT{
            createComment(input:{
                postId:"` + props.postId + `",
                content:"` + text + `"
            }){
                id
            }
        }
        `;
        const CREATE_SUB_COMMENT = `
        mutation CREATE_SUB_COMMENT{
            createSubcomment(input:{
                postId:"` + props.postId + `",
                mainCommentId:"` + mainCommentId + `",
                content:"` + text + `"
            }){
                id
            }
        }
        `;

        if (mainCommentId === null) {
            axios.post(Constants.POST_GRAPHQL_API, {
                    query: CREATE_COMMENT
                },
                {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                })
                .then(res => console.log(res))
                .catch(err => console.log(err))
        } else {
            axios.post(Constants.POST_GRAPHQL_API, {
                    query: CREATE_SUB_COMMENT
                },
                {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                })
                .then(res => console.log(res))
                .catch(err => console.log(err))
        }
    }

    const deleteComment = (commentId) => {
        if (window.confirm("Are you sure you want to delete this comment?")) {
            const updatedPostComments = postComments.filter(
                (postComment) => postComment.id !== commentId
            )
            setPostComments(updatedPostComments)
        }
    }

    useEffect(() => {
        setPostComments(props.comments)
    }, [])

    return (
        <div className="comments">
            <hr/>
            <CommentForm submitLabel="Comment" handleSubmit={createComment}/>
            <div className="comments_container">
                {postComments.map(postComment => (
                    <Comment key={postComment.id}
                             comment={postComment}
                             replies={postComment.subComments}
                             deleteComment={deleteComment}
                             activeComment={activeComment}
                             setActiveComment={setActiveComment}
                             addComment={createComment}
                    />
                ))}
            </div>
        </div>
    )
}

export default Comments