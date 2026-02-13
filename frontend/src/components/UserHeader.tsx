import {User} from "../models/user"

interface Props {
    user: User
}

export default function UserHeader({user} : Props){
    return (
    <div className="user-header">
        <div className="user-info">
            {user.picture && (
                <img
                    src={user.picture}
                    alt={user.name}
                    className="user-avatar"/>
            )}
            <span className="user-name">{user.name}</span>
        </div>
        <a href="/logout" className="logout-btn">Logout</a>
    </div>
    );
}