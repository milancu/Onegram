import {useEffect, useState} from "react";
import Comment from "./Comment";
import CommentForm from "./CommentForm";

const Comments = (props) => {
    const [postComments, setPostComments] = useState([]);
    const rootComments = postComments.filter((postComment) => postComment.parentId === null);
    const getReplies = commentId => {
        return postComments.filter((postComment) => postComment.parentId === commentId);
    }
    const addComment = (text, parentId) => {
        console.log('addComment', text, parentId)
    }
    const createComment = async (text, parentId = null) => {
        return {
            "id": Math.random()
        }
    }

    useEffect(() => {
        setPostComments(props.comments);
    }, []);
    return (
        <div className="comments">
            <hr />
            <CommentForm submitLabel="Write" handleSubmit={addComment}/>
            <div className="comments_container">
                {rootComments.map(rootComment => (
                    <Comment key={rootComment.id}
                             comment={rootComment}
                             replies={getReplies(rootComment.id)}/>
                ))}
            </div>
        </div>
    );
};

export default Comments;