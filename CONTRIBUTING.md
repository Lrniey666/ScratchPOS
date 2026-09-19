# 貢獻指南

語言：[繁體中文](CONTRIBUTING.md) · [English](docs/CONTRIBUTING.en.md)

這是 2023 課程作業的封存展示倉。歡迎修文件、補安裝註記、修明顯缺陷。請先當歷史文物看，再動手。

## 動工前

1. 讀根目錄 [`README.md`](README.md) 與 [`docs/README.md`](docs/README.md)。
2. 改連線或表格時，同步 [`docs/database.md`](docs/database.md) 與 `sql/lottery_pos.sql`。
3. 衝突時：**課堂能跑的行為 > 展示倉文件**。文件寫錯就改文件，不要為了「比較現代」重寫整份 JavaFX。

## 慣例

| 項目 | 約定 |
| --- | --- |
| 對外說明 | 繁中在 `README.md`；英文在 `docs/README.en.md`，兩邊一起改 |
| 日期 | `YYYY-MM-DD`，台北時間 |
| 變更紀錄 | `CHANGELOG.md` 的 `## [Unreleased]`（Keep a Changelog 2.0.0） |
| 換行 | LF（`.gitattributes`） |
| 商品 | 只用虛構的「○○示範券」。不要把官方票名或官方圖加回來 |

## 請不要

- 提交 `original-data/`、官方彩券圖、學號報告、Word、`build/`
- 提交真實帳密，或把 `config/db.properties` 推進公開分支
- 在程式裡硬寫本機絕對路徑或學號
- 把本倉說成官方彩券系統，或拿真實票面當展示圖

不可逆的動作（force push、刪遠端、把原料庫打進歷史）請先問。

## 改完必做

1. Notable 變更寫進 `CHANGELOG.md` → `## [Unreleased]`
2. 動到 Hero／安裝／結構 → 繁中與英文 README 一起改
3. 若改了票面產生器，重跑 `python tools/make_demo_cards.py`，並核對 `src/imgs/` 仍是原創圖
