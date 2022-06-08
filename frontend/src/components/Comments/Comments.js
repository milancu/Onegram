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

    function deleteComment(commentId, hasParent) {
        const DELETE_COMMENT = `
        mutation DELETE_COMMENT{
            deleteComment(input:{
                postId:"` + props.postId + `",
                id:"` + commentId + `"
            })
        }
        `;
        const DELETE_SUBCOMMENT = `
        mutation DELETE_SUBCOMMENT{
            deleteSubcomment(input:{
                postId:"` + props.postId + `",
                subcommentId:"` + commentId + `"
            })
        }
        `;

        if (window.confirm("Are you sure you want to delete this comment?")) {
            if (hasParent) {
                axios.post(Constants.POST_GRAPHQL_API, {
                    query: DELETE_SUBCOMMENT
                }, {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                })
                    .then(res => console.log(res))
                    .catch(err => console.log(err))
            } else {
                axios.post(Constants.POST_GRAPHQL_API, {
                    query: DELETE_COMMENT
                }, {
                    headers: {
                        "Authorization": "Bearer " + localStorage.getItem('token')
                    }
                })
                    .then(res => console.log(res))
                    .catch(err => console.log(err))
            }
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
                             hasParent={false}
                             replyPermission={true}
                             postAuthorId={props.postAuthorId}
                    />
                ))}
            </div>
        </div>
    )
}

export default Comments