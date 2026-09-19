# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [Unreleased]

### Added

- Order analysis tab: order count, revenue, tickets sold, order list, product ranking, refresh.
- `sql/lottery_pos.sql` with fictional demo stock and three sample orders.
- `config/db.properties.example` plus environment-variable overrides.
- Original ticket faces from `tools/make_demo_cards.py`.
- Bilingual README (zh-Hant / en), docs index, database note, MIT licence.

### Changed

- Database name is `lottery_pos`. Student-id names are gone from the public tree.
- Window titles no longer claim to be an official lottery till.
- MariaDB driver lives in `lib/`.

### Removed

- Official lottery artwork, course Word reports, NetBeans `private/` paths, `build/`, and the 2023 SQL dump stay in local `original-data/` and are gitignored.

## [1.0.0] - 2023-06

### Added

- Course hand-in: JavaFX order entry, product maintenance, menu-bar shell, MariaDB persistence.
- Analysis page was a placeholder ("not yet open").
