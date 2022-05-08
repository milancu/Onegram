// src/components/Profile_dashboard/index.js

import React, {Component} from 'react';
import './App.css';
import Profile from '../../components/Profile';
import Header from "../../components/Header";

class Profile_dashboard extends Component {
    render() {
        console.log("cauky mnauky2")
        return (
            <div className="App">
                <Header nickname="John D. Veloper"
                        profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>
                <br/>
                <br/>
                <Profile nickname="John D. Veloper"
                         profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>
            </div>
        );
    }
}

export default Profile_dashboard;