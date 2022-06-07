import React from "react";
import './single-message.css'

export const Single_message = (props) => {
    return (
        <div className="single-message-box">
            <div className={"message-user-profilepicture"}>
                <img className={"profile-image"} src={props.image} alt={"testProfilePhoto"}/>
            </div>

            <div className={"message-box"}>
                <strong><p className={"request-link"}>{props.username}</p></strong>

                <p className={"request-link truncate"}>{props.message}</p>
            </div>
        </div>
    );
}

export default Single_message;