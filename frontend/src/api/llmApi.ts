export async function sendPrompt(prompt: string): Promise<string> {
    const response = await fetch("/api/prompt", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ prompt })
    });

    if (!response.ok) {
        throw new Error("Failed to send prompt");
    }

    return response.text();
}
