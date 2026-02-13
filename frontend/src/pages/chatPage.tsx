import PromptInput from "../components/promptInput";
import ChatWindow from "../components/chatWindow";
import {useChat} from "../hooks/useChat";
import {User} from "../models/user";
import UserHeader from "../components/UserHeader";

interface Props{
    user: User;
}

export default function ChatPage({user}: Props) {
    const { messages, stream, send } = useChat();

    const hasMessages = messages.length > 0;

    return (
        <div className={`page ${hasMessages ? "has-messages" : "empty"}`}>
            <UserHeader user={user}/>
            <ChatWindow messages={messages} streaming={stream.streaming} />
            <PromptInput onSubmit={send} />
        </div>
    );
}
