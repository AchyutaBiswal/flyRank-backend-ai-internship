# References — FL-05: Agent Concepts and MCP Basics

## Official Resources Used

1. **Anthropic — Building Effective Agents**
   https://www.anthropic.com/engineering/building-effective-agents
   Anthropic's own engineering write-up distinguishing **workflows** (predefined code paths orchestrating LLMs and tools) from **agents** (systems where the LLM dynamically directs its own process and tool usage). This was the main source for Sections 1 and 2 of the explainer.

2. **Model Context Protocol — Introduction**
   https://modelcontextprotocol.io/introduction
   The official MCP documentation site, describing MCP as an open standard for connecting AI applications to external tools and data sources, and introducing the core primitives (Tools, Resources, Prompts). Used as the main source for Section 4 of the explainer.

3. **Anthropic Academy — Introduction to Model Context Protocol**
   https://anthropic.skilljar.com/introduction-to-model-context-protocol
   Anthropic's official beginner course on MCP, covering the three core primitives (tools, resources, prompts) and walking through building a simple MCP server/client with the Python SDK.

## Notes

- All links above were verified as of the time this assignment was written. If any link has changed or moved by the time this is graded, search "site:anthropic.com building effective agents" or "site:modelcontextprotocol.io introduction" to find the current version.
- No other external tutorials or blog posts were used as primary sources for the technical claims in this submission — only the three official resources above.
