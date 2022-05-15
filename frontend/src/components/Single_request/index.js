// src/components/Single_request.js

import React from "react";
import './single_request.css'
import {Link} from "react-router-dom";

const testProfilePhoto = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
const nickname = 'Test_nickname'

export const Single_request = () => {
    return (
        <div className={'requestItems'}>
            <div className={"single-request"}>
                {/*TODO link on correct profile*/}
                <Link to={'/profile'} className={"request-user-profilepicture"}>
                    <img src={testProfilePhoto} alt={"testProfilePhoto"} className="request-user-profilepicture"/>
                </Link>

                <Link to='/profile'>
                    <a className={"request-link"}>{nickname}</a>
                </Link>

                {/*TODO rozliseni spravneho requestu*/}
                <div className={"request-responses"}>
                    <button className={"accept-button"} type="submit" value="Accept">Accept</button>
                    <button className={"delete-button"} type="submit" value="Delete">Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Single_request;