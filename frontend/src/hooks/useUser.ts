import {useEffect, useState} from "react";
import {User} from "../models/user";
import {fetchUser} from "../api/userApi";

export function useUser() {
    const {user, setUser} = useState<User | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchUser()
            .then(setUser)
            .finally(() => setLoading(false));
    }, []);

    return {user, loading};
}