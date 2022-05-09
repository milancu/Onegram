import {Route, Routes} from "react-router-dom";
import React from 'react';

import {
    Profile_dashboard,
    Profile
} from "../pages"; //cte z indexu kde je to namapovani

export const UserRoutes = () => {
    return (<React.Fragment>
        <Routes>
            <Route exact path="/index" element={<Profile/>}/>
            <Route exact path="/index" element={<Profile_dashboard/>}/>
        </Routes>
    </React.Fragment>)
}
