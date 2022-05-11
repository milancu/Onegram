// src/components/Profile_dashboard/index.js

import React, {Component} from 'react';
import '../../App.css';
import Profile from '../../components/Profile';
import Header from "../../components/Header";
import Footer from "../../components/Footer";

export const Profile_dashboard = () => {
    return (
        <div className="App">
            <Header nickname="John D. Veloper"
                    profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>
            <Profile nickname="John D. Veloper"
                     profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
                     description="Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed convallis magna eu sem. Aenean vel massa quis mauris vehicula lacinia. Nunc tincidunt ante vitae massa. Fusce nibh. Cras pede libero, dapibus nec, pretium sit amet, tempor quis. Maecenas libero. Pellentesque ipsum. Mauris metus. Nullam lectus justo, vulputate eget mollis sed, tempor sed magna. Fusce consectetuer risus a nunc. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. In rutrum. Mauris suscipit, ligula sit amet pharetra semper, nibh ante cursus purus, vel sagittis velit mauris vel metus. Pellentesque ipsum. Sed ac dolor sit amet purus malesuada congue. Etiam posuere lacus quis dolor. Duis viverra diam non justo."
                     webLinkUrl={"CaukyMnauky.com"}
                     follows={56}
                     followers={8964}
                     postsNumber={87}
            />
            <div className={"postImageContainer"}>

            </div>
            <Footer />
        </div>
    )
}

export default Profile_dashboard;