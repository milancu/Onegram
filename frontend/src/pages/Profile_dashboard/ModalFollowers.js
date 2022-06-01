import React, { Component }  from 'react';


const Followers = () => {
    return (
        <div>

        </div>

    )
}



let props;

export const ModalFollowers = (prop) => {
    props = prop;
    if (!props.show) return (<></>);
    return <Followers/>
}
