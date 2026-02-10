import { useState } from "react";
import { streamPrompt } from "../api/streamApi";

export function useStream() {
    const [text, setText] = useState("");
    const [streaming, setStreaming] = useState(false);

    const start = (prompt: string) => {
        setText("");
        setStreaming(true);

        return streamPrompt(
            prompt,
            token => setText(prev => prev + token),
            () => setStreaming(false)
        );
    };

    return { text, streaming, start };
}
