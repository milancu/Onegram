import "./Comment.css"
import CommentForm from "./CommentForm";
import React, { Component }  from 'react';

const Comment = ({comment, replies, addComment, updateComment, deleteComment, activeComment, setActiveComment, parentId=null}) => {
    const canReply = comment.parentId == null; //Boolean (logged in)
    const canEdit = true; // author == current user
    const canDelete = true; // author == current user
    const isReplying = activeComment && activeComment.type === "replying" && activeComment.id === comment.id
    const isEditing = activeComment && activeComment.type === "editing" && activeComment.id === comment.id
    const replyId = parentId ? parentId : comment.id;
    return (
        <div className="comment">
            <div className="comment-image-container">
                <img src={comment.userImage} alt="user"/>
            </div>
            <div className="comment-right-part">
                <div className="comment-content">
                    <div className="comment-author">
                        {comment.username}
                    </div>
                    {!isEditing && <div className="comment-text">{comment.content}</div>}
                    {isEditing && (
                        <CommentForm submitLabel="Update"
                                     initialText={comment.content}
                                     handleSubmit={(text) => updateComment(text, comment.id)}
                                     handleCancel={() => setActiveComment(null)}/>
                    )}
                    <div className="comment-actions">
                        {canReply && <div className="comment-action" onClick={() => setActiveComment({
                            id: comment.id,
                            type: "replying"
                        })}>Reply</div>}
                        {canDelete &&
                            <div className="comment-action" onClick={() => deleteComment(comment.id)}>Delete</div>}
                    </div>
                </div>
                {isReplying && (
                    <CommentForm submitLabel="Reply" handleSubmit={(text) => addComment(text, replyId)}/>
                )}
                {replies.length > 0 && (
                    <div className="replies">
                        {replies.map((reply) => (
                            <Comment
                                key={reply.id}
                                comment={reply}
                                replies={[]}
                                deleteComment={deleteComment}
                                activeComment={activeComment}
                                setActiveComment={setActiveComment}
                                addComment={addComment}
                                updateComment={updateComment}
                                parentId={comment.id}
                            />
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
};

export default Comment;