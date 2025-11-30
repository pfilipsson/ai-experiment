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
- ChromaDB (local mode)
- Retrieval-Augmented Generation (RAG)
- Parsing YAML/JSON for OpenAPI v3

### Frontend
- Node + React  
- UI for uploading Swagger files and viewing results  

## 🔧 Backend Status

A working **Spring Boot backend skeleton** is implemented.

### ✔ Running the backend

```bash
cd backend
mvn spring-boot:run
```

The backend will start on:

```
http://localhost:8080
```

### ✔ Health check endpoint

```
GET /api/health
→ "Backend is running"
```

### ✔ Current backend structure

```
backend/
├── pom.xml
├── src/main/java/com/ai/experiment/AiExperimentApplication.java
└── src/main/java/com/ai/experiment/controllers/
      ├── HealthController.java
      └── UploadController.java
```

## 📁 Sample OpenAPI Files

This project includes sample Swagger/OpenAPI specifications located in:

```
samples/
└── example.yml
```

> **Note:**  
> The backend supports `.yaml`, `.yml`, and `.json` formats.  
> The included example uses the `.yml` extension.

## 🧪 Testing the Upload Endpoint

Start the backend:

```bash
cd backend
mvn spring-boot:run
```

Then, from the **project root**, run:

```bash
curl -F "file=@samples/example.yml" http://localhost:8080/api/upload
```

Expected response:

```
File uploaded successfully.
```

Using an absolute path also works:

```bash
curl -F "file=@/full/path/to/ai-experiment/samples/example.yml"      http://localhost:8080/api/upload
```

---

## 🤖 LLM Setup: Cloud vs Local (Ollama)

This project supports **two LLM modes**.

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
- Great for structured API analysis  

**Cons**
- Requires API key  
- Internet needed  
- Cost per token  

---

### 2. Local LLMs via Ollama (Optional)

Runs small LLMs locally and offline.

Supports `.yaml`, `.yml`, `.json` parsing with:

- `llama3.2:3b`
- `mistral:7b-instruct-q4`
- `llama3.1:8b-q4`


## 🚀 Getting Started

### 1. Prerequisites

| Tool | Version | Check |
|------|---------|--------|
| Java | 21 or 25 | `java -version` |
| Maven | 3.x | `mvn -v` |
| Node | 18+ | `node -v` |
| ChromaDB | 0.5.x | `chroma --version` |
| Git | Latest | `git --version` |

---

### 2. Clone the Repository

```bash
git clone https://github.com/<your-org>/ai-experiment.git
cd ai-experiment
```


## 🔍 How the Analysis Works

1. Rule documents in `/rules/` are embedded into ChromaDB.
2. User uploads a Swagger/OpenAPI spec through the frontend.
3. Backend:
   - Parses YAML or JSON
   - Extracts operations, schemas, paths
   - Retrieves relevant rules via vector search
   - Builds an LLM prompt using RAG context
   - Receives a structured analysis
4. Frontend displays:
   - Guideline violations  
   - Improvement suggestions  
   - A readable analysis report  


## 📌 Notes & Limitations

- This is an **educational experiment**, not a production tool.
- Only **public** API documentation or generic guidelines are allowed.
- No proprietary/internal Swagger files should be uploaded.
- Performance and scalability are intentionally out of scope.


## 🔮 Future Ideas

- Auto-fix proposals  
- Swagger version diffing  
- Rule plugin system  
- Export analysis reports  
- Pre-validation with strict OpenAPI validators  
