# 安裝

語言：[繁體中文](../README.md) · [English](README.en.md)

## 環境

| 項目 | 2023 繳交時 | 現在最低 |
| --- | --- | --- |
| JDK | 8（內建 JavaFX） | 8，或之後自帶 JavaFX 的組合 |
| IDE | NetBeans 8.2 | 能跑 JavaFX 的同等環境 |
| 資料庫 | MariaDB 11.0、埠 3306 | MariaDB / MySQL 相容即可 |
| 驅動 | `mariadb-java-client-2.2.3.jar` | 已放在 `lib/` |

較新的 JDK 沒有內建 JavaFX。若只用 JDK 11+，要另外加 OpenJFX，本展示倉不保證那條路。

## 資料庫

1. 啟動 MariaDB。
2. 用有建庫權限的帳號執行 [`sql/lottery_pos.sql`](../sql/lottery_pos.sql)。
3. 課程帳號常是 `mis` / `mis123`。沒有就自己建一個，並寫進 `config/db.properties`。

```sql
CREATE USER IF NOT EXISTS 'mis'@'localhost' IDENTIFIED BY 'mis123';
GRANT ALL ON lottery_pos.* TO 'mis'@'localhost';
FLUSH PRIVILEGES;
```

這是課堂預設，**不要**用在公開主機。

## 連線檔

複製範本：

```powershell
Copy-Item config\db.properties.example config\db.properties
```

`DBConnection` 讀取順序：

1. `LOTTERY_POS_DB_URL` / `LOTTERY_POS_DB_USER` / `LOTTERY_POS_DB_PASSWORD`
2. 工作目錄的 `db.properties` 或 `config/db.properties`（`db.url`、`db.user`、`db.password`）
3. `jdbc:mariadb://localhost:3306/lottery_pos` + `mis` / `mis123`

從 NetBeans 按 Run 時，工作目錄通常是專案根。若從 `dist/` 跑 jar，把 `db.properties` 放在啟動當下的目錄。

## 開啟專案

1. NetBeans → Open Project → 這個資料夾。
2. 確認 Libraries 看得到 `lib/mariadb-java-client-2.2.3.jar`。
3. 主類：`lab7_pos_integration_menubar.LotteryPosTabPaneMenu`。
4. Run。

驅動官方來源：[MariaDB Connector/J](https://mariadb.com/kb/en/about-mariadb-connector-j/)。本倉這份 2.2.3 與 2023 classpath 相同。

## 重畫票面

需要 Python 3 與 Pillow：

```powershell
python tools/make_demo_cards.py
```

只會覆寫 `src/imgs/` 的原創卡，不會動 `original-data/`。
