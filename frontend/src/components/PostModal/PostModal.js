import React from "react"
import "./postModal.css"

const PostModal = ({closePostModal}) => {
    //window.confirm(props.post.caption)
    return (
        <div className={"post-modal-background"} >
            <div className={"post-modal-container"}>
                <div className={"post-modal-close-btn"}>
                    <button onClick={() => closePostModal(false)}> X </button>
                </div>
                <div className={"post-modal-image"}>
                </div>
                <div className={"post-modal-user"}>
                </div>
                <div className={"post-modal-comments"}>
                </div>
            </div>
        </div>
    )
}

export default PostModal