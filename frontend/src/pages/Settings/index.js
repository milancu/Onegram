// src/components/Settings/index.js

import React, {Component} from 'react';
import './settings.css';
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import {Follow_requests, Profile_settings_form
} from "../../pages";
import * as Constants from "../../gql/query";

export const Settings = () => {

    // function changeProfile() {
    //
    //     let description = document.getElementById('description').value;
    //     const query = `{"query":"mutation {\\n\\tcreatePost(input:{\\n\\t\\tdescription:\\"` + description + `\\"\\n\\t}){\\n\\t\\tid\\n\\t}\\n}"}`;
    //
    //     let formData = new FormData();
    //     formData.append("operations", query);
    //     formData.append("file", images[0].file);
    //     console.log(images[0].file)
    //
    //     let xhr = new XMLHttpRequest();
    //     xhr.open('POST', Constants.POST_GRAPHQL_API, true);
    //     xhr.setRequestHeader('Authorization', "Bearer " + localStorage.getItem('token'));
    //     xhr.send(formData);
    // }

    return (
        <div>
            <Header />
            <Follow_requests />
            <h2>Profile settings</h2>
            <Profile_settings_form />
            <Footer />
        </div>
    )
}

export default Settings;