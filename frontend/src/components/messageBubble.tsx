import { Message } from "../models/Message";
import StreamingCursor from "./streamCursor";


interface Props {
    message: Message;
    streaming?: boolean;
}

export default function MessageBubble({ message, streaming }: Props) {
    return (
        <div className={`bubble ${message.role}`}>
    <span>{message.content}</span>
    {streaming && <StreamingCursor />}
    </div>
);
}
