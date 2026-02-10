import PromptInput from "../components/PromptInput";
import ChatWindow from "../components/ChatWindow";
import { useChat } from "../hooks/useChat";

export default function ChatPage() {
    const { messages, stream, send } = useChat();

    return (
        <div className="page">
            <ChatWindow messages={messages} streaming={stream.streaming} />
            <PromptInput onSubmit={send} />
        </div>
    );
}
