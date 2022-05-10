import "./Comment.css"

const Comment = ({comment, replies}) => {
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
                    <div className="comment-text">
                        {comment.content}
                    </div>
                </div>

                {replies.length > 0 && (
                    <div className="replies">
                        {replies.map((reply) => (
                            <Comment key={reply.id} comment={reply} replies={[]}/>
                        ))}
                    </div>
                )}
            </div>
        </div>
    );
};

export default Comment;