import {useEffect, useState} from "react";
import Comment from "./Comment";
import CommentForm from "./CommentForm";
import React, { Component }  from 'react';

const Comments = (props) => {
    const [postComments, setPostComments] = useState([])
    const [activeComment, setActiveComment] = useState(null)

    const rootComments = postComments.filter((postComment) => postComment.parentId === null)
    const getReplies = commentId => {
        return postComments.filter((postComment) => postComment.parentId === commentId)
    }
    const addComment = (text, parentId) => {
        createComment(text, parentId).then(comment => {
            setPostComments([comment, ...postComments])
        })
        setActiveComment(null)
    } //TODO rewrite to work with backend

    const createComment = async (text, parentId = null) => {
        return {
            id: Math.random().toString(36).substring(2, 9),
            content: text,
            parentId: parentId,
            userImage: "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",
            username: "John D. Veloper",
            likeAmount: 0
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

    const updateComment = async (text, commentId) => {
        const updatedPostComments = postComments.map(postComment => {
            if (postComment.id === commentId) {
                return {...postComment, content: text}
            }
            return postComment
        })
        setPostComments(updatedPostComments)
        setActiveComment(null);
    }

    useEffect(() => {
        setPostComments(props.comments)
    }, [])
    return (
        <div className="comments">
            <hr />
            <CommentForm submitLabel="Write" handleSubmit={addComment}/>
            <div className="comments_container">
                {rootComments.map(rootComment => (
                    <Comment key={rootComment.id}
                             comment={rootComment}
                             replies={getReplies(rootComment.id)}
                             deleteComment={deleteComment}
                             activeComment={activeComment}
                             setActiveComment={setActiveComment}
                             addComment={addComment}
                             updateComment={updateComment}
                    />
                ))}
            </div>
        </div>
    )
}

export default Comments