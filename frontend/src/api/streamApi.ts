export function streamPrompt(
    prompt: string,
    onToken: (token: string) => void,
    onComplete: () => void,
    onError?: (err: any) => void
) {
    fetch("/api/stream", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ prompt })
    }).catch(onError);

    const source = new EventSource("/api/stream");

    source.onmessage = (event) => {
        onToken(event.data);
    };

    source.onerror = (err) => {
        source.close();
        onError?.(err);
        onComplete();
    };

    return () => source.close();
}
