import "./Comment.css"

const Comment = ({comment}) => {
    return (
        <div className="comment">
            <div className="comment-image-container">
                <img src={comment.userImage} alt="user"/>
            </div>
            <div className="comment-right-part">
                <div className="comment-content">
                    <div className="comment-author">
                        {comment.username};
                    </div>
                    <div className="comment-text">
                        {comment.content};
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Comment;