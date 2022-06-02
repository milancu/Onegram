import React from "react"
import "./postModal.css"
import Comments from "../Comments";

const PostModal = ({closePostModal, post, user}) => {

    return (
        <div className={"post-modal-background"} >
            <div className={"post-modal-container"}>
                <div className={"post-modal-image"}>
                    <img src={post.imagePaths} alt={post.description}/>
                </div>
                <div className={"post-modal-right-part"}>
                    <div className={"post-modal-user-container"}>
                        <div className={"post-modal-close-btn"}>
                            <button onClick={() => closePostModal()}> X </button>
                        </div>
                        <div className={"post-modal-user"}>
                            <div className={"post-modal-user-image"}>
                                <img src={JSON.parse(user).image} alt={JSON.parse(user).username}/>
                            </div>
                            <div className={"post-modal-user-caption"}>
                                <p className={"post-modal-user-nickname"}>
                                    {JSON.parse(user).username}
                                </p>
                                <p className={"post-modal-user-text"}>
                                    {post.description}
                                </p>

                            </div>
                        </div>
                    </div>
                    <div className={"post-modal-comments"}>
                        <Comments comments = {[
                            {
                                "id": 1,
                                "parentId": null,
                                "content": "hi",
                                "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",
                                "username": "John D. Veloper",
                                "likeAmount": 5
                            },
                            {
                                "id": "2",
                                "parentId": 1,
                                "content": "hi",
                                "userImage": "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg",
                                "username": "John D. Veloper",
                                "likeAmount": 3
                            }
                        ]}
                        />
                    </div>
                </div>

            </div>
        </div>
    )
}

export default PostModal
