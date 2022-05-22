import React from "react";
import './profile-settings-form.css'
import {Link} from "react-router-dom";

import './profile-settings-form.css'

//TODO openable
//TODO potencialne zmena hesla a emailu

const testProfilePhoto = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
const nickname = 'Test_nickname'

export const Profile_settings_form = () => {
    return (
        <form className={"settings-form"}>
            {/*    username, link, bio, profileImage, public*/}

            <label htmlFor="username">Username:</label><br/>
            <input type="text" id="username" name="username"/><br/>
            <label htmlFor="bio">Bio:</label><br/>
            <textarea id="bio" name="bio" rows="8" cols="50">
            </textarea><br/>
            <label htmlFor="link">Link:</label><br/>
            <input type="text" id="link" name="link"/><br/>
            <div className={"setting-photo"}>
                <div className={"Post-user-profilepicture profile-image"}>
                    <img src={testProfilePhoto}/>
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
                    <input type="checkbox" />
                    <span className="slider round"/>
                </label>
            </div>
        </form>
    );
}

export default Profile_settings_form;