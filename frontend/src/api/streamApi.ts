export async function streamPrompt(
    prompt: string,
    onToken: (token: string) => void,
    onComplete: () => void,
    onError?: (err: any) => void
) {
    try {
        const response = await fetch("/api/stream", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ prompt })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const reader = response.body?.getReader();
        const decoder = new TextDecoder();

        if (!reader) {
            throw new Error("Stream reader not available");
        }

        let buffer = "";
        let completed = false;

        while (true) {
            const { value, done } = await reader.read();
            if (done) break;

            buffer += decoder.decode(value, { stream: true });

            let boundary;
            while ((boundary = buffer.indexOf("\n\n")) !== -1) {
                const rawEvent = buffer.slice(0, boundary);
                buffer = buffer.slice(boundary + 2);

                const lines = rawEvent.split("\n");
                let eventData = "";

                for (const line of lines) {
                    if (line.startsWith("data:")) {
                        eventData += line.slice(5);
                    }
                }

                if (eventData === "[DONE]") {
                    completed = true;
                    break;
                }

                if (eventData === "[ERROR]") {
                    throw new Error("Backend streaming error");
                }

                if (eventData) {
                    onToken(eventData);
                }
            }
        }

        if (!completed) {
            throw new Error("Stream ended unexpectedly");
        }

        onComplete();
    } catch (err) {
        console.error("Streaming error:", err);
        onError?.(err);
        onComplete();
    }
}
