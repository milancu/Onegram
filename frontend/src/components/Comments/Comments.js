import {useEffect, useState} from "react";
import Comment from "./Comment";

const Comments = (props) => {
    const [postComments, setPostComments] = useState([]);
    const rootComments = postComments.filter((postComment) => postComment.parentId === null);

    useEffect(() => {
        setPostComments(props.comments);
    }, []);
    return (
        <div className="comments">
            <h3 className="comments-title">Comments</h3>
            <div className="comments_container">
                {rootComments.map(rootComment => (
                    <Comment key={rootComment.id} comment={rootComment}/>
                ))}
            </div>
        </div>
    );
};

export default Comments;