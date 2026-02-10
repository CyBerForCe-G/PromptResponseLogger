export function createSSE(
    url: string,
    onMessage: (data: string) => void,
    onError?: () => void
) {
    const source = new EventSource(url);

    source.onmessage = (event) => onMessage(event.data);
    source.onerror = () => {
        source.close();
        onError?.();
    };

    return () => source.close();
}
