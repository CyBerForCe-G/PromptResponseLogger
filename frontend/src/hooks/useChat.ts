import { useState } from "react";
import { Message } from "../models/Message";
import { useStream } from "./useStream";

export function useChat() {
    const [messages, setMessages] = useState<Message[]>([]);
    const stream = useStream();

    const send = (prompt: string) => {
        const userMsg: Message = {
            id: crypto.randomUUID(),
            role: "user",
            content: prompt
        };

        setMessages(prev => [...prev, userMsg]);

        stream.start(prompt);

        const assistantMsg: Message = {
            id: crypto.randomUUID(),
            role: "assistant",
            content: ""
        };

        setMessages(prev => [...prev, assistantMsg]);
    };

    return { messages, stream, send };
}
