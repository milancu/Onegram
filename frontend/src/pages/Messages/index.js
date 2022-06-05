import React, {Component} from 'react';
import './messages.css';
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import Single_message from "../../components/Single_message";
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";
import * as Constants from "../../gql/query";

export const Messages = () => {

    let messagesData;

    axios.post(Constants.USER_GRAPHQL_API,
        {
            query: Constants.GET_LATEST_MESSAGE
        }, {
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            }
        }).then(r => {
        messagesData = r.data.data.latestMessages;
        localStorage.setItem('messagesData', JSON.stringify(messagesData));
        // console.log(messagesData);
    })

    const username = 'test'
    const message = 'message'
    const image = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"

    return (
        <div>
            <Header/>
            <div className={"messages"}>
                <h2 className={"messages-h2"}>Messages</h2>

                {JSON.parse(localStorage.getItem('messagesData')).map(message => (
                    <Link to={'/message-detail'}>
                            <Single_message username={message.receiver.username} message={message.message} image={message.receiver.image}/>
                    </Link>
                ))}
            </div>

            <Footer/>
        </div>
    )
}

export default Messages;