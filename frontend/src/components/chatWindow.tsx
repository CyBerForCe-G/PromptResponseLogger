import { Message } from "../models/Message";
import MessageBubble from "./messageBubble";

interface Props {
    messages: Message[];
    streaming: boolean;
}

export default function ChatWindow({ messages, streaming }: Props) {
    return (
        <div className="chat-window">
            {messages.map((m, idx) => (
                <MessageBubble
                    key={m.id}
                    message={m}
                    streaming={streaming && idx === messages.length - 1}
                />
            ))}
        </div>
    );
}
