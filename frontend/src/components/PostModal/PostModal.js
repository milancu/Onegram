import React from "react"
import "./postModal.css"
import Comments from "../Comments";
import axios from 'axios';
import * as Constants from "../../gql/query";
import {Link} from "react-router-dom";

const PostModal = ({closePostModal, post}) => {

    let userId = post.authorId
    const postId = post.id;
    let user = {};
    let data = JSON.parse(localStorage.getItem('users'));
    for (let u of data) {
        if (u.id === userId) {
            user.image = u.image;
            user.username = u.username;
        }
    }

    function deletePost() {
        const DELETE_POST = `mutation {
        deletePost(input: {
             id:"` + postId + `"
        })}`
        console.log(DELETE_POST)
        axios.post(Constants.POST_GRAPHQL_API, {
                query: DELETE_POST
            },
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    const thisUser = JSON.parse(localStorage.getItem('userData'));
    const canDelete = userId === thisUser.id;

    return (
        <div className={"post-modal-background"}>
            <div className={"post-modal-container"}>
                <div className={"post-modal-image"}>
                    <img src={post.imagePaths} alt={post.description}/>
                </div>
                <div className={"post-modal-right-part"}>
                    <div className={"post-modal-user-container"}>
                        <div className={"post-modal-close-btn"}>
                            <button onClick={() => closePostModal()}> X</button>
                        </div>
                        <div className={"post-modal-user"}>
                            <Link to={'/profile'}>
                                <div className={"post-modal-user-image"}>
                                    <img src={user.image} alt={user.username}/>
                                </div>
                            </Link>
                            <div className={"post-modal-user-caption"}>
                                <Link to={'/profile'}>
                                    <p className={"post-modal-user-nickname"}>
                                        {user.username}
                                        {"\n"}
                                        {canDelete
                                            ? <button onClick={() => deletePost()}>Delete</button>
                                            : ""
                                        }

                                    </p>
                                </Link>
                                <p className={"post-modal-user-text"}>
                                    {post.description}
                                </p>

                            </div>
                        </div>
                    </div>
                    <div className={"post-modal-comments"}>
                        <Comments comments={[
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
