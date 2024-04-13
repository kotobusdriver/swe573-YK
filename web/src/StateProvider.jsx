import {useState} from "react";
import {ApplicationContext} from "./ApplicationContext.jsx";

export const StateProvider = ({ children }) => {
    const [state, setState] = useState(false);
    function login() {
        setState(true);
    }
    return (
        <ApplicationContext.Provider value={{
            state,
            login
        }}>
            {children}
        </ApplicationContext.Provider>
    );
};