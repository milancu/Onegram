
import {useState} from "react";
import './profile_dashboard.css'
import Single_follow from "../../components/Single_follow";

const Form = () => {



    const valid = (e) => {
        e.preventDefault();
    }


    return (
        <div className={"center-follower-box"}>
            <div className={'follow-list'}>
                <Single_follow />
                <Single_follow />
                <Single_follow />
                <Single_follow />
                <Single_follow />
                <Single_follow />
            </div>

        </div>

    )
}

let props;

export const ModalFollowing = (prop) => {
    props = prop;
    if (!props.show) return (<></>);
    return <Form/>
}
