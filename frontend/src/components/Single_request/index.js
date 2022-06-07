// src/components/Single_request.js

import React from "react";
import './single_request.css'
import {Link} from "react-router-dom";
import axios from "axios";
import * as Constants from "../../gql/query";


export const Single_request = (props) => {

    function acceptRequest(){
        const ACCEPT_REQUEST =`
        mutation ACCEPT_REQUEST{
            acceptRequest(input:{
                requestId:`+ props.requestId+`
            })
        }
        `;
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
                <Link to={'/profile/'+props.id} className={"request-user-profilepicture"}>
                    <img src={props.image} alt={"testProfilePhoto"} className="request-user-profilepicture"/>
                </Link>

                <Link to={'/profile/'+props.id}>
                    <p className={"request-link"}>{props.username}</p>
                </Link>

                <div className={"request-responses"}>
                    <button id={"acceptRequest"} className={"accept-button"} type="submit" value="Accept" onClick={acceptRequest}>Accept</button>
                    <button id={"rejectRequest"} className={"delete-button"} type="submit" value="Delete" onClick={rejectRequest}>Delete</button>
                </div>
            </div>
        </div>
    );
}

export default Single_request;