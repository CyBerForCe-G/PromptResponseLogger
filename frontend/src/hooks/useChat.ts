import {Message} from "../models/message";
import {useState} from "react";
import {streamPrompt} from "../api/streamApi";

export function useChat() {
    const [messages, setMessages] = useState<Message[]>([]);
    const [streaming, setStreaming] = useState(false);

    const send = (prompt: string) => {
        if (!prompt.trim() || streaming) return;

        const userMsg: Message = {
            id: crypto.randomUUID(),
            role: "user",
            content: prompt
        };

        const assistantId = crypto.randomUUID();

        const assistantMsg: Message = {
            id: assistantId,
            role: "assistant",
            content: ""
        };

        setMessages(prev => [...prev, userMsg, assistantMsg]);
        setStreaming(true);

        streamPrompt(
            prompt,

            // ✅ Append raw token
            (token) => {
                setMessages(prev =>
                    prev.map(msg =>
                        msg.id === assistantId
                            ? {
                                ...msg,
                                content: msg.content + token
                            }
                            : msg
                    )
                );
            },

            // Complete
            () => {
                setStreaming(false);
            },

            // Error
            (error) => {
                console.error("Stream failed:", error);

                setMessages(prev =>
                    prev.map(msg =>
                        msg.id === assistantId
                            ? {
                                ...msg,
                                content:
                                    msg.content +
                                    "\n\n⚠️ Connection interrupted"
                            }
                            : msg
                    )
                );

                setStreaming(false);
            }
        );
    };

    return { messages, stream: { streaming }, send };
}
