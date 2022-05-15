// src/components/Follow_requests.js

import React from "react";
import './follow_requests.css'
import {Link} from "react-router-dom";
import {Single_request, Profile_settings_form
} from "../../pages";

export const Follow_requests = () => {
    return (
        <div>
            {/*TODO zprovoznit filter requestu nebo uplne zrusit*/}
            {/*TODO OPENABLE*/}
            <h2>Follow requests</h2>
            <form>
                <input id="searchInput" type="text" placeholder="Search"/>
            </form>
            <Single_request />
            <Single_request />
            <Single_request />
            <br/>
            <hr/>
        </div>
    );
}

export default Follow_requests;