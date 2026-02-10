import { useState } from "react";

interface Props {
    onSubmit: (text: string) => void;
}

export default function PromptInput({ onSubmit }: Props) {
    const [value, setValue] = useState("");

    const handleSend = () => {
        if (!value.trim()) return;
        onSubmit(value);
        setValue("");
    };

    return (
        <div className="prompt-input">
        <textarea
            value={value}
    onChange={e => setValue(e.target.value)}
    placeholder="Ask something..."
    />
    <button onClick={handleSend}>Send</button>
        </div>
);
}
