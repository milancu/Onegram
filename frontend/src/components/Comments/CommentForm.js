import {useState} from "react";
import "./CommentForm.css"

const CommentForm = ({handleSubmit, submitLabel}) => {
    const [text, setText] = useState("");
    const isTextAreaDisabled = text.length === 0;
    const onSubmit = event => {
        event.preventDefault()
        handleSubmit(text)
        setText("")
    }
    return (
        <form className="comment-form-wrapper" onSubmit={onSubmit}>
            <input type="text" className="comment-form-textarea"
                      placeholder="Add comment..."
                      value={text}
                      onChange={(e) => setText(e.target.value)}
            />
            <button className="comment-form-button" disabled={isTextAreaDisabled}>{submitLabel}</button>
        </form>
    );
};

export default CommentForm;