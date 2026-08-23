# FL-05: Agent Concepts and MCP Basics

**Intern:** Achyuta Biswal
**Internship:** FlyRank Backend AI Engineering Internship
**Track:** General AI Fluency
**Week:** 4
**Workload:** 5 hours

## Overview

This repository contains my submission for FL-05, which focuses on two things: understanding the difference between a fixed automation **workflow** and a dynamic **AI agent**, and getting hands-on experience with the **Model Context Protocol (MCP)** by connecting an AI client to a real MCP server/connector and running three tool-use tasks.

## Learning Objectives

- Understand what an AI agent is and how it differs from a normal chatbot.
- Understand the difference between a predefined workflow and a dynamic agent loop.
- Classify my own FL-04 pipeline as a workflow or an agent.
- Understand what MCP is and why it matters for AI applications.
- Understand the three core MCP primitives: Tools, Resources, and Prompts.
- Connect to a real MCP server/connector and complete three tool-use tasks with evidence.
- Identify one realistic way to make my FL-04 pipeline more agentic.

## Workflow vs Agent

A **workflow** runs a fixed, predefined sequence of steps:

```
Step A → Step B → Step C → Step D
```

An **agent** works in a loop, deciding its own next step based on what it observes:

```
Goal → Observe → Decide → Use Tool → Check Result → Decide Next Action
```

The core difference: a workflow always executes the same steps regardless of outcome; an agent adapts its next action based on the result of the previous one.

Full explanation: see [`Agent_and_MCP_Explainer.md`](./Agent_and_MCP_Explainer.md).

## FL-04 Pipeline Classification

My FL-04 assignment was **"Ship an Automation Workflow v2."**

`[ADD YOUR FL-04 IMPLEMENTATION DETAILS HERE]` — describe what the pipeline does, what triggers it, and whether it ever changes its next step based on an observed result.

Based on the assignment name and available information, FL-04 is most likely a **workflow**, not an agent, since it appears to run a fixed sequence of steps rather than dynamically deciding what to do next. This will be confirmed once the implementation detail above is filled in.

## What is MCP?

**Model Context Protocol (MCP)** is a standardized way for an AI application to connect to external tools and data sources — similar to how USB provides one common standard for connecting many different devices to a computer, instead of a separate custom cable for each one.

## MCP Primitives

### Tools
Actions the AI can actively perform (e.g., a function that searches files or calls an API).

### Resources
Data the AI can read (e.g., the contents of a file or a database record).

### Prompts
Reusable templates that structure how a task is asked, so the AI approaches similar tasks consistently.

## MCP Setup

- **AI Client:** `[Claude Desktop / Claude / Other]`
- **MCP Server/Connector:** `[ADD NAME]`
- **Setup description:** `[ADD A SHORT DESCRIPTION OF HOW YOU CONNECTED THE MCP SERVER/CONNECTOR — e.g., installed via config file, connected through Claude Desktop settings, etc.]`

## Three MCP Tasks

### Task 1
**Description:** `[ADD TASK 1 DESCRIPTION]`
**Screenshot:** `screenshots/02-task-1-tool-call.png`

### Task 2
**Description:** `[ADD TASK 2 DESCRIPTION]`
**Screenshot:** `screenshots/03-task-2-tool-call.png`

### Task 3
**Description:** `[ADD TASK 3 DESCRIPTION]`
**Screenshot:** `screenshots/04-task-3-tool-call.png`

## Agent Upgrade for FL-04

**Current workflow behavior:** `[ADD YOUR FL-04 DETAIL HERE]`

**Proposed agent behavior:** Instead of always moving to the next fixed step, the pipeline would first check the result of the previous step using a tool call, and decide its next action based on that outcome (e.g., retry once on failure instead of continuing blindly).

**MCP tool that could support this:** A simple status-check or file-read tool that lets the pipeline verify whether the previous step actually succeeded.

Full explanation: see [`Agent_and_MCP_Explainer.md`](./Agent_and_MCP_Explainer.md), Section 7.

## Key Learnings

- An agent is defined by its **decision loop** (observe → decide → act → check), not just by using AI at all.
- Most "automation" I've built so far (including FL-04) is workflow-style — fixed steps, not adaptive decisions.
- MCP's value is that it gives an AI system **real access to tools and data**, not just knowledge from training.
- Tools, Resources, and Prompts are the three basic building blocks MCP uses to connect an AI to the outside world.

## Evidence

The following screenshots are required and should be placed in the `screenshots/` folder:

- [ ] `01-mcp-setup.png` — MCP server/connector successfully connected
- [ ] `02-task-1-tool-call.png` — Task 1 showing an actual tool call and result
- [ ] `03-task-2-tool-call.png` — Task 2 showing an actual tool call and result
- [ ] `04-task-3-tool-call.png` — Task 3 showing an actual tool call and result

**Note:** These screenshots have not yet been captured/added. This README and the explainer should not be marked as fully complete until they are added and this section is updated.

## References

- [Anthropic — Building Effective Agents](https://www.anthropic.com/engineering/building-effective-agents)
- [Model Context Protocol — Introduction](https://modelcontextprotocol.io/introduction)
- [Anthropic Academy — Introduction to Model Context Protocol](https://anthropic.skilljar.com/introduction-to-model-context-protocol)

See also [`references/Resources.md`](./references/Resources.md) for the full reference list with notes.
