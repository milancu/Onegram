// src.App.js

import React from 'react';
import './App.css';
import {Profile, Profile_dashboard} from "./pages";

// import {UserRoutes} from "./routes/UserRoutes.js";

export const App = (props) => {
    function useRoutes(props) {
        return undefined;
    }

    return useRoutes([
        {element: <Profile_dashboard/>},
        {element: <Profile/>}
    ]);
}

export default App;
