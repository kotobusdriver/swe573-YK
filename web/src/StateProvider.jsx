import {useEffect, useState} from "react";
import {ApplicationContext} from "./ApplicationContext.jsx";

export const StateProvider = ({ children }) => {
    const userFromLocalStorage = localStorage.getItem("user");
    console.log("from local storage: " + userFromLocalStorage)
    const userData = userFromLocalStorage != null ? JSON.parse(userFromLocalStorage) : null;
    const [user, setUser] = useState(userData);

    function login(loggedInUser) {
        if (loggedInUser != null) {
            localStorage.setItem("user", loggedInUser);
            setUser(JSON.parse(loggedInUser));
        }
    }

    function logout() {
        localStorage.removeItem("user");
        setUser(null);
    }

    return (
        <ApplicationContext.Provider value={{
            user,
            login,
            logout
        }}>
            {children}
        </ApplicationContext.Provider>
    );
};