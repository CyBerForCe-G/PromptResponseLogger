import { useEffect, useRef } from "react";
import { Message } from "../models/Message";
import ReactMarkdown from "react-markdown";
import {Prism as SyntaxHighLighter} from "react-syntax-highlighter";
import {oneDark} from "react-syntax-highlighter/dist/esm/styles/prism";
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

    const isLastAssistantMessage = (index: number) => {
        return messages[index]?.role === "assistant" && index === messages.length - 1;
    };

    return (
        <div className="chat-window">
            {messages.map((msg, index) => (
                <div key={msg.id} className={`bubble ${msg.role}`}>
                    <ReactMarkdown remarkPlugins={[remarkGfm]}
                    components={{
                        code({node, className, children, ...props}){
                            const match= /langauge-(\w+)/.exec(className || "");
                            const codeString = String(children).replace(/n$/, "");

                            if (match){
                                return (
                                    <div className="code-block">
                                        <div className="code-header">
                                            <span className="code-language">{match[1]}</span>
                                            <button className="copy-btn"
                                                    onClick={() => navigator.clipboard.writeText(codeString)}
                                            >Copy</button>
                                        </div>
                                        <SyntaxHighLighter
                                            style={oneDark}
                                            language={match[1]}
                                            PreTag="div"
                                            customStyle={{
                                                margin: 0,
                                                borderRadius: "0 0 8px 8px",
                                                fontSize: "13px"
                                            }}></SyntaxHighLighter>
                                    </div>
                                );
                            }
                            return (
                                <code className="inline-code" {...props}>
                                    {children}
                                </code>
                            );
                        }
                    }}>
                        {msg.content}
                    </ReactMarkdown>
                    {streaming && isLastAssistantMessage(index) && (
                        <span className="typing-curosr"></span>
                    )}
                </div>
            ))}
            <div ref={bottomRef} />
        </div>
    );
}
