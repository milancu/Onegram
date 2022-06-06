import React from "react";
import {Link} from "react-router-dom";
import axios from "axios";
import * as Constants from "../../gql/query";

const testname = "Buri"
const testImage = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

export const Single_follow = ({follow}) => {

    function unfollowUser() {
        const UNFOLLOW_USER = `
        mutation UNFOLLOW_USER{
            unFollowUser(input:{
                userId:` + follow.id + `
            })
        }
        `;
        axios.post(Constants.USER_GRAPHQL_API, {
                query: UNFOLLOW_USER
            },
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    function followUser() {
        const FOLLOW_USER = `
        mutation FOLLOW_USER{
            followUser(input:{
                userId:` + follow.id + `
            })
        }
        `;
        axios.post(Constants.USER_GRAPHQL_API, {
                query: FOLLOW_USER
            },
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    return (
        <div className={"follower-box"}>
            <div className="modal-profile-photo">
                <div className={"modal-profile-main"}>
                    <Link to={'/profile/' + follow.id}>
                        <div className={"modal-profile-user-profilepicture"}>
                            <img src={follow.image} alt={follow.username}
                                 className="modal-profile-user-profilepicture"/>
                        </div>
                    </Link>
                </div>
            </div>
            <div className={'center-height width-name'}>
                <Link to={'/profile/' + follow.id}>
                    {follow.username}
                </Link>
            </div>
            {/*TODO button follow, unfollow / podle reality*/}
            <div className={'center-height'}>
                {follow.followed ? <button className={'follow-button'} onClick={unfollowUser}>
                        Unfollow
                    </button> :
                    <button className={'follow-button'} onClick={followUser}>
                        Follow
                    </button>
                }

            </div>
        </div>
    );
}

export default Single_follow;