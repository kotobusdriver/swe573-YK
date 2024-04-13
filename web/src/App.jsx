import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from './pages/Layout'
import Home from './pages/Home'
import CreateCommunity from './pages/CreateCommunity'
import RegisterUser from './pages/RegisterUser'
import Login from "./pages/Login.jsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<Home/>}/>
                    <Route path="create-community" element={<CreateCommunity/>}/>
                    <Route path="register" element={<RegisterUser/>}/>
                    <Route path="login" element={<Login/>}/>
                    <Route path="*" element={<Home/>}/>
                </Route>
            </Routes>
        </BrowserRouter>
)
}

export default App
