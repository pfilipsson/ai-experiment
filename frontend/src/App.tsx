import { useState } from "react";
import type { FormEvent } from "react";

function App() {
  const [responseText, setResponseText] = useState<string>("");
  const [error, setError] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const form = event.currentTarget;
    const formData = new FormData(form);

    try {
      setIsLoading(true);
      setError(null);
      setResponseText("");

      const res = await fetch("/api/analyze", {
        method: "POST",
        body: formData,
      });

      if (!res.ok) {
        throw new Error(`Server returned ${res.status}`);
      }

      const text = await res.text();
      setResponseText(text);
    } catch (e) {
      console.error(e);
      setError((e as Error).message);
    } finally {
      setIsLoading(false);
    }
  };

  let displayData = "";

  try {
    displayData =  JSON.parse(responseText).llmOutput;
  } catch (_e) {}
  return (
    <>
      <main className="container" style={{ marginTop: "30px" }}>
        <article>
          <h1>Validate APIs</h1>

          <form
            onSubmit={handleSubmit}
            method="POST"
            encType="multipart/form-data"
            action="/api/analyze"
          >
            <input type="file" name="file" required />
            <br />
            <input
              type="submit"
              value={isLoading ? "Uploading..." : "Upload"}
              disabled={isLoading}
            />
          </form>
        </article>

        <article>
          <h2>Response</h2>

          <pre>
            {isLoading && "Loading...\n"}
            {error && `Error: ${error}\n`}
            <pre style={{ whiteSpace: "pre-wrap" }}>
              {displayData.replace(/\\n/g, "\n")}
            </pre>
          </pre>
        </article>
      </main>
    </>
  );
}

export default App;
