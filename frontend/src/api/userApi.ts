import {User} from "../models/user"
export async function fetchUser(): Promise<User | null> {
    try{
        const response = await fetch("/api/user", {
            credentials: "include"
        });

        if(response.status == 401 || response.status == 403){
            return null;
        }

        if(!response.ok){
            throw new Error(`HTTP error! status ${response.status}`);
        }

        const text = await response.text();
        if(!text){
            return null;
        }
        return JSON.parse(text);
    }catch(err){
        console.error("Failed to fetch user: ", err);
        return null;
    }
}