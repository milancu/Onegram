import React from "react";
import {Link} from "react-router-dom";

const testname = "Buri"
const testImage = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

export const Single_follow = ({follow}) => {
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
                <button className={'follow-button'}>
                    Follow
                </button>
            </div>

        </div>
    );
}

export default Single_follow;