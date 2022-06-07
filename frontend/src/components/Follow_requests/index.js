// src/components/Follow_requests.js

import React from "react";
import './follow_requests.css'
import {Single_request} from "../../pages";

export const Follow_requests = () => {

    return (
        <div>
            <h2>Follow requests</h2>
            <form>
                <input id="searchInput" type="text" placeholder="Search"/>
            </form>
            {JSON.parse(localStorage.getItem('requestsData')).map(request => (
                <Single_request image={request.sender.image}
                                username={request.sender.username}
                                id={request.sender.id}
                                requestId={request.id}/>
            ))}
            <br/>
            <hr/>
        </div>
    );
}

export default Follow_requests;