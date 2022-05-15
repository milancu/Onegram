import React from "react";
import './single-message.css'
import {Link} from "react-router-dom";

const testProfilePhoto = "https://t4.ftcdn.net/jpg/02/19/63/31/360_F_219633151_BW6TD8D1EA9OqZu4JgdmeJGg4JBaiAHj.jpg"
const nickname = 'Test_nickname'
//TODO pak vzresit ykracovani nahledu zpravy
const testText = 'Lorem ipsum (zkráceně lipsum) je označení pro standardní pseudolatinský text užívaný v grafickém designu a navrhování jako demonstrativní výplňový text při vytváření pracovních ukázek grafických návrhů (např. internetových stránek, rozvržení časopisů či všech druhů reklamních materiálů). Lipsum tak pracovně znázorňuje text v ukázkových maketách (tzv. mock-up) předtím, než bude do hotového návrhu vložen smysluplný obsah.'

export const Single_message = () => {
    return (
        <div className="single-message-box">
            {/*TODO link to id*/}
            <div className={"message-user-profilepicture"}>
                <img className={"profile-image"} src={testProfilePhoto} alt={"testProfilePhoto"}/>
            </div>

            <div className={"message-box"}>
                <strong><p className={"request-link"}>{nickname}</p></strong>

                <p className={"request-link truncate"}>{testText}</p>
            </div>
        </div>
    );
}

export default Single_message;