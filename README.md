# AI Experiment – Swagger/OpenAPI Analyzer with RAG

This project is an **experimental learning initiative** for exploring how to use
AI, RAG (Retrieval-Augmented Generation), and automated code analysis to
evaluate **Swagger/OpenAPI specifications** against a set of **API guideline rules**.

The purpose is **learning**, not production readiness.  

## 🎯 Goals

- Build a simple system that can:
  1. Read a Swagger/OpenAPI file
  2. Parse it into a structured form
  3. Check it against a set of API guideline rules
  4. Use a local RAG store to fetch relevant rules
  5. Use an LLM to generate an analysis report
- Keep the project completely **outside internal infrastructure**
- Avoid using proprietary information
- Use only **public** documentation or generic API principles
- Build something end-to-end in a short timeframe
- Focus on **learning modern AI tooling**

## Implementation Steps

- ✅ Set up backend (Spring Boot skeleton, health check, upload endpoint) 
- ✅ Add Swagger/OpenAPI parsing for .yaml, .yml, .json
- ✅ Extract basic metadata (title, version, paths, operations)
- ✅ Create /rules folder and add initial guideline documents
- ✅ Add OpenAI embedding integration and working test endpoint
- ✅ Embed rule documents and store them in vector store (in-memory)
- ✅ Implement vector search to retrieve the most relevant rules
- ❌ Add support for calling an LLM (cloud first, Ollama optional)
- ❌ Build prompt combining: API metadata + retrieved rules
- ❌ Generate structured analysis (issues, warnings, improvements)
- ❌ Implement a simple React UI for uploading files and showing results
- ❌ End-to-end flow: upload → parse → RAG → LLM → display
- ❌ Optional stretch goals (if time allows):
    - ❌ auto-fix suggestions
    - ❌ API diffing
    - ❌ export results

## 🏗 Project Architecture

```text
ai-experiment/
├── backend/                # Java + Spring Boot service
│   ├── src/main/java/...   # Controllers, DTOs, service logic
│   ├── src/main/resources/ # application.properties, rule embeddings, etc.
│   └── pom.xml
│
├── frontend/               # React UI (Node)
│   ├── public/
│   ├── src/
│   └── package.json
│
├── rules/                  # Text files with API rules for RAG
│   ├── naming.md
│   ├── paths.md
│   ├── errors.md
│   ├── security.md
│   └── bim-keys.md
│
├── samples/                # Example Swagger/OpenAPI specs
│   ├── public-api.yaml
│   └── example.yml
│
└── README.md
```

## 🧰 Tech Stack

### Backend
- Java 21+ (OpenJDK)
- Spring Boot
- Spring AI (OpenAI integration)
- Swagger Parser (OpenAPI 3)
- In-memory Vector Store (for development/learning)
- Retrieval-Augmented Generation (RAG)
- LLM provider integration (cloud or local)

### Frontend
- Node + React  
- UI for uploading Swagger files and viewing results  

## 🔧 Backend Status

The backend is currently **up and running** with file upload and OpenAPI parsing.

### ✔ Running the backend

```bash
cd backend
mvn spring-boot:run
```

Available at:

```
http://localhost:8080
```

### ✔ Health check endpoint

```
GET /api/health
→ "Backend is running"
```

### ✔ Upload endpoint (Swagger/OpenAPI files)

```
POST /api/upload
Content-Type: multipart/form-data
```

Accepts:
- `.yaml`
- `.yml`
- `.json`

### ✔ OpenAPI parsing implemented

Uploaded files are parsed into a real OpenAPI model (`io.swagger.v3.oas.models.OpenAPI`).

The backend currently returns:

- API title
- Version
- Number of paths
- Filename

Example response:

```json
{
  "file": "example.yml",
  "title": "Sample API",
  "version": "1.0",
  "paths": 1
}
```

### ✔ Current backend structure

```
backend/
├── pom.xml
├── src/main/java/com/ai/experiment/AiExperimentApplication.java
├── src/main/java/com/ai/experiment/services/OpenApiService.java
└── src/main/java/com/ai/experiment/controllers/
      ├── HealthController.java
      └── UploadController.java
```

## 📁 Sample OpenAPI Files

```
samples/
└── example.yml
```

The backend supports `.yaml`, `.yml`, and `.json`.
The included example uses `.yml`.

## 🧪 Testing the Upload Endpoint

Start backend:

```bash
cd backend
mvn spring-boot:run
```

Upload a file (from project root):

```bash
curl -F "file=@samples/example.yml" http://localhost:8080/api/upload
```

Summarize a file (from *backend* root):

```bash
curl -F "file=@../samples/PaymentInitiation.json" http://localhost:8080/api/summary
```

With absolute path:

```bash
curl -F "file=@/full/path/to/ai-experiment/samples/example.yml"      http://localhost:8080/api/upload
```

## 🔍 Testing the RAG System

The backend includes a **vector search** system for finding relevant API guidelines.

### Environment Setup

Set your OpenAI API key:

```bash
export OPENAI_API_KEY="sk-your-api-key-here"
```

### ✅ Reindex Rules

Load and embed all rule documents from the `/rules` directory:

```bash
# Reindex all rules
curl -X POST http://localhost:8080/api/rules/reindex

# Response
{
  "message": "Successfully indexed 45 rule chunks from 7 files"
}
```

This command:
- Reads all `.md` files from `../rules/`
- Splits them into chunks
- Generates embeddings using OpenAI
- Stores them in the in-memory vector store

### ✅ Search for Relevant Rules

Search for rules using natural language queries:

```bash
# Search for naming conventions (pretty output with jq)
curl -s "http://localhost:8080/api/rules/search?query=naming+conventions&topK=3" | jq '.'

# Search for error handling
curl -s "http://localhost:8080/api/rules/search?query=error+handling&topK=5" | jq '.'

# Search for versioning best practices
curl -s "http://localhost:8080/api/rules/search?query=API+versioning&topK=3" | jq '.'

# Search for BIM keys (domain-specific)
curl -s "http://localhost:8080/api/rules/search?query=BIM+keys&topK=3" | jq '.'
```

**Example Response:**

```json
[
  {
    "fileName": "naming.md",
    "text": "Use lowercase with hyphens for path segments...",
    "similarity": 0.87
  },
  {
    "fileName": "versioning.md",
    "text": "Include version in URL path: /v1/resource...",
    "similarity": 0.82
  }
]
```

### Useful Search Commands

```bash
# Show only fileName and first 100 chars
curl -s "http://localhost:8080/api/rules/search?query=naming&topK=3" | \
  jq '.[] | {fileName, preview: (.text | .[0:100])}'

# Get only the most relevant result
curl -s "http://localhost:8080/api/rules/search?query=error+codes&topK=1" | jq '.[0]'

# Count results
curl -s "http://localhost:8080/api/rules/search?query=security&topK=10" | jq 'length'
```

**Install jq** (for pretty JSON):

```bash
# macOS
brew install jq

# Ubuntu/Debian
sudo apt install jq
```

## 🤖 LLM Setup: Cloud vs Local (Ollama)

This project supports **two modes** for the analysis step.

### 1. Cloud LLMs (Recommended)

Examples:
- Claude 3.5 Sonnet
- ChatGPT (GPT-4.1, GPT-4o)
- Gemini / DeepSeek / Groq models

**Pros**
- Best analysis quality
- Fast responses
- No installation
- Works reliably on any machine

**Cons**
- Requires API key
- Internet required
- Token cost applies

### 2. Local LLMs via Ollama (Optional)

Supports models like:
- `llama3.2:3b`
- `mistral:7b-instruct-q4`
- `llama3.1:8b-q4`

Good for offline experiments.

## 🚀 Getting Started

### 1. Prerequisites

| Tool | Version | Check |
|------|---------|--------|
| Java | 21 or 25 | `java -version` |
| Maven | 3.x | `mvn -v` |
| Node | 18+ | `node -v` |
| Git | Latest | `git --version` |
| jq (optional) | Latest | `jq --version` |

### 2. Clone the Repository

```bash
git clone https://github.com/<your-org>/ai-experiment.git
cd ai-experiment
```

## 🔍 How the Analysis Works

1. Rule documents in `/rules/` are embedded into an in-memory vector store.
2. User uploads a Swagger/OpenAPI spec through the frontend.
3. Backend:
   - Parses YAML or JSON
   - Extracts operations, schemas, paths
   - Retrieves relevant rules via vector search (semantic similarity)
   - Builds an LLM prompt using RAG context
   - Receives a structured analysis
4. Frontend displays:
   - Guideline violations
   - Improvement suggestions
   - A readable analysis report

## 📌 Notes & Limitations

- This is an **educational experiment**, not a production tool.
- Only public API documentation or generic guidelines are allowed.
- No proprietary/internal Swagger files should be uploaded.
- Performance and scalability are intentionally simplified.

## 🔮 Future Ideas

- Auto-fix proposals
- Swagger version diffing
- Rule plugin system
- Export analysis reports
- Pre-validation with strict OpenAPI validators