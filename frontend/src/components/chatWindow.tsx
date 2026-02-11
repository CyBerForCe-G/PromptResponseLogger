import { useEffect, useRef } from "react";
import { Message } from "../models/Message";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";

interface Props {
    messages: Message[];
    streaming: boolean;
}

export default function ChatWindow({ messages, streaming }: Props) {
    const bottomRef = useRef<HTMLDivElement | null>(null);

    useEffect(() => {
        bottomRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [messages]);

    return (
        <div className="chat-window">
            {messages.map(msg => (
                <div key={msg.id} className={`bubble ${msg.role}`}>
                    <ReactMarkdown remarkPlugins={[remarkGfm]}>
                        {msg.content}
                    </ReactMarkdown>
                </div>
            ))}
            <div ref={bottomRef} />
        </div>
    );
}
