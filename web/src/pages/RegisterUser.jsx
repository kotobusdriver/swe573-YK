import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

function RegisterUser() {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    function registerUser() {
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    name: new String(name),
                    email: new String(email),
                    password: new String(password),
                }
            )
        };

        fetch(`/api/users`, requestOptions)
            .catch(error => console.error(error));

        goHome();
    }

    let navigate = useNavigate();
    const goHome = () =>{
        let path = `home`;
        navigate(path);
    }

    return (
        <>
            <div className="m-5 border border-light-subtle p-3 rounded-2">
                <h6>Register a user</h6>
                <form>
                    <div className="mb-3">
                        <label htmlFor="exampleInputName1" className="form-label">Name</label>
                        <input type="text" className="form-control" id="inputName" value={name}
                               onChange={(e) => setName(e.target.value)}/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
                        <input type="email" className="form-control" id="inputEmail" aria-describedby="emailHelp" value={email}
                               onChange={(e) => setEmail(e.target.value)}/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
                        <input type="password" className="form-control" id="inputPassword" value={password}
                               onChange={(e) => setPassword(e.target.value)}/>
                    </div>
                    <button type="button" className="btn btn-primary" onClick={registerUser}>Register</button>
                </form>
            </div>
        </>
    )
}

export default RegisterUser
