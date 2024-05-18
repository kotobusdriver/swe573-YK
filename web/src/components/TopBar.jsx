import {useContext, useState} from 'react';
import {ApplicationContext} from "../ApplicationContext.jsx";
import {useNavigate} from "react-router-dom";

function TopBar() {
    const context = useContext(ApplicationContext);
    const [searchText, setSearchText] = useState(null);

    function logout() {
        context.logout();
        goHome();
    }

    let navigate = useNavigate();
    const goHome = () => {
        let path = `home`;
        navigate(path);
    }

    const goToSearchResults = () => {
        let path = `search-results/${searchText}`;
        navigate(path);
    }

    function search() {
        goToSearchResults();
    }

    return (
        <>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container-fluid">
                    <a className="navbar-brand" href="/">GetToGether</a>
                    <form className="d-flex" role="search">
                        <input className="form-control me-2" type="search" placeholder="Search"
                               aria-label="Search"
                               value={searchText}
                               onChange={(e) => setSearchText(e.target.value)}/>
                        <button className="btn btn-outline-success" type="button" onClick={search}>Search</button>
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
                                    context.user &&
                                    <li className="nav-item">
                                        <a className="nav-link" href="#">{context.user.name}</a>
                                    </li>
                                }
                                {
                                    context.user &&
                                    <li className="nav-item">
                                        <a className="nav-link" href="#" onClick={logout}>Logout</a>
                                    </li>
                                }
                                {
                                    !context.user &&
                                    <li className="nav-item">
                                        <a className="nav-link" href="/register">Register</a>
                                    </li>
                                }
                                {
                                    !context.user &&
                                    <li className="nav-item">
                                        <a className="nav-link" href="/login">Log in</a>
                                    </li>
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
