// src/components/Follow_requests.js

import React from "react";
import './follow_requests.css'
import {Link} from "react-router-dom";
import {Single_request, Profile_settings_form} from "../../pages";
import axios from "axios";
import * as Constants from "../../gql/query";

// console.log(JSON.parse(localStorage.getItem('requestsData')));

export const Follow_requests = () => {

    console.log(JSON.parse(localStorage.getItem('requestsData')))
    return (
        <div>
            {/*TODO zprovoznit filter requestu nebo uplne zrusit*/}
            {/*TODO OPENABLE*/}
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