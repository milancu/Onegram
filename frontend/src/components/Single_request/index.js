// src/components/Single_request.js

import React from "react";
import './single_request.css'
import {Link} from "react-router-dom";
import axios from "axios";
import * as Constants from "../../gql/query";

const testProfilePhoto = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
const nickname = 'Test_nickname'

export const Single_request = (props) => {

    function acceptRequest(){
        const ACCEPT_REQUEST =`
        mutation ACCEPT_REQUEST{
            acceptRequest(input:{
                requestId:`+ props.requestId+`
            })
        }
        `;
        // console.log(ACCEPT_REQUEST);
        axios.post(Constants.USER_GRAPHQL_API, {
                query: ACCEPT_REQUEST
            },
            {
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem('token')
                }
            })
            .then(res => console.log(res))
            .catch(err => console.log(err))
    }

    function rejectRequest(){
        const REJECT_REQUEST =`
        mutation REJECT_REQUEST{
            rejectRequest(input:{
                requestId:`+ props.requestId+`
            })
        }
        `;
        // console.log(ACCEPT_REQUEST);
        axios.post(Constants.USER_GRAPHQL_API, {
                query: REJECT_REQUEST
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
        <div className={'requestItems'}>
            <div className={"single-request"}>
                {/*TODO link on correct profile*/}
                <Link to={'/profile/'+props.id} className={"request-user-profilepicture"}>
                    <img src={props.image} alt={"testProfilePhoto"} className="request-user-profilepicture"/>
                </Link>

                <Link to='/profile'>
                    <a className={"request-link"}>{props.username}</a>
                </Link>

                {/*TODO rozliseni spravneho requestu*/}
                <div className={"request-responses"}>
                    <button id={"acceptRequest"} className={"accept-button"} type="submit" value="Accept" onClick={acceptRequest}>Accept</button>
                    <button id={"rejectRequest"} className={"delete-button"} type="submit" value="Delete" onClick={rejectRequest}>Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Single_request;