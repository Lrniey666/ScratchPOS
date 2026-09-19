"""Generate original ScratchPOS ticket faces. No official lottery artwork."""
from pathlib import Path

from PIL import Image, ImageDraw, ImageFont

OUT = Path(__file__).resolve().parent.parent / "src" / "imgs"
OUT.mkdir(parents=True, exist_ok=True)

CARDS = [
    ("2k-1.jpg", "金庫示範券", "NT 2000", (155, 27, 48), (120, 18, 36)),
    ("2k-2.jpg", "紅包示範券", "NT 2000", (168, 32, 40), (110, 16, 28)),
    ("1k-1.jpg", "行運示範券", "NT 1000", (122, 31, 61), (90, 20, 48)),
    ("1k-2.jpg", "吉利示範券", "NT 1000", (130, 40, 70), (96, 24, 52)),
    ("1k-3.jpg", "年終示範券", "NT 1000", (110, 36, 58), (82, 22, 44)),
    ("5-1.jpg", "玉門示範券", "NT 500", (196, 69, 54), (150, 48, 36)),
    ("5-2.jpg", "五福示範券", "NT 500", (180, 72, 48), (140, 50, 32)),
    ("5-3.jpg", "發袋示範券", "NT 500", (172, 58, 42), (132, 40, 28)),
    ("5-4.jpg", "金線示範券", "NT 500", (188, 86, 40), (148, 62, 24)),
    ("5-5.jpg", "財路示範券", "NT 500", (164, 64, 46), (124, 44, 30)),
    ("2-1.jpg", "起手示範券", "NT 200", (29, 106, 122), (18, 78, 92)),
    ("2-2.jpg", "三星示範券", "NT 200", (26, 98, 118), (16, 72, 88)),
    ("2-3.jpg", "新歲示範券", "NT 200", (32, 112, 124), (20, 84, 94)),
    ("2-4.jpg", "滿屋示範券", "NT 200", (24, 90, 108), (14, 66, 80)),
    ("2-5.jpg", "連線示範券", "NT 200", (36, 118, 128), (22, 88, 96)),
    ("2-6.jpg", "方城示範券", "NT 200", (28, 86, 104), (16, 62, 78)),
    ("2-7.jpg", "連發示範券", "NT 200", (34, 100, 116), (20, 74, 86)),
    ("1-1.jpg", "聚寶示範券", "NT 100", (27, 58, 75), (16, 40, 54)),
    ("1-2.jpg", "旺來示範券", "NT 100", (32, 64, 80), (20, 46, 58)),
    ("1-3.jpg", "新春示範券", "NT 100", (36, 52, 78), (22, 36, 56)),
    ("1-4.jpg", "金鼓示範券", "NT 100", (24, 50, 70), (14, 34, 50)),
    ("1-5.jpg", "來訪示範券", "NT 100", (30, 60, 82), (18, 42, 60)),
    ("1-6.jpg", "小兔示範券", "NT 100", (40, 56, 84), (26, 38, 62)),
    ("1-7.jpg", "備用示範券", "NT 100", (28, 48, 68), (16, 32, 48)),
]


def font(size: int) -> ImageFont.FreeTypeFont:
    for path in (
        r"C:\Windows\Fonts\msjh.ttc",
        r"C:\Windows\Fonts\msjhbd.ttc",
        r"C:\Windows\Fonts\mingliu.ttc",
        r"C:\Windows\Fonts\segoeui.ttf",
    ):
        try:
            return ImageFont.truetype(path, size)
        except OSError:
            continue
    return ImageFont.load_default()


def paint_card(name: str, title: str, price: str, bg, accent) -> None:
    img = Image.new("RGB", (480, 360), bg)
    draw = ImageDraw.Draw(img)
    draw.ellipse((-80, -70, 140, 150), fill=accent)
    draw.ellipse((360, 230, 540, 410), fill=(232, 184, 56))
    draw.rectangle((18, 18, 461, 341), outline=(232, 184, 56), width=4)
    draw.rectangle((28, 28, 150, 66), fill=(232, 184, 56))
    draw.text((48, 34), "DEMO", font=font(20), fill=bg)
    draw.text((28, 128), title, font=font(34), fill=(255, 248, 236))
    draw.text((28, 208), price, font=font(40), fill=(232, 184, 56))
    draw.text((28, 298), "ScratchPOS  course demo", font=font(16), fill=(255, 248, 236))
    img.save(OUT / name, quality=92)


def paint_icon() -> None:
    img = Image.new("RGB", (256, 256), (26, 20, 35))
    draw = ImageDraw.Draw(img)
    draw.rounded_rectangle((36, 48, 220, 168), radius=16, fill=(155, 27, 48))
    draw.rounded_rectangle((56, 184, 200, 220), radius=8, fill=(232, 184, 56))
    draw.ellipse((108, 28, 148, 68), fill=(232, 184, 56))
    draw.text((78, 84), "POS", font=font(36), fill=(255, 248, 236))
    img.save(OUT / "TL.png")


if __name__ == "__main__":
    for row in CARDS:
        paint_card(*row)
    paint_icon()
    print(f"Wrote {len(CARDS) + 1} files to {OUT}")
