# FL-05 — Checklists and Task Suggestions

This file is **for your own use** while finishing the assignment — it doesn't need to go into the graded README, but you can keep it in the repo as working notes, or delete it once everything is done.

---

## Part A — Screenshot Evidence Checklist

You need **4 screenshots total**, saved into `screenshots/` with these exact filenames (matching what the README already references):

- [ ] **`01-mcp-setup.png`** — Shows your MCP server/connector successfully connected to your AI client (e.g., the connector listed as "Connected" in Claude Desktop's settings, or a successful connection message in a terminal/log).
- [ ] **`02-task-1-tool-call.png`** — Shows Task 1 in progress: the chat/interface visibly showing the AI **calling a tool** (not just replying from its own knowledge) and the tool's actual output.
- [ ] **`03-task-2-tool-call.png`** — Same as above, for Task 2.
- [ ] **`04-task-3-tool-call.png`** — Same as above, for Task 3.

**What makes a screenshot "good evidence":**
- The tool call itself should be visible (most AI clients show something like "Calling tool: search_files..." or an expandable tool-use block).
- The **result returned by the tool** should be visible, not just the AI's final worded answer.
- If your client hides tool calls by default, look for a setting like "show tool use" or check the developer/debug view before taking the screenshot.

**What does NOT count as evidence:**
- A screenshot of only the AI's final answer, with no visible tool call.
- A screenshot of you typing the connector's name in chat (that just shows intent, not actual use).

---

## Part B — 3 Beginner-Friendly MCP Task Suggestions

These are **suggestions only** — you still need to actually run them yourself and capture real screenshots. Do not mark any of these as done until you've actually performed them.

### Suggestion 1 — Read/Search a Local File
- **Task objective:** Confirm the AI can read real content from a file on your machine instead of guessing.
- **Example user prompt:** "Look inside my `notes.txt` file in this folder and tell me what the third bullet point says."
- **Expected tool action:** A file-read or file-search tool call against the local filesystem.
- **Expected result:** The AI quotes/paraphrases the actual third bullet point from your real file.
- **Screenshot to capture:** The tool call plus the returned file content, and the AI's answer matching it.

### Suggestion 2 — Work with GitHub
- **Task objective:** Confirm the AI can retrieve real, current information from a GitHub repository (yours or a public one) rather than from training data.
- **Example user prompt:** "Check my GitHub repo `[your-repo-name]` and tell me the title of the most recent open issue."
- **Expected tool action:** A GitHub connector tool call (e.g., "list issues" or "get repository").
- **Expected result:** The AI returns the actual, current issue title from the live repository.
- **Screenshot to capture:** The tool call and the real issue data returned, compared against what's actually shown on GitHub.

### Suggestion 3 — Query External Information Through a Connected Tool
- **Task objective:** Confirm the AI can pull in outside, current information it could not know from training alone (e.g., current weather, a live search result, or data from a connected API/service).
- **Example user prompt:** "Use the connected [weather/search/other] tool to tell me [something current, e.g., today's weather in your city]."
- **Expected tool action:** A call to the relevant connected tool/API.
- **Expected result:** A current, verifiable answer that matches the tool's real output.
- **Screenshot to capture:** The tool call and its returned data, alongside the AI's final answer.

---

## Part C — Final FlyRank Submission Checklist

- [ ] 600–900 word explainer completed (`Agent_and_MCP_Explainer.md`)
- [ ] Workflow vs agent explained
- [ ] FL-04 classified (with real implementation details filled in, not just placeholders)
- [ ] MCP explained
- [ ] Tools explained
- [ ] Resources explained
- [ ] Prompts explained
- [ ] MCP server/connector actually connected
- [ ] Three MCP tasks actually completed (not just planned)
- [ ] Screenshots captured and placed in `screenshots/`
- [ ] One concrete agent upgrade identified for FL-04
- [ ] GitHub repository organized per the folder structure
- [ ] Public GitHub link ready
- [ ] FlyRank submission completed

---

## What I Still Need to Do Manually

1. Fill in every `[ADD ... HERE]` placeholder in `Agent_and_MCP_Explainer.md` and `README.md` with real details about my actual FL-04 pipeline.
2. Choose and connect an actual MCP server/connector (e.g., a filesystem server, a GitHub connector) to an actual AI client (e.g., Claude Desktop).
3. Run three real tool-use tasks (see Part B suggestions above, or my own ideas) and confirm each one actually triggers a visible tool call.
4. Take the 4 required screenshots (Part A) and save them into `screenshots/` with the exact filenames used in the README.
5. Update the `MCP Setup`, `Three MCP Tasks`, and `Evidence` sections in both the explainer and the README with the real server/client names, real task descriptions, and confirmation that screenshots have been added.
6. Re-check the explainer's word count is still between 600–900 words after filling in the FL-04 details.
7. Push everything to a public GitHub repository using the folder structure below.
8. Copy the public GitHub link into my FlyRank submission form.

---

## Recommended Folder Structure

```text
Week5_FL05_Agent_Concepts_MCP/
│
├── README.md                        → Main graded overview (Part 2 structure)
├── Agent_and_MCP_Explainer.md       → The 600–900 word explainer (Part 1)
├── CHECKLIST.md                     → This file — working notes, optional to keep
│
├── screenshots/
│   ├── 01-mcp-setup.png             → MCP connector successfully connected
│   ├── 02-task-1-tool-call.png      → Task 1 tool call evidence
│   ├── 03-task-2-tool-call.png      → Task 2 tool call evidence
│   └── 04-task-3-tool-call.png      → Task 3 tool call evidence
│
└── references/
    └── Resources.md                 → Full reference list with notes
```

- **`README.md`** is what a grader will read first — it should summarize everything and link out to the explainer.
- **`Agent_and_MCP_Explainer.md`** is the full written deliverable required by the assignment brief.
- **`screenshots/`** holds only the 4 evidence images, named exactly as referenced in the README.
- **`references/Resources.md`** documents exactly which official sources were used, so the grader can verify claims.
