import { useContext } from 'react';
import {ApplicationContext} from "../ApplicationContext.jsx";

function TopBar() {
    const context = useContext(ApplicationContext);
    console.log("Context : " + context);


    return (
        <>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container-fluid">
                    <a className="navbar-brand" href="/">Community Web</a>
                    <form className="d-flex" role="search">
                        <input className="form-control me-2" type="search" placeholder="Search"
                               aria-label="Search"/>
                        <button className="btn btn-outline-success" type="submit">Search</button>
                    </form>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="p-2">
                        <div className="collapse navbar-collapse" id="navbarNav">
                            <ul className="navbar-nav">
                                <li className="nav-item">
                                    <a className="nav-link" href="#">My Feed</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="#">Inbox</a>
                                </li>
                                {
                                    context.user ?
                                        (
                                            <li className="nav-item">
                                                <a className="nav-link" href="#">{context.user.name}</a>
                                            </li>
                                        )
                                        : (
                                            <div>
                                            <li className="nav-item">
                                                <a className="nav-link" href="/register">Register</a>
                                            </li>
                                            <li className="nav-item">
                                                <a className="nav-link" href="/login">Log in</a>
                                            </li>
                                            </div>
                                        )
                                }
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>
        </>
    )
}

export default TopBar
