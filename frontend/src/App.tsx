import ChatPage from "./pages/ChatPage";
import "./styles/app.css";
import {useUser} from "./hooks/useUser";
import LoginPage from "./pages/LoginPage";

export default function App() {
    const {user, loading} = useUser();

    if(loading) {
        return (
            <div className="loading-screen">
                <div className="spinner"></div>
            </div>
        );
    }
    if(!user) {
        return <LoginPage/>
    }
    return <ChatPage user={user}/>;
}
