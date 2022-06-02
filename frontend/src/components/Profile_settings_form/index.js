import React from "react";
import './profile-settings-form.css'
import {Link} from "react-router-dom";

import './profile-settings-form.css'
import axios from "axios";
import * as Constants from "../../gql/query";

export const Profile_settings_form = () => {
    let data = JSON.parse(localStorage.getItem('userData'));
    let username = data.username;
    let bio = data.bio;
    let link = data.link;
    let isPublic = data.isPublic;

// TODO asynchronne
    if(isPublic){
        let switchPublic = document.querySelector('#isPublicInput');
        // console.log(switchPublic)
        if(switchPublic){
            switchPublic.checked = true
        }
        // switchPublic.checked = true
    }

    let requestsData;

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_MY_FOLLOW_REQUESTS
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        // console.log(r)
        requestsData = r.data.data.followRequests;
        localStorage.setItem('requestsData', JSON.stringify(requestsData));
        console.log(requestsData);
    })
    return (
        <form className={"settings-form"}>
            <label htmlFor="username">Username:</label><br/>
            <input type="text" id="username" name="username" value={username}/><br/>
            <label htmlFor="bio">Bio:</label><br/>
            <textarea id="bio" name="bio" rows="8" cols="50" >
                {bio}
            </textarea><br/>
            <label htmlFor="link">Link:</label><br/>
            <input type="text" id="link" name="link" value={link}/><br/>
            <div className={"setting-photo"}>
                <div className={"Post-user-profilepicture profile-image"}>
                    <img src={data.image}/>
                </div>
                {/*TODO / Stylovani file buttonu*/}
                <input type="file"
                       id="profile-photo" name="profile-photo"
                       accept="image/png, image/jpeg"/>
            </div>
            <br/>
            <div className={'is-public'}>
                Public profile:
                <label className="switch">
                    <input id='isPublicInput' type="checkbox" />
                    <span className="slider round"/>
                </label>
            </div>
        </form>

    //    TODO save button
    );
}

export default Profile_settings_form;
