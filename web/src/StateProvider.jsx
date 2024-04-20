import {useState} from "react";
import {ApplicationContext} from "./ApplicationContext.jsx";

export const StateProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    function login(user) {
        if (user != null) {
            setUser(user);
        }
    }
    return (
        <ApplicationContext.Provider value={{
            user,
            login
        }}>
            {children}
        </ApplicationContext.Provider>
    );
};