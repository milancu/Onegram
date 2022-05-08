import {Route, Routes} from "react-router-dom";
import React from 'react';

import {
    Header,
    Post,
    Profile,
    Profile_dashboard,
} from "../components";

export const UserRoutes = () => {
    return (<React.Fragment>
        <Routes>
            <Route exact path="/index" element={<Header/>}/>
            <Route exact path="/index" element={<Post/>}/>
            <Route exact path="/index" element={<Profile/>}/>
            <Route exact path="/index" element={<Profile_dashboard/>}/>
        </Routes>
    </React.Fragment>)
}
