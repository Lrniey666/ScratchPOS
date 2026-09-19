# Contributing

Languages: [繁體中文](../CONTRIBUTING.md) · [English](CONTRIBUTING.en.md)

This repository is archived 2023 coursework. Documentation fixes, install notes and obvious defect repairs are welcome. Treat the desktop app as an artefact first.

## Before you start

1. Read [`../README.md`](../README.md) and [`README.md`](README.md).
2. Connection or table changes must land in [`database.md`](database.md) and `sql/lottery_pos.sql` together.
3. When they disagree: **classroom behaviour wins**. Fix the docs; do not modernise the whole JavaFX tree and call it the original.

## Conventions

| Item | Rule |
| --- | --- |
| Public copy | Traditional Chinese in `README.md`; English in `docs/README.en.md` — edit both |
| Dates | `YYYY-MM-DD`, Taipei |
| Changelog | `## [Unreleased]` in `CHANGELOG.md` (Keep a Changelog 2.0.0) |
| Line endings | LF (`.gitattributes`) |
| Stock | Fictional “demo tickets” only. Do not bring official names or official art back |

## Please do not

- Commit `original-data/`, official lottery images, student-id reports, Word files, or `build/`
- Commit live passwords, or push `config/db.properties`
- Hard-code absolute machine paths or student numbers
- Present this repository as an official lottery system, or use real tickets as demo art

Ask first before irreversible git history changes.

## After a change

1. Note it under `## [Unreleased]` in `CHANGELOG.md`
2. Keep the two README tours in step if you touch the hero, install steps or tree
3. If the card generator changes, re-run `python tools/make_demo_cards.py` and confirm `src/imgs/` is still original
