# Agent Concepts and MCP Basics

**By:** Achyuta Biswal
**Internship:** FlyRank Backend AI Engineering Internship
**Assignment:** FL-05 – Agent Concepts and MCP Basics (Week 4, General AI Fluency Track)

## 1. What is an AI Agent?

The first thing I had to get clear in my head was what actually makes something an "agent" instead of just a chatbot. A normal chatbot mostly answers whatever you type. It reads your message, generates a reply, and stops. It doesn't check whether its answer actually worked, and it doesn't go do something in the real world on its own.

An **agent** is different because it works in a loop: it has a **goal**, it **plans** a rough approach to reach that goal, it **uses tools** to take real actions (not just talk), it **observes the result**, and then **decides the next step** based on what it saw. It repeats this until the goal is reached, then stops.

A simple example: if you ask a chatbot "is this website down?", it might just guess based on general knowledge. An agent would actually **use a tool** to ping the website, **observe** the real response, and only then answer — retrying or checking a different way if the first attempt failed.

## 2. Workflow vs Agent

This distinction matters for backend engineering, so I'll keep it simple instead of using heavy AI jargon.

**Workflow:**
`Step A → Step B → Step C → Step D`
The steps are fixed in advance by whoever built it. The system just executes them in order, every time, no matter what happens along the way.

**Agent:**
`Goal → Observe → Decide → Use Tool → Check Result → Decide Next Action`
Here, the system isn't following a fixed script. It looks at the current situation, decides what to do next, and can change its approach if something unexpected happens.

The short version: a workflow **always does the same steps**; an agent **decides its own steps** based on what it observes.

## 3. Classifying My FL-04 Pipeline

My FL-04 assignment was "Ship an Automation Workflow v2."

`[ADD YOUR FL-04 IMPLEMENTATION DETAILS HERE — describe what your pipeline actually does step by step, e.g. what triggers it, what tools/APIs it calls, and whether it makes any decisions on its own or just runs the same steps every time]`

Based on the assignment name alone ("workflow," not "agent"), FL-04 is most likely a **workflow**, not an agent — it probably runs a fixed sequence of steps rather than dynamically deciding what to do based on results. I am not claiming FL-04 is an agent, since it was built and described as a workflow. Once I fill in the detail above, I can confirm this by checking one thing: does it ever change its next action based on a result it observed? If not, it's a workflow.

## 4. What is MCP?

**MCP (Model Context Protocol)** is a standard way for an AI application to connect to external tools and data sources. The easiest way to describe it: **MCP is like a standardized connection between an AI application and external tools/data** — similar to how USB lets many devices plug into a laptop using one common standard, instead of needing a different cable for each device.

MCP is built around three main ideas:

- **Tools** — actions the AI can perform, like calling a function (example: a "search_files" tool that looks through a folder).
- **Resources** — data the AI can read, like a file or database record (example: a resource exposing a config file's contents).
- **Prompts** — reusable templates that structure how a task is asked (example: a saved template for "summarize this document").

## 5. MCP vs Normal Chat

Normal chat can only respond using what the model already knows from training. It can't read your local files, check today's data, or call an API by itself. MCP changes this by giving the AI actual access to tools and data — for example, reading a file on disk, querying an external service, calling an API, working with a GitHub repository, or querying a database. I'm being careful not to claim I've personally done any of these yet — I'll document exactly what I tested in the MCP Experiment section below.

## 6. What Would Make FL-04 an Agent?

To make FL-04 more agentic, it would need: a clear **goal** instead of a fixed task list, the ability to **dynamically decide** the next step, the ability to **choose between tools** depending on the situation, a way to **check whether a step actually worked**, the ability to **decide the next action** based on that check (retry, skip, or continue), and a clear condition for **when to stop**.

## 7. One Concrete Agent Upgrade

**Current workflow behavior:** `[ADD YOUR FL-04 DETAIL HERE — e.g., "the workflow always sends a notification after processing, even if processing failed"]`

**Proposed agent behavior:** Instead of always moving to the next fixed step, the system would first **check the result** of the previous step using a tool call, and only decide the next action based on that outcome — for example, retrying a failed step once before giving up.

**MCP tool that could be used:** A simple "status check" or "file read" tool that lets the agent verify whether the previous step's output actually succeeded before continuing.

**How the agent would decide:** If the check reports success, move on; if it reports failure, retry once, and if it fails again, stop and report the failure instead of continuing blindly.

**Why this is useful:** It prevents the workflow from silently continuing after a failure — a realistic reliability improvement for backend automation.

## 8. MCP Experiment

- **MCP Server/Connector:** `[ADD NAME]`
- **AI Client:** `[Claude Desktop / Claude / Other]`
- **Task 1:** `[ADD TASK]`
- **Task 2:** `[ADD TASK]`
- **Task 3:** `[ADD TASK]`

I have not yet completed these tasks — I will fill in the actual server, client, and three tasks once I run them, along with the corresponding screenshots.
