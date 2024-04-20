import {useContext, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {ApplicationContext} from "../ApplicationContext.jsx";

function Login() {
    const appContext = useContext(ApplicationContext);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [hasError, setHasError] = useState(false);

    function login() {
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    email: new String(email),
                    password: new String(password),
                }
            )
        };

        fetch(`http://localhost:8080/api/login`, requestOptions)
            .then(response => {
                if(!response.ok) throw new Error("error");
                else return response.text()
            })
            .then(user => setLoggedIn(user))
            .catch(error => handleLoginFailure(error));
    }

    function setLoggedIn(user) {
        appContext.login(user);
        console.log("logged in");
        setHasError(false);
        goHome();
    }

    function handleLoginFailure(error) {
        console.error(error);
        setHasError(true);
    }

    let navigate = useNavigate();
    const goHome = () => {
        let path = `home`;
        navigate(path);
    }

    return (
        <>
            <div className="m-5 border border-light-subtle p-3 rounded-2">
                <h6>Login</h6>
                <form>
                    <div className="mb-3">
                        <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
                        <input type="email" className="form-control" id="inputEmail"
                               aria-describedby="emailHelp"
                               value={email}
                               onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
                        <input type="password" className="form-control" id="inputPassword" value={password}
                               onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                    {
                        hasError &&
                            <div>
                                Invalid login
                            </div>
                    }
                    <button type="button" className="btn btn-primary" onClick={login}>Login</button>
                </form>
            </div>
        </>
    )
}

export default Login
