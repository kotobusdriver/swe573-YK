import { Outlet } from "react-router-dom";
import TopBar from '../components/TopBar'

function Layout() {
    return (
    <>
        <TopBar/>
        <Outlet />
    </>
)
}

export default Layout
