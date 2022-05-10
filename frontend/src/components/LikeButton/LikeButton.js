import React, {useState} from "react";
import cn from "classnames";
import {ReactComponent as Hand} from "./hand.svg";
import "./LikeButton.scss";

const LikeButton = () => {
    const [liked, setLiked] = useState(null);
    const [clicked, setClicked] = useState(false);

    return (
        <button
            onClick={() => {
                setLiked(!liked);
                setClicked(true);
            }}
            onAnimationEnd={() => setClicked(false)}
            className={cn("like-button-wrapper", {
                liked,
                clicked,
            })}
        >
            <div className="like-button">
                <Hand />
                <span>Like</span>
                <span className={cn("suffix", { liked })}>d</span>
            </div>
        </button>
    );
};

export default LikeButton;