import React, {Component} from 'react';
import './messages.css';
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import Single_message from "../../components/Single_message";
import {Link, useNavigate} from "react-router-dom";

export const Messages = () => {
    return (
        <div>
            <Header nickname="John D. Veloper"
                    profilepicture="https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"/>

            <div className={"messages"}>
                    <h2 className={"messages-h2"}>Messages</h2>
                    <Link to={'/message-detail'}>
                    <Single_message />
                    </Link>
                    <Link to={'/message-detail'}>
                            <Single_message />
                    </Link>
                    <Link to={'/message-detail'}>
                            <Single_message />
                    </Link>
                    <Link to={'/message-detail'}>
                            <Single_message />
                    </Link>
                    <Link to={'/message-detail'}>
                            <Single_message />
                    </Link>
                    <Link to={'/message-detail'}>
                            <Single_message />
                    </Link>
                    <Link to={'/message-detail'}>
                            <Single_message />
                    </Link>
            </div>

            <Footer/>
        </div>
    )
}

export default Messages;