import {useState} from "react";
import "./CommentForm.css"
import React, { Component }  from 'react';

const CommentForm = ({handleSubmit, submitLabel, hasCancelButton = false, initialText = "", handleCancel}) => {
    const [text, setText] = useState(initialText);
    const isTextAreaDisabled = text.length === 0;
    const onSubmit = event => {
        event.preventDefault()
        handleSubmit(text, null)
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
            {hasCancelButton && (
                <button type="button" className="comment-form-button comment-form-cancel-button" onClick={handleCancel}>Cancel</button>
            )}
        </form>
    );
};

export default CommentForm;