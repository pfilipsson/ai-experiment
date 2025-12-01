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
- ✅ Add support for calling an LLM (cloud first, Ollama optional)
- ✅ Build prompt combining: API metadata + retrieved rules
- ✅ Generate structured analysis (issues, warnings, improvements)
- ❌ Implement a simple React UI for uploading files and showing results
- ❌ End-to-end flow: upload → parse → RAG → LLM → display
- ❌ Optional stretch goals:
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

The backend is currently **fully working end-to-end** with:

- OpenAPI upload + parsing  
- Automatic rule indexing on startup (RAG initialization)  
- Semantic rule search  
- LLM-based OpenAPI compliance analysis  

### ✔ Running the backend

```bash
cd backend
mvn spring-boot:run
```

You should see:

```
>>> Loading rule embeddings...
✓ Successfully indexed X rule chunks in memory
>>> Loaded X rule chunks into vector store.
```

Available at:

```
http://localhost:8080
```

### ✔ Health check

```
GET /api/health
→ "Backend is running"
```

### ✔ OpenAPI analysis endpoint

```
POST /api/analyze
```

Example:

```bash
curl -F "file=@samples/example.yml" http://localhost:8080/api/analyze
```

### ✔ Rule search endpoint

```bash
curl "http://localhost:8080/api/rules/search?query=naming"
```

## 📁 Sample OpenAPI Files

```
samples/
└── example.yml
```

## 🧪 Testing the Analysis

```
curl -s -F "file=@samples/example.yml" http://localhost:8080/api/analyze \
  | jq -r '.llmOutput'
```

## 🔍 RAG System

1. Rules in `/rules/` are embedded at startup  
2. OpenAPI spec uploaded  
3. Metadata extracted  
4. Relevant rules retrieved  
5. LLM generates compliance report  

Example rule search:

```bash
curl "http://localhost:8080/api/rules/search?query=versioning"
```

## 🤖 LLM Setup

Set your key:

```bash
export SPRING_AI_OPENAI_API_KEY="your-key"
```

## 🚀 Getting Started

```bash
git clone <repo>
cd ai-experiment
```

## 🔍 How Analysis Works

1. Rule docs → embeddings  
2. Upload spec  
3. Parse + extract  
4. Retrieve rule chunks  
5. LLM analysis  
6. Structured report  

## 📌 Notes

- Educational experiment only  
- No internal Swagger files  
- Performance simplified  

## 🔮 Future Ideas

- Auto-fix proposals  
- Swagger diffing  
- Rule plugins  
- Export reports  
