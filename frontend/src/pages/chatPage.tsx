import PromptInput from "../components/promptInput";
import ChatWindow from "../components/chatWindow";
import {useChat} from "../hooks/useChat";

export default function ChatPage() {
    const { messages, stream, send } = useChat();

    const hasMessages = messages.length > 0;

    return (
        <div className={`page ${hasMessages ? "has-messages" : "empty"}`}>
            <ChatWindow messages={messages} streaming={stream.streaming} />
            <PromptInput onSubmit={send} />
        </div>
    );
}
